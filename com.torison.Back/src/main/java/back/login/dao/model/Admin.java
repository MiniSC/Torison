package back.login.dao.model;


import javax.persistence.*;

/**
 * ======================
 * @author dongjj
 * @time 18.3.6
 * aminUserEntity
 * ======================
 */
@Entity
@Table(name = "t_admin")
public class Admin  {

    @Id
    @Column(name = "a_id")
    @GeneratedValue
    private Integer adminId;
    /**
     * 账号
     */
    @Column(name = "a_account")
    private String account;
    /**
     * 密码
     */
    @Column(name = "a_password")
    private String password;
    /**
     * 姓名
     */
    @Column(name = "a_name")
    private String adminName;
    /**
     * 权限
     */
    @Column(name = "a_authentication")
    private String authentication;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
