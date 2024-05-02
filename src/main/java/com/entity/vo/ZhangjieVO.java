package com.entity.vo;

import com.entity.ZhangjieEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 章节
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhangjie")
public class ZhangjieVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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
     * 创建时间 show2 photoShow
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
	 * 设置：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
