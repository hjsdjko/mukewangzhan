package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.XueshengEntity;
import com.entity.view.XueshengView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学生 Dao 接口
 *
 * @author 
 */
public interface XueshengDao extends BaseMapper<XueshengEntity> {

   List<XueshengView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
