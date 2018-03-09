package back.UserManage.dao.jpa;

import back.UserManage.dao.model.RouteMaker;
import back.UserManage.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserJPA extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Integer>,QueryByExampleExecutor<User> {
}
