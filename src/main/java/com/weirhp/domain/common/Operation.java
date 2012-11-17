package com.weirhp.domain.common;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

@Table("operation")
public class Operation extends BaseObject {
    private static final long serialVersionUID = 19876543L;
    @Id
    private Long           id;
    @Column
    private String            path;
    @Column("class_name")
    private String            className;
    @Column("method_name")
    private String            methodName;
    @Column("unique_code")
    @Name
    private String            uniqueCode;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public String getUniqueCode() {
        return uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    
}
