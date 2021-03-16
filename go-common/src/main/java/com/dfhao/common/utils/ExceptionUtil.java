package com.dfhao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
        Writer writer = null;
        PrintWriter printWriter = null;
        try {
            writer = new StringWriter();
            printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            return writer.toString();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException ioe) {
                logger.error("ExceptionUtil-关闭流-异常", ioe);
            }
        }
    }
}
