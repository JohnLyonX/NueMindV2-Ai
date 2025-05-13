package com.nue.teamnus.agent.Impl;

import com.nue.teamnus.agent.EduButlerChatStudentAgent;
import com.nue.teamnus.agent.EduButlerChatTeacherAgent;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EduButlerChatStudentAgentImpl implements EduButlerChatStudentAgent {
    @Autowired
    private StreamingChatLanguageModel qwenMaxStreamingChatModel;

    @Override
    public Flux<String> chat4Student(String userId, String message) {
        EduButlerChatStudentAgent eduButlerChatAgent = AiServices.create(EduButlerChatStudentAgent.class, qwenMaxStreamingChatModel);
        return eduButlerChatAgent.chat4Student(userId, message);
    }
}
