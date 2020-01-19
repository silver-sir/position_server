package com.tct.positionApp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Status implements Serializable {
    int id;

    String statusName;

}
