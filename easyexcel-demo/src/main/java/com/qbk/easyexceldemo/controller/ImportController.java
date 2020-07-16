package com.qbk.easyexceldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.qbk.easyexceldemo.entity.ExcelData;
import com.qbk.easyexceldemo.listener.ExcelDataListener;
import com.qbk.easyexceldemo.service.ExeclDataService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 导入
 */
@RestController
public class ImportController {

    private final static List<String> EXCEL_EXTENSION = Arrays.asList("xls","xlsx");

    @Autowired
    private ExeclDataService execlDataService;

    /**
     * 导入平台摄像头
     */
    @PostMapping(value = "/import", consumes="multipart/form-data")
    public String importData(MultipartFile file) throws Exception {
        if(Objects.isNull(file)){
           return "file不能为空" ;
        }
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!EXCEL_EXTENSION.contains(extension)){
            return "file只能是excel文件" ;
        }

        try {
            EasyExcel.read(
                    file.getInputStream(),
                    ExcelData.class,
                    new ExcelDataListener(execlDataService)
            ).sheet().doRead();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "ok";
    }
}
