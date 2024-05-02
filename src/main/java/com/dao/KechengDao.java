package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.KechengEntity;
import com.entity.view.KechengView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程 Dao 接口
 *
 * @author 
 */
public interface KechengDao extends BaseMapper<KechengEntity> {

   List<KechengView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
