package com.upFile.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Data
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


    private UniqueFile(Builder builder){
        this.encryptName = builder.encryptName;
        this.createTime = builder.createTime;
        this.path = builder.path;
        this.size = builder.size;
    }

    public static Builder create(){
        return new Builder();
    }

    /**
     * builder
     */
    public static class Builder{
        private String encryptName;
        private String path;
        private Timestamp createTime;
        private Long size;

        public Builder setEncryptName(String encryptName) {
            this.encryptName = encryptName;
            return this ;
        }
        public Builder setPath(String path) {
            this.path = path;
            return this ;
        }
        public Builder setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
            return this ;
        }
        public Builder setSize(Long size) {
            this.size = size;
            return this ;
        }
        public UniqueFile build() {
            return new UniqueFile(this);
        }

    }









}
