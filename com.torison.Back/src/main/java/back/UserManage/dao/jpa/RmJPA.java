package back.UserManage.dao.jpa;

import back.UserManage.dao.model.RouteMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface RmJPA extends JpaRepository<RouteMaker,Integer>,JpaSpecificationExecutor<RouteMaker>,PagingAndSortingRepository<RouteMaker,Integer>,QueryByExampleExecutor<RouteMaker>{


    @Modifying
    @Query( "update RouteMaker rm set rm.userStatus = :status where rm.userId = :id" )
    int confirmRm(@Param("status")String status,  @Param("id")Integer id);



}
