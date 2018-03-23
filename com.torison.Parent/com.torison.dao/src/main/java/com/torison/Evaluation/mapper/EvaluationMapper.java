package com.torison.Evaluation.mapper;

import com.torison.Evaluation.model.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluationMapper {
    @Insert({
        "insert into evaluation (routeId, grade, ",
        "fromId, msg)",
        "values (#{routeid,jdbcType=INTEGER}, #{grade,jdbcType=INTEGER}, ",
        "#{fromid,jdbcType=INTEGER}, #{msg,jdbcType=LONGVARCHAR})"
    })
    int insert(Evaluation record);

    @InsertProvider(type=EvaluationSqlProvider.class, method="insertSelective")
    int insertSelective(Evaluation record);



}