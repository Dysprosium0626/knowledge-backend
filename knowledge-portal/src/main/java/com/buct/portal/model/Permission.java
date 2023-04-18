package com.buct.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    private Integer canLogin;

    private Integer canLike;

    private Integer canComment;


    public static final String USER_ID = "user_id";

    public static final String CAN_LOGIN = "can_login";

    public static final String CAN_LIKE = "can_like";

    public static final String CAN_COMMENT = "can_comment";

}
