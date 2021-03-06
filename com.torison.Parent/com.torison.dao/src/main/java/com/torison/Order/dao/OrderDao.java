package com.torison.Order.dao;

import com.torison.Order.mapper.OrderMapper;
import com.torison.Order.model.Order;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 添加订单
     * @param order
     * @return
     */
    public int inserOrder(Order order){
        return orderMapper.insert(order);
    }

 /**
     * 根据用户编号和订单状态查询订单信息
     * @param Userid
     * @param Status
     * @return
     */
    public List<Order> listOrderByUseridAndStatus(Integer Userid , String Status){
        return orderMapper.selectByUserIDAndStatus(Userid,Status);
    }

    /**
     * 通过订单编号删除订单信息
     * @param Orderid
     * @return
     */
    public int deleteOrderByOrderID(Integer Orderid){
        return orderMapper.deleteByPrimaryKey(Orderid);
    }

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    public int updateOrder(Order order){
        return orderMapper.updateByPrimaryKey(order);
    }


    /**
     * 通过用户ID查看用户的订单
     * @param userid
     * @return
     */
    public List<Order> listOrderByUserid(Integer userid){
        return orderMapper.selectByUserID(userid);
    }

    /**
     * 根据用户编号和路线号查询订单信息
     * @param userid
     * @param routeid
     * @return
     */
    public List<Order> listOrderByUseridAndRouteID(Integer userid,Integer routeid){
        return orderMapper.selectByUserIDAndRouteID(userid,routeid);
    }

    /**
     * 根据用户编号和路线号和状态查询订单信息
     * @return
     */
    public Order listOrderByall(Order order){
        return orderMapper.selectByall(order);
    }

    public List<Order> listOrderByRouteId(Integer routeId){
        return orderMapper.selectOrderByRouteId(routeId);
    }

    public List<Order> listOrderByRouteIdAndStatus(Integer routeId,String status){
        return orderMapper.selectByRouteIdAndStatus(routeId,status);
    }






}
