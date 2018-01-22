package com.torison.dao;

import com.torison.mapper.RouteMapper;
import com.torison.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteDao {

    @Autowired
    RouteMapper routeMapper;

    /**
     * 添加旅游路线
     * @param route
     * @return
     */
    public int insert(Route route){
       return routeMapper.insert(route);
    }



}
