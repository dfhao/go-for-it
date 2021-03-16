package com.dfhao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP 转换工具类
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class IpConvertUtil {
    private static final Logger logger = LoggerFactory.getLogger(IpConvertUtil.class);

    private static Pattern pattern = Pattern.compile("\\d+");

    /**
     * 判断是否为ipv4地址
     *
     * @param ipv4Addr 字符串IP地址
     * @return 否为ipv4地址
     */
    private static boolean isIpv4Address(String ipv4Addr) {
        // 0-255的数字
        String lower = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regex = lower + "(\\." + lower + "){3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipv4Addr);
        return matcher.matches();
    }

    /**
     * IPv4地址转换为int类型数字
     *
     * @param ip 字符串IP地址，类似：192.168.0.1
     * @return int形式IP地址，类似：-1062731775
     */
    public static int ipToInt(String ip) {
        if (!isIpv4Address(ip)) {
            throw new RuntimeException("ip地址错误！");
        }
        Matcher matcher = pattern.matcher(ip);
        int result = 0;
        int counter = 0;
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group());
            result = (value << 8 * (3 - counter++)) | result;
        }
        return result;
    }

    /**
     * 将int数字转换成ipv4地址
     *
     * @param ipNum int形式IP地址，类似：-1062731775
     * @return 字符串IP地址，类似：192.168.0.1
     */
    public static String intToIp(int ipNum) {
        StringBuilder sb = new StringBuilder();
        int num;
        boolean needPoint = false;
        for (int i = 0; i < 4; i++) {
            if (needPoint) {
                sb.append(".");
            }
            needPoint = true;
            int offset = 8 * (3 - i);
            // 0x 十六进制，0xff 表示为二进制 11111111 , & 0xff 为了取到低8位二进制数据
            num = (ipNum >> offset) & 0xff;
            sb.append(num);
        }
        return sb.toString();
    }

}
