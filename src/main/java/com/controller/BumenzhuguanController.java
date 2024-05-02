
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
 * 部门主管
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bumenzhuguan")
public class BumenzhuguanController {
    private static final Logger logger = LoggerFactory.getLogger(BumenzhuguanController.class);

    @Autowired
    private BumenzhuguanService bumenzhuguanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private LaoshiService laoshiService;
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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = bumenzhuguanService.queryPage(params);

        //字典表数据转换
        List<BumenzhuguanView> list =(List<BumenzhuguanView>)page.getList();
        for(BumenzhuguanView c:list){
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
        BumenzhuguanEntity bumenzhuguan = bumenzhuguanService.selectById(id);
        if(bumenzhuguan !=null){
            //entity转view
            BumenzhuguanView view = new BumenzhuguanView();
            BeanUtils.copyProperties( bumenzhuguan , view );//把实体数据重构到view中

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
    public R save(@RequestBody BumenzhuguanEntity bumenzhuguan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bumenzhuguan:{}",this.getClass().getName(),bumenzhuguan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<BumenzhuguanEntity> queryWrapper = new EntityWrapper<BumenzhuguanEntity>()
            .eq("username", bumenzhuguan.getUsername())
            .or()
            .eq("bumenzhuguan_phone", bumenzhuguan.getBumenzhuguanPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BumenzhuguanEntity bumenzhuguanEntity = bumenzhuguanService.selectOne(queryWrapper);
        if(bumenzhuguanEntity==null){
            bumenzhuguan.setCreateTime(new Date());
            bumenzhuguan.setPassword("123456");
            bumenzhuguanService.insert(bumenzhuguan);
            return R.ok();
        }else {
            return R.error(511,"账户或者部门主管手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BumenzhuguanEntity bumenzhuguan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bumenzhuguan:{}",this.getClass().getName(),bumenzhuguan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<BumenzhuguanEntity> queryWrapper = new EntityWrapper<BumenzhuguanEntity>()
            .notIn("id",bumenzhuguan.getId())
            .andNew()
            .eq("username", bumenzhuguan.getUsername())
            .or()
            .eq("bumenzhuguan_phone", bumenzhuguan.getBumenzhuguanPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BumenzhuguanEntity bumenzhuguanEntity = bumenzhuguanService.selectOne(queryWrapper);
        if("".equals(bumenzhuguan.getBumenzhuguanPhoto()) || "null".equals(bumenzhuguan.getBumenzhuguanPhoto())){
                bumenzhuguan.setBumenzhuguanPhoto(null);
        }
        if(bumenzhuguanEntity==null){
            bumenzhuguanService.updateById(bumenzhuguan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者部门主管手机号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        bumenzhuguanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BumenzhuguanEntity> bumenzhuguanList = new ArrayList<>();//上传的东西
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
                            BumenzhuguanEntity bumenzhuguanEntity = new BumenzhuguanEntity();
//                            bumenzhuguanEntity.setUsername(data.get(0));                    //账户 要改的
//                            //bumenzhuguanEntity.setPassword("123456");//密码
//                            bumenzhuguanEntity.setBumenzhuguanName(data.get(0));                    //部门主管姓名 要改的
//                            bumenzhuguanEntity.setBumenzhuguanPhone(data.get(0));                    //部门主管手机号 要改的
//                            bumenzhuguanEntity.setBumenzhuguanPhoto("");//照片
//                            bumenzhuguanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            bumenzhuguanEntity.setBumenzhuguanEmail(data.get(0));                    //电子邮箱 要改的
//                            bumenzhuguanEntity.setCreateTime(date);//时间
                            bumenzhuguanList.add(bumenzhuguanEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //部门主管手机号
                                if(seachFields.containsKey("bumenzhuguanPhone")){
                                    List<String> bumenzhuguanPhone = seachFields.get("bumenzhuguanPhone");
                                    bumenzhuguanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> bumenzhuguanPhone = new ArrayList<>();
                                    bumenzhuguanPhone.add(data.get(0));//要改的
                                    seachFields.put("bumenzhuguanPhone",bumenzhuguanPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<BumenzhuguanEntity> bumenzhuguanEntities_username = bumenzhuguanService.selectList(new EntityWrapper<BumenzhuguanEntity>().in("username", seachFields.get("username")));
                        if(bumenzhuguanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BumenzhuguanEntity s:bumenzhuguanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //部门主管手机号
                        List<BumenzhuguanEntity> bumenzhuguanEntities_bumenzhuguanPhone = bumenzhuguanService.selectList(new EntityWrapper<BumenzhuguanEntity>().in("bumenzhuguan_phone", seachFields.get("bumenzhuguanPhone")));
                        if(bumenzhuguanEntities_bumenzhuguanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BumenzhuguanEntity s:bumenzhuguanEntities_bumenzhuguanPhone){
                                repeatFields.add(s.getBumenzhuguanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [部门主管手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        bumenzhuguanService.insertBatch(bumenzhuguanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        BumenzhuguanEntity bumenzhuguan = bumenzhuguanService.selectOne(new EntityWrapper<BumenzhuguanEntity>().eq("username", username));
        if(bumenzhuguan==null || !bumenzhuguan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(bumenzhuguan.getId(),username, "bumenzhuguan", "部门主管");
        R r = R.ok();
        r.put("token", token);
        r.put("role","部门主管");
        r.put("username",bumenzhuguan.getBumenzhuguanName());
        r.put("tableName","bumenzhuguan");
        r.put("userId",bumenzhuguan.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody BumenzhuguanEntity bumenzhuguan){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<BumenzhuguanEntity> queryWrapper = new EntityWrapper<BumenzhuguanEntity>()
            .eq("username", bumenzhuguan.getUsername())
            .or()
            .eq("bumenzhuguan_phone", bumenzhuguan.getBumenzhuguanPhone())
            ;
        BumenzhuguanEntity bumenzhuguanEntity = bumenzhuguanService.selectOne(queryWrapper);
        if(bumenzhuguanEntity != null)
            return R.error("账户或者部门主管手机号已经被使用");
        bumenzhuguan.setCreateTime(new Date());
        bumenzhuguanService.insert(bumenzhuguan);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        BumenzhuguanEntity bumenzhuguan = new BumenzhuguanEntity();
        bumenzhuguan.setPassword("123456");
        bumenzhuguan.setId(id);
        bumenzhuguanService.updateById(bumenzhuguan);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        BumenzhuguanEntity bumenzhuguan = bumenzhuguanService.selectOne(new EntityWrapper<BumenzhuguanEntity>().eq("username", username));
        if(bumenzhuguan!=null){
            bumenzhuguan.setPassword("123456");
            boolean b = bumenzhuguanService.updateById(bumenzhuguan);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrBumenzhuguan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        BumenzhuguanEntity bumenzhuguan = bumenzhuguanService.selectById(id);
        if(bumenzhuguan !=null){
            //entity转view
            BumenzhuguanView view = new BumenzhuguanView();
            BeanUtils.copyProperties( bumenzhuguan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = bumenzhuguanService.queryPage(params);

        //字典表数据转换
        List<BumenzhuguanView> list =(List<BumenzhuguanView>)page.getList();
        for(BumenzhuguanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BumenzhuguanEntity bumenzhuguan = bumenzhuguanService.selectById(id);
            if(bumenzhuguan !=null){


                //entity转view
                BumenzhuguanView view = new BumenzhuguanView();
                BeanUtils.copyProperties( bumenzhuguan , view );//把实体数据重构到view中

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
    public R add(@RequestBody BumenzhuguanEntity bumenzhuguan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bumenzhuguan:{}",this.getClass().getName(),bumenzhuguan.toString());
        Wrapper<BumenzhuguanEntity> queryWrapper = new EntityWrapper<BumenzhuguanEntity>()
            .eq("username", bumenzhuguan.getUsername())
            .or()
            .eq("bumenzhuguan_phone", bumenzhuguan.getBumenzhuguanPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BumenzhuguanEntity bumenzhuguanEntity = bumenzhuguanService.selectOne(queryWrapper);
        if(bumenzhuguanEntity==null){
            bumenzhuguan.setCreateTime(new Date());
        bumenzhuguan.setPassword("123456");
        bumenzhuguanService.insert(bumenzhuguan);
            return R.ok();
        }else {
            return R.error(511,"账户或者部门主管手机号已经被使用");
        }
    }


}
