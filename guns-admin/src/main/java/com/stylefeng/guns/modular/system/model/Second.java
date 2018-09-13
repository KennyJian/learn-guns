package com.stylefeng.guns.modular.system.model;

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
 * @since 2018-09-09
 */
@TableName("tmp_second")
public class Second extends Model<Second> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value="id", type= IdType.ID_WORKER)
    private Long id;
    /**
     * 商品
     */
    private String goods;
    /**
     * 质量
     */
    private String quality;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Second{" +
        "id=" + id +
        ", goods=" + goods +
        ", quality=" + quality +
        "}";
    }
}
