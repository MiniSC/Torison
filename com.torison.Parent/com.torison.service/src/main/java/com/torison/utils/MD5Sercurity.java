package com.torison.utils;

import com.torison.common.model.Result;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *   MD5 加密类
 * @author dongjj
 *
 */
public class MD5Sercurity {

    /**
     * for get String's value which after md5
     * @throws NoSuchAlgorithmException with result's isSuccess is false
     * @param arg
     * @return
     */
    public static Result<String> getMd5(String arg){
        Result<String> result = new Result<>();
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(arg.getBytes());
            BigInteger integer = new BigInteger(md.digest());
            result.setSuccess(true);
            result.setData(integer.toString());
            result.setRespCode("00");
            return result;
        }
        catch (NoSuchAlgorithmException e){
            result.setSuccess(false);
            return result;
        }
    }

}
