package com.nue.teamnus.pojo;

import lombok.Data;

@Data
public class EduStudent {
     /** 学生编号 */
    private Long id;

    /** 学生名字 */
    private String name;

    /** 用户头像 */
    private String url;


    /** 登陆账号 */
    private String phoneNumber;

    /** 登陆密码 */
    private String password;
}
