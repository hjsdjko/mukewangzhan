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
 * 课程订单
 *
 * @author 
 * @email
 */
@TableName("kecheng_order")
public class KechengOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KechengOrderEntity() {

	}

	public KechengOrderEntity(T t) {
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
     * 订单号
     */
    @TableField(value = "kecheng_order_uuid_number")

    private String kechengOrderUuidNumber;


    /**
     * 课程
     */
    @TableField(value = "kecheng_id")

    private Integer kechengId;


    /**
     * 用户
     */
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 实付价格
     */
    @TableField(value = "kecheng_order_true_price")

    private Double kechengOrderTruePrice;


    /**
     * 支付类型
     */
    @TableField(value = "kecheng_order_payment_types")

    private Integer kechengOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：订单号
	 */
    public String getKechengOrderUuidNumber() {
        return kechengOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setKechengOrderUuidNumber(String kechengOrderUuidNumber) {
        this.kechengOrderUuidNumber = kechengOrderUuidNumber;
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
	 * 设置：用户
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：用户
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getKechengOrderTruePrice() {
        return kechengOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setKechengOrderTruePrice(Double kechengOrderTruePrice) {
        this.kechengOrderTruePrice = kechengOrderTruePrice;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getKechengOrderPaymentTypes() {
        return kechengOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setKechengOrderPaymentTypes(Integer kechengOrderPaymentTypes) {
        this.kechengOrderPaymentTypes = kechengOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "KechengOrder{" +
            "id=" + id +
            ", kechengOrderUuidNumber=" + kechengOrderUuidNumber +
            ", kechengId=" + kechengId +
            ", xueshengId=" + xueshengId +
            ", kechengOrderTruePrice=" + kechengOrderTruePrice +
            ", kechengOrderPaymentTypes=" + kechengOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
