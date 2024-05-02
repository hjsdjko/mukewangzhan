package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.KechengOrderDao;
import com.entity.KechengOrderEntity;
import com.entity.view.KechengOrderView;
import com.service.KechengOrderService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 课程订单 服务实现类
 */
@Service("kechengOrderService")
@Transactional
public class KechengOrderServiceImpl extends ServiceImpl<KechengOrderDao, KechengOrderEntity> implements KechengOrderService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<KechengOrderView> page =new Query<KechengOrderView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
