package com.torison.Evaluation.Service;

import com.torison.Evaluation.dao.EvaluationDao;
import com.torison.Evaluation.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dongjj
 *
 */
@Service
public class EvaluationService {


    @Autowired
    private EvaluationDao evaluationDao;


    /**
     * insert Evaluation message to DB
     * @param evaluation
     * @return
     */
    public int insertEva(Evaluation evaluation){
       return evaluationDao.insertEvaluation(evaluation);
    }

    /**
     * list Evaluation message by routeid
     * @param routeid
     * @return
     */
   /* public List<Evaluation> listEvaMsgByRouteID(Integer routeid){
        return evaluationDao.listEvaByRouteId(routeid);
    }*/


}
