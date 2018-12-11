package com.aaa.ssm.controller;

import com.aaa.ssm.service.RenzhengService;
import com.aaa.ssm.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *className:RenzhengController
 *discription:个人信息认证
 *author:jiasanshui
 *createTime:2018-12-10 21:12
 */
@Controller
@RequestMapping("renzheng")
public class RenzhengController {

    @Autowired
    private RenzhengService renzhengService;

    //取出配置文件中upload.path的值，并赋值给uploadPath变量
    @Value(value = "${upload.path}")
    private String uploadPath;

    /**
     * 个人认证信息修改
     * @param map
     * @return
     */
    @RequestMapping("update")
    public Object update(@RequestParam Map map, @RequestParam MultipartFile idcarda, @RequestParam MultipartFile idcardb) throws ParseException {
        //上传图片
            String newFileNameA = FileUtil.uploadFile(uploadPath, idcarda);
            String newFileNameB = FileUtil.uploadFile(uploadPath, idcardb);
            map.put("picA",newFileNameA);
            map.put("picB",newFileNameB);
        //转换日期格式
        Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(map.get("birth").toString());
        String birth =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        map.put("birth",birth);
        //连接地址
        String address = map.get("pro").toString().concat(map.get("city").toString().concat(map.get("area").toString().concat(map.get("house").toString())));
        map.put("address",address);
        int update = renzhengService.update(map);
        if (update>0) {
            System.out.println("认证成功");
        }
        return "redirect:/jump/index";
    }
}