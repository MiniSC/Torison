package com.torison.Route.dao;

import com.torison.Route.mapper.RouteMapper;
import com.torison.Route.model.BestEndAddress;
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
     * 查询最新的十条路线
     * @return listRoute
     */
    public List<Route> queryTopTenRoute(){
        return routeMapper.queryTopTenRoute();
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

    /**
     * 查询最热门的十条路线的路线名和数量
     * @return
     */
    public List<BestEndAddress> selectBestEndAddress(){
        return routeMapper.listbestend();
    }

    /**
     * 通过目的地查询路线列表
     * @param endaddress
     * @return
     */
    public List<Route> listRouteByendAddress(String endaddress){
        return routeMapper.queryRouteByEnd(endaddress);
    }

}
