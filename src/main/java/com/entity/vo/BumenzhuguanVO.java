package com.entity.vo;

import com.entity.BumenzhuguanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 部门主管
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("bumenzhuguan")
public class BumenzhuguanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 部门主管姓名
     */

    @TableField(value = "bumenzhuguan_name")
    private String bumenzhuguanName;


    /**
     * 部门主管手机号
     */

    @TableField(value = "bumenzhuguan_phone")
    private String bumenzhuguanPhone;


    /**
     * 部门主管头像
     */

    @TableField(value = "bumenzhuguan_photo")
    private String bumenzhuguanPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "bumenzhuguan_email")
    private String bumenzhuguanEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：部门主管姓名
	 */
    public String getBumenzhuguanName() {
        return bumenzhuguanName;
    }


    /**
	 * 获取：部门主管姓名
	 */

    public void setBumenzhuguanName(String bumenzhuguanName) {
        this.bumenzhuguanName = bumenzhuguanName;
    }
    /**
	 * 设置：部门主管手机号
	 */
    public String getBumenzhuguanPhone() {
        return bumenzhuguanPhone;
    }


    /**
	 * 获取：部门主管手机号
	 */

    public void setBumenzhuguanPhone(String bumenzhuguanPhone) {
        this.bumenzhuguanPhone = bumenzhuguanPhone;
    }
    /**
	 * 设置：部门主管头像
	 */
    public String getBumenzhuguanPhoto() {
        return bumenzhuguanPhoto;
    }


    /**
	 * 获取：部门主管头像
	 */

    public void setBumenzhuguanPhoto(String bumenzhuguanPhoto) {
        this.bumenzhuguanPhoto = bumenzhuguanPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getBumenzhuguanEmail() {
        return bumenzhuguanEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setBumenzhuguanEmail(String bumenzhuguanEmail) {
        this.bumenzhuguanEmail = bumenzhuguanEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
