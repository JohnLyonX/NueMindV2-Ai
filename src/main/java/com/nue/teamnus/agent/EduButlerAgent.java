package com.nue.teamnus.agent;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;

public interface EduButlerAgent {


    @SystemMessage("""
            ### 工作流程
            1. 分析试卷的所有小题 param: x_length
            4. 调用工具 - 创建题目映射矩阵 传入params: 小题数量 x_length
            """)
    String createMapping(String content);

    @SystemMessage("""
            ### 工作流程
            1. 插入每道小题对应的分值，分值格式为 换行符 + [分值：] + 换行符
            2. 在小题内查找学生是否有作答，有则插入 [作答：+ 查找到的作答]，无则插入 [作答：无]，不能私自插入答案
            # 输出规范
            只能输出处理后的试卷内容，其他任何言语都不能输出
            """)
    String questionDocMapping(String content);

    //  ### 工作流程
    //            1. 分析学生作答是否正确，正确则插入 换行符 + [回答正确] + 换行符、不正确（包含作答为空）则插入 [正确答案：+ 这道题正确的答案]
    //            2. 所有回答错误，都需要在后面插入 [解析：这道题的解析]
    //            3. 找到所有 [回答正确] 的小题对应的[分值：]里的数值，进行累加，得到总分数。
    //            4. 将总分数插入到第一行，格式为 总分数：+ 换行符
    @SystemMessage("""
            # 工作流程
            - 在小题内提取学生的作答，有则插入 [作答：+ 查找到的作答]，无则插入 [作答：无]，不能私自插入答案
            - 判断学生[作答]里的内容是否正确（含空答）。正确则插入：\\n[正确：+ 分值]\\n，错误则插入：[错误，正确答案：题目答案]
            - 所有错误答案后必须追加：[解析：题目解析]
            # 工作规范
            - 选择题：保留原题选项
            - 需要有截图的题：仅验证[作答]内容
            """)
    String handleAnswer(String content);

}
