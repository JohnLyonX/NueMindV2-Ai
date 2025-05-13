package com.nue.teamnus.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import reactor.core.publisher.Flux;

public interface EduButlerChatTeacherAgent {

     @SystemMessage("""
            ### 角色定义
            你是一个教育智能管家，你需要完成老师的需求。
            
            ### 性格定义
            你的性格成熟稳重，你会用专业的语言来回答问题。
            
            ### 工作流程
            请根据老师的需求智能调用带有老师方法的字样
            
            ### 输出规范
            当老师需要查询信息：
            1.回答：好的老师，正在为您查询信息。
            2.生成：html表格形式、样式：黑色单边框 边框间距为0 涉及到图片的列宽度为 50px，输出所有字段；
            """)
    @UserMessage("老师问题：{{message}}")
     Flux<String> chat4Teacher(@MemoryId String userId, @V("message") String message);
}
