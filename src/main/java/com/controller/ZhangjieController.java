
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 章节
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhangjie")
public class ZhangjieController {
    private static final Logger logger = LoggerFactory.getLogger(ZhangjieController.class);

    @Autowired
    private ZhangjieService zhangjieService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private KechengService kechengService;

    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private LaoshiService laoshiService;
    @Autowired
    private BumenzhuguanService bumenzhuguanService;
    @Autowired
    private XiaozhangService xiaozhangService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        else if("部门主管".equals(role))
            params.put("bumenzhuguanId",request.getSession().getAttribute("userId"));
        else if("校长".equals(role))
            params.put("xiaozhangId",request.getSession().getAttribute("userId"));
        params.put("zhangjieDeleteStart",1);params.put("zhangjieDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zhangjieService.queryPage(params);

        //字典表数据转换
        List<ZhangjieView> list =(List<ZhangjieView>)page.getList();
        for(ZhangjieView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhangjieEntity zhangjie = zhangjieService.selectById(id);
        if(zhangjie !=null){
            //entity转view
            ZhangjieView view = new ZhangjieView();
            BeanUtils.copyProperties( zhangjie , view );//把实体数据重构到view中

                //级联表
                KechengEntity kecheng = kechengService.selectById(zhangjie.getKechengId());
                if(kecheng != null){
                    BeanUtils.copyProperties( kecheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKechengId(kecheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZhangjieEntity zhangjie, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhangjie:{}",this.getClass().getName(),zhangjie.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZhangjieEntity> queryWrapper = new EntityWrapper<ZhangjieEntity>()
            .eq("kecheng_id", zhangjie.getKechengId())
            .eq("zhangjie_name", zhangjie.getZhangjieName())
            .eq("zhangjie_video", zhangjie.getZhangjieVideo())
            .eq("zhangjie_delete", zhangjie.getZhangjieDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhangjieEntity zhangjieEntity = zhangjieService.selectOne(queryWrapper);
        if(zhangjieEntity==null){
            zhangjie.setZhangjieDelete(1);
            zhangjie.setCreateTime(new Date());
            zhangjieService.insert(zhangjie);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhangjieEntity zhangjie, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhangjie:{}",this.getClass().getName(),zhangjie.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<ZhangjieEntity> queryWrapper = new EntityWrapper<ZhangjieEntity>()
            .notIn("id",zhangjie.getId())
            .andNew()
            .eq("kecheng_id", zhangjie.getKechengId())
            .eq("zhangjie_name", zhangjie.getZhangjieName())
            .eq("zhangjie_video", zhangjie.getZhangjieVideo())
            .eq("zhangjie_delete", zhangjie.getZhangjieDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhangjieEntity zhangjieEntity = zhangjieService.selectOne(queryWrapper);
        if("".equals(zhangjie.getZhangjiePhoto()) || "null".equals(zhangjie.getZhangjiePhoto())){
                zhangjie.setZhangjiePhoto(null);
        }
        if("".equals(zhangjie.getZhangjieVideo()) || "null".equals(zhangjie.getZhangjieVideo())){
                zhangjie.setZhangjieVideo(null);
        }
        if(zhangjieEntity==null){
            zhangjieService.updateById(zhangjie);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<ZhangjieEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhangjieEntity zhangjieEntity = new ZhangjieEntity();
            zhangjieEntity.setId(id);
            zhangjieEntity.setZhangjieDelete(2);
            list.add(zhangjieEntity);
        }
        if(list != null && list.size() >0){
            zhangjieService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ZhangjieEntity> zhangjieList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZhangjieEntity zhangjieEntity = new ZhangjieEntity();
//                            zhangjieEntity.setKechengId(Integer.valueOf(data.get(0)));   //课程 要改的
//                            zhangjieEntity.setZhangjieName(data.get(0));                    //章节名称 要改的
//                            zhangjieEntity.setZhangjiePhoto("");//照片
//                            zhangjieEntity.setZhangjieVideo(data.get(0));                    //视频 要改的
//                            zhangjieEntity.setZhangjieDelete(1);//逻辑删除字段
//                            zhangjieEntity.setZhangjieContent("");//照片
//                            zhangjieEntity.setCreateTime(date);//时间
                            zhangjieList.add(zhangjieEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zhangjieService.insertBatch(zhangjieList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = zhangjieService.queryPage(params);

        //字典表数据转换
        List<ZhangjieView> list =(List<ZhangjieView>)page.getList();
        for(ZhangjieView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhangjieEntity zhangjie = zhangjieService.selectById(id);
            if(zhangjie !=null){


                //entity转view
                ZhangjieView view = new ZhangjieView();
                BeanUtils.copyProperties( zhangjie , view );//把实体数据重构到view中

                //级联表
                    KechengEntity kecheng = kechengService.selectById(zhangjie.getKechengId());
                if(kecheng != null){
                    BeanUtils.copyProperties( kecheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKechengId(kecheng.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZhangjieEntity zhangjie, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhangjie:{}",this.getClass().getName(),zhangjie.toString());
        Wrapper<ZhangjieEntity> queryWrapper = new EntityWrapper<ZhangjieEntity>()
            .eq("kecheng_id", zhangjie.getKechengId())
            .eq("zhangjie_name", zhangjie.getZhangjieName())
            .eq("zhangjie_video", zhangjie.getZhangjieVideo())
            .eq("zhangjie_delete", zhangjie.getZhangjieDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhangjieEntity zhangjieEntity = zhangjieService.selectOne(queryWrapper);
        if(zhangjieEntity==null){
            zhangjie.setZhangjieDelete(1);
            zhangjie.setCreateTime(new Date());
        zhangjieService.insert(zhangjie);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
