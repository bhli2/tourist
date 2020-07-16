package com.qbk.easyexceldemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.qbk.easyexceldemo.entity.ExcelData;
import com.qbk.easyexceldemo.service.ExeclDataService;
import com.qbk.easyexceldemo.util.BeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel Data Listener
 * Listener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 */
public class ExcelDataListener extends AnalysisEventListener<ExcelData> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelDataListener.class);

    /**
     * 每隔n条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    private final List<ExcelData> list = new ArrayList<>();

    private final ExeclDataService execlDataService;

    public ExcelDataListener(ExeclDataService execlDataService ) {
        this.execlDataService = execlDataService;

    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        //校验
        BeanValidator.check(data);
        LOGGER.info("解析到一条数据:{}",data);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        execlDataService.saveData(list);
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        LOGGER.error("解析失败:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            String error = String.format("第%d行，第%d列解析异常,类型转换错误", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
            LOGGER.error(error);
            throw new RuntimeException(error);
        }
        throw exception;
    }

}
