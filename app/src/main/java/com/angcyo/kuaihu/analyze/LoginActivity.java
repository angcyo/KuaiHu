//package com.angcyo.kuaihu.analyze;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.p000v4.app.Fragment;
//import android.support.p000v4.app.FragmentManager;
//import android.support.p000v4.app.FragmentPagerAdapter;
//import android.support.p000v4.view.ViewPager;
//import android.support.p000v4.view.ViewPager.OnPageChangeListener;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import com.flyco.tablayout.SlidingTabLayout;
//import com.mylibrary.utils.KeyboardUtils;
//import com.xxx.foxvideo.C0665R;
//import com.xxx.foxvideo.activity.BaseActivity;
//import com.xxx.foxvideo.p005ui.home.fragment.LoginViewFragment;
//import com.xxx.foxvideo.p005ui.home.fragment.RegistViewFragment;
//import java.util.ArrayList;
//import java.util.List;
//
///* renamed from: com.xxx.foxvideo.ui.home.activity.LoginActivity */
//public class LoginActivity extends BaseActivity {
//    @BindView(2131755298)
//    TextView btnPre;
//    private LoginViewFragment loginFragment;
//    @BindView(2131755189)
//    ViewPager loginVp;
//    private MyPagerAdapter mAdapter;
//    private ArrayList<Fragment> mFragments = new ArrayList();
//    private final List<String> mTitles = new ArrayList();
//    private RegistViewFragment registFragment;
//    @BindView(2131755188)
//    SlidingTabLayout slidingTabLayout;
//    @BindView(2131755297)
//    FrameLayout titleFramelayout;
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.LoginActivity$1 */
//    class C12661 implements OnPageChangeListener {
//        C12661() {
//        }
//
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        }
//
//        public void onPageSelected(int position) {
//            KeyboardUtils.hideSoftInput(LoginActivity.this);
//        }
//
//        public void onPageScrollStateChanged(int state) {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.LoginActivity$MyPagerAdapter */
//    public class MyPagerAdapter extends FragmentPagerAdapter {
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public int getCount() {
//            return LoginActivity.this.mFragments.size();
//        }
//
//        public CharSequence getPageTitle(int position) {
//            return (CharSequence) LoginActivity.this.mTitles.get(position);
//        }
//
//        public Fragment getItem(int position) {
//            return (Fragment) LoginActivity.this.mFragments.get(position);
//        }
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView((int) C0665R.layout.activity_loginandregist);
//        ButterKnife.bind((Activity) this);
//        initdata();
//        initview();
//    }
//
//    private void initdata() {
//        this.mTitles.add("注册");
//        this.mTitles.add("登陆");
//        this.loginFragment = new LoginViewFragment();
//        this.registFragment = new RegistViewFragment();
//        this.mFragments.add(this.registFragment);
//        this.mFragments.add(this.loginFragment);
//    }
//
//    protected void onResume() {
//        super.onResume();
//    }
//
//    private void initview() {
//        this.btnPre.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(C0665R.drawable.live_list_icon_delete_normal), null, null, null);
//        this.mAdapter = new MyPagerAdapter(getSupportFragmentManager());
//        this.loginVp.setAdapter(this.mAdapter);
//        this.loginVp.setOffscreenPageLimit(2);
//        this.slidingTabLayout.setViewPager(this.loginVp);
//        this.mAdapter.notifyDataSetChanged();
//        this.slidingTabLayout.notifyDataSetChanged();
//        this.loginVp.addOnPageChangeListener(new C12661());
//        this.loginVp.setCurrentItem(1);
//    }
//
//    @OnClick({2131755298})
//    public void onViewClicked() {
//        closeActivity();
//    }
//}