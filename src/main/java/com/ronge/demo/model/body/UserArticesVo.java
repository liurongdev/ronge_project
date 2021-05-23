package com.ronge.demo.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author liurong
 * @date 2021/5/15 21:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserArticesVo {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String userId;

    /**
     * 从第几条开始
     */
    private int start;

    /**
     *查询条数
     */
    private int count;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 截至时间
     */
    private Date endTime;

    /**
     * 排序键
     */
    private String orderKey;

}
