
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
 * 课程订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/kechengOrder")
public class KechengOrderController {
    private static final Logger logger = LoggerFactory.getLogger(KechengOrderController.class);

    @Autowired
    private KechengOrderService kechengOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private KechengService kechengService;
    @Autowired
    private XueshengService xueshengService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = kechengOrderService.queryPage(params);

        //字典表数据转换
        List<KechengOrderView> list =(List<KechengOrderView>)page.getList();
        for(KechengOrderView c:list){
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
        KechengOrderEntity kechengOrder = kechengOrderService.selectById(id);
        if(kechengOrder !=null){
            //entity转view
            KechengOrderView view = new KechengOrderView();
            BeanUtils.copyProperties( kechengOrder , view );//把实体数据重构到view中

                //级联表
                KechengEntity kecheng = kechengService.selectById(kechengOrder.getKechengId());
                if(kecheng != null){
                    BeanUtils.copyProperties( kecheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKechengId(kecheng.getId());
                }
                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(kechengOrder.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
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
    public R save(@RequestBody KechengOrderEntity kechengOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,kechengOrder:{}",this.getClass().getName(),kechengOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            kechengOrder.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        kechengOrder.setInsertTime(new Date());
        kechengOrder.setCreateTime(new Date());
        kechengOrderService.insert(kechengOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KechengOrderEntity kechengOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,kechengOrder:{}",this.getClass().getName(),kechengOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            kechengOrder.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<KechengOrderEntity> queryWrapper = new EntityWrapper<KechengOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KechengOrderEntity kechengOrderEntity = kechengOrderService.selectOne(queryWrapper);
        if(kechengOrderEntity==null){
            kechengOrderService.updateById(kechengOrder);//根据id更新
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
        kechengOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<KechengOrderEntity> kechengOrderList = new ArrayList<>();//上传的东西
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
                            KechengOrderEntity kechengOrderEntity = new KechengOrderEntity();
//                            kechengOrderEntity.setKechengOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            kechengOrderEntity.setKechengId(Integer.valueOf(data.get(0)));   //课程 要改的
//                            kechengOrderEntity.setXueshengId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            kechengOrderEntity.setKechengOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            kechengOrderEntity.setKechengOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            kechengOrderEntity.setInsertTime(date);//时间
//                            kechengOrderEntity.setCreateTime(date);//时间
                            kechengOrderList.add(kechengOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("kechengOrderUuidNumber")){
                                    List<String> kechengOrderUuidNumber = seachFields.get("kechengOrderUuidNumber");
                                    kechengOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> kechengOrderUuidNumber = new ArrayList<>();
                                    kechengOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("kechengOrderUuidNumber",kechengOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<KechengOrderEntity> kechengOrderEntities_kechengOrderUuidNumber = kechengOrderService.selectList(new EntityWrapper<KechengOrderEntity>().in("kecheng_order_uuid_number", seachFields.get("kechengOrderUuidNumber")));
                        if(kechengOrderEntities_kechengOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(KechengOrderEntity s:kechengOrderEntities_kechengOrderUuidNumber){
                                repeatFields.add(s.getKechengOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        kechengOrderService.insertBatch(kechengOrderList);
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
        PageUtils page = kechengOrderService.queryPage(params);

        //字典表数据转换
        List<KechengOrderView> list =(List<KechengOrderView>)page.getList();
        for(KechengOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        KechengOrderEntity kechengOrder = kechengOrderService.selectById(id);
            if(kechengOrder !=null){


                //entity转view
                KechengOrderView view = new KechengOrderView();
                BeanUtils.copyProperties( kechengOrder , view );//把实体数据重构到view中

                //级联表
                    KechengEntity kecheng = kechengService.selectById(kechengOrder.getKechengId());
                if(kecheng != null){
                    BeanUtils.copyProperties( kecheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKechengId(kecheng.getId());
                }
                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(kechengOrder.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
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
    public R add(@RequestBody KechengOrderEntity kechengOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,kechengOrder:{}",this.getClass().getName(),kechengOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("学生".equals(role)){
            KechengEntity kechengEntity = kechengService.selectById(kechengOrder.getKechengId());
            if(kechengEntity == null){
                return R.error(511,"查不到该课程");
            }
            // Double kechengNewMoney = kechengEntity.getKechengNewMoney();

            if(false){
            }
            else if(kechengEntity.getKechengNewMoney() == null){
                return R.error(511,"课程价格不能为空");
            }





            //计算所获得积分
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            XueshengEntity xueshengEntity = xueshengService.selectById(userId);
            if(xueshengEntity == null)
                return R.error(511,"学生不能为空");
            if(xueshengEntity.getNewMoney() == null)
                return R.error(511,"学生金额不能为空");


            KechengOrderEntity kechengOrderEntity = kechengOrderService.selectOne(new EntityWrapper<KechengOrderEntity>().eq("xuesheng_id", userId).eq("kecheng_id", kechengOrder.getKechengId()));
            if(kechengOrderEntity != null)
                return  R.error("该学生已经购买过该课程");


            double balance = xueshengEntity.getNewMoney() - kechengEntity.getKechengNewMoney();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            kechengOrder.setXueshengId(userId); //设置订单支付人id
            kechengOrder.setKechengOrderPaymentTypes(1);
            kechengOrder.setKechengOrderTruePrice(kechengEntity.getKechengNewMoney());
            kechengOrder.setInsertTime(new Date());
            kechengOrder.setCreateTime(new Date());
            kechengOrderService.insert(kechengOrder);//新增订单
            xueshengEntity.setNewMoney(balance);//设置金额
            xueshengService.updateById(xueshengEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }

    /*@RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String kechengOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        Integer xueshengId = Integer.valueOf(String.valueOf(params.get("xueshengId")));//用户
        Integer kechengOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("kechengOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("kechengs"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> kechengs = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        XueshengEntity xueshengEntity = xueshengService.selectById(userId);

        //当前订单表
        List<KechengOrderEntity> kechengOrderList = new ArrayList<>();
        //商品表
        List<KechengEntity> kechengList = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : kechengs) {
           //取值
            Integer kechengId = Integer.valueOf(String.valueOf(map.get("kechengId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            KechengEntity kechengEntity = kechengService.selectById(kechengId);//购买的商品
            String id = String.valueOf(map.get("id"));

            //判断商品的库存是否足够
            if(kechengEntity.getKechengKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(kechengEntity.getKechengName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                kechengEntity.setKechengKucunNumber(kechengEntity.getKechengKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            KechengOrderEntity kechengOrderEntity = new KechengOrderEntity<>();

            //赋值订单信息
            kechengOrderEntity.setKechengOrderUuidNumber(kechengOrderUuidNumber);//订单号
            kechengOrderEntity.setKechengId(kechengId);//课程
            kechengOrderEntity.setXueshengId(userId);//用户
            kechengOrderEntity.setKechengOrderPaymentTypes(kechengOrderPaymentTypes);//支付类型
            kechengOrderEntity.setInsertTime(new Date());//订单创建时间
            kechengOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(kechengOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(kechengEntity.getKechengNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(xueshengEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                    xueshengEntity.setNewMoney(xueshengEntity.getNewMoney() - money); //设置金额


                    kechengOrderEntity.setKechengOrderTruePrice(money);

                }
            }
            kechengOrderList.add(kechengOrderEntity);
            kechengList.add(kechengEntity);

        }
        kechengOrderService.insertBatch(kechengOrderList);
        kechengService.updateBatchById(kechengList);
        xueshengService.updateById(xueshengEntity);
        return R.ok();
    }*/



}
