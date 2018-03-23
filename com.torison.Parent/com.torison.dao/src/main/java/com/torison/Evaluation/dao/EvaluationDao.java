package com.torison.Evaluation.dao;

import com.torison.Evaluation.mapper.EvaluationMapper;
import com.torison.Evaluation.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationDao {

    @Autowired
    EvaluationMapper evaluationMapper;


    /**
     * insert evaluation msg & grade
     * @param evaluation
     * @return
     */
    public int insertEvaluation(Evaluation evaluation){
        return evaluationMapper.insert(evaluation);
    }

   /* *//**
     * list Evaluations by routeid for evaluate maker's credit
     * @param routeid
     * @return
     *//*
    public List<Evaluation> listEvaByRouteId(Integer routeid){
        return evaluationMapper.selectByrouteId(routeid);
    }*/

}
