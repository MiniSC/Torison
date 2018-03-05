package com.torison.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ===============================
 * Created with Intellij IDEA
 * User:
 * Date:
 * Time:
 * GitHub:
 * ===============================
 */
@Entity
@Table(name = "p_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -8457989328459938120L;

    @Id
    @GeneratedValue
    @Column(name = "p_id")
    private int UserID;

    @Column(name = "p_account")
    private String Account;

    @Column(name = "p_password")
    private String PassWord;

    @Column(name = "p_name")
    private String UserName;

    @Column(name = "p_money")
    private double money;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
