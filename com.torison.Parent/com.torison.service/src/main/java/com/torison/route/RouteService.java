package com.torison.route;

import com.torison.dao.RouteDao;
import com.torison.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteDao routeDao;

    /**
     * 插入路线
     * @param route
     */
    public int inserRoute(Route route){
       return routeDao.insert(route);
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

}
