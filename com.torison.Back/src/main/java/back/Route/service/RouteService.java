package back.Route.service;

import back.Route.dao.jpa.routeJPA;
import back.Route.dao.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

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
        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("routeId", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<Route> ex_user = Example.of(route,matcher_user);
       return  routejpa.findAll(ex_user);
    }

}
