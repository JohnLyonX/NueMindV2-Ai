package com.nue.teamnus.config;

import com.nue.teamnus.agent.EduButlerAgent;
import com.nue.teamnus.tool.ButlerAgentTeacherTools;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ButlerAgentConfig {

    @Bean
    public EduButlerAgent eduButlerAgent(ButlerAgentTeacherTools butlerAgentTools, ChatLanguageModel qwenMaxChatModel) {
        return AiServices.builder(EduButlerAgent.class)
                .chatLanguageModel(qwenMaxChatModel)
                .tools(butlerAgentTools)
                .build();
    }

    @Bean
    public EduButlerAgent eduButlerVLAgent(ButlerAgentTeacherTools butlerAgentTools, ChatLanguageModel qwenVlChatModel) {
        return AiServices.builder(EduButlerAgent.class)
                .chatLanguageModel(qwenVlChatModel)
                .tools(butlerAgentTools)
                .build();
    }


}
