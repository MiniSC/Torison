package com.upFile.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.upFile.utils.Exceptions.IllegalInputException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.security.util.Length;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.MulticastChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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

    protected static char hexDigits[]={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messageDigest = null;

    /**
     * 文件加密类
     * @param file  mvc输入的文件
     * @param encryptType 加密类型
     * @param setoff 加密出发位
     * @param length 加密长度
     * @return
     * @throws IllegalInputException length or setoff is less than zero
     * @throws IOException File not found
     * @throws NoSuchAlgorithmException encryptType is wrong
     */
    public static String EncodeMultipartFile(MultipartFile file,String encryptType,int setoff,int length)
            throws IllegalInputException,
                   IOException,
                   NoSuchAlgorithmException{
        if (length<=0){
            throw new IllegalInputException("Illegal Input,length should be larger than 0");
        }
        try{
            /**
             * FileInputStream Input
             */
            if (file.getInputStream() instanceof FileInputStream){
                log.info("InputStreamType:FileInputStream");
                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                FileChannel fileChannel = fileInputStream.getChannel();
                MappedByteBuffer byteBuffer;
                if(setoff>=fileChannel.size()){
                    throw new IllegalInputException("Illegal Input,setoff too smaller");
                }
                if (fileChannel.size()<=(setoff+length)){
                    byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,setoff,fileChannel.size());
                }else{
                    byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,setoff,length+setoff);
                }
                try {
                    messageDigest = MessageDigest.getInstance(encryptType.toString());
                    messageDigest.update(byteBuffer);
                }catch (NoSuchAlgorithmException e){
                     throw new NoSuchAlgorithmException("Wrong encrypt mode");
                }
                return  bufferToHex(messageDigest.digest());
            }
            /**
             * ByteArrayInputStream input
             */
            if(file.getInputStream()instanceof ByteArrayInputStream){
                log.info("InputStreamType:ByteArrayInputStream");
                messageDigest=MessageDigest.getInstance(encryptType.toString());
                if(setoff>=file.getBytes().length) {
                    throw new IllegalInputException("Illegal input,setoff should be smaller than file bytes length");
                }
                if (file.getBytes().length <= setoff+length) {
                    messageDigest.update(file.getBytes());
                } else {
                    messageDigest.update(Arrays.copyOfRange(file.getBytes(), setoff, setoff+length));
                }
                return bufferToHex(messageDigest.digest());
            }
        }catch (IOException e){
            throw new IOException("file not found");
        }
        return null;
    }

    /**
     * 转换字符串工具
     * @param bytes
     * @return
     */
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    /**
     * 转换字符串工具2
     * @param bytes
     * @param m
     * @param n
     * @return
     */
    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }
    /**
     * 转换字符串工具--工具
     * @param bt
     * @param stringbuffer
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


}
