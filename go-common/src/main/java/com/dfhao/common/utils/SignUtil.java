package com.dfhao.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 签名生成工具类
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class SignUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 生成签名
     *
     * @param params    请求参数
     * @param secretKey 密钥
     * @return 密文
     */
    public static String getSign(Map<String, String> params, String secretKey) {
        if (params == null || params.size() == 0) {
            return "";
        }
        // 1、去掉sign
        List<String> keys = new ArrayList<>(params.size());
        for (String key : params.keySet()) {
            if ("sign".equals(key)) {
                continue;
            }
            if (StringUtils.isEmpty(params.get(key))) {
                continue;
            }
            keys.add(key);
        }
        // 2、key值排序
        Collections.sort(keys);
        // 3、拼接参数
        StringBuilder strTemp = new StringBuilder();
        for (String key : keys) {
            strTemp.append(key).append(params.get(key));
        }
        String str = secretKey + strTemp + secretKey;
        // 4、生成签名()
        String sign = DigestUtils.sha256Hex(str.getBytes(StandardCharsets.UTF_8)).toUpperCase();
        params.put("sign", sign);
        return sign;
    }

}
