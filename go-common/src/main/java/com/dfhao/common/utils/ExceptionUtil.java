package com.dfhao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 异常堆栈打印工具类
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    /**
     * 获取异常的堆栈信息
     *
     * @param e 异常
     * @return 堆栈信息
     */
    public static String getExceptionInfo(Exception e) {
        try (Writer writer = new StringWriter();
             PrintWriter printWriter = new PrintWriter(writer)) {
            e.printStackTrace(printWriter);
            return writer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("ExceptionUtil-异常", e);
        }
        return "";
    }
}
