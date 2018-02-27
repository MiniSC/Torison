package com.order.controller;

import com.model.Result;
import com.order.model.OrderForm;
import com.order.model.OrderStatus;
import com.torison.Order.OrderService;
import com.torison.Order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 添加订单到购物车
     * @param order
     * @param routeid
     * @param num
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public Result addOrder(Order order,Integer routeid,Integer num , HttpServletRequest request){
        Result result = new Result();
        HttpSession session = request.getSession();
        order.setUserid(Integer.parseInt(session.getAttribute("userid").toString()));
        order.setRouteid(routeid);
        order.setNum(num);
        order.setStatus(OrderStatus.UNPAY);
        orderService.inserOrder(order);
        result.setSuccess(true);
        result.setMsg("添加成功");
        return result;
    }

    @RequestMapping(value = "/toMyOrder")
    public String toMyOrder(){
        return "test/Order/MyOrder";
    }

    @RequestMapping(value = "/getMyOrder")
    public Result getMyOrder(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Order> listOrder = orderService.listOrderByUserid(Integer.parseInt(session.getAttribute("userid").toString()));

        List<OrderForm> listOrderForm = new ArrayList<>();
        

        Result result = new Result();
        result.setObj(listOrder);
        return result;
    }
}
