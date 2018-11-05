//package com.angcyo.kuaihu.analyze;
//
//import android.content.Intent;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.text.method.HideReturnsTransformationMethod;
//import android.text.method.PasswordTransformationMethod;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.mylibrary.Constant;
//import com.mylibrary.okhttputils.OkHttpUtils;
//import com.mylibrary.okhttputils.builder.PostFormBuilder;
//import com.mylibrary.okhttputils.callback.StringCallback;
//import com.mylibrary.utils.AesEncryptionUtil;
//import com.mylibrary.utils.HttpDNS;
//import com.orhanobut.hawk.Hawk;
//import com.xxx.foxvideo.C0665R;
//import com.xxx.foxvideo.activity.BaseActivity;
//import com.xxx.foxvideo.activity.BaseFragment;
//import com.xxx.foxvideo.model.UserModel;
//import com.xxx.foxvideo.p005ui.home.activity.ForgetPswActivity;
//import com.xxx.foxvideo.p005ui.home.activity.LoginActivity;
//import com.xxx.foxvideo.p005ui.home.activity.MainActivity;
//import java.lang.reflect.Type;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///* renamed from: com.xxx.foxvideo.ui.home.fragment.LoginViewFragment */
//public class LoginViewFragment extends BaseFragment {
//    @BindView(2131755594)
//    TextView forgetPswTv;
//    private Gson gson = new GsonBuilder().create();
//    @BindView(2131755593)
//    Button loginBt;
//    @BindView(2131755589)
//    EditText loginEmailTv;
//    @BindView(2131755591)
//    EditText loginPswEt;
//    @BindView(2131755592)
//    ImageView loginPswImg;
//    Runnable taskLogin = new C08764();
//    Unbinder unbinder;
//    private Type userType = new C13291().getType();
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.LoginViewFragment$2 */
//    class C08752 implements TextWatcher {
//        private CharSequence temp;
//
//        C08752() {
//        }
//
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            this.temp = charSequence;
//        }
//
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        }
//
//        public void afterTextChanged(Editable editable) {
//            if (LoginViewFragment.this.loginEmailTv.getText().toString().trim().equals("") || LoginViewFragment.this.loginPswEt.getText().toString().trim().equals("")) {
//                LoginViewFragment.this.loginBt.setEnabled(false);
//                LoginViewFragment.this.loginBt.setBackground(LoginViewFragment.this.getResources().getDrawable(C0665R.drawable.bg_common_gray));
//                return;
//            }
//            LoginViewFragment.this.loginBt.setEnabled(true);
//            LoginViewFragment.this.loginBt.setBackground(LoginViewFragment.this.getResources().getDrawable(C0665R.drawable.bg_common_org));
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.LoginViewFragment$4 */
//    class C08764 implements Runnable {
//        C08764() {
//        }
//
//        public void run() {
//            LoginViewFragment.this.getDnsLogin();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.LoginViewFragment$1 */
//    class C13291 extends TypeToken<UserModel> {
//        C13291() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.fragment.LoginViewFragment$3 */
//    class C14773 extends StringCallback {
//        C14773() {
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
//            ((LoginActivity) LoginViewFragment.this.zzqF()).setCloseProgress();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                UserModel temp = new UserModel();
//                int code = jsonObject.getInt("code");
//                System.out.println("code:" + code);
//                System.out.println("jsonObject:" + jsonObject);
//                if (code == 0) {
//                    temp = (UserModel) LoginViewFragment.this.gson.fromJson(jsonObject.getJSONObject("data").toString(), LoginViewFragment.this.userType);
//                    Hawk.put("user", temp);
//                    System.out.println("user:" + temp.getMu_id().toString());
//                    ((BaseActivity) LoginViewFragment.this.zzqF()).setResult(-1);
//                    ((BaseActivity) LoginViewFragment.this.zzqF()).closeActivity();
//                    Toast.makeText(LoginViewFragment.this.zzqF(), "登录成功!", 0).show();
//                    return;
//                }
//                Toast.makeText(LoginViewFragment.this.zzqF(), jsonObject.getString("message"), 0).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    protected int getLayoutId() {
//        return C0665R.layout.view_login;
//    }
//
//    protected void initData() {
//        this.loginBt.setEnabled(false);
//        if (((Boolean) Hawk.get("pwsw_img", Boolean.valueOf(true))).booleanValue()) {
//            this.loginPswImg.setBackground(getResources().getDrawable(C0665R.drawable.login_ico_input_open));
//            this.loginPswEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//        } else {
//            this.loginPswImg.setBackground(getResources().getDrawable(C0665R.drawable.login_ico_input_cancel));
//            this.loginPswEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        }
//        TextWatcher textWatcher = new C08752();
//        this.loginEmailTv.addTextChangedListener(textWatcher);
//        this.loginPswEt.addTextChangedListener(textWatcher);
//    }
//
//    public void initView(LayoutInflater inflater, View view) {
//        this.unbinder = ButterKnife.bind((Object) this, view);
//    }
//
//    public void onDestroyView() {
//        super.onDestroyView();
//        this.unbinder.unbind();
//    }
//
//    @OnClick({2131755592, 2131755593, 2131755594})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case C0665R.id.login_psw_img:
//                if (((Boolean) Hawk.get("pwsw_img", Boolean.valueOf(true))).booleanValue()) {
//                    Hawk.put("pwsw_img", Boolean.valueOf(false));
//                    this.loginPswImg.setBackground(getResources().getDrawable(C0665R.drawable.login_ico_input_cancel));
//                    this.loginPswEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    return;
//                }
//                Hawk.put("pwsw_img", Boolean.valueOf(true));
//                this.loginPswImg.setBackground(getResources().getDrawable(C0665R.drawable.login_ico_input_open));
//                this.loginPswEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                return;
//            case C0665R.id.login_bt:
//                if (this.loginEmailTv.getText().toString().trim().equals("") || this.loginPswEt.getText().toString().trim().equals("")) {
//                    Toast.makeText(zzqF(), "信息不能为空!", 1);
//                    return;
//                }
//                ((LoginActivity) zzqF()).setProgress();
//                new Thread(this.taskLogin).start();
//                return;
//            case C0665R.id.forget_psw_tv:
//                ((LoginActivity) zzqF()).intentTo(new Intent(zzqF(), ForgetPswActivity.class));
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void doLogin(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("email", this.loginEmailTv.getText().toString().trim());
//            jsonObject.put("password", this.loginPswEt.getText().toString().trim());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C14773());
//    }
//
//    private void getDnsLogin() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            doLogin(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_LOGIN);
//        } else {
//            doLogin(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_LOGIN);
//        }
//    }
//}