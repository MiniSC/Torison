package com.upFile.Server;

import com.Run;
import com.upFile.model.request.FileQueryForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class)
public class FileUploadServerImpTest {

    @Autowired
    private FileUploadServerImp fileUploadServerImp;

    @Test
    public void queryFile() {
        FileQueryForm fileQueryForm = new FileQueryForm();
        fileUploadServerImp.queryFile(fileQueryForm,"");

    }
}