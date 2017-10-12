package com.shihui.mybatisplus.test.plugins.optimisticLocker.entity;

import java.io.Serializable;
import java.util.Date;

import com.shihui.mybatisplus.annotations.TableField;
import com.shihui.mybatisplus.annotations.TableName;
import com.shihui.mybatisplus.annotations.Version;

@TableName("time_version_user")
public class DateVersionUser implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @Version
    private Date version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

}
