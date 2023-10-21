package com.wy.yunoa.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 岗位信息表
 * @TableName sys_post
 */
@TableName(value ="sys_post")
@Data
public class SysPost implements Serializable {
    @Serial
    private static final long serialVersionUID = -4578550883716487811L;
    /**
     * 岗位ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（1正常 0停用）
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;

}