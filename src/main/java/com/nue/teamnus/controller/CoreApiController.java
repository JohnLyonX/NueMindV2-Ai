package com.nue.teamnus.controller;

import com.nue.teamnus.agent.EduButlerAgent;
import com.nue.teamnus.agent.EduButlerChatStudentAgent;
import com.nue.teamnus.agent.EduButlerChatTeacherAgent;
import com.nue.teamnus.agent.EduSaleAgent;
import com.nue.teamnus.service.IAgentService;
import com.nue.teamnus.service.IEduExamFilelibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api")
public class CoreApiController {
    @Autowired
    private EduSaleAgent eduSaleAgent;

    @Autowired
    private EduButlerAgent eduButlerAgent;

    @Autowired
    @Qualifier("teacherChatAgent")
    private EduButlerChatTeacherAgent eduButlerChatTeacherAgent;

    @Autowired
    @Qualifier("studentChatAgent")
    private EduButlerChatStudentAgent eduButlerChatStudentAgent;



    @Autowired
    private IAgentService agentService;
    @Autowired
    private IEduExamFilelibService eduExamFilelibService;


    @GetMapping("/saleAgent")
    public Flux<String> saleAgent(String userId, String message) {
        return eduSaleAgent.chat(userId, message);
    }

    @GetMapping("/aiAgent")
    public Flux<String> aiAgent(String userId, String message) {
        return eduSaleAgent.aiChat(userId, message);
    }

    @GetMapping("/butlerAgent4Teacher")
    public Flux<String> butlerAgent4Teacher(String userId, String message) {
        return eduButlerChatTeacherAgent.chat4Teacher(userId, message);
    }

    @GetMapping("/butlerAgent4Student")
    public Flux<String> butlerAgent4Student(String userId, String message) {
        return eduButlerChatStudentAgent.chat4Student(userId, message);
    }


    @PostMapping("/correct4Student")
    public String correct4Student(@RequestParam("file") MultipartFile file) {
        return agentService.intelligentCorrection4Student(file);
    }

    @GetMapping("/correct")
    public void correct4Teacher(Long id, String fileName) {
        String overContent = agentService.intelligentCorrection4Teacher(id, fileName);
    }
}
