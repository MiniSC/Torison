package com.upFile.common.repository;

import com.upFile.model.UniqueFile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UniqueFileRepository for Paging&Sorting but not support screening
 * 继承CrudRepository, 实现了一组分页排序相关方法
 *
 * @extend CrudRepository
 * @author HenryYu
 * @date 2018.2.1 9:48
 * @version 1.0.1
 * @copy HenryYu's privateBox 1.0.0
 *
 * Use as a autowired interface
 */
public interface UniqueFileRepository extends PagingAndSortingRepository<UniqueFile, String> {

}
