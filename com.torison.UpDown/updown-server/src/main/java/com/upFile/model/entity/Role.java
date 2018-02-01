package com.upFile.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Role class
 * 
 * @author HenryYu
 * @date 2017/12/15
 * @version 1.0.0
 */
@Entity
public class Role implements Serializable{
    @Id
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}