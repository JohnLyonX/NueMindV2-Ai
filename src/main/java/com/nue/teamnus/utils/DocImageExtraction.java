package com.nue.teamnus.utils;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Component
public class DocImageExtraction {
    @Autowired
    private ChatLanguageModel qwenVlChatModel;

    public String handleDocImg(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String encodeToString = Base64.getEncoder().encodeToString(bytes);
        UserMessage userMessage = UserMessage.from(TextContent.from("只需要输出学生运行程序的结果,其余什么内容都不能输出"), ImageContent.from(encodeToString, contentType));
        ChatResponse chatResponse = qwenVlChatModel.chat(userMessage);
        return chatResponse.aiMessage().text();
    }
}
