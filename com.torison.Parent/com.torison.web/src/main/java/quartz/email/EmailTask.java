package quartz.email;

import com.model.User;
import com.torison.Order.OrderService;
import com.torison.Order.model.Order;
import com.torison.Route.model.Route;
import com.torison.User.UserService;
import com.torison.route.RouteService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Configurable
@EnableScheduling
public class EmailTask{

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    RouteService routeService;

    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0/5 * * * * ? ")
    public void reportCurrentByCron(){
        List<Route> routes = new LinkedList<>();
        List<User> users = new LinkedList<>();
        List<Order> orders = new LinkedList<>();
        executeQuery(routes,users,orders);
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

    private void executeQuery(List<Route> routes, List<User> users, List<Order> orders){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,100,200, TimeUnit.MICROSECONDS, new LinkedBlockingQueue(400),new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.execute(
                () -> {
           routes=routeService.queryAllRoute();
                });
        threadPoolExecutor.execute(
                () -> {

                });
        threadPoolExecutor.execute(
                () -> {

                });


    }

}