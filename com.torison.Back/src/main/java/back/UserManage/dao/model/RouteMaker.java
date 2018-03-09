package back.UserManage.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "route_maker")
public class RouteMaker {

    @Id
    @Column(name = "UserID")
    @GeneratedValue
    private Integer userId;

    @Column(name = "Pic1")
    private String pic1;

    @Column(name = "Pic2")
    private String pic2;

    @Column(name = "Introduce")
    private String introduce;

    @Column(name = "Status")
    private String userStatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
