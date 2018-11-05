//package com.angcyo.kuaihu.analyze;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.p000v4.app.Fragment;
//import android.util.Log;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import java.util.ArrayList;
//
//public abstract class BaseFragment extends Fragment {
//    private View convertView;
//    protected int errorFlag1 = 0;
//    protected int errorFlag2 = 0;
//    protected int errorFlag3 = 0;
//    protected int errorFlag4 = 0;
//    private boolean isFirstLoad = true;
//    private boolean isInitView = false;
//    private boolean isVisible = false;
//    protected ArrayList<String> leftDomain1 = new ArrayList();
//    protected ArrayList<String> leftDomain2 = new ArrayList();
//    protected ArrayList<String> leftDomain3 = new ArrayList();
//    protected ArrayList<String> leftDomain4 = new ArrayList();
//    private SparseArray<View> mViews;
//
//    protected abstract int getLayoutId();
//
//    protected abstract void initData();
//
//    protected abstract void initView(LayoutInflater layoutInflater, View view);
//
//    @Nullable
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.d("BaseFragment", " " + getClass().getSimpleName());
//        this.convertView = inflater.inflate(getLayoutId(), container, false);
//        this.mViews = new SparseArray();
//        initView(inflater, this.convertView);
//        this.isInitView = true;
//        lazyLoadData();
//        return this.convertView;
//    }
//
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.d("BaseFraent", " " + getClass().getSimpleName());
//    }
//
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        Log.d("BaseFragment", "isVisibleToUser " + isVisibleToUser + "   " + getClass().getSimpleName());
//        if (isVisibleToUser) {
//            this.isVisible = true;
//            lazyLoadData();
//        } else {
//            this.isVisible = false;
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }
//
//    private void lazyLoadData() {
//        if (this.isFirstLoad) {
//            Log.d("BaseFragment", "第一次加载  isInitView  " + this.isInitView + "  isVisible  " + this.isVisible + "   " + getClass().getSimpleName());
//        } else {
//            Log.d("BaseFragment", "不是第一次加载 isInitView  " + this.isInitView + "  isVisible  " + this.isVisible + "   " + getClass().getSimpleName());
//        }
//        if (this.isFirstLoad && this.isVisible && this.isInitView) {
//            Log.d("BaseFragment", "完成数据第一次加载   " + getClass().getSimpleName());
//            initData();
//            this.isFirstLoad = false;
//            return;
//        }
//        Log.d("BaseFragment", "不加载   " + getClass().getSimpleName());
//    }
//
//    protected <E extends View> E findView(int viewId) {
//        if (this.convertView == null) {
//            return null;
//        }
//        View view = (View) this.mViews.get(viewId);
//        if (view != null) {
//            return view;
//        }
//        E view2 = this.convertView.findViewById(viewId);
//        this.mViews.put(viewId, view2);
//        return view2;
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