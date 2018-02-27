package com.torison.User;

import com.torison.User.dao.UserDao;
import com.torison.User.model.User;
import com.torison.common.model.RespEntity;
import com.torison.common.model.Result;
import com.torison.common.model.respCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 查看登录的用户是否存在，用户名密码是否正确
     * @see respCode "00"用户验证正确   "01"密码错误  "02"不存在
     * @param user
     * @return
     */
    public RespEntity testLogin(User user){

        RespEntity respEntity = new RespEntity();
        User userByAcc = userDao.getUserByAcc(user.getAccount());
        if (userByAcc!=null){
              if (userByAcc.getPassword().equals(user.getPassword())){
                  respEntity.setData(userByAcc);
                  respEntity.setRespCode(respCode.Login.TREUUSER);
                return  respEntity;
              }else {
                  respEntity.setRespCode(respCode.Login.WRONGPWD);
                  return respEntity;
              }
        }else{
            respEntity.setRespCode(respCode.Login.NOTEXIST);
            return respEntity;
        }
    }

    public int saveUser(User user){
       return userDao.insertUser(user);
    }

    public int update(User user){
        return userDao.updateByIDSelective(user);
    }

    public User getUserByAcc(String account){
        return userDao.getUserByAcc(account);
    }
}
