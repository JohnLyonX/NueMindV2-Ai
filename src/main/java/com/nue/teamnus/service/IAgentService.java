package com.nue.teamnus.service;

import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAgentService {
    String intelligentCorrection4Student(MultipartFile file);
    String intelligentCorrection4Teacher(Long id,String filePath);
}
