package com.dao;

import com.entity.BumenzhuguanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BumenzhuguanView;

/**
 * 部门主管 Dao 接口
 *
 * @author 
 */
public interface BumenzhuguanDao extends BaseMapper<BumenzhuguanEntity> {

   List<BumenzhuguanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
