package com.nue.teamnus.config;

import com.nue.teamnus.agent.EduSaleAgent;
import com.nue.teamnus.tool.SaleAgentTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleAgentConfig {

    /**
     * Sale智能体配置
     *
     * @return
     */
    @Bean
    public EduSaleAgent eduSaleAgent(SaleAgentTools saleAgentTools, StreamingChatLanguageModel qwenMaxStreamingChatModel) {
        return AiServices.builder(EduSaleAgent.class)
                .streamingChatLanguageModel(qwenMaxStreamingChatModel)
                .tools(saleAgentTools).chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }
}
