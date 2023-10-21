package com.wy.yunoa.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 * @TableName wechat_menu
 */
@TableName(value ="wechat_menu")
@Data
public class WechatMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 8456285379662169693L;
    /**
     * 编号
     */
    private Long id;

    /**
     * 上级id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 网页 链接，用户点击菜单可打开链接
     */
    private String url;

    /**
     * 菜单KEY值，用于消息接口推送
     */
    private String meunKey;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    @TableLogic()
    @TableField("is_deleted")
    private Integer delFlag;

}