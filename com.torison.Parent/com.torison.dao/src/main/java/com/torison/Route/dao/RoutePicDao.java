package com.torison.Route.dao;

import com.torison.Route.mapper.RoutePicMapper;
import com.torison.Route.model.RoutePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutePicDao {

    @Autowired
    private RoutePicMapper routePicMapper;

    public int insert(RoutePic routePic){
        return  routePicMapper.insert(routePic);
    }

    public RoutePic selectByPrimaryKey (Integer ID){
        return routePicMapper.selectByPrimaryKey(ID);
    }
}
