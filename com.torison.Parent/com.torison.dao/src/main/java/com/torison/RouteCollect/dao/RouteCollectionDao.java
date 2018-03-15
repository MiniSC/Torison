package com.torison.RouteCollect.dao;

import com.torison.RouteCollect.mapper.RouteCollectionMapper;
import com.torison.RouteCollect.model.RouteCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteCollectionDao {

    @Autowired
    private RouteCollectionMapper routeCollectionMapper;

    /**
     * 查看路线收藏
     * @param userID
     * @return
     */
    public List<RouteCollection> listRouteCollection(Integer userID){
        return routeCollectionMapper.selectByPrimaryKey(userID);
    }
    /**
     * 查看路线收藏
     * @param userID
     * @return
     */
    public RouteCollection listRouteCollectionByUserIdAndRouteId(Integer userID,Integer routeID){
        return routeCollectionMapper.selectByPrimaryKeyAndRouteId(userID,routeID);
    }

    /**
     * 删除路线收藏信息
     * @param userid
     * @param routeid
     * @return
     */
    public int deleteRouteCollection(Integer userid ,Integer routeid){
        return routeCollectionMapper.deleteByPrimaryKey(userid,routeid);
    }

    /**
     * 添加路线收藏信息
     * @param routeCollection
     * @return
     */
    public int addRouteCollection(RouteCollection routeCollection){
        return routeCollectionMapper.insert(routeCollection);
    }
}
