package com.torison.routemaker;

import com.torison.routeMaker.dao.RouteMakerDao;
import com.torison.routeMaker.model.RouteMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteMakerService {

    @Autowired
    private RouteMakerDao routeMakerDao;


    public void insertMaker(RouteMaker routeMaker){
        routeMakerDao.insertMaker(routeMaker);
    }



}
