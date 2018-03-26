package back.UserManage.dao.jpa;

import back.UserManage.dao.model.RouteMaker;
import back.UserManage.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserJPA extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Integer>,QueryByExampleExecutor<User> {


    /**
     * 修改用户等级为2--冻结
     * @param rank
     * @param id
     * @return
     */
    @Modifying
    @Query( "update User user set user.rank = :rank where user.userId = :id" )
    int changeRank(@Param("rank")String rank, @Param("id")Integer id);
}
