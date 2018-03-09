package back.UserManage.service;

import back.UserManage.dao.jpa.RmJPA;
import back.UserManage.dao.jpa.UserJPA;
import back.UserManage.dao.model.RouteMaker;
import back.UserManage.dao.model.RouteMakerForm;
import back.UserManage.dao.model.User;
import back.common.model.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class RMService {

    @Autowired
    private RmJPA rmJPA;

    @Autowired
    private UserJPA userJPA;


    /**
     * =============================
     * 根据条件动态查找
     * 这里要把“”空白字符串转换为null否则会作为条件传入
     * @param routeMaker
     * @return
     */
    public List<RouteMakerForm> findMakerByConditioins(RouteMaker routeMaker){

        User user = new User();
        user.setUserId(routeMaker.getUserId());
        List<RouteMakerForm> listRouteMakerForm = new LinkedList<>();

        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("userID", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<User> ex_user = Example.of(user,matcher_user);
        List<User> listUser = userJPA.findAll(ex_user);

        ExampleMatcher matcher_ = ExampleMatcher.matching()
                .withMatcher("userID", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<RouteMaker> ex = Example.of(routeMaker,matcher_);
        List<RouteMaker> listMaker = rmJPA.findAll(ex);

        for (RouteMaker routeMaker1:listMaker){
            for (User user1 : listUser){
                if (routeMaker1.getUserId().equals(user1.getUserId())){
                    RouteMakerForm routeMakerForm = new RouteMakerForm();
                    copyProperties(routeMaker1,routeMakerForm);
                    copyProperties(user1,routeMakerForm);
                    listRouteMakerForm.add(routeMakerForm);
                }
            }
        }

        return listRouteMakerForm;
    }

    /**
     * 给与用户权限
     * @param id
     * @param status
     * @return
     */
    @Transactional
    public Result giveAuthentication(String id,String status){
        Result result = new Result();
        if(rmJPA.confirmRm(status,Integer.parseInt(id))==0){
            result.setSuccess(false);
            result.setRespMsg("系统异常");
            return result;
        }
         result.setSuccess(true);
        return result;
    }
}
