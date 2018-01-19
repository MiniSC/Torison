import com.torison.Application_Dao;
import com.torison.dao.nomaluserDao;
import com.torison.model.nomalUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application_Dao.class)
public class nomalUserTest {
    @Autowired
    nomaluserDao nd;

    @Test
    public  void testNomalInsert(){
        nomalUser nomaluser = new nomalUser();
        nomaluser.setUseraccountnum("123");
        nomaluser.setUserid(1);
        nomaluser.setUserpassword("123");
        nd.savenomalUser(nomaluser);
    }




}
