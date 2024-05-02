package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.KechengOrderEntity;
import com.entity.view.KechengOrderView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程订单 Dao 接口
 *
 * @author 
 */
public interface KechengOrderDao extends BaseMapper<KechengOrderEntity> {

   List<KechengOrderView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
