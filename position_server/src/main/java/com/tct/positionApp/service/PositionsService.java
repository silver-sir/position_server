package com.tct.positionApp.service;

import com.tct.positionApp.domain.Positions;

import java.util.Date;
import java.util.List;

public interface PositionsService {
    List<Positions> findAllPositions(int student_id);

    int insertPosition(Positions position);

    List<Positions> findPositionsByDay(int student_id, String time);

    Positions findPosition(int student_id);
}
