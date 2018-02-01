package com.upFile.api.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * XXX class
 * 
 * @author HenryYu
 * @date 2018年1月10日下午3:49:57
 * @version 1.0.0
 */
@Entity
@Data
public class File implements Serializable{

	@Id
	private String id;
	private Timestamp createTime;
	private String originalName;
	private String extension;
	private boolean deleted;
	private String username;
	private String encryptName;
	private Long size;

	public File() {
		id=UUID.randomUUID().toString();
		createTime=new Timestamp(System.currentTimeMillis());
	}
}
 

