package com.torison.route;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.torison.Route.dao.RoutePicDao;
import com.torison.common.model.Result;
import com.torison.Route.model.RoutePic;
import com.torison.common.model.respCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutePicService {

    @Autowired
    private RoutePicDao routePicDao;

    public Result inserPic(List<String> picPaths, Integer routeId){
        Result result = new Result();
        RoutePic routePic = new RoutePic();
        routePic.setRouteid(routeId);
        routePic.setRoutepic1(picPaths.get(0));
        routePic.setRoutepic2(picPaths.get(1));
        routePic.setRoutepic3(picPaths.get(2));
        if(routePicDao.insert(routePic)!=0){
            result.setRespCode(respCode.SUCCESS);
            result.setSuccess(true);
        }
        return result;
    }

    public RoutePic selectPicByID(Integer ID){
        return routePicDao.selectByPrimaryKey(ID);
    }
}
