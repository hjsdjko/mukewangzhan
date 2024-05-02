package com.entity.view;

import com.entity.ZhangjieLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 章节留言
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zhangjie_liuyan")
public class ZhangjieLiuyanView extends ZhangjieLiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 xuesheng
			/**
			* 学生姓名
			*/
			private String xueshengName;
			/**
			* 学生手机号
			*/
			private String xueshengPhone;
			/**
			* 学生身份证号
			*/
			private String xueshengIdNumber;
			/**
			* 学生头像
			*/
			private String xueshengPhoto;
			/**
			* 班级
			*/
			private Integer banjiTypes;
				/**
				* 班级的值
				*/
				private String banjiValue;
			/**
			* 电子邮箱
			*/
			private String xueshengEmail;

		//级联表 zhangjie
			/**
			* 章节名称
			*/
			private String zhangjieName;
			/**
			* 章节照片
			*/
			private String zhangjiePhoto;
			/**
			* 视频
			*/
			private String zhangjieVideo;
			/**
			* 逻辑删除
			*/
			private Integer zhangjieDelete;
			/**
			* 章节介绍
			*/
			private String zhangjieContent;

	public ZhangjieLiuyanView() {

	}

	public ZhangjieLiuyanView(ZhangjieLiuyanEntity zhangjieLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, zhangjieLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



































				//级联表的get和set xuesheng
					/**
					* 获取： 学生姓名
					*/
					public String getXueshengName() {
						return xueshengName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setXueshengName(String xueshengName) {
						this.xueshengName = xueshengName;
					}
					/**
					* 获取： 学生手机号
					*/
					public String getXueshengPhone() {
						return xueshengPhone;
					}
					/**
					* 设置： 学生手机号
					*/
					public void setXueshengPhone(String xueshengPhone) {
						this.xueshengPhone = xueshengPhone;
					}
					/**
					* 获取： 学生身份证号
					*/
					public String getXueshengIdNumber() {
						return xueshengIdNumber;
					}
					/**
					* 设置： 学生身份证号
					*/
					public void setXueshengIdNumber(String xueshengIdNumber) {
						this.xueshengIdNumber = xueshengIdNumber;
					}
					/**
					* 获取： 学生头像
					*/
					public String getXueshengPhoto() {
						return xueshengPhoto;
					}
					/**
					* 设置： 学生头像
					*/
					public void setXueshengPhoto(String xueshengPhoto) {
						this.xueshengPhoto = xueshengPhoto;
					}
					/**
					* 获取： 班级
					*/
					public Integer getBanjiTypes() {
						return banjiTypes;
					}
					/**
					* 设置： 班级
					*/
					public void setBanjiTypes(Integer banjiTypes) {
						this.banjiTypes = banjiTypes;
					}


						/**
						* 获取： 班级的值
						*/
						public String getBanjiValue() {
							return banjiValue;
						}
						/**
						* 设置： 班级的值
						*/
						public void setBanjiValue(String banjiValue) {
							this.banjiValue = banjiValue;
						}
					/**
					* 获取： 电子邮箱
					*/
					public String getXueshengEmail() {
						return xueshengEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setXueshengEmail(String xueshengEmail) {
						this.xueshengEmail = xueshengEmail;
					}


				//级联表的get和set zhangjie

					/**
					* 获取： 章节名称
					*/
					public String getZhangjieName() {
						return zhangjieName;
					}
					/**
					* 设置： 章节名称
					*/
					public void setZhangjieName(String zhangjieName) {
						this.zhangjieName = zhangjieName;
					}
					/**
					* 获取： 章节照片
					*/
					public String getZhangjiePhoto() {
						return zhangjiePhoto;
					}
					/**
					* 设置： 章节照片
					*/
					public void setZhangjiePhoto(String zhangjiePhoto) {
						this.zhangjiePhoto = zhangjiePhoto;
					}
					/**
					* 获取： 视频
					*/
					public String getZhangjieVideo() {
						return zhangjieVideo;
					}
					/**
					* 设置： 视频
					*/
					public void setZhangjieVideo(String zhangjieVideo) {
						this.zhangjieVideo = zhangjieVideo;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getZhangjieDelete() {
						return zhangjieDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setZhangjieDelete(Integer zhangjieDelete) {
						this.zhangjieDelete = zhangjieDelete;
					}
					/**
					* 获取： 章节介绍
					*/
					public String getZhangjieContent() {
						return zhangjieContent;
					}
					/**
					* 设置： 章节介绍
					*/
					public void setZhangjieContent(String zhangjieContent) {
						this.zhangjieContent = zhangjieContent;
					}













}
