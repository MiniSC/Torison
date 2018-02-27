import com.torison.Application_Dao;
import com.torison.Friend.FriendSerivce;
import com.torison.Friend.model.Friend;
import com.torison.Order.OrderService;
import com.torison.Order.dao.OrderDao;
import com.torison.Order.model.Order;
import com.torison.Route.model.Route;
import com.torison.Route.model.RoutePic;
import com.torison.nomalUser.model.nomalUser;
import com.torison.nomalUser.nomalUserService;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application_Dao.class)
public class nomalUserTest {
    @Autowired
    nomalUserService userService;

    @Autowired
    RouteService routeService;

    @Autowired
    RoutePicService routePicService;

    @Autowired
    private OrderService orderService;

    @Autowired
    FriendSerivce friendSerivce;

    @Test
    public  void testNomalInsert(){
        nomalUser nomaluser = new nomalUser();
        nomaluser.setUseraccountnum("123");
        nomaluser.setUserpassword("123");
        userService.save(nomaluser);
    }

    @Test
    public void testLogin(){
        nomalUser nomaluser = new nomalUser();
        nomaluser.setUseraccountnum("123");
        nomaluser.setUserpassword("123");
        System.out.println(userService.login(nomaluser).getRespCode());
    }

    @Test
    public void testupdate(){
        nomalUser nomaluser = new nomalUser();
        nomaluser.setUseraccountnum("123");
        nomaluser.setUserpassword("234");
        System.out.println(userService.updateUser(nomaluser));
    }


    @Test
    public void testqueryAll(){
       List<Route> list =  routeService.queryAllRoute();
        System.out.println(list.size());
    }

    @Test
    public void testPicServiceselectByID(){
        RoutePic routePic = routePicService.selectPicByID(2);
        System.out.println(routePic.getRouteid());
    }

    @Test
    public void testselectRouteById(){
        System.out.println(routeService.selectRouteById(3).getRoutename());
    }



    @Test
    public void testInsertOrder(){
        Order order = new Order();
        order.setUserid(1);
        orderService.inserOrder(order);
    }

    @Test
    public void testFriend(){
        Friend friend = new Friend();
        friend.setUserid(1);
        friend.setFriendid(2);
        friendSerivce.addFriend(friend);
    }

}
