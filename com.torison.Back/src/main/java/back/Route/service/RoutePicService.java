package back.Route.service;

import back.Route.dao.jpa.routePicJPA;
import back.Route.dao.model.Route;
import back.Route.dao.model.RoutePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutePicService {

    @Autowired
    private routePicJPA routepicjpa;


    public List<RoutePic> listRoutePic(RoutePic routePic){
        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("routeid", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<RoutePic> ex_user = Example.of(routePic,matcher_user);
        return  routepicjpa.findAll(ex_user);
    }
}
