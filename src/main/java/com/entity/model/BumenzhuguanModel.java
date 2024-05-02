package com.entity.model;

import com.entity.BumenzhuguanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 部门主管
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BumenzhuguanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 部门主管姓名
     */
    private String bumenzhuguanName;


    /**
     * 部门主管手机号
     */
    private String bumenzhuguanPhone;


    /**
     * 部门主管头像
     */
    private String bumenzhuguanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String bumenzhuguanEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：部门主管姓名
	 */
    public String getBumenzhuguanName() {
        return bumenzhuguanName;
    }


    /**
	 * 设置：部门主管姓名
	 */
    public void setBumenzhuguanName(String bumenzhuguanName) {
        this.bumenzhuguanName = bumenzhuguanName;
    }
    /**
	 * 获取：部门主管手机号
	 */
    public String getBumenzhuguanPhone() {
        return bumenzhuguanPhone;
    }


    /**
	 * 设置：部门主管手机号
	 */
    public void setBumenzhuguanPhone(String bumenzhuguanPhone) {
        this.bumenzhuguanPhone = bumenzhuguanPhone;
    }
    /**
	 * 获取：部门主管头像
	 */
    public String getBumenzhuguanPhoto() {
        return bumenzhuguanPhoto;
    }


    /**
	 * 设置：部门主管头像
	 */
    public void setBumenzhuguanPhoto(String bumenzhuguanPhoto) {
        this.bumenzhuguanPhoto = bumenzhuguanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getBumenzhuguanEmail() {
        return bumenzhuguanEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setBumenzhuguanEmail(String bumenzhuguanEmail) {
        this.bumenzhuguanEmail = bumenzhuguanEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
