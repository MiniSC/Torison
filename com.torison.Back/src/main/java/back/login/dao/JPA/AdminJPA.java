package back.login.dao.JPA;

import back.login.dao.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminJPA extends JpaRepository<Admin,Integer> {

    @Query(value = "SELECT * FROM t_admin WHERE a_account=?1 AND a_password=?2" , nativeQuery = true)
    public List<Admin> listAdminWithAccountANDPASSWORD(String account,String password);

    @Query(value = "SELECT * FROM t_admin WHERE a_account=?1 " , nativeQuery = true)
    public List<Admin> listAdminWithAccount(String account);

    @Modifying
    @Query( "update Admin a set a.password = :password where a.account =:account" )
    int update(@Param("password")String password, @Param("account")String account);




}
