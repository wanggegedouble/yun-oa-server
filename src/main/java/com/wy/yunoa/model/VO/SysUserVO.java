package com.wy.yunoa.model.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  00:32
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Schema(description = "用户接口条件查询返回类")
public class SysUserVO {
    /**
     * 会员id
     */
    @Schema(description = "用户Id")
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 姓名
     */
    @Schema(description = "用户名")
    private String name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String headUrl;

    /**
     * 部门id
     */
    @Schema(description = "部门Id")
    private Long deptId;

    /**
     * 岗位id
     */
    @Schema(description = "岗位Id")
    private Long postId;

    /**
     * 微信openId
     */
    @Schema(description = "微信openId")
    private String openId;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（1：正常 0：停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
