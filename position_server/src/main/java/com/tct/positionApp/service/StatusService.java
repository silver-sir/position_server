package com.tct.positionApp.service;

import com.tct.positionApp.domain.Status;

public interface StatusService {

    public Status getStatus(int id);

    public void deleteStatus(int id);
}
