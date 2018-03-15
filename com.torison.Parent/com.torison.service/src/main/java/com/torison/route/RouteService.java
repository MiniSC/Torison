package com.torison.route;

import com.torison.Route.dao.RouteDao;
import com.torison.Route.model.Route;
import com.torison.route.model.RouteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteDao routeDao;

    /**
     * 插入路线
     * @param form
     *
     */
    public int inserRoute(RouteForm form){
        Route route = new Route();
        form.transTo(route);
        routeDao.insert(route);
        return route.getRouteid();
    }

    /**
     * 修改路线
     * @param route
     */
    public int updateRoute(Route route){
        return routeDao.updateRoute(route);
    }

    /**
     * 删除路线
     * @param id
     */
    public void deleteRoute(Integer id){
        routeDao.deleteRoute(id);
    }

    /**
     * 查询所有路线
     * @return
     */
    public List<Route> queryAllRoute(){
       return routeDao.queryAllRoute();
    }

    /**
     * 查询最新十条路线
     * @return
     */
    public List<Route> queryTopTenRoute(){
        return routeDao.queryTopTenRoute();
    }

    /**
     * 根据ID查询路线的详细信息
     * @param id
     * @return
     */
    public Route selectRouteById(Integer id){
        return routeDao.selectRouteById(id);
    }

    /**
     * 根据ID查询路线的详细信息
     * @param makerid
     * @return
     */
    public List<Route> selectRouteByMakerId(Integer makerid){
        return routeDao.selectRouteByMakerId(makerid);
    }

}
