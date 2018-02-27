package com.torison.routeMaker.dao;

import com.torison.routeMaker.mapper.RouteMakerMapper;
import com.torison.routeMaker.model.RouteMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteMakerDao {

    @Autowired
    private RouteMakerMapper routeMakerMapper;

    public void insertMaker(RouteMaker routeMaker){
        routeMakerMapper.insert(routeMaker);
    }

}
