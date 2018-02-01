import com.Run;
import com.upFile.Server.FileUploadServerImp;
import com.upFile.model.entity.File;
import com.upFile.model.request.FileQueryForm;

import com.upFile.model.response.DataGrid;
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
    public void test(){
        System.out.println(1);

    }
}
