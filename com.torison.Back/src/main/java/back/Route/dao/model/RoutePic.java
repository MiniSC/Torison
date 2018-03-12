package back.Route.dao.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "route_pic")
public class RoutePic {
    @Id
    @Column(name = "routeid")
    @GeneratedValue
    private Integer routeid;

    @Column(name = "routepic1")
    private String routepic1;

    @Column(name = "routepic2")
    private String routepic2;

    @Column(name = "routepic3")
    private String routepic3;

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public String getRoutepic1() {
        return routepic1;
    }

    public void setRoutepic1(String routepic1) {
        this.routepic1 = routepic1 == null ? null : routepic1.trim();
    }

    public String getRoutepic2() {
        return routepic2;
    }

    public void setRoutepic2(String routepic2) {
        this.routepic2 = routepic2 == null ? null : routepic2.trim();
    }

    public String getRoutepic3() {
        return routepic3;
    }

    public void setRoutepic3(String routepic3) {
        this.routepic3 = routepic3 == null ? null : routepic3.trim();
    }
}