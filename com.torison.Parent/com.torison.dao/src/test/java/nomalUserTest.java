import com.torison.Application_Dao;
import com.torison.Friend.dao.FriendDao;
import com.torison.Route.dao.RouteDao;
import com.torison.Route.model.Route;
import com.torison.nomalUser.dao.nomaluserDao;
import com.torison.nomalUser.model.nomalUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application_Dao.class)
public class nomalUserTest {
    @Autowired
    nomaluserDao nd;

    @Autowired
    RouteDao rd;

    @Autowired
    FriendDao fd;

    @Test
    public  void testNomalInsert(){
        nomalUser nomaluser = new nomalUser();
        nomaluser.setUseraccountnum("123");
        nomaluser.setUserid(1);
        nomaluser.setUserpassword("123");
        nd.savenomalUser(nomaluser);
    }

    @Test
    public void insert(){
        Route route = new Route();
        route.setRouteid(2);
        rd.insert(route);
    }
    @Test
    public void routeselect (){
        System.out.println(rd.queryAllRoute());
    }
    @Test
    public void updateroute(){
        Route route = new Route();
        route.setRouteid(1);
        route.setRouteendaddress("hangzhou");
        System.out.println(rd.updateRoute(route));
    }

    @Test
    public void testdeleteroute(){
        int id = 1;
        rd.deleteRoute(id);

    }








}
