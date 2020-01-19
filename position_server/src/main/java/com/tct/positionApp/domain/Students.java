package com.tct.positionApp.domain;

import lombok.Data;


@Data
public class Students {

    private int id;

    private String userName;

    private String password;

    private Parents parent;

    private String reg_id;
}
