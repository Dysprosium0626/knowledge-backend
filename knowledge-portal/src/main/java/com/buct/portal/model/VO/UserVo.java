package com.buct.portal.model.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否为管理员")
    private Integer isAdmin;

    @ApiModelProperty("个人描述")
    private String description;

    @ApiModelProperty("性别")
    private Integer gender;


    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";

    public static final String IS_ADMIN = "is_admin";

    public static final String DESCRIPTION = "description";

    public static final String GENDER = "gender";

    public UserVo(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
