package back.UserManage.service;

import back.UserManage.dao.jpa.UserJPA;
import back.UserManage.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJPA userJPA;
    /**
     * 根据条件查询路线信息
     * @param user
     * @return
     */
    public List<User> listUser(User user){
        ExampleMatcher matcher_user = ExampleMatcher.matching()
                .withMatcher("routeId", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<User> ex_user = Example.of(user,matcher_user);
        return  userJPA.findAll(ex_user);
    }
}
