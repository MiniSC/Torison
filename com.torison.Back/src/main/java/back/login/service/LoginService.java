package back.login.service;

import back.common.model.ResEntity;
import back.common.model.RespCode;
import back.login.dao.JPA.AdminJPA;
import back.login.dao.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private AdminJPA adminJPA;

    /**
     * 登录服务
     * @param admin
     * @return
     */
    public ResEntity<Admin> login(Admin admin){
        ResEntity<Admin> resEntity = new ResEntity<>();
        if (adminJPA.listAdminWithAccount(admin.getAccount()).isEmpty()){
            resEntity.setSuccess(false);
            resEntity.setRespMsg(RespCode.NONE.code());
            return resEntity;
        }
        if ((admin = adminJPA.listAdminWithAccountANDPASSWORD(admin.getAccount(),admin.getPassword()).get(0))==null){
            resEntity.setSuccess(false);
            resEntity.setRespMsg(RespCode.WRONG.code());
            return resEntity;
        }
        resEntity.setData(admin);
        resEntity.setSuccess(true);
        resEntity.setRespMsg(RespCode.LOGSUCCESS.code());
        return resEntity;
    }

    @Transactional
    public void updatepwd(Admin admin){
        adminJPA.update(admin.getPassword(),admin.getAccount());
    }
}
