package com.just.ytdemo.dao.core;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created on 2018-03-15 下午11:15.
 *
 * @author yutao.yan
 * @since 1.8
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
