package com.upFile.utils;

import com.upFile.utils.Exceptions.IllegalInputException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * @author HenryYu
 * @datd 2018.2.1  15:29
 * @version 1.0.1
 * @cover 1.0.0 from PrivateBox from HenryYu
 */
public class Encrypt {

    private static final Log log = LogFactory.getLog(Encrypt.class);

    protected static char hexDidits[]={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messageDigest = null;

    public static String EncodeMultipartFile(MultipartFile file,String encrptType,int setoff,int length)throws IllegalInputException,IOException{
        if (length<=0){
            throw new IllegalInputException("Illegal Input,length should be larger than 0");
        }
        try{
            if (file.getInputStream() instanceof FileInputStream){
                log.info("InputStreamType:FileInputStream");

            }
        }catch (IOException e){
            throw new IOException("file not found");
        }
        return null;
    }


}
