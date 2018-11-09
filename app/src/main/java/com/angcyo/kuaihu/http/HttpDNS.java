package com.angcyo.kuaihu.http;

import android.util.Log;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HttpDNS {
    public static ArrayList<String> DOMAINLIST = new ArrayList(Arrays.asList(new String[]{"api.kuaihuapi.com", "api.khuapi.com"}));

    private static long m_defaultTTL = 600000;
    private static HashMap<String, CacheEntity> m_dnscache = new HashMap();
    public static String m_httpdns_url = "http://119.29.29.29/d?dn=";

    static class CacheEntity {
        private String[] ips;
        private long timestamp;

        public CacheEntity(long timestmp, String[] ips) {
            this.timestamp = timestmp;
            this.ips = ips;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public String[] getIps() {
            return this.ips;
        }
    }

    public static String getAddressByName(String host) {
        String[] ips = null;
        CacheEntity entity = (CacheEntity) m_dnscache.get(host);
        if (entity == null) {
            Log.i("cachedns", "soft reference miss");
        } else if (System.currentTimeMillis() - entity.timestamp < m_defaultTTL) {
            ips = entity.getIps();
            Log.i("cachedns", "get ips from cache:" + host);
        }
        if (ips == null || ips.length == 0) {
            ips = getips(host);
            m_dnscache.put(host, new CacheEntity(System.currentTimeMillis(), ips));
            Log.i("cachedns", "add ips to cache:" + host);
        }
        Log.i("cachedns", "get ips:" + host + " ");
        if (ips == null || ips.length <= 0) {
            return "";
        }
        return ips[(int) (Math.random() * ((double) ips.length))];
    }

    private static String[] getips(String host) {
        String[] ret;
        try {
            String[] ret2;
            Scanner scanner = new Scanner(new OkHttpClient().newCall(new Builder().url(m_httpdns_url + host.trim()).build()).execute().body().byteStream()).useDelimiter("\\A");
            String rsp = scanner.hasNext() ? scanner.next() : "";
            if (rsp.indexOf(";") > -1) {
                ret2 = rsp.split(";");
            } else {
                ret2 = new String[]{rsp.trim()};
            }
            ret = ret2;
        } catch (IOException e) {
            e.printStackTrace();
            ret = null;
        }
        if (ret == null) {
            try {
                System.out.println("NNNNN:" + ((InetAddress) Dns.SYSTEM.lookup(host).get(0)).getHostAddress());
                return new String[]{((InetAddress) Dns.SYSTEM.lookup(host).get(0)).getHostAddress().toString()};
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }
}