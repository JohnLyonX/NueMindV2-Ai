package com.nue.teamnus.config;

import com.nue.teamnus.agent.EduButlerChatStudentAgent;
import com.nue.teamnus.tool.ButlerAgentStudentTools;
import com.nue.teamnus.tool.ButlerAgentTeacherTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ButlerAgentChatStudentConfig {

    @Bean("studentChatAgent")  // Unique bean name
    public EduButlerChatStudentAgent eduButlerChatStudentAgent(
            ButlerAgentStudentTools butlerAgentStudentTools, StreamingChatLanguageModel qwenMaxStreamingChatModel ) {
        return AiServices.builder(EduButlerChatStudentAgent.class)
                .streamingChatLanguageModel(qwenMaxStreamingChatModel)
                .tools(butlerAgentStudentTools)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(30))
                .build();
    }
}
