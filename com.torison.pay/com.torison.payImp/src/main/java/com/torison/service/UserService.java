package com.torison.service;

import com.torison.JPA.UserJPA;
import com.torison.model.PayEntity;
import com.torison.model.UserEntity;
import com.torison.model.PayEnum;
import com.torison.model.ResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJPA userJPA;

    /**
     * 添加账户，即用户新增开户
     * @param userEntity
     * @return
     */
    public ResEntity<UserEntity> addPayEntity(UserEntity userEntity){
        ResEntity resEntity = new ResEntity();
        try {
            userJPA.saveAndFlush(userEntity);
        }catch (RuntimeException e){
            resEntity.setSuccess(false);
            resEntity.setResMsg("支付账户新增失败");
            return resEntity;
        }
        resEntity.setSuccess(true);
        resEntity.setResCode(PayEnum.RespCode.SUCCESS);
        return resEntity;
    }

    /**
     * 修改用户金额，支付或充值功能
     * @param payEntity
     * @return
     */
    public ResEntity modifyMoney(PayEntity payEntity)
    {
        ResEntity resEntity = new ResEntity();
        if (payEntity.getStatus()==null||"".equals(payEntity.getStatus())){
            resEntity.setSuccess(false);
            resEntity.setResMsg("请指明对数据的操作方式");
            return  resEntity;
        }
        if (PayEnum.PayStatus.INCREASE.equals(payEntity.getStatus())){
            UserEntity userEntity = userJPA.queryUserEntityByAccount(payEntity.getAccount()).get(0);
            userEntity.setMoney(userEntity.getMoney()+payEntity.getMoney());
            userJPA.updateMoneyByAccountAndMoney(userEntity.getAccount(),userEntity.getMoney());
            resEntity.setSuccess(true);
            return resEntity;
        }
        if (PayEnum.PayStatus.DECREASE.equals(payEntity.getStatus())){
            UserEntity userEntity = userJPA.queryUserEntityByAccount(payEntity.getAccount()).get(0);
            userEntity.setMoney(userEntity.getMoney()-payEntity.getMoney());
            userJPA.updateMoneyByAccountAndMoney(userEntity.getAccount(),userEntity.getMoney());
            resEntity.setSuccess(true);
            return resEntity;
        }

        resEntity.setSuccess(false);
        resEntity.setResMsg("操作有误");
        return resEntity;
    }

}
