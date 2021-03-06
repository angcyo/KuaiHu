package com.angcyo.kuaihu.http;

import android.os.Environment;
import java.util.ArrayList;
import java.util.Arrays;

public class Constant {
    public static final String AES_IV = "5efd3f6060emaomi";
    public static final String AES_PWD = "625202f9149maomi";
    public static final String API_ADD_COMMENT = "/api/community/addComment";
    public static final String API_ALL_NOTICE = "/api/notice/listCenter";
    public static final String API_APP = "/api/apps/index";
    public static final String API_APPDOWN = "/api/apps/downloadApp";
    public static final String API_APPSHOW = "/api/apps/showApps";
    public static final String API_ATTENTION_LIST = "/api/community/attentionList";
    public static final String API_ATTENTION_USER = "/api/community/attentionUser";
    public static final String API_CAHT_INFO = "/api/chats/listOneChats";
    public static final String API_COLLECTION = "/api/community/videoCollect";
    public static final String API_COLLECTION_LIST = "/api/community/videoCollectList";
    public static final String API_DIRECTMESSAGE_LIST = "/api/chats/getChatUsers";
    public static final String API_DOMAIN = "/api/system/getSiteName";
    public static final String API_EXCHANGE_CODE = "/api/exchange/exVipCard";
    public static final String API_EXCHANGE_COIN = "/api/exchange/index";
    public static final String API_EXCHANGE_COIN_LIST = "/api/exchange/listAll";
    public static final String API_FEEDBACK = "/api/users/feedback";
    public static final String API_FINDPWD = "/api/users/findPassword";
    public static final String API_FQA = "/api/question/listAll";
    public static final String API_GET_COMMENT = "/api/community/listComments";
    public static final String API_GUIDE = "/api/boot/listAll";
    public static final String API_LIKE_USER = "/api/community/videoLike";
    public static final String API_LOGIN = "/api/users/login";
    public static final String API_NEW_NOTICE = "/api/notice/listAll";
    public static final String API_REGISTER = "/api/users/register";
    public static final String API_SEND_CHAT = "/api/chats/sendMsg";
    public static final String API_SHARE = "/api/system/getInfo";
    public static final String API_SPLASH = "/api/boot/bootUp";
    public static final String API_STATIC = "/api/statistics/getData";
    public static final String API_UPDATE_AVATAR = "/api/users/updateavatar";
    public static final String API_UPDATE_PSW = "/api/users/editPsd";
    public static final String API_UPDATE_USER = "/api/users/updateUserInfo";
    public static final String API_UPLOAD_IMG = "/api/videos/create";
    public static final String API_UPLOAD_VIDEO = "/api/videos/video_post";
    public static final String API_USER = "/api/users/getUserInfo";
    public static final String API_USER_BAS = "/api/users/userdata";
    public static final String API_VERSION = "/api/version/getVersion";
    public static final String API_VIDEO_ADS = "/api/ads/index";
    public static final String API_VIDEO_ALL = "/api/videos/listAll";
    public static final String API_VIDEO_DETAIL = "/api/videos/detail";
    public static final String API_VIDEO_HOT = "/api/videos/listHot";
    public static final String API_WEBSITE = "/api/community/getWebsite";
    public static String BACKUP_DOMAIN = "123.apikuaihu.com";
    public static final int BANNER_TIME = 4000;
    public static String DEFAULT_BACKUP_DOMAIN = "api.kuaihuapi.com";
    public static ArrayList<String> DOMAINLIST = new ArrayList(Arrays.asList(new String[]{"api.kuaihuapi.com", "api.khuapi.com"}));
    public static final String DOWNLOAD = (Environment.getExternalStorageDirectory().getPath() + "/download/");
    public static String HOST_HTTP = "http://";
    public static String HOST_HTTPS = "http://";
    public static String HOST_PORT = ":8099";
    public static String HOST_PORT_UPLOAD = ":8090";
    public static String PATH_NOVEL = AppUtils.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    public static String PATH_PICTURE = AppUtils.getAppContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    public static final String SDCARD = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/FoxVideo");
    public static final int SPLASH_TIME = 10000;
    public static final String URL_SIG_KEY = "maomi_pass_xyz";
    public static final int VIDEO_BANNER_TIME = 10000;
    public static String VIDEO_HOST = "up.apikuaihu.com";
}