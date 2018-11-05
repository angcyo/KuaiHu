//package com.angcyo.kuaihu.analyze;
//
//import android.app.Activity;
//import android.app.AlertDialog.Builder;
//import android.app.Dialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.Color;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.Timer;
//import java.util.TimerTask;
//
///* renamed from: cn.jzvd.JZVideoPlayerStandard */
//public class JZVideoPlayerStandard extends JZVideoPlayer {
//    protected static Timer DISMISS_CONTROL_VIEW_TIMER;
//    public ImageView backButton;
//    private BroadcastReceiver battertReceiver = new C03341();
//    public ImageView batteryLevel;
//    public LinearLayout batteryTimeLayout;
//    public ProgressBar bottomProgressBar;
//    private boolean brocasting = false;
//    public TextView clarity;
//    public PopupWindow clarityPopWindow;
//    public ProgressBar loadingProgressBar;
//    protected Dialog mBrightnessDialog;
//    protected ProgressBar mDialogBrightnessProgressBar;
//    protected TextView mDialogBrightnessTextView;
//    protected ImageView mDialogIcon;
//    protected ProgressBar mDialogProgressBar;
//    protected TextView mDialogSeekTime;
//    protected TextView mDialogTotalTime;
//    protected ImageView mDialogVolumeImageView;
//    protected ProgressBar mDialogVolumeProgressBar;
//    protected TextView mDialogVolumeTextView;
//    protected DismissControlViewTimerTask mDismissControlViewTimerTask;
//    protected Dialog mProgressDialog;
//    public TextView mRetryBtn;
//    public LinearLayout mRetryLayout;
//    protected Dialog mVolumeDialog;
//    public TextView replayTextView;
//    public ImageView thumbImageView;
//    public ImageView tinyBackImageView;
//    public TextView titleTextView;
//    public TextView videoCurrentTime;
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$1 */
//    class C03341 extends BroadcastReceiver {
//        C03341() {
//        }
//
//        public void onReceive(Context context, Intent intent) {
//            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
//                int level = intent.getIntExtra("level", 0);
//                int percent = (level * 100) / intent.getIntExtra("scale", 100);
//                if (percent < 15) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_10);
//                } else if (percent >= 15 && percent < 40) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_30);
//                } else if (percent >= 40 && percent < 60) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_50);
//                } else if (percent >= 60 && percent < 80) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_70);
//                } else if (percent >= 80 && percent < 95) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_90);
//                } else if (percent >= 95 && percent <= 100) {
//                    JZVideoPlayerStandard.this.batteryLevel.setBackgroundResource(C0344R.C0341drawable.jz_battery_level_100);
//                }
//                JZVideoPlayerStandard.this.getContext().unregisterReceiver(JZVideoPlayerStandard.this.battertReceiver);
//                JZVideoPlayerStandard.this.brocasting = false;
//            }
//        }
//    }
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$3 */
//    class C03363 implements OnClickListener {
//        C03363() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//            JZVideoPlayerStandard.this.onEvent(101);
//            JZVideoPlayerStandard.this.startVideo();
//            JZVideoPlayer.WIFI_TIP_DIALOG_SHOWED = true;
//        }
//    }
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$4 */
//    class C03374 implements OnClickListener {
//        C03374() {
//        }
//
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$5 */
//    class C03385 implements OnCancelListener {
//        C03385() {
//        }
//
//        public void onCancel(DialogInterface dialog) {
//            dialog.dismiss();
//        }
//    }
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$6 */
//    class C03396 implements Runnable {
//        C03396() {
//        }
//
//        public void run() {
//            JZVideoPlayerStandard.this.bottomContainer.setVisibility(4);
//            JZVideoPlayerStandard.this.topContainer.setVisibility(4);
//            JZVideoPlayerStandard.this.startButton.setVisibility(4);
//            if (JZVideoPlayerStandard.this.clarityPopWindow != null) {
//                JZVideoPlayerStandard.this.clarityPopWindow.dismiss();
//            }
//            if (JZVideoPlayerStandard.this.currentScreen != 3) {
//                JZVideoPlayerStandard.this.bottomProgressBar.setVisibility(0);
//            }
//        }
//    }
//
//    /* renamed from: cn.jzvd.JZVideoPlayerStandard$DismissControlViewTimerTask */
//    public class DismissControlViewTimerTask extends TimerTask {
//        public void run() {
//            JZVideoPlayerStandard.this.dissmissControlView();
//        }
//    }
//
//    public JZVideoPlayerStandard(Context context) {
//        super(context);
//    }
//
//    public JZVideoPlayerStandard(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public void init(Context context) {
//        super.init(context);
//        this.batteryTimeLayout = (LinearLayout) findViewById(C0344R.C0342id.battery_time_layout);
//        this.bottomProgressBar = (ProgressBar) findViewById(C0344R.C0342id.bottom_progress);
//        this.titleTextView = (TextView) findViewById(C0344R.C0342id.title);
//        this.backButton = (ImageView) findViewById(C0344R.C0342id.back);
//        this.thumbImageView = (ImageView) findViewById(C0344R.C0342id.thumb);
//        this.loadingProgressBar = (ProgressBar) findViewById(C0344R.C0342id.loading);
//        this.tinyBackImageView = (ImageView) findViewById(C0344R.C0342id.back_tiny);
//        this.batteryLevel = (ImageView) findViewById(C0344R.C0342id.battery_level);
//        this.videoCurrentTime = (TextView) findViewById(C0344R.C0342id.video_current_time);
//        this.replayTextView = (TextView) findViewById(C0344R.C0342id.replay_text);
//        this.clarity = (TextView) findViewById(C0344R.C0342id.clarity);
//        this.mRetryBtn = (TextView) findViewById(C0344R.C0342id.retry_btn);
//        this.mRetryLayout = (LinearLayout) findViewById(C0344R.C0342id.retry_layout);
//        this.thumbImageView.setOnClickListener(this);
//        this.backButton.setOnClickListener(this);
//        this.tinyBackImageView.setOnClickListener(this);
//        this.clarity.setOnClickListener(this);
//        this.mRetryBtn.setOnClickListener(this);
//    }
//
//    public void setUp(Object[] dataSourceObjects, int defaultUrlMapIndex, int screen, Object... objects) {
//        super.setUp(dataSourceObjects, defaultUrlMapIndex, screen, objects);
//        if (objects.length != 0) {
//            this.titleTextView.setText(objects[0].toString());
//        }
//        if (this.currentScreen == 2) {
//            this.fullscreenButton.setImageResource(C0344R.C0341drawable.jz_shrink);
//            this.backButton.setVisibility(0);
//            this.tinyBackImageView.setVisibility(4);
//            this.batteryTimeLayout.setVisibility(0);
//            if (((LinkedHashMap) dataSourceObjects[0]).size() == 1) {
//                this.clarity.setVisibility(8);
//            } else {
//                this.clarity.setText(JZUtils.getKeyFromDataSource(dataSourceObjects, this.currentUrlMapIndex));
//                this.clarity.setVisibility(0);
//            }
//            changeStartButtonSize((int) getResources().getDimension(C0344R.dimen.jz_start_button_w_h_fullscreen));
//        } else if (this.currentScreen == 0 || this.currentScreen == 1) {
//            this.fullscreenButton.setImageResource(C0344R.C0341drawable.jz_enlarge);
//            this.backButton.setVisibility(8);
//            this.tinyBackImageView.setVisibility(4);
//            changeStartButtonSize((int) getResources().getDimension(C0344R.dimen.jz_start_button_w_h_normal));
//            this.batteryTimeLayout.setVisibility(8);
//            this.clarity.setVisibility(8);
//        } else if (this.currentScreen == 3) {
//            this.tinyBackImageView.setVisibility(0);
//            setAllControlsVisiblity(4, 4, 4, 4, 4, 4, 4);
//            this.batteryTimeLayout.setVisibility(8);
//            this.clarity.setVisibility(8);
//        }
//        setSystemTimeAndBattery();
//        if (this.tmp_test_back) {
//            this.tmp_test_back = false;
//            JZVideoPlayerManager.setFirstFloor(this);
//            JZVideoPlayer.backPress();
//        }
//    }
//
//    public void changeStartButtonSize(int size) {
//        LayoutParams lp = this.startButton.getLayoutParams();
//        lp.height = size;
//        lp.width = size;
//        lp = this.loadingProgressBar.getLayoutParams();
//        lp.height = size;
//        lp.width = size;
//    }
//
//    public int getLayoutId() {
//        return C0344R.C0343layout.jz_layout_standard;
//    }
//
//    public void onStateNormal() {
//        super.onStateNormal();
//        changeUiToNormal();
//    }
//
//    public void onStatePreparing() {
//        super.onStatePreparing();
//        changeUiToPreparing();
//    }
//
//    public void onStatePreparingChangingUrl(int urlMapIndex, long seekToInAdvance) {
//        super.onStatePreparingChangingUrl(urlMapIndex, seekToInAdvance);
//        this.loadingProgressBar.setVisibility(0);
//        this.startButton.setVisibility(4);
//    }
//
//    public void onStatePlaying() {
//        super.onStatePlaying();
//        changeUiToPlayingClear();
//    }
//
//    public void onStatePause() {
//        super.onStatePause();
//        changeUiToPauseShow();
//        cancelDismissControlViewTimer();
//    }
//
//    public void onStateError() {
//        super.onStateError();
//        changeUiToError();
//    }
//
//    public void onStateAutoComplete() {
//        super.onStateAutoComplete();
//        changeUiToComplete();
//        cancelDismissControlViewTimer();
//        this.bottomProgressBar.setProgress(100);
//    }
//
//    public boolean onTouch(View v, MotionEvent event) {
//        int id = v.getId();
//        if (id != C0344R.C0342id.surface_container) {
//            if (id == C0344R.C0342id.bottom_seek_progress) {
//                switch (event.getAction()) {
//                    case 0:
//                        cancelDismissControlViewTimer();
//                        break;
//                    case 1:
//                        startDismissControlViewTimer();
//                        break;
//                }
//            }
//        }
//        switch (event.getAction()) {
//            case 1:
//                startDismissControlViewTimer();
//                if (this.mChangePosition) {
//                    long duration = getDuration();
//                    long j = this.mSeekTimePosition * 100;
//                    if (duration == 0) {
//                        duration = 1;
//                    }
//                    this.bottomProgressBar.setProgress((int) (j / duration));
//                }
//                if (!(this.mChangePosition || this.mChangeVolume)) {
//                    onEvent(102);
//                    onClickUiToggle();
//                    break;
//                }
//        }
//        return super.onTouch(v, event);
//    }
//
//    public void onClick(View v) {
//        super.onClick(v);
//        int i = v.getId();
//        if (i == C0344R.C0342id.thumb) {
//            if (this.dataSourceObjects == null || JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex) == null) {
//                Toast.makeText(getContext(), getResources().getString(C0344R.string.no_url), 0).show();
//            } else if (this.currentState == 0) {
//                if (JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex).toString().startsWith("file") || JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex).toString().startsWith("/") || JZUtils.isWifiConnected(getContext()) || WIFI_TIP_DIALOG_SHOWED) {
//                    onEvent(101);
//                    startVideo();
//                    return;
//                }
//                showWifiDialog(101);
//            } else if (this.currentState == 6) {
//                onClickUiToggle();
//            }
//        } else if (i == C0344R.C0342id.surface_container) {
//            startDismissControlViewTimer();
//        } else if (i == C0344R.C0342id.back) {
//            JZVideoPlayer.backPress();
//        } else if (i == C0344R.C0342id.back_tiny) {
//            if (JZVideoPlayerManager.getFirstFloor().currentScreen == 1) {
//                JZVideoPlayer.quitFullscreenOrTinyWindow();
//            } else {
//                JZVideoPlayer.backPress();
//            }
//        } else if (i == C0344R.C0342id.clarity) {
//            final LinearLayout layout = (LinearLayout) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0344R.C0343layout.jz_layout_clarity, null);
//            View.OnClickListener mQualityListener = new View.OnClickListener() {
//                public void onClick(View v) {
//                    JZVideoPlayerStandard.this.onStatePreparingChangingUrl(((Integer) v.getTag()).intValue(), JZVideoPlayerStandard.this.getCurrentPositionWhenPlaying());
//                    JZVideoPlayerStandard.this.clarity.setText(JZUtils.getKeyFromDataSource(JZVideoPlayerStandard.this.dataSourceObjects, JZVideoPlayerStandard.this.currentUrlMapIndex));
//                    for (int j = 0; j < layout.getChildCount(); j++) {
//                        if (j == JZVideoPlayerStandard.this.currentUrlMapIndex) {
//                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#fff85959"));
//                        } else {
//                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#ffffff"));
//                        }
//                    }
//                    if (JZVideoPlayerStandard.this.clarityPopWindow != null) {
//                        JZVideoPlayerStandard.this.clarityPopWindow.dismiss();
//                    }
//                }
//            };
//            for (int j = 0; j < ((LinkedHashMap) this.dataSourceObjects[0]).size(); j++) {
//                TextView clarityItem = (TextView) View.inflate(getContext(), C0344R.C0343layout.jz_layout_clarity_item, null);
//                clarityItem.setText(JZUtils.getKeyFromDataSource(this.dataSourceObjects, j));
//                clarityItem.setTag(Integer.valueOf(j));
//                layout.addView(clarityItem, j);
//                clarityItem.setOnClickListener(mQualityListener);
//                if (j == this.currentUrlMapIndex) {
//                    clarityItem.setTextColor(Color.parseColor("#fff85959"));
//                }
//            }
//            this.clarityPopWindow = new PopupWindow(layout, -2, -2, true);
//            this.clarityPopWindow.setContentView(layout);
//            this.clarityPopWindow.showAsDropDown(this.clarity);
//            layout.measure(0, 0);
//            this.clarityPopWindow.update(this.clarity, -40, 46, Math.round((float) (layout.getMeasuredWidth() * 2)), layout.getMeasuredHeight());
//        } else if (i != C0344R.C0342id.retry_btn) {
//        } else {
//            if (JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex).toString().startsWith("file") || JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex).toString().startsWith("/") || JZUtils.isWifiConnected(getContext()) || WIFI_TIP_DIALOG_SHOWED) {
//                initTextureView();
//                addTextureView();
//                JZMediaManager.setDataSource(this.dataSourceObjects);
//                JZMediaManager.setCurrentDataSource(JZUtils.getCurrentFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex));
//                onStatePreparing();
//                onEvent(1);
//                return;
//            }
//            showWifiDialog(0);
//        }
//    }
//
//    public void showWifiDialog(int action) {
//        super.showWifiDialog(action);
//        Builder builder = new Builder(getContext());
//        builder.setMessage(getResources().getString(C0344R.string.tips_not_wifi));
//        builder.setPositiveButton(getResources().getString(C0344R.string.tips_not_wifi_confirm), new C03363());
//        builder.setNegativeButton(getResources().getString(C0344R.string.tips_not_wifi_cancel), new C03374());
//        builder.setOnCancelListener(new C03385());
//        builder.create().show();
//    }
//
//    public void onStartTrackingTouch(SeekBar seekBar) {
//        super.onStartTrackingTouch(seekBar);
//        cancelDismissControlViewTimer();
//    }
//
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        super.onStopTrackingTouch(seekBar);
//        if (this.currentState == 3) {
//            dissmissControlView();
//        } else {
//            startDismissControlViewTimer();
//        }
//    }
//
//    public void onClickUiToggle() {
//        if (this.bottomContainer.getVisibility() != 0) {
//            setSystemTimeAndBattery();
//            this.clarity.setText(JZUtils.getKeyFromDataSource(this.dataSourceObjects, this.currentUrlMapIndex));
//        }
//        if (this.currentState == 1) {
//            changeUiToPreparing();
//            if (this.bottomContainer.getVisibility() != 0) {
//                setSystemTimeAndBattery();
//            }
//        } else if (this.currentState == 3) {
//            if (this.bottomContainer.getVisibility() == 0) {
//                changeUiToPlayingClear();
//            } else {
//                changeUiToPlayingShow();
//            }
//        } else if (this.currentState != 5) {
//        } else {
//            if (this.bottomContainer.getVisibility() == 0) {
//                changeUiToPauseClear();
//            } else {
//                changeUiToPauseShow();
//            }
//        }
//    }
//
//    public void setSystemTimeAndBattery() {
//        this.videoCurrentTime.setText(new SimpleDateFormat("HH:mm").format(new Date()));
//        if (!this.brocasting) {
//            getContext().registerReceiver(this.battertReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
//        }
//    }
//
//    public void onCLickUiToggleToClear() {
//        if (this.currentState == 1) {
//            if (this.bottomContainer.getVisibility() == 0) {
//                changeUiToPreparing();
//            }
//        } else if (this.currentState == 3) {
//            if (this.bottomContainer.getVisibility() == 0) {
//                changeUiToPlayingClear();
//            }
//        } else if (this.currentState == 5) {
//            if (this.bottomContainer.getVisibility() == 0) {
//                changeUiToPauseClear();
//            }
//        } else if (this.currentState == 6 && this.bottomContainer.getVisibility() == 0) {
//            changeUiToComplete();
//        }
//    }
//
//    public void setProgressAndText(int progress, long position, long duration) {
//        super.setProgressAndText(progress, position, duration);
//        if (progress != 0) {
//            this.bottomProgressBar.setProgress(progress);
//        }
//    }
//
//    public void setBufferProgress(int bufferProgress) {
//        super.setBufferProgress(bufferProgress);
//        if (bufferProgress != 0) {
//            this.bottomProgressBar.setSecondaryProgress(bufferProgress);
//        }
//    }
//
//    public void resetProgressAndTime() {
//        super.resetProgressAndTime();
//        this.bottomProgressBar.setProgress(0);
//        this.bottomProgressBar.setSecondaryProgress(0);
//    }
//
//    public void changeUiToNormal() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToPreparing() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(4, 4, 4, 0, 0, 4, 4);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(4, 4, 4, 0, 0, 4, 4);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToPlayingShow() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToPlayingClear() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
//                return;
//            case 2:
//                setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToPauseShow() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToPauseClear() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
//                return;
//            case 2:
//                setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToComplete() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void changeUiToError() {
//        switch (this.currentScreen) {
//            case 0:
//            case 1:
//                setAllControlsVisiblity(4, 4, 0, 4, 4, 4, 0);
//                updateStartImage();
//                return;
//            case 2:
//                setAllControlsVisiblity(4, 4, 0, 4, 4, 4, 0);
//                updateStartImage();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int thumbImg, int bottomPro, int retryLayout) {
//        this.topContainer.setVisibility(topCon);
//        this.bottomContainer.setVisibility(bottomCon);
//        this.startButton.setVisibility(startBtn);
//        this.loadingProgressBar.setVisibility(loadingPro);
//        this.thumbImageView.setVisibility(thumbImg);
//        this.bottomProgressBar.setVisibility(bottomPro);
//        this.mRetryLayout.setVisibility(retryLayout);
//    }
//
//    public void updateStartImage() {
//        if (this.currentState == 3) {
//            this.startButton.setVisibility(0);
//            this.startButton.setImageResource(C0344R.C0341drawable.jz_click_pause_selector);
//            this.replayTextView.setVisibility(4);
//        } else if (this.currentState == 7) {
//            this.startButton.setVisibility(4);
//            this.replayTextView.setVisibility(4);
//        } else if (this.currentState == 6) {
//            this.startButton.setVisibility(0);
//            this.startButton.setImageResource(C0344R.C0341drawable.jz_click_replay_selector);
//            this.replayTextView.setVisibility(0);
//        } else {
//            this.startButton.setImageResource(C0344R.C0341drawable.jz_click_play_selector);
//            this.replayTextView.setVisibility(4);
//        }
//    }
//
//    public void showProgressDialog(float deltaX, String seekTime, long seekTimePosition, String totalTime, long totalTimeDuration) {
//        super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
//        if (this.mProgressDialog == null) {
//            View localView = LayoutInflater.from(getContext()).inflate(C0344R.C0343layout.jz_dialog_progress, null);
//            this.mDialogProgressBar = (ProgressBar) localView.findViewById(C0344R.C0342id.duration_progressbar);
//            this.mDialogSeekTime = (TextView) localView.findViewById(C0344R.C0342id.tv_current);
//            this.mDialogTotalTime = (TextView) localView.findViewById(C0344R.C0342id.tv_duration);
//            this.mDialogIcon = (ImageView) localView.findViewById(C0344R.C0342id.duration_image_tip);
//            this.mProgressDialog = createDialogWithView(localView);
//        }
//        if (!this.mProgressDialog.isShowing()) {
//            this.mProgressDialog.show();
//        }
//        this.mDialogSeekTime.setText(seekTime);
//        this.mDialogTotalTime.setText(" / " + totalTime);
//        this.mDialogProgressBar.setProgress(totalTimeDuration <= 0 ? 0 : (int) ((100 * seekTimePosition) / totalTimeDuration));
//        if (deltaX > 0.0f) {
//            this.mDialogIcon.setBackgroundResource(C0344R.C0341drawable.jz_forward_icon);
//        } else {
//            this.mDialogIcon.setBackgroundResource(C0344R.C0341drawable.jz_backward_icon);
//        }
//        onCLickUiToggleToClear();
//    }
//
//    public void dismissProgressDialog() {
//        super.dismissProgressDialog();
//        if (this.mProgressDialog != null) {
//            this.mProgressDialog.dismiss();
//        }
//    }
//
//    public void showVolumeDialog(float deltaY, int volumePercent) {
//        super.showVolumeDialog(deltaY, volumePercent);
//        if (this.mVolumeDialog == null) {
//            View localView = LayoutInflater.from(getContext()).inflate(C0344R.C0343layout.jz_dialog_volume, null);
//            this.mDialogVolumeImageView = (ImageView) localView.findViewById(C0344R.C0342id.volume_image_tip);
//            this.mDialogVolumeTextView = (TextView) localView.findViewById(C0344R.C0342id.tv_volume);
//            this.mDialogVolumeProgressBar = (ProgressBar) localView.findViewById(C0344R.C0342id.volume_progressbar);
//            this.mVolumeDialog = createDialogWithView(localView);
//        }
//        if (!this.mVolumeDialog.isShowing()) {
//            this.mVolumeDialog.show();
//        }
//        if (volumePercent <= 0) {
//            this.mDialogVolumeImageView.setBackgroundResource(C0344R.C0341drawable.jz_close_volume);
//        } else {
//            this.mDialogVolumeImageView.setBackgroundResource(C0344R.C0341drawable.jz_add_volume);
//        }
//        if (volumePercent > 100) {
//            volumePercent = 100;
//        } else if (volumePercent < 0) {
//            volumePercent = 0;
//        }
//        this.mDialogVolumeTextView.setText(volumePercent + "%");
//        this.mDialogVolumeProgressBar.setProgress(volumePercent);
//        onCLickUiToggleToClear();
//    }
//
//    public void dismissVolumeDialog() {
//        super.dismissVolumeDialog();
//        if (this.mVolumeDialog != null) {
//            this.mVolumeDialog.dismiss();
//        }
//    }
//
//    public void showBrightnessDialog(int brightnessPercent) {
//        super.showBrightnessDialog(brightnessPercent);
//        if (this.mBrightnessDialog == null) {
//            View localView = LayoutInflater.from(getContext()).inflate(C0344R.C0343layout.jz_dialog_brightness, null);
//            this.mDialogBrightnessTextView = (TextView) localView.findViewById(C0344R.C0342id.tv_brightness);
//            this.mDialogBrightnessProgressBar = (ProgressBar) localView.findViewById(C0344R.C0342id.brightness_progressbar);
//            this.mBrightnessDialog = createDialogWithView(localView);
//        }
//        if (!this.mBrightnessDialog.isShowing()) {
//            this.mBrightnessDialog.show();
//        }
//        if (brightnessPercent > 100) {
//            brightnessPercent = 100;
//        } else if (brightnessPercent < 0) {
//            brightnessPercent = 0;
//        }
//        this.mDialogBrightnessTextView.setText(brightnessPercent + "%");
//        this.mDialogBrightnessProgressBar.setProgress(brightnessPercent);
//        onCLickUiToggleToClear();
//    }
//
//    public void dismissBrightnessDialog() {
//        super.dismissBrightnessDialog();
//        if (this.mBrightnessDialog != null) {
//            this.mBrightnessDialog.dismiss();
//        }
//    }
//
//    public Dialog createDialogWithView(View localView) {
//        Dialog dialog = new Dialog(getContext(), C0344R.style.jz_style_dialog_progress);
//        dialog.setContentView(localView);
//        Window window = dialog.getWindow();
//        window.addFlags(8);
//        window.addFlags(32);
//        window.addFlags(16);
//        window.setLayout(-2, -2);
//        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
//        localLayoutParams.gravity = 17;
//        window.setAttributes(localLayoutParams);
//        return dialog;
//    }
//
//    public void startDismissControlViewTimer() {
//        cancelDismissControlViewTimer();
//        DISMISS_CONTROL_VIEW_TIMER = new Timer();
//        this.mDismissControlViewTimerTask = new DismissControlViewTimerTask();
//        DISMISS_CONTROL_VIEW_TIMER.schedule(this.mDismissControlViewTimerTask, 2500);
//    }
//
//    public void cancelDismissControlViewTimer() {
//        if (DISMISS_CONTROL_VIEW_TIMER != null) {
//            DISMISS_CONTROL_VIEW_TIMER.cancel();
//        }
//        if (this.mDismissControlViewTimerTask != null) {
//            this.mDismissControlViewTimerTask.cancel();
//        }
//    }
//
//    public void onAutoCompletion() {
//        super.onAutoCompletion();
//        cancelDismissControlViewTimer();
//    }
//
//    public void onCompletion() {
//        super.onCompletion();
//        cancelDismissControlViewTimer();
//        if (this.clarityPopWindow != null) {
//            this.clarityPopWindow.dismiss();
//        }
//    }
//
//    public void dissmissControlView() {
//        if (this.currentState != 0 && this.currentState != 7 && this.currentState != 6 && getContext() != null && (getContext() instanceof Activity)) {
//            ((Activity) getContext()).runOnUiThread(new C03396());
//        }
//    }
//}