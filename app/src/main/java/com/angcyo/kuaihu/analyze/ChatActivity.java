//package com.angcyo.kuaihu.analyze;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
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
//import com.xxx.foxvideo.model.ChatMessage;
//import com.xxx.foxvideo.model.UserModel;
//import com.xxx.foxvideo.p005ui.home.adapter.ChatAdapter;
//import com.xxx.foxvideo.widget.xlistview.XListView;
//import com.xxx.foxvideo.widget.xlistview.XListView.IXListViewListener;
//import java.lang.reflect.Type;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.List;
//import okhttp3.Call;
//import okhttp3.Request;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity */
//public class ChatActivity extends BaseActivity implements IXListViewListener {
//    public static final String EXTRA_KEY_AVATAR = "avatar";
//    public static final String EXTRA_KEY_UID = "uId";
//    public static final String EXTRA_KEY_UNAME = "uName";
//    private TextView btn_next;
//    private TextView btn_pre;
//    private RelativeLayout chatBg;
//    private ImageButton chatting_mode_btn;
//    private Gson gson = new GsonBuilder().create();
//    private Handler handler = new Handler();
//    private String mChatToAvatar;
//    private String mChatToUID;
//    private String mChatToUName = "";
//    private OnClickListener mClickListener;
//    private Comparator<ChatMessage> mComparator = new C071510();
//    private Handler mHandler = new Handler();
//    private EditText mInputEt;
//    private ChatAdapter mListAdapter;
//    private XListView mListView;
//    private ArrayList<ChatMessage> mMsgList = new ArrayList();
//    private Button mSendBtn;
//    private Type messageType = new C12442().getType();
//    private Type messagesType = new C12431().getType();
//    private int page = 1;
//    private int pageCount = 0;
//    private int perPage = 20;
//    private PopupWindow popup;
//    Runnable taskChat = new C071611();
//    Runnable taskSend = new C071712();
//    private TextView title;
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$10 */
//    class C071510 implements Comparator<ChatMessage> {
//        C071510() {
//        }
//
//        public int compare(ChatMessage lhs, ChatMessage rhs) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                Date ldate = sdf.parse(lhs.getMc_created());
//                Date rdate = sdf.parse(rhs.getMc_created());
//                if (ldate.getTime() > rdate.getTime()) {
//                    return 1;
//                }
//                if (ldate.getTime() < rdate.getTime()) {
//                    return -1;
//                }
//                return 0;
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$11 */
//    class C071611 implements Runnable {
//        C071611() {
//        }
//
//        public void run() {
//            ChatActivity.this.getDnsChat();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$12 */
//    class C071712 implements Runnable {
//        C071712() {
//        }
//
//        public void run() {
//            ChatActivity.this.getDnsSend();
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$3 */
//    class C07183 implements OnTouchListener {
//        C07183() {
//        }
//
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == 0) {
//                ChatActivity.this.showKeywordMethod(v);
//                if (ChatActivity.this.mMsgList != null && ChatActivity.this.mMsgList.size() - 1 >= 0) {
//                    ChatActivity.this.mListView.setSelection(ChatActivity.this.mMsgList.size() - 1);
//                }
//            }
//            return false;
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$4 */
//    class C07194 implements OnTouchListener {
//        C07194() {
//        }
//
//        public boolean onTouch(View v, MotionEvent event) {
//            ChatActivity.this.hideKeywordMethod();
//            return false;
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$5 */
//    class C07205 implements TextWatcher {
//        C07205() {
//        }
//
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        }
//
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        public void afterTextChanged(Editable s) {
//            if (s.toString().trim().length() == 0) {
//                ChatActivity.this.mSendBtn.setVisibility(8);
//            } else {
//                ChatActivity.this.mSendBtn.setVisibility(0);
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$6 */
//    class C07216 implements Runnable {
//        C07216() {
//        }
//
//        public void run() {
//            ChatActivity.this.mListView.setSelection(ChatActivity.this.mListAdapter.getCount());
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$7 */
//    class C07227 implements OnClickListener {
//        C07227() {
//        }
//
//        public void onClick(View v) {
//            int vId = v.getId();
//            if (vId == C0665R.id.chat_send_btn) {
//                ChatActivity.this.sendTextMsg();
//            } else if (vId == C0665R.id.btn_pre) {
//                ChatActivity.this.closeActivity();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$1 */
//    class C12431 extends TypeToken<List<ChatMessage>> {
//        C12431() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$2 */
//    class C12442 extends TypeToken<ChatMessage> {
//        C12442() {
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$8 */
//    class C14458 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$8$1 */
//        class C07231 implements Runnable {
//            C07231() {
//            }
//
//            public void run() {
//                if (ChatActivity.this.page == 1) {
//                    ChatActivity.this.setProgress("");
//                }
//            }
//        }
//
//        C14458() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//            ChatActivity.this.handler.post(new C07231());
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
//            ChatActivity.this.setCloseProgress();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                System.out.println("jsonObject:" + jsonObject);
//                if (code == 0) {
//                    JSONObject json_data = jsonObject.getJSONObject("data");
//                    ChatActivity.this.pageCount = json_data.getInt("pageCount");
//                    List<ChatMessage> tempList = (List) ChatActivity.this.gson.fromJson(json_data.getJSONArray("list").toString(), ChatActivity.this.messagesType);
//                    int oldSize = ChatActivity.this.mMsgList.size();
//                    if (ChatActivity.this.page == 1) {
//                        ChatActivity.this.mMsgList.clear();
//                        ChatActivity.this.mMsgList.addAll(tempList);
//                    } else {
//                        ChatActivity.this.mMsgList.addAll(tempList);
//                    }
//                    ChatActivity.this.sortMsgList();
//                    ChatActivity.this.mListAdapter.notifyDataSetChanged();
//                    if (oldSize > 0) {
//                        ChatActivity.this.mListView.setSelection(ChatActivity.this.mMsgList.size() - oldSize);
//                    } else {
//                        ChatActivity.this.moveToLast();
//                    }
//                    ChatActivity.this.mListView.stopRefresh();
//                    return;
//                }
//                Toast.makeText(ChatActivity.this, jsonObject.getString("message"), 1).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$9 */
//    class C14469 extends StringCallback {
//
//        /* renamed from: com.xxx.foxvideo.ui.home.activity.ChatActivity$9$1 */
//        class C07241 implements Runnable {
//            C07241() {
//            }
//
//            public void run() {
//                if (ChatActivity.this.page == 1) {
//                    ChatActivity.this.setProgress("发送中...");
//                }
//            }
//        }
//
//        C14469() {
//        }
//
//        public void onBefore(Request request, int id) {
//            super.onBefore(request, id);
//            ChatActivity.this.handler.post(new C07241());
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
//            ChatActivity.this.setCloseProgress();
//            try {
//                JSONObject jsonObject = new JSONObject(AesEncryptionUtil.decrypt(response, Constant.AES_PWD, Constant.AES_IV));
//                int code = jsonObject.getInt("code");
//                System.out.println("jsonObject:" + jsonObject);
//                if (code == 0) {
//                    JSONObject json_data = jsonObject.getJSONObject("data");
//                    ChatMessage temp = new ChatMessage();
//                    temp = (ChatMessage) ChatActivity.this.gson.fromJson(json_data.toString(), ChatActivity.this.messageType);
//                    ChatMessage cm = new ChatMessage();
//                    cm.setMc_send_uid(((UserModel) Hawk.get("user")).getMu_id());
//                    cm.setMc_reveive_uid(ChatActivity.this.mChatToUID);
//                    cm.setMc_message(ChatActivity.this.mInputEt.getText().toString());
//                    cm.setMc_created(temp.getMc_created());
//                    ChatActivity.this.mMsgList.add(cm);
//                    ChatActivity.this.sortMsgList();
//                    ChatActivity.this.mListAdapter.notifyDataSetChanged();
//                    ChatActivity.this.mInputEt.setText("");
//                    ChatActivity.this.hideKeywordMethod();
//                    Toast.makeText(ChatActivity.this, "发送成功!", 1).show();
//                    return;
//                }
//                Toast.makeText(ChatActivity.this, jsonObject.getString("message"), 1).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView((int) C0665R.layout.msg_chat);
//        initClickListener();
//        initView();
//        initData();
//    }
//
//    public String getChatToUID() {
//        return this.mChatToUID;
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    private void initView() {
//        this.btn_pre = (TextView) findViewById(C0665R.id.btn_pre);
//        this.btn_next = (TextView) findViewById(C0665R.id.btn_next);
//        this.title = (TextView) findViewById(C0665R.id.head_title);
//        this.title.setText(this.mChatToUName);
//        this.btn_pre.setClickable(true);
//        this.btn_pre.setOnClickListener(this.mClickListener);
//        this.btn_next.setVisibility(8);
//        this.btn_next.setClickable(true);
//        this.btn_next.setOnClickListener(this.mClickListener);
//        this.title.setText("私信");
//        this.chatBg = (RelativeLayout) findViewById(C0665R.id.chatBg);
//        this.mInputEt = (EditText) findViewById(C0665R.id.chat_input_et);
//        this.mInputEt.setOnTouchListener(new C07183());
//        this.mSendBtn = (Button) findViewById(C0665R.id.chat_send_btn);
//        this.mSendBtn.setEnabled(true);
//        this.mSendBtn.setOnClickListener(this.mClickListener);
//        this.mListView = (XListView) findViewById(C0665R.id.chat_lv);
//        this.mListView.setXListViewListener(this);
//        this.mListView.setPullLoadEnable(false);
//        this.mListView.setOverScrollMode(2);
//        this.mListView.setOnTouchListener(new C07194());
//        this.mInputEt.addTextChangedListener(new C07205());
//    }
//
//    private void initData() {
//        this.mChatToUID = getIntent().getStringExtra(EXTRA_KEY_UID);
//        this.mChatToUName = TextUtils.isEmpty(getIntent().getStringExtra(EXTRA_KEY_UNAME)) ? "" : getIntent().getStringExtra(EXTRA_KEY_UNAME);
//        this.mChatToAvatar = TextUtils.isEmpty(getIntent().getStringExtra(EXTRA_KEY_AVATAR)) ? "" : getIntent().getStringExtra(EXTRA_KEY_AVATAR);
//        this.mListAdapter = new ChatAdapter(this, this.mMsgList, this.mChatToUID, this.mChatToUName, this.mChatToAvatar);
//        this.mListView.setAdapter(this.mListAdapter);
//        this.title.setText(this.mChatToUName);
//        this.page = 1;
//        new Thread(this.taskChat).start();
//    }
//
//    private void moveToLast() {
//        this.mListView.post(new C07216());
//    }
//
//    private void initClickListener() {
//        this.mClickListener = new C07227();
//    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == 4) {
//            closeActivity();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void sendTextMsg() {
//        new Thread(this.taskSend).start();
//    }
//
//    private void showKeywordMethod(View v) {
//        ((InputMethodManager) getSystemService("input_method")).showSoftInput(v, 1);
//    }
//
//    private void hideKeywordMethod() {
//        InputMethodManager imm = (InputMethodManager) getSystemService("input_method");
//        View focusView = getCurrentFocus();
//        if (focusView != null) {
//            imm.hideSoftInputFromWindow(focusView.getWindowToken(), 2);
//        }
//    }
//
//    protected void onPause() {
//        super.onPause();
//    }
//
//    protected void onStop() {
//        super.onStop();
//    }
//
//    protected void onResume() {
//        super.onResume();
//    }
//
//    protected void onStart() {
//        super.onStart();
//    }
//
//    public void onRefresh() {
//        if (this.page <= this.pageCount) {
//            this.page++;
//            new Thread(this.taskChat).start();
//            return;
//        }
//        this.mListView.stopRefresh();
//    }
//
//    public void onLoadMore() {
//    }
//
//    public void getChatInfo(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(EXTRA_KEY_UID, ((UserModel) Hawk.get("user")).getMu_id());
//            jsonObject.put("fuId", this.mChatToUID);
//            jsonObject.put("page", this.page);
//            jsonObject.put("perPage", this.perPage);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C14458());
//    }
//
//    public void sendChat(String path) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("send_uid", ((UserModel) Hawk.get("user")).getMu_id());
//            jsonObject.put("receive_uid", this.mChatToUID);
//            jsonObject.put("message", this.mInputEt.getText());
//            System.out.println("uid:" + ((UserModel) Hawk.get("user")).getMu_id() + " " + this.mChatToUID);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ((PostFormBuilder) OkHttpUtils.post().addParams("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV)).getParams().url(path)).build().execute(new C14469());
//    }
//
//    public void sortMsgList() {
//        Collections.sort(this.mMsgList, this.mComparator);
//    }
//
//    private void getDnsChat() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            getChatInfo(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_CAHT_INFO);
//        } else {
//            getChatInfo(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_CAHT_INFO);
//        }
//    }
//
//    private void getDnsSend() {
//        String ip = HttpDNS.getAddressByName(MainActivity.DOMAIN);
//        System.out.println("ip:" + ip);
//        if ("".equals(ip)) {
//            sendChat(Constant.HOST_HTTPS + Constant.BACKUP_DOMAIN + Constant.API_SEND_CHAT);
//        } else {
//            sendChat(Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_SEND_CHAT);
//        }
//    }
//}