//package com.angcyo.kuaihu.analyze;
//
//import android.animation.Animator;
//import android.animation.Animator.AnimatorListener;
//import android.app.AlertDialog.Builder;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.support.p003v7.widget.RecyclerView;
//import android.support.p003v7.widget.RecyclerView.OnScrollListener;
//import android.text.Editable;
//import android.text.Layout.Alignment;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout.LayoutParams;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.angcyo.kuaihu.MainActivity;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.listener.OnItemClickListener;
//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;
//import com.danikula.videocache.CacheListener;
//import com.danikula.videocache.HttpProxyCacheServer;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.mylibrary.Constant;
//import com.mylibrary.okhttputils.OkHttpUtils;
//import com.mylibrary.okhttputils.builder.PostFormBuilder;
//import com.mylibrary.okhttputils.callback.StringCallback;
//import com.mylibrary.utils.AesEncryptionUtil;
//import com.mylibrary.utils.AppUtils;
//import com.mylibrary.utils.ConvertUtils;
//import com.mylibrary.utils.HttpDNS;
//import com.mylibrary.utils.ImageLoadUtil;
//import com.mylibrary.utils.KeyboardUtils;
//import com.mylibrary.utils.ScreenUtils;
//import com.mylibrary.utils.SpanUtils;
//import com.mylibrary.widget.imageview.CircleImageView;
//import com.mylibrary.widget.loadingLayout.LoadingLayout;
//import com.mylibrary.widget.loadingLayout.LoadingLayout.OnReloadListener;
//import com.mylibrary.widget.recyclerviewDecoration.FullyLinearLayoutManager;
//import com.orhanobut.hawk.Hawk;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//import com.xxx.foxvideo.C0665R;
//import com.xxx.foxvideo.activity.BaseActivity;
//import com.xxx.foxvideo.app.MyApplication;
//import com.xxx.foxvideo.model.BannerModel;
//import com.xxx.foxvideo.model.FloorReplyModel;
//import com.xxx.foxvideo.model.ShareModel;
//import com.xxx.foxvideo.model.SystemInfoModel;
//import com.xxx.foxvideo.model.UserModel;
//import com.xxx.foxvideo.model.VideoDetailModel;
//import com.xxx.foxvideo.model.VideoModel;
//import com.xxx.foxvideo.myinterface.EditTextDo;
//import com.xxx.foxvideo.p005ui.home.adapter.FloorAdapter;
//import com.xxx.foxvideo.p005ui.home.adapter.VideoBannerAdapter;
//import com.xxx.foxvideo.popup.SharePopup;
//import com.xxx.foxvideo.popup.SharePopup.OnShareClickListener;
//import com.xxx.foxvideo.popup.VipTipPopup;
//import com.xxx.foxvideo.utils.UserUtils;
//import java.io.File;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.regex.Pattern;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONException;
//import org.json.JSONObject;
//import p004cn.jzvd.JZVideoPlayer;
//import p004cn.jzvd.JZVideoPlayerStandard;
//
///* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity */
//public class VideoDetailActivity extends BaseActivity implements OnClickListener, EditTextDo, CacheListener {
//    public static final int REQUEST_SHARE_QQ_CODE = 1001;
//    public static final int REQUEST_SHARE_WECHAT_CODE = 1000;
//    public static final String VIDEO_MODEL = "VIDEO_MODEL";
//    private LayoutParams bannerImgLayoutParams;
//    private LayoutParams bannerJumpLayoutParams;
//    private List<BannerModel> bannerLists = new ArrayList();
//    private ImageView banner_img;
//    private Button banner_jump_bt;
//    private LoadingLayout card_detail_loading;
//    private File file;
//    private int floorPosition;
//    private Gson gson = new GsonBuilder().create();
//    private Handler handler = new Handler();
//    private TextView heard_announcement_tv;
//    private TextView heard_comment_tv;
//    private TextView heard_isVip_tip_tv;
//    private TextView heard_like_tv;
//    private RecyclerView heard_recyclerview;
//    private HttpProxyCacheServer httpProxyCacheServer;
//    private boolean isEnd = false;
//    private boolean islike = false;
//    private FullyLinearLayoutManager linearLayoutManager;
//    private FloorAdapter listCommentsAdapter;
//    private List<FloorReplyModel> listCommentsLists = new ArrayList();
//    private Type listCommentsModelType = new C12961().getType();
//    private Type listCommentsType = new C12972().getType();
//    private String mcParentId;
//    private String mcReplyuId;
//    private String mcText;
//    private LinearLayout movie_detail_edit_body_ll;
//    private MyCountDownTimer myCountDownTimer;
//    private View notLoadingView;
//    private int page = 1;
//    private int perPage = 20;
//    private LayoutParams qqLayoutParams;
//    private String sendType = "1";
//    private LayoutParams shareLayoutParams;
//    private SharePopup sharePopup;
//    private RelativeLayout.LayoutParams smartRefreshLayoutParams;
//    Runnable taskAddComment = new C081522();
//    Runnable taskAttentionUser = new C081724();
//    Runnable taskCollection = new C082027();
//    Runnable taskGetCommentData = new C081623();
//    Runnable taskLikeUser = new C081926();
//    Runnable taskVideoDetail = new C081825();
//    private VideoBannerAdapter videoBannerAdapter;
//    private VideoDetailModel videoDetailModel = new VideoDetailModel();
//    private Type videoDetailType = new C12983().getType();
//    private VideoModel videoModel;
//    private LayoutParams videoPlayerLayoutParams;
//    private CircleImageView video_detail_avatar_civ;
//    private TextView video_detail_cancel_tv;
//    private ImageView video_detail_colletion_img;
//    private EditText video_detail_edit;
//    private RelativeLayout video_detail_head_rl;
//    private LinearLayout video_detail_like_avatar_root_ll;
//    private ImageView video_detail_like_img;
//    private TextView video_detail_like_tv;
//    private RecyclerView video_detail_recyclerview;
//    private TextView video_detail_send_tv;
//    private ImageView video_detail_share_img;
//    private SmartRefreshLayout video_detail_smartRefreshLayout;
//    private Button video_qq;
//    private Button video_share;
//    private JZVideoPlayerStandard videoplayer;
//    private VipTipPopup vipTipPopup;
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$12 */
//    class C080212 implements DialogInterface.OnClickListener {
//        C080212() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$13 */
//    class C080313 implements DialogInterface.OnClickListener {
//        C080313() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            new Thread(VideoDetailActivity.this.taskAttentionUser).start();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$14 */
//    class C080414 implements DialogInterface.OnClickListener {
//        C080414() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$15 */
//    class C080515 implements DialogInterface.OnClickListener {
//        C080515() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            new Thread(VideoDetailActivity.this.taskCollection).start();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$22 */
//    class C081522 implements Runnable {
//        C081522() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsAddComment();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$23 */
//    class C081623 implements Runnable {
//        C081623() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsGetComment();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$24 */
//    class C081724 implements Runnable {
//        C081724() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsAttentionUser();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$25 */
//    class C081825 implements Runnable {
//        C081825() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsVideoDetail();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$26 */
//    class C081926 implements Runnable {
//        C081926() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsLikeUser();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$27 */
//    class C082027 implements Runnable {
//        C082027() {
//        }
//
//        public void run() {
//            VideoDetailActivity.this.getDnsCollection();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$6 */
//    class C08216 implements TextWatcher {
//        C08216() {
//        }
//
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if (s.length() > 0) {
//                VideoDetailActivity.this.video_detail_send_tv.setBackground(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.comment_bg_yes));
//                VideoDetailActivity.this.video_detail_send_tv.setTextColor(VideoDetailActivity.this.getResources().getColor(C0665R.color.white));
//                return;
//            }
//            VideoDetailActivity.this.video_detail_send_tv.setBackground(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.comment_bg_no));
//            VideoDetailActivity.this.video_detail_send_tv.setTextColor(VideoDetailActivity.this.getResources().getColor(C0665R.color.card_date));
//        }
//
//        public void afterTextChanged(Editable s) {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$MyCountDownTimer */
//    class MyCountDownTimer extends CountDownTimer {
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        public void onFinish() {
//            VideoDetailActivity.this.banner_jump_bt.setVisibility(8);
//        }
//
//        public void onTick(long millisUntilFinished) {
//            VideoDetailActivity.this.banner_jump_bt.setText("跳过  " + (millisUntilFinished / 1000));
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$11 */
//    class C129511 implements OnShareClickListener {
//        C129511() {
//        }
//
//        public void onClick(int what) {
//            switch (what) {
//                case 1000:
//                    Intent intent = new Intent();
//                    intent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
//                    intent.setAction("android.intent.action.SEND");
//                    intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(VideoDetailActivity.this.file));
//                    intent.setType("video/*");
//                    intent.setFlags(268435456);
//                    VideoDetailActivity.this.startActivity(Intent.createChooser(intent, "分享到"));
//                    return;
//                case 1001:
//                    Intent intent1 = new Intent();
//                    intent1.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
//                    intent1.setAction("android.intent.action.SEND");
//                    intent1.putExtra("android.intent.extra.STREAM", Uri.fromFile(VideoDetailActivity.this.file));
//                    intent1.setType("video/*");
//                    intent1.setFlags(268435456);
//                    VideoDetailActivity.this.startActivity(Intent.createChooser(intent1, "分享到"));
//                    return;
//                default:
//                    return;
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$1 */
//    class C12961 extends TypeToken<FloorReplyModel> {
//        C12961() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$2 */
//    class C12972 extends TypeToken<List<FloorReplyModel>> {
//        C12972() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$3 */
//    class C12983 extends TypeToken<VideoDetailModel> {
//        C12983() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$4 */
//    class C12994 implements OnReloadListener {
//        C12994() {
//        }
//
//        public void onReload(View v) {
//            VideoDetailActivity.this.page = 1;
//            VideoDetailActivity.this.getCommentData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_GET_COMMENT);
//            VideoDetailActivity.this.getVideoDetail(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VIDEO_DETAIL);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$5 */
//    class C13005 implements VipTipPopup.OnClickListener {
//        C13005() {
//        }
//
//        public void onClick() {
//            VideoDetailActivity.this.intentTo(new Intent(VideoDetailActivity.this, ExChangeActivity.class));
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$7 */
//    class C13017 implements OnRefreshListener {
//        C13017() {
//        }
//
//        public void onRefresh(final RefreshLayout refreshlayout) {
//            refreshlayout.getLayout().postDelayed(new Runnable() {
//                public void run() {
//                    VideoDetailActivity.this.page = 1;
//                    new Thread(VideoDetailActivity.this.taskGetCommentData).start();
//                    new Thread(VideoDetailActivity.this.taskVideoDetail).start();
//                    VideoDetailActivity.this.banner_jump_bt.setVisibility(0);
//                    VideoDetailActivity.this.handler.removeCallbacksAndMessages(null);
//                    if (VideoDetailActivity.this.myCountDownTimer != null) {
//                        VideoDetailActivity.this.myCountDownTimer.cancel();
//                    }
//                    refreshlayout.finishRefresh();
//                }
//            }, 2000);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$8 */
//    class C13028 implements OnLoadmoreListener {
//        C13028() {
//        }
//
//        public void onLoadmore(final RefreshLayout refreshlayout) {
//            refreshlayout.getLayout().postDelayed(new Runnable() {
//                public void run() {
//                    VideoDetailActivity.this.page = VideoDetailActivity.this.page + 1;
//                    new Thread(VideoDetailActivity.this.taskGetCommentData).start();
//                    refreshlayout.finishLoadmore();
//                    if (VideoDetailActivity.this.isEnd) {
//                        if (VideoDetailActivity.this.notLoadingView == null) {
//                            VideoDetailActivity.this.notLoadingView = VideoDetailActivity.this.getLayoutInflater().inflate(C0665R.layout.not_loading, (ViewGroup) VideoDetailActivity.this.video_detail_recyclerview.getParent(), false);
//                        }
//                        VideoDetailActivity.this.listCommentsAdapter.addFooterView(VideoDetailActivity.this.notLoadingView);
//                        refreshlayout.setLoadmoreFinished(true);
//                    }
//                }
//            }, 1000);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$9 */
//    class C13039 extends OnScrollListener {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$9$1 */
//        class C08241 implements AnimatorListener {
//            C08241() {
//            }
//
//            public void onAnimationStart(Animator animator) {
//            }
//
//            public void onAnimationEnd(Animator animator) {
//                VideoDetailActivity.this.movie_detail_edit_body_ll.setVisibility(0);
//            }
//
//            public void onAnimationCancel(Animator animator) {
//            }
//
//            public void onAnimationRepeat(Animator animator) {
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$9$2 */
//        class C08252 implements AnimatorListener {
//            C08252() {
//            }
//
//            public void onAnimationStart(Animator animator) {
//            }
//
//            public void onAnimationEnd(Animator animator) {
//                VideoDetailActivity.this.movie_detail_edit_body_ll.setVisibility(8);
//            }
//
//            public void onAnimationCancel(Animator animator) {
//            }
//
//            public void onAnimationRepeat(Animator animator) {
//            }
//        }
//
//        C13039() {
//        }
//
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            int visibleItemCount = ((FullyLinearLayoutManager) recyclerView.getLayoutManager()).getChildCount();
//            if (!VideoDetailActivity.this.isLandscape()) {
//                if (visibleItemCount > 5) {
//                    VideoDetailActivity.this.video_detail_head_rl.setBackgroundColor(VideoDetailActivity.this.getResources().getColor(C0665R.color.white));
//                } else {
//                    VideoDetailActivity.this.video_detail_head_rl.setBackgroundColor(VideoDetailActivity.this.getResources().getColor(C0665R.color.transparent));
//                }
//                if (VideoDetailActivity.this.video_detail_recyclerview.canScrollVertically(-1)) {
//                    if (VideoDetailActivity.this.movie_detail_edit_body_ll.getVisibility() == 8) {
//                        YoYo.with(Techniques.SlideInUp).duration(500).withListener(new C08241()).playOn(VideoDetailActivity.this.movie_detail_edit_body_ll);
//                    }
//                } else if (VideoDetailActivity.this.movie_detail_edit_body_ll.getVisibility() == 0) {
//                    YoYo.with(Techniques.SlideOutDown).duration(200).withListener(new C08252()).playOn(VideoDetailActivity.this.movie_detail_edit_body_ll);
//                }
//            }
//        }
//
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            VideoDetailActivity.this.mcParentId = "";
//            VideoDetailActivity.this.mcReplyuId = "";
//            VideoDetailActivity.this.sendType = "1";
//            KeyboardUtils.hideSoftInput(VideoDetailActivity.this);
//            if (TextUtils.isEmpty(VideoDetailActivity.this.video_detail_edit.getText())) {
//                VideoDetailActivity.this.video_detail_edit.setText("");
//                VideoDetailActivity.this.video_detail_edit.setHint("说点什么...(禁止留言联系方式，请送发私信)");
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$16 */
//    class C146116 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$16$1 */
//        class C08061 implements Runnable {
//            C08061() {
//            }
//
//            public void run() {
//                VideoDetailActivity.this.setProgress("发布评论中..");
//            }
//        }
//
//        C146116() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//            VideoDetailActivity.this.handler.post(new C08061());
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
//            VideoDetailActivity.this.setCloseProgress();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                if (jsonObject.getInt("code") == 0) {
//                    FloorReplyModel floorReplyModel = (FloorReplyModel) VideoDetailActivity.this.gson.fromJson(jsonObject.getJSONObject("data").toString(), VideoDetailActivity.this.listCommentsModelType);
//                    if (VideoDetailActivity.this.sendType.equals("1")) {
//                        if (VideoDetailActivity.this.listCommentsAdapter.getData().size() == 0) {
//                            VideoDetailActivity.this.listCommentsAdapter.addData((Object) floorReplyModel);
//                        } else {
//                            VideoDetailActivity.this.listCommentsAdapter.addData(0, (Object) floorReplyModel);
//                        }
//                        VideoDetailActivity.this.toClickPosition(1);
//                    } else if (VideoDetailActivity.this.sendType.equals("2")) {
//                        VideoDetailActivity.this.listCommentsLists.set(VideoDetailActivity.this.floorPosition - 1, floorReplyModel);
//                    } else if (VideoDetailActivity.this.sendType.equals("3")) {
//                        VideoDetailActivity.this.listCommentsLists.set(VideoDetailActivity.this.floorPosition - 1, floorReplyModel);
//                    }
//                    VideoDetailActivity.this.listCommentsAdapter.notifyDataSetChanged();
//                    VideoDetailActivity.this.video_detail_edit.setText("");
//                    VideoDetailActivity.this.video_detail_edit.setHint("说点什么...(禁止留言联系方式，请送发私信)");
//                    VideoDetailActivity.this.mcParentId = "";
//                    VideoDetailActivity.this.mcReplyuId = "";
//                    VideoDetailActivity.this.sendType = "1";
//                    VideoDetailActivity.this.mcText = "";
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$17 */
//    class C146217 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$17$1 */
//        class C08081 implements OnClickListener {
//
//            /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$17$1$1 */
//            class C08071 implements Runnable {
//                C08071() {
//                }
//
//                public void run() {
//                    VideoDetailActivity.this.video_detail_edit.requestFocus();
//                    KeyboardUtils.showSoftInput(VideoDetailActivity.this, VideoDetailActivity.this.video_detail_edit);
//                }
//            }
//
//            C08081() {
//            }
//
//            public void onClick(View view) {
//                VideoDetailActivity.this.video_detail_recyclerview.post(new C08071());
//            }
//        }
//
//        C146217() {
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
//                List<FloorReplyModel> tempList = new ArrayList();
//                if (jsonObject.getInt("code") == 0) {
//                    tempList = (List) VideoDetailActivity.this.gson.fromJson(jsonObject.getJSONObject("data").getJSONArray("list").toString(), VideoDetailActivity.this.listCommentsType);
//                    if (tempList.isEmpty() || tempList.size() <= 0) {
//                        VideoDetailActivity.this.isEnd = true;
//                    } else if (tempList.size() < VideoDetailActivity.this.perPage) {
//                        VideoDetailActivity.this.isEnd = true;
//                    } else {
//                        VideoDetailActivity.this.isEnd = false;
//                    }
//                    if (VideoDetailActivity.this.page == 1) {
//                        VideoDetailActivity.this.listCommentsLists.clear();
//                        VideoDetailActivity.this.listCommentsLists.addAll(tempList);
//                        VideoDetailActivity.this.listCommentsAdapter.setNewData(VideoDetailActivity.this.listCommentsLists);
//                        VideoDetailActivity.this.listCommentsAdapter.removeAllFooterView();
//                        VideoDetailActivity.this.video_detail_smartRefreshLayout.setLoadmoreFinished(false);
//                        VideoDetailActivity.this.listCommentsAdapter.notifyDataSetChanged();
//                        if (VideoDetailActivity.this.listCommentsLists.size() == 0 && VideoDetailActivity.this.listCommentsLists.isEmpty()) {
//                            VideoDetailActivity.this.video_detail_smartRefreshLayout.setLoadmoreFinished(true);
//                            LinearLayout emptyView = (LinearLayout) VideoDetailActivity.this.getLayoutInflater().inflate(C0665R.layout.empty_comment, (ViewGroup) VideoDetailActivity.this.video_detail_recyclerview.getParent(), false);
//                            emptyView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
//                            emptyView.setOnClickListener(new C08081());
//                            VideoDetailActivity.this.listCommentsAdapter.setEmptyView((View) emptyView);
//                            VideoDetailActivity.this.listCommentsAdapter.setHeaderAndEmpty(true);
//                            VideoDetailActivity.this.listCommentsAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                        return;
//                    }
//                    VideoDetailActivity.this.listCommentsAdapter.addData((Collection) tempList);
//                    VideoDetailActivity.this.listCommentsAdapter.notifyDataSetChanged();
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$18 */
//    class C146318 extends StringCallback {
//        C146318() {
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
//                if (jsonObject.getInt("code") != 0) {
//                    Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//                } else if (VideoDetailActivity.this.videoDetailModel.getIs_attention() == 1) {
//                    VideoDetailActivity.this.videoDetailModel.setIs_attention(0);
//                    VideoDetailActivity.this.video_detail_like_tv.setText("+关注");
//                    VideoDetailActivity.this.video_detail_like_avatar_root_ll.setBackgroundDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.detail_focus_no_bg));
//                    Toast.makeText(VideoDetailActivity.this, "取消关注", 0).show();
//                } else {
//                    VideoDetailActivity.this.videoDetailModel.setIs_attention(1);
//                    VideoDetailActivity.this.video_detail_like_tv.setText("已关注");
//                    VideoDetailActivity.this.video_detail_like_avatar_root_ll.setBackgroundDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.detail_focus_yes_bg));
//                    Toast.makeText(VideoDetailActivity.this, "关注成功", 0).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19 */
//    class C146419 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$1 */
//        class C08091 implements Runnable {
//            C08091() {
//            }
//
//            public void run() {
//                if (VideoDetailActivity.this.page == 1) {
//                    VideoDetailActivity.this.card_detail_loading.setStatus(4);
//                }
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$2 */
//        class C08102 implements Runnable {
//            C08102() {
//            }
//
//            public void run() {
//                if (VideoDetailActivity.this.page == 1) {
//                    VideoDetailActivity.this.card_detail_loading.setStatus(2);
//                }
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$3 */
//        class C08113 implements OnClickListener {
//            C08113() {
//            }
//
//            public void onClick(View view) {
//                if (!TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getAds().getCa_web_url()) && Pattern.matches("[a-zA-z]+://[^\\s]*", VideoDetailActivity.this.videoDetailModel.getAds().getCa_web_url())) {
//                    VideoDetailActivity.this.intentTo(new Intent("android.intent.action.VIEW", Uri.parse(VideoDetailActivity.this.videoDetailModel.getAds().getCa_web_url())));
//                }
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$4 */
//        class C08134 implements OnClickListener {
//
//            /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$4$1 */
//            class C08121 implements Runnable {
//                C08121() {
//                }
//
//                public void run() {
//                    Toast.makeText(AppUtils.getAppContext(), "开通vip才能跳过广告！", 0).show();
//                }
//            }
//
//            C08134() {
//            }
//
//            public void onClick(View view) {
//                if (((UserModel) Hawk.get("user")) == null || TextUtils.isEmpty(((UserModel) Hawk.get("user")).getIs_vip()) || !((UserModel) Hawk.get("user")).getIs_vip().equals("1")) {
//                    VideoDetailActivity.this.handler.post(new C08121());
//                    return;
//                }
//                VideoDetailActivity.this.banner_jump_bt.setVisibility(8);
//                VideoDetailActivity.this.initPlay();
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$19$5 */
//        class C08145 implements Runnable {
//            C08145() {
//            }
//
//            public void run() {
//                VideoDetailActivity.this.initPlay();
//            }
//        }
//
//        C146419() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//            VideoDetailActivity.this.handler.post(new C08091());
//        }
//
//        public void onAfter(int id) {
//            super.onAfter(id);
//        }
//
//        public void onError(Call call, Exception e, int id) {
//            VideoDetailActivity.this.handler.post(new C08102());
//        }
//
//        public void onResponse(String response, int id) {
//            VideoDetailActivity.this.card_detail_loading.setStatus(0);
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                if (code == 0) {
//                    VideoDetailActivity.this.videoDetailModel = (VideoDetailModel) VideoDetailActivity.this.gson.fromJson(jsonObject.getJSONObject("data").toString(), VideoDetailActivity.this.videoDetailType);
//                    if (VideoDetailActivity.this.videoDetailModel.getAds() == null || TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getAds().getCa_img_url())) {
//                        VideoDetailActivity.this.initPlay();
//                    } else {
//                        VideoDetailActivity.this.banner_img.setVisibility(0);
//                        VideoDetailActivity.this.videoplayer.setVisibility(8);
//                        ImageLoadUtil.loadImageView(VideoDetailActivity.this.videoDetailModel.getAds().getCa_img_url(), VideoDetailActivity.this.banner_img, C0665R.color.black);
//                        VideoDetailActivity.this.banner_img.setOnClickListener(new C08113());
//                        VideoDetailActivity.this.banner_jump_bt.setOnClickListener(new C08134());
//                        VideoDetailActivity.this.myCountDownTimer = new MyCountDownTimer(OkHttpUtils.DEFAULT_MILLISECONDS, 1000);
//                        VideoDetailActivity.this.myCountDownTimer.start();
//                        VideoDetailActivity.this.handler.postDelayed(new C08145(), OkHttpUtils.DEFAULT_MILLISECONDS);
//                    }
//                    if (!TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getIs_attention() + "")) {
//                        if (VideoDetailActivity.this.videoDetailModel.getIs_attention() == 0) {
//                            VideoDetailActivity.this.video_detail_like_tv.setText("+关注");
//                            VideoDetailActivity.this.video_detail_like_avatar_root_ll.setBackgroundDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.detail_focus_no_bg));
//                        } else {
//                            VideoDetailActivity.this.video_detail_like_tv.setText("已关注");
//                            VideoDetailActivity.this.video_detail_like_avatar_root_ll.setBackgroundDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.detail_focus_yes_bg));
//                        }
//                    }
//                    if (!TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getIs_collect() + "")) {
//                        if (VideoDetailActivity.this.videoDetailModel.getIs_collect() != 0) {
//                            VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion_select));
//                        } else if (VideoDetailActivity.this.isLandscape()) {
//                            VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion));
//                        } else {
//                            VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion_white));
//                        }
//                    }
//                    ImageLoadUtil.loadImageView(VideoDetailActivity.this.videoDetailModel.getMu_avatar(), VideoDetailActivity.this.video_detail_avatar_civ, C0665R.drawable.detail_avatar_secret);
//                    VideoDetailActivity.this.heard_announcement_tv.setText(new SpanUtils().appendImage((int) C0665R.drawable.icon_announcement, 2).append(VideoDetailActivity.this.videoDetailModel.getMu_name() + ":").setForegroundColor(Color.parseColor("#33b5e5")).setAlign(Alignment.ALIGN_CENTER).append(VideoDetailActivity.this.videoDetailModel.getMv_title()).setAlign(Alignment.ALIGN_CENTER).create());
//                    if (TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getMv_like())) {
//                        VideoDetailActivity.this.heard_like_tv.setText("0 喜欢");
//                    } else {
//                        VideoDetailActivity.this.heard_like_tv.setText(VideoDetailActivity.this.videoDetailModel.getMv_like() + " 喜欢");
//                    }
//                    if (TextUtils.isEmpty(VideoDetailActivity.this.videoDetailModel.getMv_comment())) {
//                        VideoDetailActivity.this.heard_comment_tv.setText("0 评论");
//                    } else {
//                        VideoDetailActivity.this.heard_comment_tv.setText(VideoDetailActivity.this.videoDetailModel.getMv_comment() + " 评论");
//                    }
//                } else if (code == 1) {
//                    Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//                } else {
//                    Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$20 */
//    class C146520 extends StringCallback {
//        C146520() {
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
//                if (jsonObject.getInt("code") == 0) {
//                    VideoDetailActivity.this.islike = true;
//                    VideoDetailActivity.this.video_detail_like_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_like_selected));
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$21 */
//    class C146621 extends StringCallback {
//        C146621() {
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
//                if (jsonObject.getInt("code") != 0) {
//                    Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//                } else if (VideoDetailActivity.this.videoDetailModel.getIs_collect() == 1) {
//                    VideoDetailActivity.this.videoDetailModel.setIs_collect(0);
//                    if (VideoDetailActivity.this.isLandscape()) {
//                        VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion));
//                    } else {
//                        VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion_white));
//                    }
//                    Toast.makeText(VideoDetailActivity.this, "取消收藏", 0).show();
//                } else {
//                    VideoDetailActivity.this.videoDetailModel.setIs_collect(1);
//                    VideoDetailActivity.this.video_detail_colletion_img.setImageDrawable(VideoDetailActivity.this.getResources().getDrawable(C0665R.drawable.icon_colletion_select));
//                    Toast.makeText(VideoDetailActivity.this, "收藏成功", 0).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.VideoDetailActivity$10 */
//    class C148810 extends OnItemClickListener {
//        C148810() {
//        }
//
//        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//            if (!TextUtils.isEmpty(((BannerModel) VideoDetailActivity.this.bannerLists.get(position)).getCa_web_url()) && Pattern.matches("[a-zA-z]+://[^\\s]*", ((BannerModel) VideoDetailActivity.this.bannerLists.get(position)).getCa_web_url())) {
//                VideoDetailActivity.this.intentTo(new Intent("android.intent.action.VIEW", Uri.parse(((BannerModel) VideoDetailActivity.this.bannerLists.get(position)).getCa_web_url())));
//            }
//        }
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView((int) C0665R.layout.activity_video_detail);
//        initDate();
//        initView();
//    }
//
//    public void onBackPressed() {
//        if (this.videoplayer == null || !JZVideoPlayer.backPress()) {
//            super.onBackPressed();
//        }
//    }
//
//    protected void onPause() {
//        super.onPause();
//        if (this.videoplayer != null) {
//            JZVideoPlayer.releaseAllVideos();
//        }
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        if (!TextUtils.isEmpty(this.videoDetailModel.getMv_play_url())) {
//            this.httpProxyCacheServer.unregisterCacheListener(this);
//        }
//        this.handler.removeCallbacksAndMessages(null);
//        if (this.myCountDownTimer != null) {
//            this.myCountDownTimer.cancel();
//        }
//    }
//
//    private void initDate() {
//        this.videoModel = (VideoModel) getIntent().getSerializableExtra(VIDEO_MODEL);
//        this.httpProxyCacheServer = MyApplication.getProxy(AppUtils.getAppContext());
//        new Thread(this.taskGetCommentData).start();
//        new Thread(this.taskVideoDetail).start();
//    }
//
//    private void initView() {
//        this.card_detail_loading = (LoadingLayout) findViewById(C0665R.id.card_detail_loading);
//        this.card_detail_loading.setOnReloadListener(new C12994());
//        this.vipTipPopup = new VipTipPopup(this);
//        this.vipTipPopup.setOnClickListenerr(new C13005());
//        this.video_detail_head_rl = (RelativeLayout) findViewById(C0665R.id.video_detail_head_rl);
//        this.video_detail_cancel_tv = (TextView) findViewById(C0665R.id.video_detail_cancel_tv);
//        this.video_detail_cancel_tv.setOnClickListener(this);
//        this.video_detail_share_img = (ImageView) findViewById(C0665R.id.video_detail_share_img);
//        this.video_detail_share_img.setOnClickListener(this);
//        this.video_detail_like_img = (ImageView) findViewById(C0665R.id.video_detail_like_img);
//        this.video_detail_like_img.setOnClickListener(this);
//        this.video_detail_colletion_img = (ImageView) findViewById(C0665R.id.video_detail_colletion_img);
//        this.video_detail_colletion_img.setOnClickListener(this);
//        this.video_detail_like_avatar_root_ll = (LinearLayout) findViewById(C0665R.id.video_detail_like_avatar_root_ll);
//        this.video_detail_like_tv = (TextView) findViewById(C0665R.id.video_detail_like_tv);
//        this.video_detail_like_tv.setOnClickListener(this);
//        this.video_detail_avatar_civ = (CircleImageView) findViewById(C0665R.id.video_detail_avatar_civ);
//        this.video_detail_avatar_civ.setOnClickListener(this);
//        if (UserUtils.getUserId().equals(this.videoModel.getMu_id())) {
//            this.video_detail_like_avatar_root_ll.setVisibility(8);
//            this.video_detail_colletion_img.setVisibility(8);
//        } else {
//            this.video_detail_like_avatar_root_ll.setVisibility(0);
//            this.video_detail_colletion_img.setVisibility(0);
//        }
//        this.movie_detail_edit_body_ll = (LinearLayout) findViewById(C0665R.id.movie_detail_edit_body_ll);
//        this.video_detail_edit = (EditText) findViewById(C0665R.id.video_detail_edit);
//        this.video_detail_send_tv = (TextView) findViewById(C0665R.id.video_detail_send_tv);
//        this.video_detail_send_tv.setOnClickListener(this);
//        this.video_detail_edit.addTextChangedListener(new C08216());
//        this.video_detail_recyclerview = (RecyclerView) findViewById(C0665R.id.video_detail_recyclerview);
//        this.linearLayoutManager = new FullyLinearLayoutManager((Context) this);
//        this.video_detail_recyclerview.setLayoutManager(this.linearLayoutManager);
//        this.listCommentsAdapter = new FloorAdapter(this.listCommentsLists, this);
//        this.video_detail_recyclerview.setHasFixedSize(true);
//        this.video_detail_recyclerview.setAdapter(this.listCommentsAdapter);
//        this.video_detail_smartRefreshLayout = (SmartRefreshLayout) findViewById(C0665R.id.video_detail_smartRefreshLayout);
//        this.video_detail_smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
//        this.video_detail_smartRefreshLayout.setDisableContentWhenRefresh(false);
//        this.video_detail_smartRefreshLayout.setDisableContentWhenLoading(false);
//        this.video_detail_smartRefreshLayout.setOnRefreshListener(new C13017());
//        this.video_detail_smartRefreshLayout.setOnLoadmoreListener(new C13028());
//        this.video_detail_recyclerview.setOnScrollListener(new C13039());
//        View headView = getLayoutInflater().inflate(C0665R.layout.header_video_detail, this.video_detail_recyclerview, false);
//        this.listCommentsAdapter.addHeaderView(headView);
//        this.heard_announcement_tv = (TextView) headView.findViewById(C0665R.id.heard_announcement_tv);
//        this.heard_like_tv = (TextView) headView.findViewById(C0665R.id.heard_like_tv);
//        this.heard_comment_tv = (TextView) headView.findViewById(C0665R.id.heard_comment_tv);
//        this.videoplayer = (JZVideoPlayerStandard) headView.findViewById(C0665R.id.videoplayer);
//        this.banner_jump_bt = (Button) headView.findViewById(C0665R.id.banner_jump_bt);
//        this.banner_img = (ImageView) headView.findViewById(C0665R.id.banner_img);
//        this.heard_isVip_tip_tv = (TextView) headView.findViewById(C0665R.id.heard_isVip_tip_tv);
//        this.heard_isVip_tip_tv.setSelected(true);
//        this.heard_recyclerview = (RecyclerView) headView.findViewById(C0665R.id.heard_recyclerview);
//        this.heard_recyclerview.setLayoutManager(new FullyLinearLayoutManager((Context) this));
//        this.heard_recyclerview.setHasFixedSize(true);
//        this.videoBannerAdapter = new VideoBannerAdapter(this.bannerLists);
//        this.heard_recyclerview.setAdapter(this.videoBannerAdapter);
//        SystemInfoModel systemInfoModel = (SystemInfoModel) Hawk.get(MainActivity.SYSTEM_INFO);
//        if (systemInfoModel != null) {
//            List<BannerModel> adv = systemInfoModel.getAdv();
//            if (adv.size() == 0 || adv.isEmpty()) {
//                this.heard_recyclerview.setVisibility(8);
//            } else {
//                this.heard_recyclerview.setVisibility(0);
//                this.bannerLists.clear();
//                this.bannerLists.addAll(adv);
//                this.videoBannerAdapter.notifyDataSetChanged();
//            }
//            List<ShareModel> system = systemInfoModel.getSystem();
//            if (system.size() == 0 || system.size() <= 3) {
//                this.heard_isVip_tip_tv.setVisibility(8);
//            } else {
//                this.heard_isVip_tip_tv.setVisibility(0);
//                if (!TextUtils.isEmpty(((ShareModel) system.get(3)).getMs_help())) {
//                    this.heard_isVip_tip_tv.setText(((ShareModel) system.get(3)).getMs_help());
//                }
//            }
//        } else {
//            this.heard_recyclerview.setVisibility(8);
//            this.heard_isVip_tip_tv.setVisibility(8);
//        }
//        this.heard_recyclerview.addOnItemTouchListener(new C148810());
//        this.smartRefreshLayoutParams = (RelativeLayout.LayoutParams) this.video_detail_smartRefreshLayout.getLayoutParams();
//        this.videoPlayerLayoutParams = (LayoutParams) this.videoplayer.getLayoutParams();
//        this.bannerImgLayoutParams = (LayoutParams) this.banner_img.getLayoutParams();
//        this.bannerJumpLayoutParams = (LayoutParams) this.banner_jump_bt.getLayoutParams();
//        this.video_share = (Button) headView.findViewById(C0665R.id.video_share);
//        this.video_share.setVisibility(8);
//        this.shareLayoutParams = (LayoutParams) this.video_share.getLayoutParams();
//        this.video_qq = (Button) headView.findViewById(C0665R.id.video_qq);
//        this.video_qq.setVisibility(8);
//        this.qqLayoutParams = (LayoutParams) this.video_qq.getLayoutParams();
//        if (isLandscape()) {
//            this.smartRefreshLayoutParams.topMargin = ConvertUtils.dp2px(this, 45.0f);
//            this.smartRefreshLayoutParams.bottomMargin = ConvertUtils.dp2px(this, 50.0f);
//            this.movie_detail_edit_body_ll.setVisibility(0);
//            this.video_detail_cancel_tv.setCompoundDrawablesWithIntrinsicBounds(C0665R.drawable.return_re, 0, 0, 0);
//            this.video_detail_like_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_like_normal));
//            this.video_detail_share_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_share_normal));
//            this.video_detail_colletion_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_colletion));
//            this.videoPlayerLayoutParams.height = ConvertUtils.dp2px(this, 250.0f);
//            this.bannerImgLayoutParams.height = ConvertUtils.dp2px(this, 250.0f);
//            this.banner_img.setScaleType(ScaleType.FIT_CENTER);
//            this.bannerJumpLayoutParams.topMargin = ConvertUtils.dp2px(this, 20.0f);
//            this.shareLayoutParams.rightMargin = ConvertUtils.dp2px(this, 10.0f);
//            this.shareLayoutParams.bottomMargin = ConvertUtils.dp2px(this, 50.0f);
//            this.qqLayoutParams.rightMargin = ConvertUtils.dp2px(this, 10.0f);
//            this.qqLayoutParams.bottomMargin = ConvertUtils.dp2px(this, 100.0f);
//        } else {
//            this.movie_detail_edit_body_ll.setVisibility(8);
//            this.video_detail_cancel_tv.setCompoundDrawablesWithIntrinsicBounds(C0665R.drawable.return_re_white, 0, 0, 0);
//            this.video_detail_like_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_like_white));
//            this.video_detail_share_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_share_white));
//            this.video_detail_colletion_img.setImageDrawable(getResources().getDrawable(C0665R.drawable.icon_colletion_white));
//            this.videoPlayerLayoutParams.height = ScreenUtils.getScreenHeight();
//            this.bannerImgLayoutParams.height = ScreenUtils.getScreenHeight();
//            this.banner_img.setScaleType(ScaleType.FIT_CENTER);
//            this.bannerJumpLayoutParams.topMargin = ConvertUtils.dp2px(this, 55.0f);
//            this.shareLayoutParams.rightMargin = ConvertUtils.dp2px(this, 10.0f);
//            this.shareLayoutParams.bottomMargin = ConvertUtils.dp2px(this, 80.0f);
//            this.qqLayoutParams.rightMargin = ConvertUtils.dp2px(this, 10.0f);
//            this.qqLayoutParams.bottomMargin = ConvertUtils.dp2px(this, 130.0f);
//        }
//        this.video_detail_smartRefreshLayout.setLayoutParams(this.smartRefreshLayoutParams);
//        this.videoplayer.setLayoutParams(this.videoPlayerLayoutParams);
//        this.banner_img.setLayoutParams(this.bannerImgLayoutParams);
//        this.banner_jump_bt.setLayoutParams(this.bannerJumpLayoutParams);
//        this.video_share.setLayoutParams(this.shareLayoutParams);
//        this.video_qq.setLayoutParams(this.qqLayoutParams);
//        this.video_share.setOnClickListener(this);
//        this.video_qq.setOnClickListener(this);
//        this.sharePopup = new SharePopup(this);
//        this.sharePopup.setOnShareClickListener(new C129511());
//    }
//
//    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
//        if (percentsAvailable == 100) {
//            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
//            intent.setData(Uri.fromFile(cacheFile));
//            sendBroadcast(intent);
//            this.file = cacheFile;
//            this.video_share.setVisibility(0);
//            this.video_qq.setVisibility(0);
//        }
//    }
//
//    public boolean isLandscape() {
//        if (Integer.valueOf(this.videoModel.getMv_play_width()).intValue() / Integer.valueOf(this.videoModel.getMv_play_height()).intValue() > 1 || Integer.valueOf(this.videoModel.getMv_play_width()).intValue() / Integer.valueOf(this.videoModel.getMv_play_height()).intValue() == 1) {
//            return true;
//        }
//        return false;
//    }
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case C0665R.id.video_detail_cancel_tv:
//                setResult(-1, new Intent());
//                closeActivity();
//                return;
//            case C0665R.id.video_detail_like_img:
//                if (!this.islike) {
//                    new Thread(this.taskLikeUser).start();
//                    return;
//                }
//                return;
//            case C0665R.id.video_detail_share_img:
//                intentTo(new Intent(this, ShareActivity.class));
//                return;
//            case C0665R.id.video_detail_colletion_img:
//                if (this.videoDetailModel.getIs_collect() == 0) {
//                    new Thread(this.taskCollection).start();
//                    return;
//                } else {
//                    new Builder(this).setTitle("提示").setMessage("你确定取消收藏该帖子？").setPositiveButton("确定", new C080515()).setNegativeButton("取消", new C080414()).show();
//                    return;
//                }
//            case C0665R.id.video_detail_like_tv:
//                if (this.videoDetailModel.getIs_attention() == 0) {
//                    new Thread(this.taskAttentionUser).start();
//                    return;
//                } else {
//                    new Builder(this).setTitle("提示").setMessage("你确定不再关注Ta？").setPositiveButton("确定", new C080313()).setNegativeButton("取消", new C080212()).show();
//                    return;
//                }
//            case C0665R.id.video_detail_avatar_civ:
//                Intent intent = new Intent(this, UserInformationActivity.class);
//                intent.putExtra("u_id", this.videoModel.getMu_id());
//                intentTo(intent);
//                return;
//            case C0665R.id.video_qq:
//                if (this.file.exists()) {
//                    this.sharePopup.showPopupWindow();
//                    return;
//                } else {
//                    Toast.makeText(this, "视频文件不存在", 0).show();
//                    return;
//                }
//            case C0665R.id.video_share:
//                if (this.file.exists()) {
//                    this.sharePopup.showPopupWindow();
//                    return;
//                } else {
//                    Toast.makeText(this, "视频文件不存在", 0).show();
//                    return;
//                }
//            case C0665R.id.video_detail_send_tv:
//                if (TextUtils.isEmpty(this.video_detail_edit.getText().toString().trim())) {
//                    Toast.makeText(this, "评论内容不能为空...", 0).show();
//                    return;
//                }
//                if (UserUtils.isUserLogin()) {
//                    this.mcText = this.video_detail_edit.getText().toString().trim();
//                    new Thread(this.taskAddComment).start();
//                } else {
//                    intentTo(new Intent(this, LoginActivity.class));
//                }
//                KeyboardUtils.hideSoftInput(this);
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void toClickPosition(int position) {
//        this.linearLayoutManager.scrollToPositionWithOffset(position, 0);
//    }
//
//    public void getFocus() {
//        this.video_detail_edit.requestFocus();
//        KeyboardUtils.showSoftInput(this, this.video_detail_edit);
//    }
//
//    public void replyFloor(String mc_id, String mu_id, String mu_name, int position, String sendtype) {
//        this.mcParentId = mc_id;
//        this.floorPosition = position;
//        this.sendType = sendtype;
//        this.mcReplyuId = mu_id;
//        if (this.sendType.equals("2")) {
//            this.video_detail_edit.setHint("请输入评论内容");
//        } else if (this.sendType.equals("3")) {
//            this.video_detail_edit.setHint("回复" + mu_name + ":");
//        }
//    }
//
//    public void deltetFloor(String mc_id, String mu_id, int position, String deletetype) {
//    }
//
//    public void addComment(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//            jsonObject.put("mvId", this.videoModel.getMv_id());
//            jsonObject.put("mcText", this.mcText);
//            jsonObject.put("mcParentId", this.mcParentId);
//            jsonObject.put("mcReplyuId", this.mcReplyuId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146116());
//    }
//
//    public void getCommentData(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("mvId", this.videoModel.getMv_id());
//            jsonObject.put("perPage", this.perPage);
//            jsonObject.put("page", this.page);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146217());
//    }
//
//    public void attentionUser(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//            jsonObject.put("attentionId", this.videoModel.getMu_id());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146318());
//    }
//
//    public void getVideoDetail(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//            jsonObject.put("mvId", this.videoModel.getMv_id());
//            jsonObject.put("type", Hawk.get("line", "2"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146419());
//    }
//
//    private void initPlay() {
//        this.banner_img.setVisibility(8);
//        this.videoplayer.setVisibility(0);
//        if (this.videoDetailModel.getIs_play() == 1) {
//            this.httpProxyCacheServer.registerCacheListener(this, this.videoDetailModel.getMv_play_url());
//            String proxyUrl = this.httpProxyCacheServer.getProxyUrl(this.videoDetailModel.getMv_play_url());
//            if (this.httpProxyCacheServer.isCached(this.videoDetailModel.getMv_play_url())) {
//                this.video_share.setVisibility(0);
//                this.video_qq.setVisibility(0);
//                System.out.println("AA:" + Uri.parse(proxyUrl).getPath());
//                this.file = new File(Uri.parse(proxyUrl).getPath());
//            } else {
//                this.video_share.setVisibility(8);
//                this.video_qq.setVisibility(8);
//            }
//            this.videoplayer.setUp(proxyUrl, 0, new Object[0]);
//            ImageLoadUtil.loadImageView(this.videoDetailModel.getMv_img_url(), this.videoplayer.thumbImageView, C0665R.color.bg_color_1);
//            this.videoplayer.startVideo();
//            return;
//        }
//        this.videoplayer.setUp("", 0, new Object[0]);
//        ImageLoadUtil.loadImageView(this.videoDetailModel.getMv_img_url(), this.videoplayer.thumbImageView, C0665R.color.bg_color_1);
//        if (this.vipTipPopup != null && !isFinishing()) {
//            this.vipTipPopup.showPopupWindow();
//        }
//    }
//
//    public void likenUser(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("mvId", this.videoModel.getMv_id());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146520());
//    }
//
//    public void collection(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//            jsonObject.put("mvId", this.videoModel.getMv_id());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C146621());
//    }
//
//    private void getDnsAddComment() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            addComment(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_ADD_COMMENT);
//        } else {
//            addComment(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_ADD_COMMENT);
//        }
//    }
//
//    private void getDnsGetComment() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getCommentData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_GET_COMMENT);
//        } else {
//            getCommentData(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_GET_COMMENT);
//        }
//    }
//
//    private void getDnsAttentionUser() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            attentionUser(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_ATTENTION_USER);
//        } else {
//            attentionUser(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_ATTENTION_USER);
//        }
//    }
//
//    private void getDnsVideoDetail() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getVideoDetail(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VIDEO_DETAIL);
//        } else {
//            getVideoDetail(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_DETAIL);
//        }
//    }
//
//    private void getDnsLikeUser() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            likenUser(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_LIKE_USER);
//        } else {
//            likenUser(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_LIKE_USER);
//        }
//    }
//
//    private void getDnsCollection() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            collection(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_COLLECTION);
//        } else {
//            collection(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_COLLECTION);
//        }
//    }
//}