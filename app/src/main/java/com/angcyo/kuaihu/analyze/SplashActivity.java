//package com.angcyo.kuaihu;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.os.StrictMode;
//import android.os.StrictMode.ThreadPolicy.Builder;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//import com.angcyo.kuaihu.http.*;
//import com.angcyo.uiview.less.utils.utilcode.utils.NetworkUtils;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.orhanobut.hawk.Hawk;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class SplashActivity extends BaseActivity implements OnClickListener, PermissionCallbacks {
//    private static final int RC_EXTERNAL_STORAGE_PERM = 124;
//    private Type domainType = new C12292().getType();
//    private Gson gson = new GsonBuilder().create();
//    private GuideModel guideModels = null;
//    private Type guideType = new C12281().getType();
//    private Handler handler = new Handler();
//    private Button jump_bt;
//    private Tracker mTracker;
//    private MyCountDownTimer myCountDownTimer;
//    private RelativeLayout relativeLayout;
//    private ImageView splash_img;
//    Runnable taskGetDomain = new C06707();
//    Runnable taskSplash = new C06718();
//    Runnable taskStatistics = new C06729();
//    private boolean usePassWdLogin;
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$3 */
//    class C06693 implements Runnable {
//        C06693() {
//        }
//
//        public void run() {
//            if (NetworkUtils.isConnected()) {
//                boolean isFirstLogin = SharedPreferencesUtil.getInstance().getBoolean("isFirstLogin", true);
//                int appCode = SharedPreferencesUtil.getInstance().getInt("appCode", 1);
//                if (isFirstLogin || appCode != AppUtils.getAppVersionCode(SplashActivity.this)) {
//                    SplashActivity.this.intentTo(new Intent(SplashActivity.this, GuideActivity.class));
//                    SplashActivity.this.finish();
//                    return;
//                }
//                if (SplashActivity.this.usePassWdLogin) {
//                    SplashActivity.this.intentTo(new Intent(SplashActivity.this, LockSetActivity.class));
//                } else {
//                    SplashActivity.this.intentTo(new Intent(SplashActivity.this, MainActivity.class));
//                }
//                SplashActivity.this.finish();
//                return;
//            }
//            Toast.makeText(SplashActivity.this, C0665R.string.not_network, 1).show();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$7 */
//    class C06707 implements Runnable {
//        C06707() {
//        }
//
//        public void run() {
//            SplashActivity.this.leftDomain3.clear();
//            SplashActivity.this.leftDomain3.addAll(Constant.DOMAINLIST);
//            SplashActivity.this.errorFlag3 = 0;
//            SplashActivity.this.getDnsDomain();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$8 */
//    class C06718 implements Runnable {
//        C06718() {
//        }
//
//        public void run() {
//            SplashActivity.this.getDnsSplash();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$9 */
//    class C06729 implements Runnable {
//        C06729() {
//        }
//
//        public void run() {
//            SplashActivity.this.getDnsStatistics();
//        }
//    }
//
//    class MyCountDownTimer extends CountDownTimer {
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        public void onFinish() {
//            SplashActivity.this.jump_bt.setText("正在进入");
//        }
//
//        public void onTick(long millisUntilFinished) {
//            SplashActivity.this.jump_bt.setText("跳过  " + (millisUntilFinished / 1000));
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$1 */
//    class C12281 extends TypeToken<GuideModel> {
//        C12281() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$2 */
//    class C12292 extends TypeToken<List<String>> {
//        C12292() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$4 */
//    class C14384 extends StringCallback {
//        C14384() {
//        }
//
//        public void onError(Call call, Exception e, int id) {
//            System.out.println("FUCK" + e.toString());
//            if (SplashActivity.this.leftDomain3.size() > 0) {
//                SplashActivity.this.getDnsDomain();
//            } else if (SplashActivity.this.errorFlag3 < 1) {
//                SplashActivity splashActivity = SplashActivity.this;
//                splashActivity.errorFlag3++;
//                SplashActivity.this.getDomain(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_DOMAIN);
//            }
//        }
//
//        public void onResponse(String response, int id) {
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                if (jsonObject.getInt("code") == 0) {
//                    List<String> domainList = (List) SplashActivity.this.gson.fromJson(jsonObject.getJSONArray("data").toString().trim(), SplashActivity.this.domainType);
//                    System.out.println("caonima:" + ((String) domainList.get(0)));
//                    Hawk.put("domain", domainList.get(0));
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$5 */
//    class C14395 extends StringCallback {
//        C14395() {
//        }
//
//        public void onError(Call call, Exception e, int id) {
//        }
//
//        public void onResponse(String response, int id) {
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                if (jsonObject.getInt("code") == 0) {
//                    SplashActivity.this.guideModels = (GuideModel) SplashActivity.this.gson.fromJson(jsonObject.getJSONObject("data").toString().trim(), SplashActivity.this.guideType);
//                    if (SplashActivity.this.guideModels != null) {
//                        ImageLoadUtil.loadImageView(SplashActivity.this.guideModels.getBu_img_url(), SplashActivity.this.splash_img, C0665R.drawable.default_image_bg);
//                        return;
//                    }
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.activity.SplashActivity$6 */
//    class C14406 extends StringCallback {
//        C14406() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//        }
//
//        public void onAfter(int id) {
//            super.onAfter(id);
//        }
//
//        public void onError(Call call, Exception e, int id) {
//        }
//
//        public void onResponse(String response, int id) {
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                System.out.print(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                System.out.println("jsonObject:" + jsonObject.toString());
//                int code = jsonObject.getInt("code");
//                System.out.println("code:" + code);
//                if (code != 0) {
//                    Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(1024, 1024);
//        if (VERSION.SDK_INT > 9) {
//            StrictMode.setThreadPolicy(new Builder().permitAll().build());
//        }
//        setContentView((int) C0665R.layout.activity_splash);
//        initView();
//        storageAndPhoneStateTask();
//        this.mTracker = MyApplication.getsInstance().getDefaultTracker();
//    }
//
//    private void initView() {
//        this.splash_img = (ImageView) findViewById(C0665R.id.splash_img);
//        this.splash_img.setOnClickListener(this);
//        this.jump_bt = (Button) findViewById(C0665R.id.jump_bt);
//        this.jump_bt.setOnClickListener(this);
//        this.relativeLayout = (RelativeLayout) findViewById(C0665R.id.relativelayout_welcome);
//        String secretKey = (String) Hawk.get("secret_key", "");
//        boolean usePassWdLogin = SharedPreferencesUtil.getInstance().getBoolean("usePassWdLogin", false);
//        if (secretKey == null || "".equals(secretKey) || !usePassWdLogin) {
//            this.usePassWdLogin = false;
//        } else {
//            this.usePassWdLogin = true;
//        }
//    }
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case C0665R.id.jump_bt:
//                String[] perms = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};
//                if (!EasyPermissions.hasPermissions(this, perms)) {
//                    EasyPermissions.requestPermissions((Activity) this, getString(C0665R.string.request_phone_storage_permissions_tip), (int) RC_EXTERNAL_STORAGE_PERM, perms);
//                    return;
//                } else if (NetworkUtils.isConnected()) {
//                    boolean isFirstLogin = SharedPreferencesUtil.getInstance().getBoolean("isFirstLogin", true);
//                    int appCode = SharedPreferencesUtil.getInstance().getInt("appCode", 1);
//                    if (isFirstLogin || appCode != AppUtils.getAppVersionCode(this)) {
//                        intentTo(new Intent(this, GuideActivity.class));
//                        finish();
//                    } else {
//                        if (this.usePassWdLogin) {
//                            intentTo(new Intent(this, LockSetActivity.class));
//                        } else {
//                            intentTo(new Intent(this, MainActivity.class));
//                        }
//                        finish();
//                    }
//                    this.jump_bt.setClickable(false);
//                    return;
//                } else {
//                    Toast.makeText(this, C0665R.string.not_network, 1).show();
//                    return;
//                }
//            case C0665R.id.splash_img:
//                if (this.guideModels != null && !TextUtils.isEmpty(this.guideModels.getBu_web_url()) && Pattern.matches("[a-zA-z]+://[^\\s]*", this.guideModels.getBu_web_url())) {
//                    intentTo(new Intent("android.intent.action.VIEW", Uri.parse(this.guideModels.getBu_web_url())));
//                    return;
//                }
//                return;
//            default:
//                return;
//        }
//    }
//
//    @AfterPermissionGranted(124)
//    public void storageAndPhoneStateTask() {
//        String[] perms = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            new Thread(this.taskGetDomain).start();
//            new Thread(this.taskSplash).start();
//            new Thread(this.taskStatistics).start();
//            this.myCountDownTimer = new MyCountDownTimer(OkHttpUtils.DEFAULT_MILLISECONDS, 1000);
//            this.myCountDownTimer.start();
//            this.handler.postDelayed(new C06693(), OkHttpUtils.DEFAULT_MILLISECONDS);
//            return;
//        }
//        EasyPermissions.requestPermissions((Activity) this, getString(C0665R.string.request_phone_storage_permissions_tip), (int) RC_EXTERNAL_STORAGE_PERM, perms);
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    public void onPermissionsGranted(int requestCode, List<String> list) {
//    }
//
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied((Activity) this, (List) perms)) {
//            new AppSettingsDialog.Builder((Activity) this).build().show();
//        }
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
//            Toast.makeText(this, getString(C0665R.string.setting_back_to_activity_tip), 0).show();
//        }
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        this.handler.removeCallbacksAndMessages(null);
//    }
//
//    private void getDomain(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("type", "1");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data",
//                AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV))
//                .getParams().url(path)).build()
//                .execute(new C14384());
//    }
//
//    private void getSplash(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("type", "2");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C14395());
//    }
//
//    public void getStatisticsData(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("ms_deviceid", AppUtils.getAndroidID(getApplicationContext()));
//            jsonObject.put("ms_device_name", AppUtils.getModel());
//            jsonObject.put("ms_app_version", AppUtils.getAppVersionName(getApplicationContext()));
//            jsonObject.put("ms_system_version", AppUtils.getOSVersion());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println("jsonObject:" + jsonObject.toString());
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C14406());
//    }
//
//    private void getDnsDomain() {
//        String ip = HttpDNS.getAddressByName(popDomain(this.leftDomain3));
//        System.out.println("ip:" + ip);
//        if (!"".equals(ip)) {
//            getDomain(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_DOMAIN);
//        } else if (this.leftDomain3.size() > 0) {
//            getDnsDomain();
//        } else if (this.errorFlag3 < 1) {
//            this.errorFlag3++;
//            getDomain(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_DOMAIN);
//        }
//    }
//
//    private void getDnsSplash() {
//        String ip = HttpDNS.getAddressByName((String) Hawk.get("domain", Constant.DEFAULT_BACKUP_DOMAIN));
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getSplash(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_SPLASH);
//        } else {
//            getSplash(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_SPLASH);
//        }
//    }
//
//    private void getDnsStatistics() {
//        String ip = HttpDNS.getAddressByName((String) Hawk.get("domain", Constant.DEFAULT_BACKUP_DOMAIN));
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getStatisticsData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_STATIC);
//        } else {
//            getStatisticsData(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_STATIC);
//        }
//    }
//}