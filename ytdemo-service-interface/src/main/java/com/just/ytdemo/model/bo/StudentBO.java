package com.just.ytdemo.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yutao.yan
 * @date 2018/12/3 16:37
 */
@Data
public class StudentBO implements Serializable {

    private static final long serialVersionUID = 5481455390528299622L;

    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

}
