//package com.angcyo.kuaihu.analyze;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.os.Process;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class BaseActivity extends AppCompatActivity {
//    public static ArrayList<Activity> activityList = new ArrayList();
//    public TextView btn_next;
//    public TextView btn_pre;
//    private TextView content_tip;
//    public AlertDialog dialog;
//    protected int errorFlag1 = 0;
//    protected int errorFlag2 = 0;
//    protected int errorFlag3 = 0;
//    protected int errorFlag4 = 0;
//    protected int errorFlag5 = 0;
//    protected int errorFlag6 = 0;
//    private LVCircular lVCircularCD;
//    protected ArrayList<String> leftDomain1 = new ArrayList();
//    protected ArrayList<String> leftDomain2 = new ArrayList();
//    protected ArrayList<String> leftDomain3 = new ArrayList();
//    protected ArrayList<String> leftDomain4 = new ArrayList();
//    protected ArrayList<String> leftDomain5 = new ArrayList();
//    protected ArrayList<String> leftDomain6 = new ArrayList();
//    public TextView title;
//
//    protected void onStart() {
//        super.onStart();
//        MyApplication.getsInstance().addActivity(this);
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStatusBar();
//        setRequestedOrientation(1);
//        activityList.add(this);
//        SwipeBackHelper.onCreate(this);
//        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(true).setSwipeEdge(Callback.DEFAULT_DRAG_ANIMATION_DURATION).setSwipeEdgePercent(0.2f).setSwipeSensitivity(0.5f).setClosePercent(0.8f).setSwipeRelateEnable(false).setSwipeRelateOffset(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
//        View view = LayoutInflater.from(this).inflate(C0665R.layout.progressbar_pop_layout, null);
//        this.content_tip = (TextView) view.findViewById(C0665R.id.content_tip);
//        this.lVCircularCD = (LVCircular) view.findViewById(C0665R.id.lVCircularCD);
//        this.lVCircularCD.setRoundColor(getResources().getColor(C0665R.color.loading_view));
//        this.lVCircularCD.setViewColor(getResources().getColor(C0665R.color.loading_view));
//        Builder builder = new Builder(this);
//        builder.setView(view);
//        this.dialog = builder.create();
//        this.dialog.getWindow().setDimAmount(0.0f);
//        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
//    }
//
//    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onPostCreate(savedInstanceState, persistentState);
//        SwipeBackHelper.onPostCreate(this);
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        SwipeBackHelper.onDestroy(this);
//        activityList.remove(this);
//        if (this.dialog != null) {
//            this.lVCircularCD.stopAnim();
//            this.dialog.dismiss();
//        }
//    }
//
//    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, getResources().getColor(C0665R.color.colorAccent), 0);
//    }
//
//    public void intentTo(Intent intent) {
//        startActivity(intent);
//        if (getParent() != null) {
//            getParent().overridePendingTransition(C0665R.anim.slide_in_from_right, C0665R.anim.slide_out_from_left);
//        } else {
//            overridePendingTransition(C0665R.anim.slide_in_from_right, C0665R.anim.slide_out_from_left);
//        }
//    }
//
//    public void intentToResult(Intent intent, int tag) {
//        startActivityForResult(intent, tag);
//        if (getParent() != null) {
//            getParent().overridePendingTransition(C0665R.anim.slide_in_from_right, C0665R.anim.slide_out_from_left);
//        } else {
//            overridePendingTransition(C0665R.anim.slide_in_from_right, C0665R.anim.slide_out_from_left);
//        }
//    }
//
//    public void closeActivity() {
//        finish();
//        overridePendingTransition(C0665R.anim.slide_in_from_left, C0665R.anim.slide_out_from_right);
//    }
//
//    public void onBackPressed() {
//        closeActivity();
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == 0) {
//            exitApp();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void exitApp() {
//        if (activityList.size() > 0) {
//            Iterator it = activityList.iterator();
//            while (it.hasNext()) {
//                ((Activity) it.next()).finish();
//            }
//            Process.killProcess(Process.myPid());
//        }
//    }
//
//    public void setProgress() {
//        if (!isFinishing()) {
//            if (this.content_tip != null) {
//                this.content_tip.setText(getString(C0665R.string.loading));
//            }
//            if (this.dialog != null && !this.dialog.isShowing()) {
//                this.lVCircularCD.startAnim();
//                this.dialog.show();
//            }
//        }
//    }
//
//    public void setProgress(String tip) {
//        if (!isFinishing()) {
//            if (!(this.content_tip == null || TextUtils.isEmpty(tip))) {
//                this.content_tip.setText(tip);
//            }
//            if (this.dialog != null && !this.dialog.isShowing()) {
//                this.lVCircularCD.startAnim(JZVideoPlayer.FULL_SCREEN_NORMAL_DELAY);
//                this.dialog.show();
//            }
//        }
//    }
//
//    public void setCloseProgress() {
//        if (!isFinishing() && this.dialog != null && this.dialog.isShowing()) {
//            this.lVCircularCD.stopAnim();
//            this.dialog.dismiss();
//        }
//    }
//
//    protected String popDomain(ArrayList<String> dlist) {
//        if (dlist.size() <= 0) {
//            return "";
//        }
//        String tmp = (String) dlist.get(0);
//        dlist.remove(tmp);
//        return tmp;
//    }
//}