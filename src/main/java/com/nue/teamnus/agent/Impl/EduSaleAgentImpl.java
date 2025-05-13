package com.nue.teamnus.agent.Impl;

import com.nue.teamnus.agent.EduSaleAgent;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EduSaleAgentImpl implements EduSaleAgent {
    @Autowired
    private StreamingChatLanguageModel openAiStreamingChatModel;


    @Override
    public Flux<String> chat(String userId, String message) {

        EduSaleAgent eduSaleAgent = AiServices.create(EduSaleAgent.class, openAiStreamingChatModel);
        return eduSaleAgent.chat(userId, message);

    }

    @Override
    public Flux<String> aiChat(String userId, String message) {
        EduSaleAgent eduSaleAgent = AiServices.create(EduSaleAgent.class, openAiStreamingChatModel);
        return eduSaleAgent.aiChat(userId, message);
    }
}
