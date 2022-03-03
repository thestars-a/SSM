package com.zhang.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadUtil {
    public static Map<String,Object> uploadPic(MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        if (file.getSize()>10485760){
            result.put("code", "1");
            result.put("msg", "上传失败,图片太大了");
            result.put("data", data);
            return result;
        }
        //设置图片保存的本地路径  前缀路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        // 获取原始图片的扩展名
        String originalFilename = file.getOriginalFilename();
        // 使用uuid生成文件新的名字
        String newFileName = UUID.randomUUID().toString().replaceAll("-","") + originalFilename;
        File saveFile = new File(filePath,newFileName);
        if(!saveFile.exists()){
            saveFile.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(new File(filePath,newFileName));
        //upload要求返回的数据格式
        result.put("code", "0");
        result.put("msg", "上传成功");
        //将文件路径返回
        data.put("src", "/static/upload/"+newFileName);
        result.put("data", data);
        return result;
    }
}
