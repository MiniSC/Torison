package com.torison.api;


import com.torison.api.model.UserEntity;
import com.torison.api.model.PayEntity;
import com.torison.api.model.ResEntity;
import org.springframework.stereotype.Service;

@Service
public interface PayServiceApi {



    /**
     * 添加账户，即用户新增开户
     * @param userEntity
     * @return
     */
    public ResEntity<UserEntity> addPayEntity(UserEntity userEntity);

    /**
     * 修改用户金额，支付或充值功能
     * @param payEntity
     * @return
     */
    public ResEntity modifyMoney(PayEntity payEntity);

    /**
     * 根据账号查询余额
     * @param account
     * @return
     */
    public ResEntity<Double> queryMoneyByAccount(String account);


}
