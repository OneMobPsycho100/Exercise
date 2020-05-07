package com.cmz.plugin.utils;

import com.alibaba.fastjson.JSON;
import com.cmz.plugin.module.PluginSite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 10:29
 */
public class PluginUtil {

    public static PluginSite getSite(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        InputStream input = url.openStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PluginUtil.copy(input, output);
        String jsonValue = output.toString("UTF-8");
        return JSON.parseObject(jsonValue, PluginSite.class);
    }

    private static long copy(InputStream source, OutputStream sink) throws IOException {
        long nread = 0L;
        byte[] buf = new byte[8192];
        int n;
        while ((n = source.read(buf)) > 0) {
            sink.write(buf, 0, n);
            nread += n;
        }
        return nread;
    }
}
