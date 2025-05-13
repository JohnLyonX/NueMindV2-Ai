package com.nue.teamnus.config;

import com.nue.teamnus.agent.EduButlerAgent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static dev.langchain4j.model.chat.request.ResponseFormat.JSON;

@Configuration
public class LLMConfig {

    @Value("${llm.qwenMax.base_url}")
    private String qwenMaxAiUrl;
    @Value("${llm.qwenMax.model}")
    private String qwenMaxModel;

    @Value("${llm.qwenVl.base_url}")
    private String qwenVlAiUrl;
    @Value("${llm.qwenVl.model}")
    private String qwenVlModel;


    @Value("${llm.qwenApiKey}")
    private String api_key;

    /**
     * qwen max ai流式输出
     *
     * @return
     */
    @Bean
    public StreamingChatLanguageModel qwenMaxStreamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .baseUrl(qwenMaxAiUrl)
                .modelName(qwenMaxModel)
                .apiKey(api_key)
                .timeout(Duration.ofSeconds(720))
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    /**
     * qwen max ai普通输出
     *
     * @return
     */
    @Bean
    public ChatLanguageModel qwenMaxChatModel() {
        return OpenAiChatModel.builder()
                .baseUrl(qwenMaxAiUrl)
                .modelName(qwenMaxModel)
                .apiKey(api_key)
                .timeout(Duration.ofSeconds(720))
                .logRequests(true)
                .logResponses(true)
                .build();
    }


    /**
     * qwen vl ai普通输出
     *
     * @return
     */
    @Bean
    public ChatLanguageModel qwenVlChatModel() {
        return OpenAiChatModel.builder()
                .baseUrl(qwenVlAiUrl)
                .modelName(qwenVlModel)
                .apiKey(api_key)
                .logRequests(true)
                .logResponses(true)
                .timeout(Duration.ofSeconds(720))
                .build();
    }

}
