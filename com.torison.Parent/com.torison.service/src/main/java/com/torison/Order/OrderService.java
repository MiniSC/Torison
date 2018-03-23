package com.torison.Order;

import com.torison.Order.dao.OrderDao;

import com.torison.Order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 添加订单
     * @param order
     * @return
     */
    public int inserOrder(Order order){
        return orderDao.inserOrder(order);
    }

  /**
     * 根据用户编号和订单状态查询订单信息
     * @param Userid
     * @param Status
     * @return
     */
    public List<Order> listOrderByUseridAndStatus(Integer Userid , String Status){
        return orderDao.listOrderByUseridAndStatus(Userid,Status);
    }

    /**
     * 通过订单编号删除订单信息
     * @param Orderid
     * @return
     */
    public int deleteOrderByOrderID(Integer Orderid){
        return orderDao.deleteOrderByOrderID(Orderid);
    }

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    public int updateOrder(Order order){
        return orderDao.updateOrder(order);
    }


    /**
     * 通过用户ID查看用户的订单
     * @param userid
     * @return
     */
    public List<Order> listOrderByUserid(Integer userid){
        return orderDao.listOrderByUserid(userid);
    }

    /**
     * 根据路线id和userid查询订单信息
     * 在订单新增前检查
     * @param userid
     * @param routeid
     * @return
     */
    public Order listOrderByUseridAndRouteid(Integer userid , Integer routeid) {
        List<Order> listOrder = orderDao.listOrderByUseridAndRouteID(userid,routeid);
        if (listOrder.isEmpty()){
            return null;
        }
        if (listOrder.size()==1){
            return listOrder.get(0);
        }
        return null;
    }

    /**
     * 根据路线id和userid和状态查询订单信息
     * 在订单新增前检查
     * @param userid
     * @param routeid
     * @return
     */
    public Order listOrderByall(Order order) {
       return orderDao.listOrderByall(order);

    }





}
