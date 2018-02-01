package com.upFile.api.model.request;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * XXX class
 * 
 * @author HenryYu
 * @date 2018年1月30日下午5:13:13
 * @version 1.0.0
 */
@Data
public class FileQueryForm extends PaginationForm implements Serializable{


	private static final long serialVersionUID = -7566033338379243438L;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 初始文件名称
	 */
	private String originalName;

	/**
	 * 扩展名称
	 */
	private String extension;


}
 

