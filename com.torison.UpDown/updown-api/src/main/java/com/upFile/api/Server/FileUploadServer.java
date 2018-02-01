package com.upFile.api.Server;


import com.upFile.api.model.entity.File;
import com.upFile.api.model.request.FileQueryForm;
import com.upFile.api.model.response.DataGrid;
import org.springframework.stereotype.Service;

@Service
public interface FileUploadServer {

    /**
     * 文件查询方法
     * 参数：文件查询请求表单--fileQueryForm 用户名--UserName--getUser.getUsername
     * @param fileQueryForm
     * @param UserName
     * @return DataGrid<File><br>
     * Sort(JPA--Direction....(JPA sort rule ASC or DESC) with param sort by
     * Page JPA's Paining Entity
     */
    public DataGrid<File> queryFile(FileQueryForm fileQueryForm, String UserName);

}
