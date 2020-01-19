package com.tct.positionApp.domain;

import lombok.Data;

import java.util.List;

@Data
public class Parents {

    private int id;

    private String userName;

    private String cellPhone;

    private String password;

    private String reg_id;

    private String form_id;

    private String open_id;

    private Status status;

    private List<Students> students;

}
