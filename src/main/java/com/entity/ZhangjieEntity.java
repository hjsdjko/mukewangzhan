package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 章节
 *
 * @author 
 * @email
 */
@TableName("zhangjie")
public class ZhangjieEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhangjieEntity() {

	}

	public ZhangjieEntity(T t) {
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
    @TableField(value = "kecheng_id")

    private Integer kechengId;


    /**
     * 章节名称
     */
    @TableField(value = "zhangjie_name")

    private String zhangjieName;


    /**
     * 章节照片
     */
    @TableField(value = "zhangjie_photo")

    private String zhangjiePhoto;


    /**
     * 视频
     */
    @TableField(value = "zhangjie_video")

    private String zhangjieVideo;


    /**
     * 逻辑删除
     */
    @TableField(value = "zhangjie_delete")

    private Integer zhangjieDelete;


    /**
     * 章节介绍
     */
    @TableField(value = "zhangjie_content")

    private String zhangjieContent;


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
    public Integer getKechengId() {
        return kechengId;
    }


    /**
	 * 获取：课程
	 */

    public void setKechengId(Integer kechengId) {
        this.kechengId = kechengId;
    }
    /**
	 * 设置：章节名称
	 */
    public String getZhangjieName() {
        return zhangjieName;
    }


    /**
	 * 获取：章节名称
	 */

    public void setZhangjieName(String zhangjieName) {
        this.zhangjieName = zhangjieName;
    }
    /**
	 * 设置：章节照片
	 */
    public String getZhangjiePhoto() {
        return zhangjiePhoto;
    }


    /**
	 * 获取：章节照片
	 */

    public void setZhangjiePhoto(String zhangjiePhoto) {
        this.zhangjiePhoto = zhangjiePhoto;
    }
    /**
	 * 设置：视频
	 */
    public String getZhangjieVideo() {
        return zhangjieVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setZhangjieVideo(String zhangjieVideo) {
        this.zhangjieVideo = zhangjieVideo;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhangjieDelete() {
        return zhangjieDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhangjieDelete(Integer zhangjieDelete) {
        this.zhangjieDelete = zhangjieDelete;
    }
    /**
	 * 设置：章节介绍
	 */
    public String getZhangjieContent() {
        return zhangjieContent;
    }


    /**
	 * 获取：章节介绍
	 */

    public void setZhangjieContent(String zhangjieContent) {
        this.zhangjieContent = zhangjieContent;
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
        return "Zhangjie{" +
            "id=" + id +
            ", kechengId=" + kechengId +
            ", zhangjieName=" + zhangjieName +
            ", zhangjiePhoto=" + zhangjiePhoto +
            ", zhangjieVideo=" + zhangjieVideo +
            ", zhangjieDelete=" + zhangjieDelete +
            ", zhangjieContent=" + zhangjieContent +
            ", createTime=" + createTime +
        "}";
    }
}
