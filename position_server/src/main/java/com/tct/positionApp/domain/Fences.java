package com.tct.positionApp.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Fences {

    private int id;

    private String fence_name;

    private Students student;

    private double longitude;

    private double latitude;

    private double radius;

    private Date createTime = new Date();

    private Status status;
}
