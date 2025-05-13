package com.nue.teamnus;

import com.nue.teamnus.agent.EduButlerAgent;
import com.nue.teamnus.agent.EduSaleAgent;
import com.nue.teamnus.service.IEduCoursesService;
import com.nue.teamnus.service.ITeachersService;
import com.nue.teamnus.utils.ByteArrayMultipartFile;
import com.nue.teamnus.utils.HandleFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@SpringBootTest
@MapperScan("com.nue.teamnus.mapper")
@Slf4j
class TeamnusCoreApplicationTests {
    @Autowired
    private EduButlerAgent eduButlerAgent;

    @Autowired
    private IEduCoursesService iCoursesService;

    @Autowired
    private ITeachersService iTeachersService;



    @Test
    void contextLoads() throws FileNotFoundException {
    }


    @Test
    void agentTest() throws Exception {

        System.out.println();
    }



}
