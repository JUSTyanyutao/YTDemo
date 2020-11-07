package com.just.ytdemo.dao;

import com.just.ytdemo.dao.core.CommonMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created on 2018-03-19 下午5:49.
 *
 * @author <a href="mailto:xiaolei.fu@weimob.com">xiaolei.fu</a>
 * @since 1.8
 */
@AllArgsConstructor
public abstract class CommonDAO<T extends CommonMapper<K>, K> {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private T mapper;

    public K get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<K> getPage(Example example, int pageNo) {
        return getPage(example, pageNo, DEFAULT_PAGE_SIZE);
    }

    public List<K> getPage(Example example, int pageNo, int pageSize) {
        int start = (pageNo - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(start, pageSize);
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    public long count(Example example) {
        return mapper.selectCountByExample(example);
    }

    public int insert(K record) {
        return mapper.insertSelective(record);
    }

    public int insert(List<K> records) {
        return mapper.insertList(records);
    }

    public int update(K record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

}
