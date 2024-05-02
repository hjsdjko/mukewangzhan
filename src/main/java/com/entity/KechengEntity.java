package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 课程
 *
 * @author 
 * @email
 */
@TableName("kecheng")
public class KechengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KechengEntity() {

	}

	public KechengEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 课程
     */
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 课程名称
     */
    @TableField(value = "kecheng_name")

    private String kechengName;


    /**
     * 课程照片
     */
    @TableField(value = "kecheng_photo")

    private String kechengPhoto;


    /**
     * 课程类型
     */
    @TableField(value = "kecheng_types")

    private Integer kechengTypes;


    /**
     * 课程原价
     */
    @TableField(value = "kecheng_old_money")

    private Double kechengOldMoney;


    /**
     * 现价
     */
    @TableField(value = "kecheng_new_money")

    private Double kechengNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "kecheng_clicknum")

    private Integer kechengClicknum;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "kecheng_delete")

    private Integer kechengDelete;


    /**
     * 课程介绍
     */
    @TableField(value = "kecheng_content")

    private String kechengContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：课程
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：课程
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：课程名称
	 */
    public String getKechengName() {
        return kechengName;
    }


    /**
	 * 获取：课程名称
	 */

    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }
    /**
	 * 设置：课程照片
	 */
    public String getKechengPhoto() {
        return kechengPhoto;
    }


    /**
	 * 获取：课程照片
	 */

    public void setKechengPhoto(String kechengPhoto) {
        this.kechengPhoto = kechengPhoto;
    }
    /**
	 * 设置：课程类型
	 */
    public Integer getKechengTypes() {
        return kechengTypes;
    }


    /**
	 * 获取：课程类型
	 */

    public void setKechengTypes(Integer kechengTypes) {
        this.kechengTypes = kechengTypes;
    }
    /**
	 * 设置：课程原价
	 */
    public Double getKechengOldMoney() {
        return kechengOldMoney;
    }


    /**
	 * 获取：课程原价
	 */

    public void setKechengOldMoney(Double kechengOldMoney) {
        this.kechengOldMoney = kechengOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getKechengNewMoney() {
        return kechengNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setKechengNewMoney(Double kechengNewMoney) {
        this.kechengNewMoney = kechengNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getKechengClicknum() {
        return kechengClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setKechengClicknum(Integer kechengClicknum) {
        this.kechengClicknum = kechengClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getKechengDelete() {
        return kechengDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setKechengDelete(Integer kechengDelete) {
        this.kechengDelete = kechengDelete;
    }
    /**
	 * 设置：课程介绍
	 */
    public String getKechengContent() {
        return kechengContent;
    }


    /**
	 * 获取：课程介绍
	 */

    public void setKechengContent(String kechengContent) {
        this.kechengContent = kechengContent;
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

    @Override
    public String toString() {
        return "Kecheng{" +
            "id=" + id +
            ", laoshiId=" + laoshiId +
            ", kechengName=" + kechengName +
            ", kechengPhoto=" + kechengPhoto +
            ", kechengTypes=" + kechengTypes +
            ", kechengOldMoney=" + kechengOldMoney +
            ", kechengNewMoney=" + kechengNewMoney +
            ", kechengClicknum=" + kechengClicknum +
            ", shangxiaTypes=" + shangxiaTypes +
            ", kechengDelete=" + kechengDelete +
            ", kechengContent=" + kechengContent +
            ", createTime=" + createTime +
        "}";
    }
}
