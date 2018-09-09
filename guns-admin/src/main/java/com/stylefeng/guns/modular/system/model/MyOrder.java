package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author kenny
 * @since 2018-09-08
 */
@TableName("my_order")
public class MyOrder extends Model<MyOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value="id", type= IdType.ID_WORKER)
    private Long id;
    /**
     * 下单人名称
     */
    private String user;
    /**
     * 地点
     */
    private String place;
    /**
     * 商品名称
     */
    private String goods;
    /**
     * 下单时间
     */
    private Date createtime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
        "id=" + id +
        ", user=" + user +
        ", place=" + place +
        ", goods=" + goods +
        ", createtime=" + createtime +
        "}";
    }
}
