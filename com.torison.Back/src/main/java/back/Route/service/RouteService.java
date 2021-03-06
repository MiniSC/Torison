package back.Route.service;

import back.Route.dao.jpa.routeJPA;
import back.Route.dao.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class RouteService {

    @Autowired
    private routeJPA routejpa;

    /**
     * 根据条件查询路线信息
     * @param route
     * @return
     */
    public List<Route> listRoute(Route route){
        if ("".equals(route.getRouteId())){
            route.setRouteId(null);
        }
        if ("".equals(route.getRouteName())){
            route.setRouteName(null);
        }
        if ("".equals(route.getRouteFromId())){
            route.setRouteFromId(null);
        }
        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("routeId", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<Route> ex_user = Example.of(route,matcher_user);
       return  routejpa.findAll(ex_user);
    }

    /**
     * 根据条件查询路线信息
     * @param route
     * @return
     */
    public List<Route> listRoutePass(Route route){
        if ("".equals(route.getRouteId())){
            route.setRouteId(null);
        }
        if ("".equals(route.getRouteName())){
            route.setRouteName(null);
        }
        if ("".equals(route.getRouteFromId())){
            route.setRouteFromId(null);
        }
        route.setStatus(0);
        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("routeId", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<Route> ex_user = Example.of(route,matcher_user);
        return  routejpa.findAll(ex_user);
    }

    /**
     * deleteRoute
     * @param route
     */
    public void deleteRoute(Route route){
         routejpa.delete(route);
    }

    /**
     * 路线审核状态更新
     * @param status
     * @param routeId
     */
    @Transactional
    public boolean updateRoute(Integer status, Integer routeId){
        try {
            routejpa.update(status, routeId);
        }catch (RuntimeException e){
            return false;
        }
        return true;
    }

}
