package com.upFile.api.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * easyUI PaginationForm class
 * 
 * @author HenryYu
 * @date 2018年1月8日下午3:24:44
 * @version 1.0.0
 */
@Data
public class PaginationForm implements Serializable {

	private static final long serialVersionUID = -5186368323215162707L;
	/**
	 * 页数
	 */
	private int page;
	/**
	 * 当页显示条数
	 */
	private int rows;
	/**
	 * 排序方式
	 */
	private String sort;
	/**
	 *请求者
	 */
	private String order;


}
 

