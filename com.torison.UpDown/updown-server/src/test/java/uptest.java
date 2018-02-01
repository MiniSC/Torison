import com.Run;
import com.upFile.Server.FileUploadServerImp;
import com.upFile.utils.Exceptions.IllegalInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class)
public class uptest {

    @Autowired
    FileUploadServerImp fileUploadServer;

    @Test
    public void testException(){
        if (true){
            throw new IllegalInputException();
        }
        System.out.println(1);

    }
}
