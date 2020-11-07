package com.just.ytdemo.util;

import com.just.ytdemo.exception.BaseErrorCode;
import com.just.ytdemo.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.Enumeration;

/**
 * @author xiaolei.fu 2018/1/30 16:38
 * @version 0.1-SNAPSHOT
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProcessUtil {

    private static final String HOST_ADDRESS_LOCAL = "127.0.0.1";

    /**
     * 获取系统进程号
     */
    public static String getSystemProcessId() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        return name.split("@")[0];
    }

    /**
     * 获取系统ip地址
     */
    public static String getIp() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals(HOST_ADDRESS_LOCAL)) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            throw new BaseException(BaseErrorCode.FAILED.getCode(),e.getMessage());
        }
        throw new BaseException(BaseErrorCode.FAILED.getCode(),"获取本机ip失败");
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"获取HostName失败");
        }
    }

}
