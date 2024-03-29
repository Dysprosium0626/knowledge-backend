package com.buct.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("audit")
@AllArgsConstructor
@NoArgsConstructor
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "file_id", type = IdType.AUTO)
    private String fileId;
    private Integer isAudit;
    private Integer result;

}
