package back.Route.dao.jpa;

import back.Route.dao.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface routeJPA extends JpaRepository<Route,Integer>{

    /*@Modifying
    @Query("update Route rt set")*/
    @Modifying
    @Query( "update Route r set r.status = :status where r.routeId = :routeId" )
    int update(@Param("status")Integer status, @Param("routeId")Integer routeId);


}
