package com.torison.Evaluation.mapper;

import com.torison.Evaluation.model.Evaluation;
import org.apache.ibatis.jdbc.SQL;

public class EvaluationSqlProvider {

    public String insertSelective(Evaluation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("evaluation");
        
        if (record.getRouteid() != null) {
            sql.VALUES("routeId", "#{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("grade", "#{grade,jdbcType=INTEGER}");
        }
        
        if (record.getFromid() != null) {
            sql.VALUES("fromId", "#{fromid,jdbcType=INTEGER}");
        }
        
        if (record.getMsg() != null) {
            sql.VALUES("msg", "#{msg,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }
}