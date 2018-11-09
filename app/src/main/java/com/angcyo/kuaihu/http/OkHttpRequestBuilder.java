//package com.angcyo.kuaihu.http;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {
//    protected Map<String, String> headers;
//    /* renamed from: id */
//    protected int f49id;
//    protected TreeMap<String, String> params;
//    protected Object tag;
//    protected String url;
//
//    public abstract RequestCall build();
//
//    /* renamed from: id */
//    public T mo9589id(int id) {
//        this.f49id = id;
//        return this;
//    }
//
//    public T url(String url) {
//        this.url = url;
//        return this;
//    }
//
//    public T tag(Object tag) {
//        this.tag = tag;
//        return this;
//    }
//
//    public T headers(Map<String, String> headers) {
//        this.headers = headers;
//        return this;
//    }
//
//    public T addHeader(String key, String val) {
//        if (this.headers == null) {
//            this.headers = new LinkedHashMap();
//        }
//        this.headers.put(key, val);
//        return this;
//    }
//}