package com.angcyo.kuaihu.http.bean;

import com.angcyo.http.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2018/11/02
 */
public class HttpBean {

    /**
     * code : 0
     * message :
     * data :
     */

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return code == 0;
    }

    public List getDataList() {
        if (data instanceof List) {
            return (List) data;
        }
        return new ArrayList();
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
