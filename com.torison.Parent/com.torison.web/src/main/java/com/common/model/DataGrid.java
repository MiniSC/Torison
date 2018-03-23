package com.common.model;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * JSON模型
 *
 * 用户后台向前台返回的列表对象
 *
 *
 */
public class DataGrid implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7637609958589426231L;

    private int total = 0;

    private List<T> rows = new ArrayList<T>();

    public DataGrid() {
    }

    public DataGrid(int total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
