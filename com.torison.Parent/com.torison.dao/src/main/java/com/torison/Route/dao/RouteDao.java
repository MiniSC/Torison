package com.torison.Route.dao;

import com.torison.Route.mapper.RouteMapper;
import com.torison.Route.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 更新路线信息
     * @param route
     * @return
     */
    public int updateRoute(Route route){
        return routeMapper.updateByIDSelective(route);
    }

    /**
     * 删除路线信息
     * @param id
     * @return
     */
    public int deleteRoute(int id){
        return routeMapper.deleteByID(id);
    }

    /**
     * 根据路线号查询路线信息
     * @param id
     * @return
     */
    public Route selectRouteById(int id){
        return routeMapper.queryRouteByID(id).get(0);
    }

    /**
     * 根据发布者编号查询路线信息
     * @param makerid
     * @return
     */
    public List<Route> selectRouteByMakerId(int makerid){
        return routeMapper.queryRouteByMakerID(makerid);
    }


}
