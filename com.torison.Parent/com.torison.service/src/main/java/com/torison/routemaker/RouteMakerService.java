package com.torison.routemaker;

import com.torison.routeMaker.dao.RouteMakerDao;
import com.torison.routeMaker.model.RouteMaker;
import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteMakerService {

    @Autowired
    private RouteMakerDao routeMakerDao;


    /**
     * 插入制作者
     * @param routeMaker
     */
    public void insertMaker(RouteMaker routeMaker){
        routeMakerDao.insertMaker(routeMaker);
    }

    public RouteMaker queryMaker(Integer makerID){
      return  routeMakerDao.queryMaker(makerID);
    }





}
