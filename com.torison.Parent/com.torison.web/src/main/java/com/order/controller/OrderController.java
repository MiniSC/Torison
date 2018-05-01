package com.order.controller;

import com.model.Result;
import com.model.User;
import com.order.model.OrderForm;
import com.order.model.OrderStatus;
import com.torison.Evaluation.Service.EvaluationService;
import com.torison.Evaluation.model.Evaluation;
import com.torison.Order.OrderService;
import com.torison.Order.model.Order;
import com.torison.Route.model.Route;
import com.torison.Route.model.RoutePic;
import com.torison.User.UserService;
import com.torison.api.PayServiceApi;
import com.torison.api.model.PayEntity;
import com.torison.api.model.PayEnum;
import com.torison.api.model.ResEntity;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluationService evaluationService;

    /**
     * 添加订单到购物车
     * @param order
     * @param
     * @param num
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public Result addOrder(Order order,Integer num , HttpServletRequest request){
        Result result = new Result();
        HttpSession session = request.getSession();
        int userid = Integer.parseInt(session.getAttribute("userid").toString());
        order.setStatus(OrderStatus.UNPAY);
        order.setUserid(userid);
        Order order1 = orderService.listOrderByall(order);
        if (order1!=null){
            order1.setNum(order1.getNum()+order.getNum());
            orderService.updateOrder(order1);
            result.setSuccess(true);
            result.setMsg("添加成功");
        }
        order.setUserid(userid);
        order.setNum(num);
        order.setStatus(OrderStatus.UNPAY);
        orderService.inserOrder(order);
        Route route = routeService.selectRouteById(order.getRouteid());
        route.setRoutelastpersonnum(route.getRoutelastpersonnum()-order.getNum());
        routeService.updateRoute(route);
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
        Order order = new Order();
        order.setRouteid(Integer.parseInt(routeId));
        order.setUserid(userId);
        order.setStatus(OrderStatus.PAYED);
        Order order1 = orderService.listOrderByall(order);
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
            for (Order order:orders) {
                order.setStatus(OrderStatus.PAYED);
            }
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

    /**
     * initialize evaluation view
     * @param routeId
     * @param
     * @return
     */
    @RequestMapping(value = "/toEvaluation")
    public String toEvaluation(String routeId,Model model){
        System.out.println(routeId);
        Integer routeid = Integer.parseInt(routeId);
        Route route = routeService.selectRouteById(routeid);
        RoutePic routePic = routePicService.selectPicByID(routeid);
        model.addAttribute("route",route);
        model.addAttribute("routePic",routePic);
        return "test/Order/Evaluation";
    }

    /**
     * Evaluation the order
     * write to evaluation table for the maker's grade
     * @param routeId
     * @param optionsRadiosinline
     * @param evaluationMsg
     * @return
     */
    @RequestMapping(value = "/Evaluation")
    @ResponseBody
    public Result Evaluation(HttpServletRequest request,String routeId,String optionsRadiosinline,String evaluationMsg){
        Result result = new Result();
        System.out.println(routeId+optionsRadiosinline+evaluationMsg);
        Evaluation evaluation = new Evaluation();
        evaluation.setFromid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
        evaluation.setGrade(Integer.parseInt(optionsRadiosinline));
        evaluation.setMsg(evaluationMsg);
        evaluation.setRouteid(Integer.parseInt(routeId));
        evaluationService.insertEva(evaluation);
        result.setSuccess(true);
        return  result;
    }


    /**
     * finished order
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/finished")
    public String finished(HttpServletRequest request,String routeId){
        Result result = new Result();
        int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        Order order  = new Order();
        order.setStatus(OrderStatus.FINISH);
        order.setUserid(userid);
        order.setRouteid(Integer.parseInt(routeId));
        order.setUserid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
        orderService.updateOrder(order);
        result.setSuccess(true);
        return  "test/Order/FinishOrder";
    }

}
