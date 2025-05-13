package com.nue.teamnus.agent.Impl;

import com.nue.teamnus.agent.EduButlerChatTeacherAgent;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EduButlerChatTeacherAgentImpl implements EduButlerChatTeacherAgent {
    @Autowired
    private StreamingChatLanguageModel qwenMaxStreamingChatModel;

    @Override
    public Flux<String> chat4Teacher(String userId, String message) {
        EduButlerChatTeacherAgent eduButlerChatAgent = AiServices.create(EduButlerChatTeacherAgent.class, qwenMaxStreamingChatModel);
        return eduButlerChatAgent.chat4Teacher(userId, message);
     }
}
