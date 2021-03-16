package com.dfhao.common.utils;

import java.io.Serializable;

/**
 * http工具类
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class HttpResultModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;
    /**
     * 响应结果
     */
    private String content;

    public HttpResultModel() {
    }

    public HttpResultModel(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
