package com.upFile.Server;


import com.upFile.common.component.BaseComponent;
import com.upFile.common.repository.FileRepository;
import com.upFile.common.repository.UniqueFileRepository;
import com.upFile.model.entity.File;
import com.upFile.model.request.FileQueryForm;
import com.upFile.model.response.DataGrid;
import com.upFile.model.response.ResponseMessage;
import com.upFile.model.response.error.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * FileUploadServer for Upload  Files
 *
 * @author HenryYu
 * @copy Dongjj
 * @data 2018.2.1 上午9.40
 * @version 1.0.1 cover 1.0.0 from HenryYu
 */
@Service
public class FileUploadServerImp extends BaseComponent {

    @Autowired
    private UniqueFileRepository uniqueFileRepository;

    @Autowired
    private FileRepository fileRepository;

    /**
     * 文件查询方法
     * 参数：文件查询请求表单--fileQueryForm 用户名--UserName--getUser.getUsername
     * @param fileQueryForm
     * @param UserName
     * @return DataGrid<File><br>
     * Sort(JPA--Direction....(JPA sort rule ASC or DESC) with param sort by
     * Page JPA's Paining Entity
     */
    public DataGrid<File> queryFile(FileQueryForm fileQueryForm, String UserName){
        DataGrid<File> dataGrid = new DataGrid<File>();
        Pageable pageable;
        if (fileQueryForm.getOrder()==null||fileQueryForm.getOrder()==null){
            Sort sort = new Sort(Direction.ASC,"createTime");
            pageable = new PageRequest(fileQueryForm.getPage()-1,fileQueryForm.getRows(),sort);
        }else{
            Sort sort = new Sort(Direction.fromString(fileQueryForm.getOrder()),fileQueryForm.getSort());
            pageable = new PageRequest(fileQueryForm.getPage()-1,fileQueryForm.getRows(),sort);
        }
        Page<File> page = fileRepository.findByUsernameAndOriginalNameLikeAndExtensionLikeAndDeleted(UserName,"%"+fileQueryForm.getOriginalName()+"%","%"+fileQueryForm.getExtension()+"%",false,pageable);
        dataGrid.setRows(page.getContent());
        dataGrid.setTotal(page.getTotalElements());
        return dataGrid;

    }

    /**
     * 删除文件的操作--逻辑删除
     * @param ids
     * @return responseMessage
     * logical delete ---change database's file delete field which is false to true
     */
    public ResponseMessage<File> delete(List<String> ids){
        ResponseMessage<File> responseMessage = new ResponseMessage<File>();
            Iterable<File> files = fileRepository.findAll(ids);
            for (File file:files
                 ) {
                file.setDeleted(true);
            }
            fileRepository.save(files);
            responseMessage.setCode(ResponseCode.Success);
            return responseMessage;
    }




}
