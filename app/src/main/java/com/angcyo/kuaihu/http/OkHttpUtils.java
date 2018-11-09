//package com.angcyo.kuaihu;
//
//import com.mylibrary.okhttputils.builder.GetBuilder;
//import com.mylibrary.okhttputils.builder.HeadBuilder;
//import com.mylibrary.okhttputils.builder.OtherRequestBuilder;
//import com.mylibrary.okhttputils.builder.PostFileBuilder;
//import com.mylibrary.okhttputils.builder.PostFormBuilder;
//import com.mylibrary.okhttputils.builder.PostStringBuilder;
//import com.mylibrary.okhttputils.callback.Callback;
//import com.mylibrary.okhttputils.request.RequestCall;
//import com.mylibrary.okhttputils.utils.Platform;
//import java.io.IOException;
//import java.util.concurrent.Executor;
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//
//public class OkHttpUtils {
//    public static final long DEFAULT_MILLISECONDS = 10000;
//    private static volatile OkHttpUtils mInstance;
//    private OkHttpClient mOkHttpClient;
//    private Platform mPlatform;
//
//    public static class METHOD {
//        public static final String DELETE = "DELETE";
//        public static final String HEAD = "HEAD";
//        public static final String PATCH = "PATCH";
//        public static final String PUT = "PUT";
//    }
//
//    public OkHttpUtils(OkHttpClient okHttpClient) {
//        if (okHttpClient == null) {
//            this.mOkHttpClient = new OkHttpClient();
//        } else {
//            this.mOkHttpClient = okHttpClient;
//        }
//        this.mPlatform = Platform.get();
//    }
//
//    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
//        if (mInstance == null) {
//            synchronized (OkHttpUtils.class) {
//                if (mInstance == null) {
//                    mInstance = new OkHttpUtils(okHttpClient);
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    public static OkHttpUtils getInstance() {
//        return initClient(null);
//    }
//
//    public Executor getDelivery() {
//        return this.mPlatform.defaultCallbackExecutor();
//    }
//
//    public OkHttpClient getOkHttpClient() {
//        return this.mOkHttpClient;
//    }
//
//    public static GetBuilder get() {
//        return new GetBuilder();
//    }
//
//    public static PostStringBuilder postString() {
//        return new PostStringBuilder();
//    }
//
//    public static PostFileBuilder postFile() {
//        return new PostFileBuilder();
//    }
//
//    public static PostFormBuilder post() {
//        return new PostFormBuilder();
//    }
//
//    public static OtherRequestBuilder put() {
//        return new OtherRequestBuilder(METHOD.PUT);
//    }
//
//    public static HeadBuilder head() {
//        return new HeadBuilder();
//    }
//
//    public static OtherRequestBuilder delete() {
//        return new OtherRequestBuilder(METHOD.DELETE);
//    }
//
//    public static OtherRequestBuilder patch() {
//        return new OtherRequestBuilder(METHOD.PATCH);
//    }
//
//    public void execute(RequestCall requestCall, Callback callback) {
//        if (callback == null) {
//            callback = Callback.CALLBACK_DEFAULT;
//        }
//        final Callback finalCallback = callback;
//        final int id = requestCall.getOkHttpRequest().getId();
//        requestCall.getCall().enqueue(new okhttp3.Callback() {
//            public void onFailure(Call call, IOException e) {
//                OkHttpUtils.this.sendFailResultCallback(call, e, finalCallback, id);
//            }
//
//            /* JADX WARNING: Failed to extract finally block: empty outs */
//            public void onResponse(okhttp3.Call r7, okhttp3.Response r8) {
//                /*
//                r6 = this;
//                r2 = r7.isCanceled();	 Catch:{ Exception -> 0x0080 }
//                if (r2 == 0) goto L_0x0024;
//            L_0x0006:
//                r2 = com.mylibrary.okhttputils.OkHttpUtils.this;	 Catch:{ Exception -> 0x0080 }
//                r3 = new java.io.IOException;	 Catch:{ Exception -> 0x0080 }
//                r4 = "Canceled!";
//                r3.<init>(r4);	 Catch:{ Exception -> 0x0080 }
//                r4 = r0;	 Catch:{ Exception -> 0x0080 }
//                r5 = r1;	 Catch:{ Exception -> 0x0080 }
//                r2.sendFailResultCallback(r7, r3, r4, r5);	 Catch:{ Exception -> 0x0080 }
//                r2 = r8.body();
//                if (r2 == 0) goto L_0x0023;
//            L_0x001c:
//                r2 = r8.body();
//                r2.close();
//            L_0x0023:
//                return;
//            L_0x0024:
//                r2 = r0;	 Catch:{ Exception -> 0x0080 }
//                r3 = r1;	 Catch:{ Exception -> 0x0080 }
//                r2 = r2.validateReponse(r8, r3);	 Catch:{ Exception -> 0x0080 }
//                if (r2 != 0) goto L_0x0061;
//            L_0x002e:
//                r2 = com.mylibrary.okhttputils.OkHttpUtils.this;	 Catch:{ Exception -> 0x0080 }
//                r3 = new java.io.IOException;	 Catch:{ Exception -> 0x0080 }
//                r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0080 }
//                r4.<init>();	 Catch:{ Exception -> 0x0080 }
//                r5 = "request failed , reponse's code is : ";
//                r4 = r4.append(r5);	 Catch:{ Exception -> 0x0080 }
//                r5 = r8.code();	 Catch:{ Exception -> 0x0080 }
//                r4 = r4.append(r5);	 Catch:{ Exception -> 0x0080 }
//                r4 = r4.toString();	 Catch:{ Exception -> 0x0080 }
//                r3.<init>(r4);	 Catch:{ Exception -> 0x0080 }
//                r4 = r0;	 Catch:{ Exception -> 0x0080 }
//                r5 = r1;	 Catch:{ Exception -> 0x0080 }
//                r2.sendFailResultCallback(r7, r3, r4, r5);	 Catch:{ Exception -> 0x0080 }
//                r2 = r8.body();
//                if (r2 == 0) goto L_0x0023;
//            L_0x0059:
//                r2 = r8.body();
//                r2.close();
//                goto L_0x0023;
//            L_0x0061:
//                r2 = r0;	 Catch:{ Exception -> 0x0080 }
//                r3 = r1;	 Catch:{ Exception -> 0x0080 }
//                r1 = r2.parseNetworkResponse(r8, r3);	 Catch:{ Exception -> 0x0080 }
//                r2 = com.mylibrary.okhttputils.OkHttpUtils.this;	 Catch:{ Exception -> 0x0080 }
//                r3 = r0;	 Catch:{ Exception -> 0x0080 }
//                r4 = r1;	 Catch:{ Exception -> 0x0080 }
//                r2.sendSuccessResultCallback(r1, r3, r4);	 Catch:{ Exception -> 0x0080 }
//                r2 = r8.body();
//                if (r2 == 0) goto L_0x0023;
//            L_0x0078:
//                r2 = r8.body();
//                r2.close();
//                goto L_0x0023;
//            L_0x0080:
//                r0 = move-exception;
//                r2 = com.mylibrary.okhttputils.OkHttpUtils.this;	 Catch:{ all -> 0x0098 }
//                r3 = r0;	 Catch:{ all -> 0x0098 }
//                r4 = r1;	 Catch:{ all -> 0x0098 }
//                r2.sendFailResultCallback(r7, r0, r3, r4);	 Catch:{ all -> 0x0098 }
//                r2 = r8.body();
//                if (r2 == 0) goto L_0x0023;
//            L_0x0090:
//                r2 = r8.body();
//                r2.close();
//                goto L_0x0023;
//            L_0x0098:
//                r2 = move-exception;
//                r3 = r8.body();
//                if (r3 == 0) goto L_0x00a6;
//            L_0x009f:
//                r3 = r8.body();
//                r3.close();
//            L_0x00a6:
//                throw r2;
//                */
//                throw new UnsupportedOperationException("Method not decompiled: com.mylibrary.okhttputils.OkHttpUtils.1.onResponse(okhttp3.Call, okhttp3.Response):void");
//            }
//        });
//    }
//
//    public void sendFailResultCallback(Call call, Exception e, Callback callback, int id) {
//        if (callback != null) {
//            final Callback callback2 = callback;
//            final Call call2 = call;
//            final Exception exception = e;
//            final int i = id;
//            this.mPlatform.execute(new Runnable() {
//                public void run() {
//                    callback2.onError(call2, exception, i);
//                    callback2.onAfter(i);
//                }
//            });
//        }
//    }
//
//    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
//        if (callback != null) {
//            this.mPlatform.execute(new Runnable() {
//                public void run() {
//                    callback.onResponse(object, id);
//                    callback.onAfter(id);
//                }
//            });
//        }
//    }
//
//    public void cancelTag(Object tag) {
//        for (Call call : this.mOkHttpClient.dispatcher().queuedCalls()) {
//            if (tag.equals(call.request().tag())) {
//                call.cancel();
//            }
//        }
//        for (Call call2 : this.mOkHttpClient.dispatcher().runningCalls()) {
//            if (tag.equals(call2.request().tag())) {
//                call2.cancel();
//            }
//        }
//    }
//}