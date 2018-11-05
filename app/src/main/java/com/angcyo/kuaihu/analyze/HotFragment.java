//package com.angcyo.kuaihu.analyze;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//
//import java.io.Serializable;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.regex.Pattern;
//
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.angcyo.kuaihu.MainActivity;
//import com.angcyo.kuaihu.http.*;
//import com.angcyo.uiview.less.utils.utilcode.utils.ConvertUtils;
//import com.angcyo.uiview.less.widget.rsen.RefreshLayout;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment */
//public class HotFragment extends BaseFragment {
//    private ConvenientBanner banner;
//    private List<BannerModel> bannerLists = new ArrayList();
//    private Type bannerType = new C13222().getType();
//    private RelativeLayout banner_rl;
//    private Gson gson = new GsonBuilder().create();
//    private Handler handler = new Handler();
//    private SmartRefreshLayout hot_smartRefreshLayout;
//    private boolean isEnd = false;
//    private View notLoadingView;
//    private int page = 1;
//    private int perPage = 14;
//    private RecyclerView recyclerView;
//    private StaggeredGridLayoutManager staggeredGridLayoutManager;
//    Runnable taskAdsData = new C087213();
//    Runnable taskHotData = new C087112();
//    private TextView title;
//    private VideoListAdapter videoListAdapter;
//    public List<VideoModel> videoModelList = new ArrayList();
//    private Type videoModelsType = new C13211().getType();
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$12 */
//    class C087112 implements Runnable {
//        C087112() {
//        }
//
//        public void run() {
//            HotFragment.this.getDnsHot();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$13 */
//    class C087213 implements Runnable {
//        C087213() {
//        }
//
//        public void run() {
//            HotFragment.this.getDnsAds();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$1 */
//    class C13211 extends TypeToken<List<VideoModel>> {
//        C13211() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$2 */
//    class C13222 extends TypeToken<List<BannerModel>> {
//        C13222() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$3 */
//    class C13233 extends OnScrollListener {
//        C13233() {
//        }
//
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            HotFragment.this.staggeredGridLayoutManager.invalidateSpanAssignments();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$5 */
//    class C13245 implements OnRefreshListener {
//        C13245() {
//        }
//
//        public void onRefresh(final RefreshLayout refreshlayout) {
//            refreshlayout.getLayout().postDelayed(new Runnable() {
//                public void run() {
//                    HotFragment.this.page = 1;
//                    new Thread(HotFragment.this.taskHotData).start();
//                    new Thread(HotFragment.this.taskAdsData).start();
//                    HotFragment.this.videoListAdapter.clearHeightMap();
//                    refreshlayout.finishRefresh();
//                }
//            }, 2000);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$6 */
//    class C13256 implements OnLoadmoreListener {
//        C13256() {
//        }
//
//        public void onLoadmore(final RefreshLayout refreshlayout) {
//            refreshlayout.getLayout().postDelayed(new Runnable() {
//                public void run() {
//                    HotFragment.this.page = HotFragment.this.page + 1;
//                    new Thread(HotFragment.this.taskHotData).start();
//                    refreshlayout.finishLoadmore();
//                    if (HotFragment.this.isEnd) {
//                        if (HotFragment.this.notLoadingView == null) {
//                            HotFragment.this.notLoadingView = HotFragment.this.zzqF().getLayoutInflater().inflate(C0665R.layout.not_loading, (ViewGroup) HotFragment.this.recyclerView.getParent(), false);
//                        }
//                        HotFragment.this.videoListAdapter.addFooterView(HotFragment.this.notLoadingView);
//                        refreshlayout.setLoadmoreFinished(true);
//                    }
//                }
//            }, 1000);
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$7 */
//    class C13267 implements CBViewHolderCreator<BannerHolderView> {
//        C13267() {
//        }
//
//        public BannerHolderView createHolder() {
//            return new BannerHolderView();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$8 */
//    class C13278 implements OnItemClickListener {
//        C13278() {
//        }
//
//        public void onItemClick(int position) {
//            if (!TextUtils.isEmpty(((BannerModel) HotFragment.this.bannerLists.get(position)).getCa_web_url()) && Pattern.matches("[a-zA-z]+://[^\\s]*", ((BannerModel) HotFragment.this.bannerLists.get(position)).getCa_web_url())) {
//                ((BaseActivity) HotFragment.this.zzqF()).intentTo(new Intent("android.intent.action.VIEW", Uri.parse(((BannerModel) HotFragment.this.bannerLists.get(position)).getCa_web_url())));
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$9 */
//    class C13289 implements OnPageChangeListener {
//        C13289() {
//        }
//
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        }
//
//        public void onPageSelected(int position) {
//            HotFragment.this.title.setText(((BannerModel) HotFragment.this.bannerLists.get(position)).getCa_title());
//        }
//
//        public void onPageScrollStateChanged(int state) {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10 */
//    class C147410 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10$1 */
//        class C08661 implements Runnable {
//            C08661() {
//            }
//
//            public void run() {
//                if (HotFragment.this.page == 1) {
//                    ((MainActivity) HotFragment.this.zzqF()).setProgress();
//                }
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10$2 */
//        class C08682 implements OnClickListener {
//
//            /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10$2$1 */
//            class C08671 implements Runnable {
//                C08671() {
//                }
//
//                public void run() {
//                    HotFragment.this.page = 1;
//                    HotFragment.this.getHotData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VIDEO_HOT);
//                }
//            }
//
//            C08682() {
//            }
//
//            public void onClick(View view) {
//                HotFragment.this.recyclerView.post(new C08671());
//            }
//        }
//
//        /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10$3 */
//        class C08703 implements OnClickListener {
//
//            /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$10$3$1 */
//            class C08691 implements Runnable {
//                C08691() {
//                }
//
//                public void run() {
//                    HotFragment.this.page = 1;
//                    new Thread(HotFragment.this.taskHotData).start();
//                }
//            }
//
//            C08703() {
//            }
//
//            public void onClick(View view) {
//                HotFragment.this.recyclerView.post(new C08691());
//            }
//        }
//
//        C147410() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//            HotFragment.this.handler.post(new C08661());
//        }
//
//        public void onAfter(int id) {
//            super.onAfter(id);
//        }
//
//        public void onError(Call call, Exception e, int id) {
//            if (HotFragment.this.page == 1) {
//                LinearLayout errorView = (LinearLayout) HotFragment.this.zzqF().getLayoutInflater().inflate(C0665R.layout.error_layout, (ViewGroup) HotFragment.this.recyclerView.getParent(), false);
//                errorView.setLayoutParams(new LayoutParams(-1, -2));
//                errorView.setPadding(0, ConvertUtils.dp2px(HotFragment.this.zzqF(), 120.0f), 0, 0);
//                errorView.setOnClickListener(new C08682());
//                HotFragment.this.videoListAdapter.setEmptyView((View) errorView);
//                HotFragment.this.videoListAdapter.setHeaderAndEmpty(true);
//                HotFragment.this.videoListAdapter.notifyDataSetChanged();
//                ((MainActivity) HotFragment.this.zzqF()).setCloseProgress();
//            }
//        }
//
//        public void onResponse(String response, int id) {
//            ((MainActivity) HotFragment.this.zzqF()).setCloseProgress();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                System.out.println("code:" + code);
//                if (code == 0) {
//                    JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
//                    List<VideoModel> tempList = new ArrayList();
//                    tempList = (List) HotFragment.this.gson.fromJson(list.toString().trim(), HotFragment.this.videoModelsType);
//                    if (tempList.isEmpty() || tempList.size() <= 0) {
//                        HotFragment.this.isEnd = true;
//                    } else if (tempList.size() < HotFragment.this.perPage) {
//                        HotFragment.this.isEnd = true;
//                    } else {
//                        HotFragment.this.isEnd = false;
//                    }
//                    if (HotFragment.this.page == 1) {
//                        HotFragment.this.videoModelList.clear();
//                        HotFragment.this.videoModelList.addAll(tempList);
//                        HotFragment.this.videoListAdapter.setNewData(HotFragment.this.videoModelList);
//                        HotFragment.this.videoListAdapter.removeAllFooterView();
//                        HotFragment.this.hot_smartRefreshLayout.setLoadmoreFinished(false);
//                        HotFragment.this.videoListAdapter.notifyDataSetChanged();
//                        if (HotFragment.this.videoModelList.size() == 0 && HotFragment.this.videoModelList.isEmpty()) {
//                            HotFragment.this.hot_smartRefreshLayout.setLoadmoreFinished(true);
//                            LinearLayout emptyView = (LinearLayout) HotFragment.this.zzqF().getLayoutInflater().inflate(C0665R.layout.empty_video_layout, (ViewGroup) HotFragment.this.recyclerView.getParent(), false);
//                            emptyView.setLayoutParams(new LayoutParams(-1, -2));
//                            emptyView.setPadding(0, ConvertUtils.dp2px(HotFragment.this.zzqF(), 120.0f), 0, 0);
//                            emptyView.setOnClickListener(new C08703());
//                            HotFragment.this.videoListAdapter.setEmptyView((View) emptyView);
//                            HotFragment.this.videoListAdapter.setHeaderAndEmpty(true);
//                            HotFragment.this.videoListAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                        return;
//                    }
//                    HotFragment.this.videoListAdapter.addData((Collection) tempList);
//                    HotFragment.this.videoListAdapter.notifyDataSetChanged();
//                    return;
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$11 */
//    class C147511 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$11$1 */
//        class C13201 implements CBViewHolderCreator<BannerHolderView> {
//            C13201() {
//            }
//
//            public BannerHolderView createHolder() {
//                return new BannerHolderView();
//            }
//        }
//
//        C147511() {
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
//                    JSONArray jsonArray_imgads = jsonObject.getJSONObject("data").getJSONObject("list").getJSONArray("imgAds");
//                    HotFragment.this.bannerLists.clear();
//                    HotFragment.this.bannerLists = (List) HotFragment.this.gson.fromJson(jsonArray_imgads.toString(), HotFragment.this.bannerType);
//                    HotFragment.this.banner.setPages(new C13201(), HotFragment.this.bannerLists);
//                    if (HotFragment.this.bannerLists.isEmpty() && HotFragment.this.bannerLists.size() == 0) {
//                        HotFragment.this.banner_rl.setVisibility(8);
//                        return;
//                    }
//                    HotFragment.this.banner_rl.setVisibility(0);
//                    HotFragment.this.title.setText(((BannerModel) HotFragment.this.bannerLists.get(0)).getCa_title());
//                    if (HotFragment.this.bannerLists.size() == 1) {
//                        HotFragment.this.banner.setCanLoop(false);
//                        return;
//                    } else {
//                        HotFragment.this.banner.setCanLoop(true);
//                        return;
//                    }
//                }
//                Toast.makeText(AppUtils.getAppContext(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.HotFragment$4 */
//    class C14764 extends com.chad.library.adapter.base.listener.OnItemClickListener {
//        C14764() {
//        }
//
//        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//        }
//
//        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            super.onItemChildClick(adapter, view, position);
//            switch (view.getId()) {
//                case C0665R.id.video_fl:
//                    if ("1".equals(((VideoModel) HotFragment.this.videoModelList.get(position)).getIs_cat_ads())) {
//                        if (!TextUtils.isEmpty(((VideoModel) HotFragment.this.videoModelList.get(position)).getMv_play_url()) && Pattern.matches("[a-zA-z]+://[^\\s]*", ((VideoModel) HotFragment.this.videoModelList.get(position)).getMv_play_url())) {
//                            ((BaseActivity) HotFragment.this.zzqF()).intentTo(new Intent("android.intent.action.VIEW", Uri.parse(((VideoModel) HotFragment.this.videoModelList.get(position)).getMv_play_url())));
//                            return;
//                        }
//                        return;
//                    } else if (UserUtils.isUserLogin()) {
//                        Intent intent = new Intent(HotFragment.this.zzqF(), VideoDetailActivity.class);
//                        intent.putExtra(VideoDetailActivity.VIDEO_MODEL, (Serializable) HotFragment.this.videoModelList.get(position));
//                        ((BaseActivity) HotFragment.this.zzqF()).intentTo(intent);
//                        return;
//                    } else {
//                        ((MainActivity) HotFragment.this.zzqF()).intentToResult(new Intent(HotFragment.this.zzqF(), LoginActivity.class), MainActivity.LOGIN_CODE);
//                        return;
//                    }
//                default:
//                    return;
//            }
//        }
//    }
//
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    public void onResume() {
//        super.onResume();
//        this.banner.startTurning(4000);
//    }
//
//    public void onPause() {
//        super.onPause();
//        this.banner.stopTurning();
//    }
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    protected int getLayoutId() {
//        return C0665R.layout.fragment_hot;
//    }
//
//    protected void initData() {
//        new Thread(this.taskHotData).start();
//        new Thread(this.taskAdsData).start();
//    }
//
//    protected void initView(LayoutInflater inflater, View view) {
//        this.recyclerView = (RecyclerView) view.findViewById(C0665R.id.recyclerview);
//        this.staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
//        this.staggeredGridLayoutManager.setGapStrategy(0);
//        this.recyclerView.setLayoutManager(this.staggeredGridLayoutManager);
//        this.videoListAdapter = new VideoListAdapter(this.videoModelList);
//        this.recyclerView.setHasFixedSize(true);
//        this.recyclerView.setAdapter(this.videoListAdapter);
//        this.recyclerView.addOnScrollListener(new C13233());
//        this.recyclerView.addOnItemTouchListener(new C14764());
//        this.hot_smartRefreshLayout = (SmartRefreshLayout) view.findViewById(C0665R.id.hot_smartRefreshLayout);
//        this.hot_smartRefreshLayout.setRefreshHeader(new ClassicsHeader(zzqF()));
//        this.hot_smartRefreshLayout.setDisableContentWhenRefresh(false);
//        this.hot_smartRefreshLayout.setDisableContentWhenLoading(false);
//        this.hot_smartRefreshLayout.setOnRefreshListener(new C13245());
//        this.hot_smartRefreshLayout.setOnLoadmoreListener(new C13256());
//        View headView = inflater.inflate(C0665R.layout.banner_head_layout, this.recyclerView, false);
//        this.videoListAdapter.addHeaderView(headView);
//        this.banner_rl = (RelativeLayout) headView.findViewById(C0665R.id.banner_rl);
//        this.banner = (ConvenientBanner) headView.findViewById(C0665R.id.banner);
//        this.title = (TextView) headView.findViewById(C0665R.id.title);
//        this.banner.setPages(new C13267(), this.bannerLists);
//        this.banner.setPageIndicator(new int[]{C0665R.drawable.point_disable, C0665R.drawable.point_unable});
//        this.banner.setPageIndicatorAlign(PageIndicatorAlign.ALIGN_PARENT_RIGHT);
//        if (!(this.bannerLists.isEmpty() && this.bannerLists.size() == 0)) {
//            this.title.setText(((BannerModel) this.bannerLists.get(0)).getCa_title());
//        }
//        this.banner.setOnItemClickListener(new C13278());
//        this.banner.setOnPageChangeListener(new C13289());
//    }
//
//    public void getHotData(String path) {
//        System.out.println("pppppp:" + path);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("page", this.page);
//            jsonObject.put("perPage", this.perPage);
//            jsonObject.put(ChatActivity.EXTRA_KEY_UID, UserUtils.getUserId());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C147410());
//    }
//
//    public void getADSData(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("mcId", "1");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C147511());
//    }
//
//    private void getDnsHot() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getHotData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VIDEO_HOT);
//        } else {
//            getHotData(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_HOT);
//        }
//    }
//
//    private void getDnsAds() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getADSData(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_VIDEO_ADS);
//        } else {
//            getADSData(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_ADS);
//        }
//    }
//}