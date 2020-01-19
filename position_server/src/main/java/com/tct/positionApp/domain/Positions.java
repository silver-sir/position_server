package com.tct.positionApp.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Positions {

    private int id;

    private double latitude;

    private double longitude;

    private Date uploadTime;

    private Students student;

    private Date createTime = new Date();

}
