package com.just.ytdemo.web.module.request;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yutao.yan
 * @date 2018/12/4 17:20
 */
@Data
public class StudentRequest implements Serializable {

    private static final long serialVersionUID = 5481455390528299622L;

    @NotNull(message = "id不能为空")
    private Long id;

}
