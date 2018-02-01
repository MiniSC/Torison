package com.upFile.common.repository;


import com.upFile.model.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.LockModeType;



/**
 * FileRepository for
 * @author HenryYu
 * @date 2018.2.1
 * @version 1.0.0
 * @copy dongjj
 */
public interface FileRepository extends PagingAndSortingRepository<File,String> {

    public Page<File> findByUsernameAndOriginalNameLikeAndExtensionLikeAndDeleted(String username, String originalName, String extension, Boolean deleted, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public File findById(String Id);

}
