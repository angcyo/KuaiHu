//package com.angcyo.kuaihu.analyze;
//
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.widget.FrameLayout;
//import android.widget.FrameLayout.LayoutParams;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestOptions;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.mylibrary.utils.ConvertUtils;
//import com.mylibrary.utils.ImageLoadUtil;
//import com.mylibrary.utils.ScreenUtils;
//import com.mylibrary.widget.imageview.CircleImageView;
//import com.xxx.foxvideo.C0665R;
//import com.xxx.foxvideo.model.VideoModel;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
///* renamed from: com.xxx.foxvideo.ui.home.adapter.VideoListAdapter */
//public class VideoListAdapter extends BaseQuickAdapter<VideoModel, BaseViewHolder> {
//    private HashMap<Integer, Integer> heightMap;
//    int image_width;
//    int[] ranColor;
//    private Random random;
//
//    public VideoListAdapter(@Nullable List<VideoModel> data) {
//        super(C0665R.layout.item_videolist, data);
//        this.heightMap = new HashMap();
//        this.random = new Random();
//        this.ranColor = new int[]{C0665R.color.bg_color_1, C0665R.color.bg_color_2, C0665R.color.bg_color_3, C0665R.color.bg_color_4, C0665R.color.bg_color_5, C0665R.color.bg_color_6, C0665R.color.bg_color_7};
//        this.image_width = 0;
//        this.image_width = ScreenUtils.getScreenWidth() / 2;
//    }
//
//    public void clearHeightMap() {
//        this.heightMap.clear();
//    }
//
//    protected void convert(BaseViewHolder helper, VideoModel videoModel) {
//        helper.addOnClickListener(C0665R.id.video_fl);
//        ImageView video_img = (ImageView) helper.getView(C0665R.id.video_img);
//        ImageView video_like_img = (ImageView) helper.getView(C0665R.id.video_like_img);
//        CircleImageView avatar_img = (CircleImageView) helper.getView(C0665R.id.avatar_img);
//        TextView video_like_tv = (TextView) helper.getView(C0665R.id.video_like_tv);
//        FrameLayout video_fl = (FrameLayout) helper.getView(C0665R.id.video_fl);
//        int position = helper.getLayoutPosition();
//        LayoutParams params = (LayoutParams) video_img.getLayoutParams();
//        params.width = this.image_width;
//        if (this.heightMap.containsKey(Integer.valueOf(position))) {
//            params.height = ((Integer) this.heightMap.get(Integer.valueOf(position))).intValue();
//        } else {
//            if (Integer.valueOf(videoModel.getMv_play_width()).intValue() / Integer.valueOf(videoModel.getMv_play_height()).intValue() > 1 || Integer.valueOf(videoModel.getMv_play_width()).intValue() / Integer.valueOf(videoModel.getMv_play_height()).intValue() == 1) {
//                params.height = ConvertUtils.dp2px(this.mContext, 200.0f);
//            } else {
//                params.height = (int) ((((double) ScreenUtils.getScreenHeight()) / 2.5d) + (Math.random() * 200.0d));
//            }
//            this.heightMap.put(Integer.valueOf(position), Integer.valueOf(params.height));
//        }
//        video_img.setLayoutParams(params);
//        if (TextUtils.isEmpty(videoModel.getMv_like())) {
//            video_like_tv.setText("0");
//        } else {
//            video_like_tv.setText(videoModel.getMv_like());
//        }
//        RequestOptions options = new RequestOptions();
//        if (Integer.valueOf(videoModel.getMv_play_width()).intValue() / Integer.valueOf(videoModel.getMv_play_height()).intValue() > 1 || Integer.valueOf(videoModel.getMv_play_width()).intValue() / Integer.valueOf(videoModel.getMv_play_height()).intValue() == 1) {
//            options.override(this.image_width, ConvertUtils.dp2px(this.mContext, 200.0f)).centerCrop().placeholder(this.ranColor[this.random.nextInt(this.ranColor.length)]).diskCacheStrategy(DiskCacheStrategy.ALL);
//        } else {
//            options.override(this.image_width, (int) (((double) ScreenUtils.getScreenHeight()) / 2.5d)).centerCrop().placeholder(this.ranColor[this.random.nextInt(this.ranColor.length)]).diskCacheStrategy(DiskCacheStrategy.ALL);
//        }
//        Glide.with(this.mContext).load(videoModel.getMv_img_url()).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(video_img);
//        ImageLoadUtil.loadCircleImageView(videoModel.getMu_avatar(), 100, 100, avatar_img, C0665R.drawable.detail_avatar_secret);
//    }
//}