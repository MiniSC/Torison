package com.upFile.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class UniqueFile implements Serializable {


    private static final long serialVersionUID = -1324873611040760614L;
    /**
     * 加密的名称
     */
    @Id
    private String encryptName;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 创建时间
     * @see java.sql.Timestamp
     * TIMESTAMP列用于INSERT或UPDATE操作时记录日期和时间。
     */
    private Timestamp createTime;
    /**
     * 文件大小
     */
    private Long size;

    public String getEncryptName() {
        return encryptName;
    }

    public void setEncryptName(String encryptName) {
        this.encryptName = encryptName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
