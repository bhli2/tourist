package com.qbk.easypoidemo.controller;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.qbk.easypoidemo.utils.EasyWordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试word生成
 */
@RestController
@RequestMapping("/demo")
public class TestController {

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("title","这是标题");
        params.put("name","李四");
        //这里是我说的一行代码
        EasyWordUtil.exportWord("word/test.docx","C:/Users/86186/Desktop","aaa.docx",params,request,response);
    }

    /**
     * 测试 linux 下 通过 读取模板 直接生成流下载
     */
    @GetMapping("/test")
    public String test(String name ,String title ,HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("title",name);
        map.put("name",title);
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "/opt/test/word/test.docx", map);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + "kk.docx");
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "s";
    }
}
