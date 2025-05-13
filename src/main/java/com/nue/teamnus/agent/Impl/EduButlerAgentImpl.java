package com.nue.teamnus.agent.Impl;

import com.nue.teamnus.agent.EduButlerAgent;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Component
public class EduButlerAgentImpl implements EduButlerAgent {
    @Autowired
    private ChatLanguageModel qwenMaxChatModel;

    @Autowired
    private StreamingChatLanguageModel qwenMaxStreamingChatModel;

    @Override
    public String createMapping(String content) {
        EduButlerAgent eduButlerAgent = AiServices.create(EduButlerAgent.class, qwenMaxChatModel);
        return eduButlerAgent.createMapping(content);
    }

    @Override
    public String questionDocMapping(String content) {
        EduButlerAgent eduButlerAgent = AiServices.create(EduButlerAgent.class, qwenMaxChatModel);
        return eduButlerAgent.questionDocMapping(content);
    }

    @Override
    public String handleAnswer(String content) {
        EduButlerAgent eduButlerAgent = AiServices.create(EduButlerAgent.class, qwenMaxChatModel);
        return eduButlerAgent.handleAnswer(content);
    }




}

