package com.spring.demo.model;

import com.spring.demo.utils.Md5Utils;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class SysUser implements Serializable {

    @Id
    private String id;
    private String userName;
    private String password;
    private String mobile;
    private int sex;
    private Integer role;
    private String realName;
    private String idCardNo;
    private String sys;
    private Date insertTime;
    private Date updateTime;

    private SysUser genId() {
        if (null == id) this.id = Md5Utils.encrypt(userName + password + idCardNo);
        return this;
    }

    public SysUser complementInfo(boolean isUpdate) {
        this.updateTime = new Date();
        if (!isUpdate) {
            genId();
            this.insertTime = this.updateTime;
        }
        return this;
    }

}
