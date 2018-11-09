package com.angcyo.kuaihu.http;

import android.support.annotation.NonNull;
import com.angcyo.uiview.less.utils.utilcode.utils.EncodeUtils;
import com.bumptech.glide.load.Key;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PostFormBuilder {
    private List<FileInput> files = new ArrayList();
    protected Map<String, String> params;

    public static class FileInput {
        public File file;
        public String filename;
        public String key;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        @NonNull
        public String toString() {
            return "FileInput{key='" + this.key + '\'' + ", filename='" + this.filename + '\'' + ", file=" + this.file + '}';
        }
    }

    public PostFormBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, (File) files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        this.files.add(new FileInput(name, filename, file));
        return this;
    }

    public PostFormBuilder addParams(String key, String val) {
        if (this.params == null) {
            this.params = new TreeMap();
        }
        this.params.put(key, val);
        return this;
    }

    public Map<String, String> buildParams() {
        if (this.params == null) {
            this.params = new TreeMap();
        }
        this.params.put("_device_id", "08791739814441" /*AppUtils.getAndroidID(AppUtils.getAppContext())*/);
        this.params.put("_app_version", "1.0.2"/*AppUtils.getAppVersionName(AppUtils.getAppContext())*/);
        this.params.put("_device_type", AppUtils.getModel());
        this.params.put("_sdk_version", AppUtils.getSDKVersion());
        this.params.put("_device_version", AppUtils.getOSVersion());
        StringBuilder sb = new StringBuilder("");
        for (Entry<String, String> entry : this.params.entrySet()) {
            sb.append("&").append((String) entry.getKey()).append("=").append(EncodeUtils.urlEncode((String) entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        this.params.put("sig", EncryptUtils.encryptMD5ToString(sb.toString().substring(1) + Constant.URL_SIG_KEY));
        return params;
    }
}