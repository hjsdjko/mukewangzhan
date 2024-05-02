package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.XueshengDao;
import com.entity.XueshengEntity;
import com.entity.view.XueshengView;
import com.service.XueshengService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 学生 服务实现类
 */
@Service("xueshengService")
@Transactional
public class XueshengServiceImpl extends ServiceImpl<XueshengDao, XueshengEntity> implements XueshengService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<XueshengView> page =new Query<XueshengView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
