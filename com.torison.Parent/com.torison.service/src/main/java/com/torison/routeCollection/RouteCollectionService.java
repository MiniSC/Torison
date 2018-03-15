package com.torison.routeCollection;

import com.torison.RouteCollect.dao.RouteCollectionDao;
import com.torison.RouteCollect.model.RouteCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteCollectionService {

    @Autowired
    private RouteCollectionDao routeCollectionDao;


    /**
     * 根据用户Id查询用户的收藏的路线
     * @param userId
     * @return
     */
    public List<RouteCollection> listRouteCollection(Integer userId){
        return routeCollectionDao.listRouteCollection(userId);
    }

    /**
     * 根据用户Id查询用户的收藏的路线
     * @param userId
     * @return
     */
    public RouteCollection listRouteCollectionByIDs(Integer userId,Integer routeId){
        return routeCollectionDao.listRouteCollectionByUserIdAndRouteId(userId, routeId);
    }



    /**
     * 删除路线收藏信息
     * @param userid
     * @param routeid
     * @return
     */
    public int deleteRouteCollection(Integer userid,Integer routeid){
        return routeCollectionDao.deleteRouteCollection(userid,routeid);
    }

    /**
     * 添加路线收藏信息
     * @param routeCollection
     * @return
     */
    public int addRouteCollection(RouteCollection routeCollection){
        return routeCollectionDao.addRouteCollection(routeCollection);
    }
}
