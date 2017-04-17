package com.baayso.springboot.access.domain;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.baayso.springboot.common.domain.BaseDO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据对象：API。
 *
 * @author ChenFangjie (2017/2/28 16:32)
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "system_access_api")
public class AccessApiDO extends BaseDO {

    private static final long serialVersionUID = 7951978574966424420L;

    private String  name;       // 名称
    private String  code;       // 编码
    private String  url;        // API访问地址
    private String  descr;      // 描述
    private Long    parentId;   // 父操作ID
    private Boolean isParent;   // 是否父级

    @Transient
    private AccessApiDO       parent;   // 上级操作
    @Transient
    private List<AccessApiDO> children; // 子操作

}
