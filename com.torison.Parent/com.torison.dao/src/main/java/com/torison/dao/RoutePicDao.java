package com.torison.dao;

import com.torison.mapper.RoutePicMapper;
import com.torison.model.RoutePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutePicDao {

    @Autowired
    private RoutePicMapper routePicMapper;

    public int insert(RoutePic routePic){
        return  routePicMapper.insert(routePic);
    }
}
