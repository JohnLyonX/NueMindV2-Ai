package com.nue.teamnus.service.Impl;

import com.nue.teamnus.agent.EduButlerAgent;
import com.nue.teamnus.pojo.EduExamFilelib;
import com.nue.teamnus.service.IAgentService;
import com.nue.teamnus.service.IEduExamFilelibService;
import com.nue.teamnus.utils.HandleFile;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

@Service
public class AgentServiceImpl implements IAgentService {
    @Autowired
    private EduButlerAgent eduButlerAgent;
    @Autowired
    private HandleFile handleFile;
    @Autowired
    private IEduExamFilelibService eduExamFilelibService;
    @Value("${upload.base_path}")
    private String upload_path;

    @Override
    public String intelligentCorrection4Student(MultipartFile file) {
        try (InputStream fis = file.getInputStream()) {
            String content = handleFile.parseWordFile(file.getInputStream());
            String answerContent = eduButlerAgent.handleAnswer(content);
            int i = handleFile.calculateTotalScore(answerContent);

            String header = String.format("试卷总分：%d\n\n", i);
            answerContent = header + answerContent;

            handleFile.writeToWordFile(answerContent, "/Users/liangzhanbo/Desktop/teamnus/MappingDoc/aw2.docx");
            return answerContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String intelligentCorrection4Teacher(Long id,String fileName) {
        String newFileName = handleFile.removeProfileUploadPrefix(fileName);
        File file = new File(upload_path + newFileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            String content = handleFile.parseWordFile(fis);
            String answerContent = eduButlerAgent.handleAnswer(content);
            int i = handleFile.calculateTotalScore(answerContent);

            String header = String.format("试卷总分：%d\n\n", i);

            answerContent = header + answerContent;

            String name = file.getName();
            String overName = "批改完成" + name;
            String removeFileNameFromPath = handleFile.removeFileNameFromPath(newFileName);
            String wp = upload_path + removeFileNameFromPath + overName; // 本地新的文件写入路径

            handleFile.writeToWordFile(answerContent, wp);

            String newUrlFileName = handleFile.removeFileNameFromPath(fileName) + overName;

            EduExamFilelib eduExamFilelib = new EduExamFilelib();
            eduExamFilelib.setId(id);
            eduExamFilelib.setCorrectPath(newUrlFileName);
            eduExamFilelib.setScore(i);
            eduExamFilelibService.update(eduExamFilelib);
            return upload_path + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
