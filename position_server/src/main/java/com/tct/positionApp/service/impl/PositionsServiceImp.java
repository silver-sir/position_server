package com.tct.positionApp.service.impl;

import com.tct.positionApp.dao.PositionsDao;
import com.tct.positionApp.domain.Positions;
import com.tct.positionApp.service.PositionsService;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service("PositionsService")
@CacheConfig(cacheNames = "position")
public class PositionsServiceImp implements PositionsService {
    @Resource
    PositionsDao positionsDao;

    @Override
    public List<Positions> findAllPositions(int student_id) {
        return positionsDao.findAllPositions(student_id);
    }

    @Override
    @CachePut(key = "#position.getStudent().getId()")
    public int insertPosition(Positions position) {
        return positionsDao.insertPosition(position);
    }

    @Override
    public List<Positions> findPositionsByDay(int student_id, String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        Date date = dateTimeFormatter.parseDateTime(time).toDate();
        System.out.println(date);
        return positionsDao.findPositionsByDay(student_id, time);
    }

    @Override
    @Cacheable(key = "#student_id")
    public Positions findPosition(int student_id) {
        return positionsDao.findPosition(student_id);
    }
}
