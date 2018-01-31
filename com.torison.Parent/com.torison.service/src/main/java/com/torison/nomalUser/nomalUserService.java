package com.torison.nomalUser;

import com.torison.common.model.Result;
import com.torison.nomalUser.dao.nomaluserDao;
import com.torison.nomalUser.model.nomalUser;
import com.torison.common.model.respCode;
import com.torison.utils.MD5Sercurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;

@Service
public class nomalUserService {

    @Autowired
    private nomaluserDao userdao;

    /**
     *普通用户注册
     * nomalUser regist
     * @param nomalUser
     * @return result
     * @throws NoSuchAlgorithmException with result's isSuccess is false
     */
    public Result save(nomalUser nomalUser){
        Result result = new Result();
        nomalUser usertest = userdao.queryUser(nomalUser.getUseraccountnum());
        if (usertest!=null && !StringUtils.isEmpty(usertest)){
            result.setRespMsg("该用户名已经存在");
            result.setRespCode(respCode.FALUSE);
            result.setSuccess(false);
            return result;
        }
        nomalUser.setUserpassword(
                MD5Sercurity.getMd5(nomalUser.getUserpassword()).getData()
        );
        userdao.savenomalUser(nomalUser);
        result.setSuccess(true);
        result.setRespCode(respCode.SUCCESS);
        return result;
    }

    /**
     * 登录
     * login
     * @param user
     * @return
     */
    public Result login(nomalUser user){
        Result result = new Result();
        nomalUser userFromDatabase = userdao.queryUser(user.getUseraccountnum());
        if (StringUtils.isEmpty(userFromDatabase)){
            result.setSuccess(false);
            result.setRespCode(respCode.FALUSE);
            result.setRespMsg("无该用户");
            return result;
        }
        String pwdInDataBase =userFromDatabase.getUserpassword();
        if (pwdInDataBase.equals(MD5Sercurity.getMd5(user.getUserpassword()).getData())){
            result.setSuccess(true);
            result.setRespCode(respCode.SUCCESS);
            return result;
        }else{
            result.setSuccess(false);
            result.setRespCode(respCode.FALUSE);
            result.setRespMsg("用户名密码错误");
            return result;
        }

    }

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws
     */
    public Result updateUser(nomalUser user){
        Result result = new Result();
        nomalUser userFromDatabase = userdao.queryUser(user.getUseraccountnum());
        if (StringUtils.isEmpty(userFromDatabase)){
            result.setSuccess(false);
            result.setRespCode(respCode.FALUSE);
            result.setRespMsg("用户不存在");
            return result;
        }
        user.setUserpassword(MD5Sercurity.getMd5(user.getUserpassword()).getData());
        Integer integer= userdao.updateUser(user);
        if (integer==1){
            result.setSuccess(true);
            result.setRespCode(respCode.SUCCESS);
            return result;
        }
        result.setSuccess(false);
        result.setRespCode(respCode.FALUSE);
        result.setRespMsg("系统异常");
        return null;
    }
}
