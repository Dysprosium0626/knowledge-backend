package com.buct.portal.model.VO;

import com.buct.portal.model.FileMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVo implements Serializable {

    private String id;

    private String url;

    private Integer isAudit;

    private Integer result;


}
