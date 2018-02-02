package com.upFile.model.entity;

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

	private File(Builder builder) {
		this.id=UUID.randomUUID().toString();
		this.createTime=new Timestamp(System.currentTimeMillis());
		this.originalName= builder.originalName;
		this.extension = builder.extension;
		this.deleted = builder.deleted;
		this.username = builder.username;
		this.encryptName = builder.encryptName;
		this.size = builder.size;
	}

	public static Builder create(){
		return new Builder();
	}

	public static class Builder{
		private String id;
		private Timestamp createTime;
		private String originalName;
		private String extension;
		private boolean deleted;
		private String username;
		private String encryptName;
		private Long size;


		public Builder setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder setOriginalName(String originalName) {
			this.originalName = originalName;
			return this;
		}

		public Builder setExtension(String extension) {
			this.extension = extension;
			return this;
		}

		public Builder setDeleted(boolean deleted) {
			this.deleted = deleted;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setEncryptName(String encryptName) {
			this.encryptName = encryptName;
			return this;
		}

		public Builder setSize(Long size) {
			this.size = size;
			return this;
		}
		public File build(){
			return new File(this);
		}
	}
}
 

