package com.qbk.source.config.datasource;

/**
 * @Author: quboka
 * @Date: 2018/12/3 15:10
 * @Description: 数据源切换
 */
public class DBContextHolder {

    /**
     * 对当前线程的操作-线程安全的
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 调用此方法，切换数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 删除数据源
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
