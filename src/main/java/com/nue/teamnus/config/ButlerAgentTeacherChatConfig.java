package com.nue.teamnus.config;

import com.nue.teamnus.agent.EduButlerChatTeacherAgent;
import com.nue.teamnus.tool.ButlerAgentTeacherTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ButlerAgentTeacherChatConfig {

    @Bean("teacherChatAgent")  // Unique bean name
    public EduButlerChatTeacherAgent eduButlerChatAgent(
            ButlerAgentTeacherTools butlerAgentTeacherTools, StreamingChatLanguageModel qwenMaxStreamingChatModel ) {
        return AiServices.builder(EduButlerChatTeacherAgent.class)
                .streamingChatLanguageModel(qwenMaxStreamingChatModel)
                .tools(butlerAgentTeacherTools)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(30))
                .build();
    }
}
