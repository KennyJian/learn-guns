package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kenny
 * @since 2018-09-13
 */
@TableName("tmp_validator")
public class Validator extends Model<Validator> {

    private static final long serialVersionUID = 1L;

    /**
     * 模板id
     */
    @TableId(value="id", type= IdType.ID_WORKER)
    private Long id;
    /**
     * 订单名字
     */
    @TableField("order_name")
    private String orderName;
    /**
     * 订单发货地
     */
    @TableField("order_from")
    private String orderFrom;
    /**
     * 订单剩余量
     */
    @TableField("order_left")
    private Integer orderLeft;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Integer getOrderLeft() {
        return orderLeft;
    }

    public void setOrderLeft(Integer orderLeft) {
        this.orderLeft = orderLeft;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Validator{" +
        "id=" + id +
        ", orderName=" + orderName +
        ", orderFrom=" + orderFrom +
        ", orderLeft=" + orderLeft +
        "}";
    }
}
