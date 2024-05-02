package com.entity.view;

import com.entity.ZhangjieEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 章节
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zhangjie")
public class ZhangjieView extends ZhangjieEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 kecheng
			/**
			* 课程 的 课程
			*/
			private Integer kechengLaoshiId;
			/**
			* 课程名称
			*/
			private String kechengName;
			/**
			* 课程照片
			*/
			private String kechengPhoto;
			/**
			* 课程类型
			*/
			private Integer kechengTypes;
				/**
				* 课程类型的值
				*/
				private String kechengValue;
			/**
			* 点击次数
			*/
			private Integer kechengClicknum;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer kechengDelete;
			/**
			* 课程介绍
			*/
			private String kechengContent;

	public ZhangjieView() {

	}

	public ZhangjieView(ZhangjieEntity zhangjieEntity) {
		try {
			BeanUtils.copyProperties(this, zhangjieEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}















				//级联表的get和set kecheng
					/**
					* 获取：课程 的 课程
					*/
					public Integer getKechengLaoshiId() {
						return kechengLaoshiId;
					}
					/**
					* 设置：课程 的 课程
					*/
					public void setKechengLaoshiId(Integer kechengLaoshiId) {
						this.kechengLaoshiId = kechengLaoshiId;
					}

					/**
					* 获取： 课程名称
					*/
					public String getKechengName() {
						return kechengName;
					}
					/**
					* 设置： 课程名称
					*/
					public void setKechengName(String kechengName) {
						this.kechengName = kechengName;
					}
					/**
					* 获取： 课程照片
					*/
					public String getKechengPhoto() {
						return kechengPhoto;
					}
					/**
					* 设置： 课程照片
					*/
					public void setKechengPhoto(String kechengPhoto) {
						this.kechengPhoto = kechengPhoto;
					}
					/**
					* 获取： 课程类型
					*/
					public Integer getKechengTypes() {
						return kechengTypes;
					}
					/**
					* 设置： 课程类型
					*/
					public void setKechengTypes(Integer kechengTypes) {
						this.kechengTypes = kechengTypes;
					}


						/**
						* 获取： 课程类型的值
						*/
						public String getKechengValue() {
							return kechengValue;
						}
						/**
						* 设置： 课程类型的值
						*/
						public void setKechengValue(String kechengValue) {
							this.kechengValue = kechengValue;
						}
					/**
					* 获取： 点击次数
					*/
					public Integer getKechengClicknum() {
						return kechengClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setKechengClicknum(Integer kechengClicknum) {
						this.kechengClicknum = kechengClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getKechengDelete() {
						return kechengDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setKechengDelete(Integer kechengDelete) {
						this.kechengDelete = kechengDelete;
					}
					/**
					* 获取： 课程介绍
					*/
					public String getKechengContent() {
						return kechengContent;
					}
					/**
					* 设置： 课程介绍
					*/
					public void setKechengContent(String kechengContent) {
						this.kechengContent = kechengContent;
					}




















}
