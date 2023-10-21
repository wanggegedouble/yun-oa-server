package com.wy.yunoa.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 审批记录
 * @TableName oa_process_record
 */
@TableName(value ="oa_process_record")
@Data
public class OaProcessRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -1686245747105154756L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 审批流程id
     */
    private Long processId;

    /**
     * 审批描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 操作用户id
     */
    private Long operateUserId;

    /**
     * 操作用户
     */
    private String operateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;

}