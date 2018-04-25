package com.torison.JPA;


import com.torison.api.model.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ===============================
 * Created with Intellij IDEA
 * User:
 * Date:
 * Time:
 * GitHub:
 * ===============================
 */
@Service
public interface UserJPA extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from p_user where p_account =  ?1",nativeQuery = true)
     List<UserEntity> queryUserEntityByAccount(String account);

    @Modifying
    @Query(value = "UPDATE UserEntity user SET user.money = ?1 WHERE user.Account = ?2")
     int updateMoneyByAccountAndMoney( @Param(value = "money") double money,@Param(value = "account") String account);




}
