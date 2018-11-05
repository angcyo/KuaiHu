//package com.angcyo.kuaihu.analyze;
//
//import android.animation.Animator;
//import android.animation.Animator.AnimatorListener;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.p000v4.app.Fragment;
//import android.support.p000v4.app.FragmentManager;
//import android.support.p000v4.app.FragmentPagerAdapter;
//import android.support.p000v4.view.GravityCompat;
//import android.support.p000v4.view.ViewPager;
//import android.support.p000v4.widget.DrawerLayout;
//import android.support.p000v4.widget.DrawerLayout.DrawerListener;
//import android.support.p003v7.app.AlertDialog.Builder;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;
//import com.flyco.tablayout.SlidingTabLayout;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.mylibrary.Constant;
//import com.mylibrary.okhttputils.OkHttpUtils;
//import com.mylibrary.okhttputils.builder.PostFormBuilder;
//import com.mylibrary.okhttputils.callback.StringCallback;
//import com.mylibrary.update.UpdateAgent;
//import com.mylibrary.update.UpdateInfo;
//import com.mylibrary.utils.AesEncryptionUtil;
//import com.mylibrary.utils.AppUtils;
//import com.mylibrary.utils.ConvertUtils;
//import com.mylibrary.utils.FileUtils;
//import com.mylibrary.utils.HttpDNS;
//import com.mylibrary.utils.ImageLoadUtil;
//import com.mylibrary.widget.swipebackhelper.SwipeBackHelper;
//import com.orhanobut.hawk.Hawk;
//import com.xxx.foxvideo.C0665R;
//import com.xxx.foxvideo.activity.BaseActivity;
//import com.xxx.foxvideo.model.MessageModel;
//import com.xxx.foxvideo.model.SystemInfoModel;
//import com.xxx.foxvideo.model.UserModel;
//import com.xxx.foxvideo.p005ui.home.fragment.FocusFragment;
//import com.xxx.foxvideo.p005ui.home.fragment.HotFragment;
//import com.xxx.foxvideo.p005ui.home.fragment.SquareFragment;
//import com.xxx.foxvideo.p005ui.home.fragment.WebFragment;
//import com.xxx.foxvideo.service.UploadService;
//import com.xxx.foxvideo.utils.CacheUtils;
//import com.xxx.foxvideo.utils.UserUtils;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity */
//public class MainActivity extends BaseActivity implements OnClickListener {
//    public static final String DOMAIN = ((String) Hawk.get("domain", Constant.DEFAULT_BACKUP_DOMAIN));
//    public static final int LOGIN_CODE = 10026;
//    public static final String SYSTEM_INFO = "systemInfo";
//    private LinearLayout announcement_ll;
//    private ImageView cancel_img;
//    private LinearLayout direct_message_ll;
//    private DrawerLayout drawer;
//    private LinearLayout drawer_app_ll;
//    private ImageView drawer_avatar_img;
//    private LinearLayout drawer_collection_ll;
//    private LinearLayout drawer_help_ll;
//    private LinearLayout drawer_line_ll;
//    private TextView drawer_name_tv;
//    private LinearLayout drawer_root_ll;
//    private LinearLayout drawer_setting_ll;
//    private TextView drawer_vip_data_tv;
//    private LinearLayout exchange_ll;
//    private FocusFragment focusFragment;
//    private Gson gson = new GsonBuilder().create();
//    private Handler handler = new Handler();
//    private HotFragment hotFragment;
//    private ProgressBar idProgress;
//    private TextView idTextview;
//    private ImageView img;
//    private LinearLayout layout;
//    private TextView login_tv;
//    private MyPagerAdapter mAdapter;
//    private int mBackKeyPressedTimes = 0;
//    private ArrayList<Fragment> mFragments = new ArrayList();
//    private final List<String> mTitles = new ArrayList();
//    private Type messageType = new C12712().getType();
//    private ArrayList<MessageModel> noticeList = new ArrayList();
//    private PopupWindow noticePop;
//    private TextView notice_text;
//    private ImageView release_img;
//    private LinearLayout settingDeleteVideoLl;
//    private TextView settingDeleteVideoTv;
//    private SlidingTabLayout slidingTabLayout;
//    private SquareFragment squareFragment;
//    private Type systemInfoType = new C12681().getType();
//    Runnable taskAdsData = new C076327();
//    Runnable taskNotice = new C076428();
//    Runnable taskUpdate = new C076125();
//    Runnable taskUserInfor = new C076226();
//    private LinearLayout top_up_ll;
//    private UpdateInfo updateModel;
//    private BroadcastReceiver uploadReceiver = new C07663();
//    private ImageView user_vip_img;
//    private ViewPager viewPager;
//    private WebFragment webFragment;
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$10 */
//    class C075010 implements Runnable {
//        C075010() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$11 */
//    class C075111 implements DialogInterface.OnClickListener {
//        C075111() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$12 */
//    class C075212 implements DialogInterface.OnClickListener {
//        C075212() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            OkHttpUtils.getInstance().cancelTag(UploadService.CANCEL_TAG);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$13 */
//    class C075313 implements Runnable {
//        C075313() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$14 */
//    class C075414 implements Runnable {
//        C075414() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$15 */
//    class C075515 implements Runnable {
//        C075515() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$16 */
//    class C075616 implements Runnable {
//        C075616() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$17 */
//    class C075717 implements Runnable {
//        C075717() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$18 */
//    class C075818 implements DialogInterface.OnClickListener {
//        C075818() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$19 */
//    class C075919 implements DialogInterface.OnClickListener {
//        C075919() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            try {
//                CacheUtils.cleanVideoCacheDir(AppUtils.getAppContext());
//                MainActivity.this.settingDeleteVideoTv.setText("0.00B");
//                Toast.makeText(MainActivity.this, MainActivity.this.getString(C0665R.string.清除成功), 0).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$23 */
//    class C076023 implements OnClickListener {
//        C076023() {
//        }
//
//        public void onClick(View view) {
//            MainActivity.this.noticeList.remove(MainActivity.this.noticeList.get(0));
//            if (MainActivity.this.noticeList.size() <= 0) {
//                MainActivity.this.noticePop.dismiss();
//            } else {
//                MainActivity.this.notice_text.setText(((MessageModel) MainActivity.this.noticeList.get(0)).getMm_content());
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$25 */
//    class C076125 implements Runnable {
//        C076125() {
//        }
//
//        public void run() {
//            MainActivity.this.getDnsUpdate();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$26 */
//    class C076226 implements Runnable {
//        C076226() {
//        }
//
//        public void run() {
//            MainActivity.this.getDnsUserInfor();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$27 */
//    class C076327 implements Runnable {
//        C076327() {
//        }
//
//        public void run() {
//            MainActivity.this.getDnsSystemInfo();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$28 */
//    class C076428 implements Runnable {
//        C076428() {
//        }
//
//        public void run() {
//            MainActivity.this.getDnsNotice();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$3 */
//    class C07663 extends BroadcastReceiver {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$3$1 */
//        class C07651 implements AnimatorListener {
//            C07651() {
//            }
//
//            public void onAnimationStart(Animator animator) {
//            }
//
//            public void onAnimationEnd(Animator animator) {
//                MainActivity.this.layout.setVisibility(8);
//            }
//
//            public void onAnimationCancel(Animator animator) {
//            }
//
//            public void onAnimationRepeat(Animator animator) {
//            }
//        }
//
//        C07663() {
//        }
//
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction() == UploadService.ACTION_UPLOAD_BROADCAST) {
//                int flag = intent.getIntExtra(UploadService.UPLOAD_FLAG, -1);
//                if (flag == 0) {
//                    MainActivity.this.idTextview.setText(intent.getStringExtra(UploadService.EXTRA_TEXT));
//                    MainActivity.this.layout.setVisibility(0);
//                    YoYo.with(Techniques.SlideInUp).duration(800).playOn(MainActivity.this.layout);
//                } else if (flag == 1) {
//                    MainActivity.this.idTextview.setText(intent.getStringExtra(UploadService.EXTRA_TEXT));
//                    if (MainActivity.this.layout.getVisibility() == 8) {
//                        MainActivity.this.layout.setVisibility(0);
//                        YoYo.with(Techniques.SlideInUp).duration(800).playOn(MainActivity.this.layout);
//                    }
//                    MainActivity.this.idProgress.setProgress((int) (100.0f * intent.getFloatExtra(UploadService.EXTRA_PROGRESS, 0.0f)));
//                } else if (flag == 2) {
//                    MainActivity.this.idTextview.setText(intent.getStringExtra(UploadService.EXTRA_TEXT));
//                    YoYo.with(Techniques.SlideOutDown).duration(1000).withListener(new C07651()).playOn(MainActivity.this.layout);
//                }
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$5 */
//    class C07675 extends Thread {
//        C07675() {
//        }
//
//        public void run() {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                MainActivity.this.mBackKeyPressedTimes = 0;
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$6 */
//    class C07686 implements Runnable {
//        C07686() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$7 */
//    class C07697 implements Runnable {
//        C07697() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$8 */
//    class C07708 implements Runnable {
//        C07708() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$9 */
//    class C07719 implements Runnable {
//        C07719() {
//        }
//
//        public void run() {
//            MainActivity.this.closeDrawer();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$1 */
//    class C12681 extends TypeToken<SystemInfoModel> {
//        C12681() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$2 */
//    class C12712 extends TypeToken<List<MessageModel>> {
//        C12712() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$4 */
//    class C12724 implements DrawerListener {
//        C12724() {
//        }
//
//        public void onDrawerSlide(View drawerView, float slideOffset) {
//            MainActivity.this.drawer.getChildAt(0).setTranslationX(((float) drawerView.getMeasuredWidth()) * (1.0f - (1.0f - slideOffset)));
//        }
//
//        public void onDrawerOpened(View drawerView) {
//        }
//
//        public void onDrawerClosed(View drawerView) {
//        }
//
//        public void onDrawerStateChanged(int newState) {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$20 */
//    class C145320 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$20$1 */
//        class C12691 extends TypeToken<UpdateInfo> {
//            C12691() {
//            }
//        }
//
//        C145320() {
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
//            Type updateType = new C12691().getType();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                if (code == 0) {
//                    MainActivity.this.updateModel = (UpdateInfo) MainActivity.this.gson.fromJson(jsonObject.getJSONObject("data").toString().trim(), updateType);
//                    new UpdateAgent(MainActivity.this, MainActivity.this.updateModel, true, false, 888, true, false).check();
//                    return;
//                }
//                if (code == 2) {
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$21 */
//    class C145421 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$21$1 */
//        class C12701 extends TypeToken<UserModel> {
//            C12701() {
//            }
//        }
//
//        C145421() {
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
//            int i = 1;
//            Type userType = new C12701().getType();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                System.out.println("jsonObject:" + jsonObject.toString());
//                System.out.println("执行");
//                if (code == 0) {
//                    JSONObject json = jsonObject.getJSONObject("data");
//                    UserModel temp = new UserModel();
//                    temp = (UserModel) MainActivity.this.gson.fromJson(json.toString().trim(), userType);
//                    if (temp.getMu_token().equals(UserUtils.getUserInfo().getMu_token())) {
//                        Hawk.put("user", temp);
//                        ImageLoadUtil.loadImageView(UserUtils.getUserInfo().getMu_avatar(), MainActivity.this.drawer_avatar_img, C0665R.drawable.detail_avatar_secret);
//                        MainActivity.this.drawer_name_tv.setText(UserUtils.getUserInfo().getMu_name());
//                        if (UserUtils.getUserInfo().getIs_vip() == null) {
//                            i = 0;
//                        }
//                        if ((i & UserUtils.getUserInfo().getIs_vip().equals("1")) != 0) {
//                            MainActivity.this.user_vip_img.setVisibility(0);
//                            MainActivity.this.drawer_vip_data_tv.setVisibility(0);
//                            MainActivity.this.drawer_vip_data_tv.setText("VIP到期时间:" + MainActivity.this.getData(UserUtils.getUserInfo().getVip_time()));
//                            return;
//                        }
//                        MainActivity.this.user_vip_img.setVisibility(8);
//                        MainActivity.this.drawer_vip_data_tv.setVisibility(8);
//                        return;
//                    }
//                    Hawk.deleteAll();
//                    MainActivity.this.intentTo(new Intent(MainActivity.this, LoginActivity.class));
//                    Toast.makeText(MainActivity.this, "密码已被修改，请重新登录!", 1).show();
//                } else if (code == 2) {
//                    Hawk.deleteAll();
//                    MainActivity.this.intentTo(new Intent(MainActivity.this, LoginActivity.class));
//                    Toast.makeText(MainActivity.this, "账号已被禁用，请重新登录!", 1).show();
//                } else {
//                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), 0).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$22 */
//    class C145522 extends StringCallback {
//        C145522() {
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
//                int code = jsonObject.getInt("code");
//                System.out.println("code:" + code);
//                if (code == 0) {
//                    Hawk.put(MainActivity.SYSTEM_INFO, (SystemInfoModel) MainActivity.this.gson.fromJson(jsonObject.getJSONObject("data").toString(), MainActivity.this.systemInfoType));
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$24 */
//    class C145624 extends StringCallback {
//        C145624() {
//        }
//
//        public void onError(Call call, Exception e, int id) {
//        }
//
//        public void onResponse(String response, int id) {
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                if (jsonObject.getInt("code") == 0) {
//                    List<MessageModel> tempList = (List) MainActivity.this.gson.fromJson(jsonObject.getJSONObject("data").getJSONArray("list").toString(), MainActivity.this.messageType);
//                    MainActivity.this.noticeList.clear();
//                    MainActivity.this.noticeList.addAll(tempList);
//                    if (MainActivity.this.noticeList.size() > 0) {
//                        MainActivity.this.initPop();
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
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.MainActivity$MyPagerAdapter */
//    public class MyPagerAdapter extends FragmentPagerAdapter {
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public int getCount() {
//            return MainActivity.this.mFragments.size();
//        }
//
//        public CharSequence getPageTitle(int position) {
//            return (CharSequence) MainActivity.this.mTitles.get(position);
//        }
//
//        public Fragment getItem(int position) {
//            return (Fragment) MainActivity.this.mFragments.get(position);
//        }
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(this.uploadReceiver);
//    }
//
//    protected void onResume() {
//        super.onResume();
//        try {
//            this.settingDeleteVideoTv.setText(ConvertUtils.byte2FitSize(FileUtils.getFolderSize(CacheUtils.getVideoCacheDir(AppUtils.getAppContext()))) + "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        checkIsUserLogin();
//    }
//
//    public void showCurrentScreen(int index) {
//        if (index >= 0 && index <= 3) {
//            this.viewPager.setCurrentItem(index);
//        }
//    }
//
//    private void checkIsUserLogin() {
//        if (UserUtils.isUserLogin()) {
//            this.drawer.setDrawerLockMode(0);
//            this.img.setVisibility(0);
//            this.login_tv.setVisibility(8);
//            return;
//        }
//        this.drawer.setDrawerLockMode(1);
//        this.img.setVisibility(8);
//        this.login_tv.setVisibility(0);
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView((int) C0665R.layout.activity_main);
//        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false).setDisallowInterceptTouchEvent(true);
//        initData();
//        initView();
//        registerReceiver(this.uploadReceiver, new IntentFilter(UploadService.ACTION_UPLOAD_BROADCAST));
//    }
//
//    private void initData() {
//        this.mTitles.add("热门");
//        this.mTitles.add("广场");
//        this.mTitles.add("关注");
//        this.mTitles.add("网页");
//        this.hotFragment = new HotFragment();
//        this.squareFragment = new SquareFragment();
//        this.focusFragment = new FocusFragment();
//        this.webFragment = new WebFragment();
//        this.mFragments.add(this.hotFragment);
//        this.mFragments.add(this.squareFragment);
//        this.mFragments.add(this.focusFragment);
//        this.mFragments.add(this.webFragment);
//        new Thread(this.taskUpdate).start();
//        new Thread(this.taskAdsData).start();
//        if (UserUtils.isUserLogin()) {
//            new Thread(this.taskUserInfor).start();
//        }
//        new Thread(this.taskNotice).start();
//    }
//
//    private void initView() {
//        this.img = (ImageView) findViewById(C0665R.id.img);
//        this.img.setOnClickListener(this);
//        this.login_tv = (TextView) findViewById(C0665R.id.login_tv);
//        this.login_tv.setOnClickListener(this);
//        this.release_img = (ImageView) findViewById(C0665R.id.release_img);
//        this.release_img.setOnClickListener(this);
//        this.announcement_ll = (LinearLayout) findViewById(C0665R.id.announcement_ll);
//        this.announcement_ll.setOnClickListener(this);
//        this.direct_message_ll = (LinearLayout) findViewById(C0665R.id.direct_message_ll);
//        this.direct_message_ll.setOnClickListener(this);
//        this.top_up_ll = (LinearLayout) findViewById(C0665R.id.top_up_ll);
//        this.top_up_ll.setOnClickListener(this);
//        this.exchange_ll = (LinearLayout) findViewById(C0665R.id.exchange_ll);
//        this.exchange_ll.setOnClickListener(this);
//        this.cancel_img = (ImageView) findViewById(C0665R.id.cancel_img);
//        this.cancel_img.setOnClickListener(this);
//        this.idTextview = (TextView) findViewById(C0665R.id.id_textview);
//        this.idProgress = (ProgressBar) findViewById(C0665R.id.id_progress);
//        this.layout = (LinearLayout) findViewById(C0665R.id.layout);
//        this.drawer_avatar_img = (ImageView) findViewById(C0665R.id.drawer_avatar_img);
//        this.drawer_avatar_img.setOnClickListener(this);
//        System.out.println("img:" + UserUtils.getUserInfo().getMu_avatar());
//        ImageLoadUtil.loadImageView(UserUtils.getUserInfo().getMu_avatar(), this.drawer_avatar_img, C0665R.drawable.detail_avatar_secret);
//        this.drawer_name_tv = (TextView) findViewById(C0665R.id.drawer_name_tv);
//        this.drawer_name_tv.setText(UserUtils.getUserInfo().getMu_name());
//        this.user_vip_img = (ImageView) findViewById(C0665R.id.user_vip_img);
//        this.drawer_vip_data_tv = (TextView) findViewById(C0665R.id.drawer_vip_data_tv);
//        if (UserUtils.getUserInfo().getIs_vip() == null || !UserUtils.getUserInfo().getIs_vip().equals("1")) {
//            this.user_vip_img.setVisibility(8);
//            this.drawer_vip_data_tv.setVisibility(8);
//        } else {
//            this.user_vip_img.setVisibility(0);
//            this.drawer_vip_data_tv.setVisibility(0);
//            if (!TextUtils.isEmpty(UserUtils.getUserInfo().getVip_time())) {
//                this.drawer_vip_data_tv.setText("VIP到期时间:" + getData(UserUtils.getUserInfo().getVip_time()));
//            }
//        }
//        this.drawer_root_ll = (LinearLayout) findViewById(C0665R.id.drawer_root_ll);
//        this.drawer_root_ll.setOnClickListener(this);
//        this.drawer_collection_ll = (LinearLayout) findViewById(C0665R.id.drawer_collection_ll);
//        this.drawer_collection_ll.setOnClickListener(this);
//        this.drawer_setting_ll = (LinearLayout) findViewById(C0665R.id.drawer_setting_ll);
//        this.drawer_setting_ll.setOnClickListener(this);
//        this.drawer_help_ll = (LinearLayout) findViewById(C0665R.id.drawer_help_ll);
//        this.drawer_help_ll.setOnClickListener(this);
//        this.drawer_app_ll = (LinearLayout) findViewById(C0665R.id.drawer_app_ll);
//        this.drawer_app_ll.setOnClickListener(this);
//        this.drawer_line_ll = (LinearLayout) findViewById(C0665R.id.drawer_line_ll);
//        this.drawer_line_ll.setOnClickListener(this);
//        this.settingDeleteVideoLl = (LinearLayout) findViewById(C0665R.id.setting_delete_video_ll);
//        this.settingDeleteVideoTv = (TextView) findViewById(C0665R.id.setting_delete_video_tv);
//        this.settingDeleteVideoLl.setOnClickListener(this);
//        try {
//            this.settingDeleteVideoTv.setText("已缓存" + ConvertUtils.byte2FitSize(FileUtils.getFolderSize(CacheUtils.getVideoCacheDir(AppUtils.getAppContext()))) + "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.drawer = (DrawerLayout) findViewById(C0665R.id.drawer_layout);
//        this.drawer.setScrimColor(getResources().getColor(C0665R.color.scrim_color));
//        this.drawer.addDrawerListener(new C12724());
//        checkIsUserLogin();
//        this.slidingTabLayout = (SlidingTabLayout) findViewById(C0665R.id.slidingTabLayout);
//        this.viewPager = (ViewPager) findViewById(C0665R.id.viewPager);
//        this.mAdapter = new MyPagerAdapter(getSupportFragmentManager());
//        this.viewPager.setAdapter(this.mAdapter);
//        this.viewPager.setOffscreenPageLimit(3);
//        this.slidingTabLayout.setViewPager(this.viewPager);
//        this.mAdapter.notifyDataSetChanged();
//        this.slidingTabLayout.notifyDataSetChanged();
//    }
//
//    public String getData(String data) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = new Date();
//        try {
//            time = dateFormat.parse(data);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return dateFormat.format(time);
//    }
//
//    public void onBackPressed() {
//        if (this.drawer.isDrawerOpen((int) GravityCompat.START)) {
//            this.drawer.closeDrawer((int) GravityCompat.START);
//        } else if (this.mBackKeyPressedTimes == 0) {
//            Toast.makeText(this, "再按一次退出程序 ", 0).show();
//            this.mBackKeyPressedTimes = 1;
//            new C07675().start();
//        } else {
//            closeActivity();
//            super.onBackPressed();
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == LOGIN_CODE && resultCode == -1) {
//            new Thread(this.taskUserInfor).start();
//        }
//    }
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case C0665R.id.img:
//                if (this.drawer.isDrawerOpen((int) GravityCompat.START)) {
//                    this.drawer.closeDrawer((int) GravityCompat.START);
//                    return;
//                } else {
//                    this.drawer.openDrawer((int) GravityCompat.START);
//                    return;
//                }
//            case C0665R.id.login_tv:
//                intentToResult(new Intent(this, LoginActivity.class), LOGIN_CODE);
//                return;
//            case C0665R.id.release_img:
//                if (UserUtils.isUserLogin()) {
//                    intentTo(new Intent(this, ReleaseActivity.class));
//                    return;
//                } else {
//                    intentToResult(new Intent(this, LoginActivity.class), LOGIN_CODE);
//                    return;
//                }
//            case C0665R.id.cancel_img:
//                new Builder(this).setTitle((CharSequence) "温馨提示").setMessage((CharSequence) "确定要取消上传？").setPositiveButton((CharSequence) "确定", new C075212()).setNegativeButton((CharSequence) "取消", new C075111()).show();
//                return;
//            case C0665R.id.drawer_root_ll:
//                closeDrawer();
//                return;
//            case C0665R.id.drawer_avatar_img:
//                System.out.println("userModel.getMu_id():" + UserUtils.getUserInfo().getMu_id());
//                Intent intent2 = new Intent(this, UserInformationActivity.class);
//                intent2.putExtra("u_id", UserUtils.getUserInfo().getMu_id());
//                intentToResult(intent2, LOGIN_CODE);
//                this.handler.postDelayed(new C075717(), 500);
//                return;
//            case C0665R.id.top_up_ll:
//                intentTo(new Intent(this, RechargeActivity.class));
//                this.handler.postDelayed(new C07719(), 500);
//                return;
//            case C0665R.id.exchange_ll:
//                intentToResult(new Intent(this, ExChangeActivity.class), LOGIN_CODE);
//                this.handler.postDelayed(new C075010(), 500);
//                return;
//            case C0665R.id.announcement_ll:
//                Intent intent = new Intent(this, MessageActivity.class);
//                intent.putExtra(MessageActivity.INDEX, 0);
//                intentTo(intent);
//                this.handler.postDelayed(new C07686(), 500);
//                return;
//            case C0665R.id.direct_message_ll:
//                Intent intent1 = new Intent(this, MessageActivity.class);
//                intent1.putExtra(MessageActivity.INDEX, 1);
//                intentTo(intent1);
//                this.handler.postDelayed(new C07697(), 500);
//                return;
//            case C0665R.id.drawer_collection_ll:
//                intentTo(new Intent(this, CollectionActivity.class));
//                this.handler.postDelayed(new C07708(), 500);
//                return;
//            case C0665R.id.drawer_app_ll:
//                intentTo(new Intent(this, AppActivity.class));
//                this.handler.postDelayed(new C075515(), 500);
//                return;
//            case C0665R.id.drawer_help_ll:
//                intentTo(new Intent(this, FQAActivity.class));
//                this.handler.postDelayed(new C075414(), 500);
//                return;
//            case C0665R.id.drawer_line_ll:
//                intentTo(new Intent(this, PlayLineActivity.class));
//                this.handler.postDelayed(new C075616(), 500);
//                return;
//            case C0665R.id.setting_delete_video_ll:
//                new Builder(this).setTitle(getString(C0665R.string.温馨提示)).setMessage(getString(C0665R.string.您确定要清除视频缓存吗)).setPositiveButton(getString(C0665R.string.确定), new C075919()).setNegativeButton(getString(C0665R.string.取消), new C075818()).show();
//                return;
//            case C0665R.id.drawer_setting_ll:
//                intentTo(new Intent(this, SettingActivity.class));
//                this.handler.postDelayed(new C075313(), 500);
//                return;
//            default:
//                return;
//        }
//    }
//
//    private void closeDrawer() {
//        if (this.drawer.isDrawerOpen((int) GravityCompat.START)) {
//            this.drawer.closeDrawer((int) GravityCompat.START);
//        }
//    }
//
//    public void checkUpdate(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("mv_code_num", AppUtils.getAppVersionCode(this));
//            jsonObject.put("mv_version", AppUtils.getAppVersionName(this));
//            jsonObject.put("type", "1");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C145320());
//    }
//
//    public void doUserInfo(String path) {
//        System.out.println("1111111111111");
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C145421());
//    }
//
//    public void getSystemInfo(String path) {
//        ((PostFormBuilder) OkHttpUtils.post().getParams().url(path)).build().execute(new C145522());
//    }
//
//    private void initPop() {
//        View popView = LayoutInflater.from(this).inflate(C0665R.layout.popwindow_notice, this.drawer, false);
//        ((TextView) popView.findViewById(C0665R.id.btn_sure)).setOnClickListener(new C076023());
//        this.notice_text = (TextView) popView.findViewById(C0665R.id.notice_text);
//        this.noticePop = new PopupWindow(popView, -1, -1);
//        this.noticePop.setFocusable(true);
//        this.noticePop.setOutsideTouchable(false);
//        this.noticePop.showAtLocation(this.drawer, 17, 0, 0);
//        this.notice_text.setText(((MessageModel) this.noticeList.get(0)).getMm_content());
//    }
//
//    private void getNotice(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("page", 1);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C145624());
//    }
//
//    private void getDnsUpdate() {
//        String ip = HttpDNS.getAddressByName(DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            checkUpdate(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VERSION);
//        } else {
//            checkUpdate(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VERSION);
//        }
//    }
//
//    private void getDnsUserInfor() {
//        String ip = HttpDNS.getAddressByName(DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            doUserInfo(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_USER_BAS);
//        } else {
//            doUserInfo(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_USER_BAS);
//        }
//    }
//
//    private void getDnsSystemInfo() {
//        String ip = HttpDNS.getAddressByName(DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getSystemInfo(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_SHARE);
//        } else {
//            getSystemInfo(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_SHARE);
//        }
//    }
//
//    private void getDnsNotice() {
//        String ip = HttpDNS.getAddressByName(DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getNotice(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_NEW_NOTICE);
//        } else {
//            getNotice(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_NEW_NOTICE);
//        }
//    }
//}