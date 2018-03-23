package com.torison.Evaluation.model;

import java.io.Serializable;

/**
 * ======================
 * payEntity
 * @author dongjj
 * @time 18.3.5
 * ======================
 */
public class PayEntity implements Serializable {
    private static final long serialVersionUID = -8592968296007935064L;
    private String Account;
    private Double Money;
    private String Status;
    private String Password;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public Double getMoney() {
        return Money;
    }

    public void setMoney(Double money) {
        Money = money;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
