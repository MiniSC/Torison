package com.order.controller;

import com.model.Result;
import com.order.model.OrderForm;
import com.order.model.OrderStatus;
import com.torison.Order.OrderService;
import com.torison.Order.model.Order;
import com.torison.Route.model.Route;
import com.torison.Route.model.RoutePic;
import com.torison.api.PayServiceApi;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import model.PayEntity;
import model.PayEnum;
import model.ResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RoutePicService routePicService;

    @Autowired
    private PayServiceApi payServiceApi;

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
        System.out.println(session.getAttribute("userid"));
        order.setUserid(Integer.parseInt(session.getAttribute("userid").toString()));
        order.setRouteid(routeid);
        order.setNum(num);
        order.setStatus(OrderStatus.UNPAY);
        orderService.inserOrder(order);
        result.setSuccess(true);
        result.setMsg("添加成功");
        return result;
    }

    /**
     * 跳转到我的订单界面
     * @return
     */
    @RequestMapping(value = "/toMyOrder")
    public String toMyOrder(HttpServletRequest request, Model model){

        int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        double allmoney = 0;
        List<Order> orders = orderService.listOrderByUseridAndStatus(userid,OrderStatus.UNPAY);
        for (Order order:orders){
            Route route = routeService.selectRouteById(order.getRouteid());
            allmoney=allmoney+route.getRouteneedmoney()*order.getNum();
        }
        model.addAttribute("allmoney",""+allmoney);
        return "test/Order/MyOrder";
    }

    /**
     * 获取订单信息
     * @param request
     * @param status
     * @return
     */
    @RequestMapping(value = "/getMyOrder")
    @ResponseBody
    public Result getMyOrder(HttpServletRequest request , String status){
        HttpSession session = request.getSession();
        Integer userId = Integer.parseInt(session.getAttribute("userid").toString());
        List<Order> listOrder = orderService.listOrderByUseridAndStatus(userId,status);
        Result result = new Result();
        result.setObj(toOrderForms(listOrder));
        result.setSuccess(true);
        return result;
    }

    /**
     * 删除订单
     * @param routeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteOrder")
    @ResponseBody
    public Result deleteOrder(String routeId,HttpServletRequest request){

        Integer userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());

        Order order1 = orderService.listOrderByUseridAndRouteid(userId,Integer.parseInt(routeId));
        int flag =orderService.deleteOrderByOrderID(
                order1.getOrderid()
        );
        if (flag==1){
            return new Result(true,"成功");
        }
        return new Result(false,"失败");
    }

    /**
     * 所有订单支付
     * @param request
     * @param allmoney
     * @return
     */
    @RequestMapping(value = "/payOrder")
    @ResponseBody
    public Result payMyOrder(HttpServletRequest request,String allmoney){
        Result result = new Result();
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        String useracc = request.getSession().getAttribute("account").toString();
        List<Order> orders = orderService.listOrderByUserid(userid);
        double allmoeny = Double.parseDouble(allmoney);
        PayEntity payEntity = new PayEntity();
        payEntity.setAccount(useracc);
        payEntity.setMoney(allmoeny);
        payEntity.setStatus(PayEnum.PayStatus.DECREASE);
        ResEntity resEntity = payServiceApi.modifyMoney(payEntity);
        if (!resEntity.isSuccess()) {
            result.setMsg(resEntity.getResMsg());
            result.setSuccess(false);
            return result;
        }

        result.setSuccess(true);
        return result;
    }
    /**
     * 一条订单支付
     * @param request
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/payOneOrder")
    @ResponseBody
    public Result payOneOrder(HttpServletRequest request,String routeId){
        Integer routeID = Integer.parseInt(routeId);
        Route route = routeService.selectRouteById(routeID);
        Result result = new Result();
        Integer userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        String useracc = request.getSession().getAttribute("account").toString();
        Order order = orderService.listOrderByUseridAndRouteid(userid,routeID);
        /* 支付功能，支付订单*/
        PayEntity payEntity = new PayEntity();
        payEntity.setAccount(useracc);
        payEntity.setMoney( route.getRouteneedmoney());
        payEntity.setStatus(PayEnum.PayStatus.DECREASE);
        ResEntity resEntity = payServiceApi.modifyMoney(payEntity);
        if (!resEntity.isSuccess()) {
            result.setMsg(resEntity.getResMsg());
            result.setSuccess(false);
            return result;
        }
        /*
        支付成功，修改订单状态为进行中--PAYED已支付状态
         */
        order.setStatus(OrderStatus.PAYED);
        if (orderService.updateOrder(order)!=1){
            result.setMsg("系统异常");
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 跳转到已完成订单
     * @return
     */
    @RequestMapping(value = "/toFinishedOrder")
    public String toFinishedOrder(){
        return "test/Order/FinishedOrder";
    }

    /**
     * 跳转到进行中订单
     * @return
     */
    @RequestMapping(value = "/toGoingOrder")
    public String toGoingOrder(){
        return "test/Order/GoingOrder";
    }

    /**
     * 通过订单转化为订单表单
     * web输出格式化
     * @param listOrder
     * @return
     */
    public List<OrderForm> toOrderForms(List<Order> listOrder){
        List<OrderForm> orderForms = new LinkedList<>();
        for (Order order:listOrder){
            Route route = routeService.selectRouteById(order.getRouteid());
            RoutePic routePic = routePicService.selectPicByID(order.getRouteid());
            OrderForm orderForm = new OrderForm(route.getRouteid(),
                    routePic.getRoutepic1(),
                    route.getRoutefromaddress(),
                    route.getRouteendaddress(),
                    order.getNum(),
                    route.getRoutename());
            orderForms.add(orderForm);
        }
        return orderForms;
    }

}
