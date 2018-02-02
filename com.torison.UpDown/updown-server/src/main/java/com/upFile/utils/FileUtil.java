package com.upFile.utils;

import com.upFile.model.UniqueFile;
import com.upFile.model.entity.File;

public class FileUtil {

    public static File getFileByUniqueFileAndOriginalFileName(UniqueFile uniqueFile,String OriginalFileName,Long size,String username){
        File file = File.create()
                .setDeleted(false)
                .setEncryptName(uniqueFile.getEncryptName())
                .setUsername(username)
                .setSize(size)
                .build();
        int pointPosition = OriginalFileName.lastIndexOf(".");
        if (pointPosition<0){
            file.setOriginalName(OriginalFileName);
            file.setExtension("");
            return file;
        }
        file.setOriginalName(OriginalFileName.substring(0,pointPosition));
        file.setExtension(OriginalFileName.substring(pointPosition,OriginalFileName.length()));
        return file;
    }
}
