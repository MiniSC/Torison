package JPA;

import com.torison.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ===============================
 * Created with Intellij IDEA
 * User:
 * Date:
 * Time:
 * GitHub:
 * ===============================
 */
public interface UserJPA extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from p_user where p_account =  ?1",nativeQuery = true)
    public List<UserEntity> queryUserEntityByAccount(String account);

    @Query(value = "UPDATE p_user SET p_money = ?2 WHERE p_user = ?1",nativeQuery = true)
    public int updateMoneyByAccountAndMoney(String p_account, double p_money);




}
