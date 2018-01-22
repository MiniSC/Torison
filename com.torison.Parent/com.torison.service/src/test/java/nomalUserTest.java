import com.torison.Application_Dao;
import com.torison.model.nomalUser;
import com.torison.nomalUser.nomalUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application_Dao.class)
public class nomalUserTest {
    @Autowired
    nomalUserService userService;

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




}
