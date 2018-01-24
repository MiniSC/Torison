package com.torison.dao;

import com.torison.mapper.RouteMapper;
import com.torison.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

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

    /**
     * 查询所有路线
     * @return listRoute
     */
    public List<Route> queryAllRoute(){
        return routeMapper.queryAllRoute();
    }

    public int updateRoute(Route route){
        return routeMapper.updateByIDSelective(route);
    }

    public int deleteRoute(int id){
        return routeMapper.deleteByID(id);
    }



}
