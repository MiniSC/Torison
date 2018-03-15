package com.torison.routeMaker.dao;

import com.torison.routeMaker.mapper.RouteMakerMapper;
import com.torison.routeMaker.model.RouteMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteMakerDao {

    @Autowired
    private RouteMakerMapper routeMakerMapper;

    /**
     * 插入制作者
     * @param routeMaker
     */
    public void insertMaker(RouteMaker routeMaker){
        routeMakerMapper.insert(routeMaker);
    }

    /**
     * 根据ID查询制作者
     * @param makerId
     * @return
     */
    public RouteMaker queryMaker(Integer makerId){
        return routeMakerMapper.selectByPrimaryKey(makerId);
    }

}
