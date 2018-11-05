package com.angcyo.kuaihu.analyze;

import java.io.Serializable;

public class VideoModel implements Serializable {
    private String is_cat_ads;
    private String mu_avatar;
    private String mu_id;
    private String mu_name;
    private String mv_comment;
    private String mv_created;
    private String mv_id;
    private String mv_img_url;
    private String mv_like;
    private String mv_play_height;
    private String mv_play_url;
    private String mv_play_width;
    private String mv_read;
    private String mv_status;
    private String mv_title;
    private String mv_updated;

    public String getMv_status() {
        return this.mv_status;
    }

    public void setMv_status(String mv_status) {
        this.mv_status = mv_status;
    }

    public String getMu_name() {
        return this.mu_name;
    }

    public void setMu_name(String mu_name) {
        this.mu_name = mu_name;
    }

    public String getMu_avatar() {
        return this.mu_avatar;
    }

    public void setMu_avatar(String mu_avatar) {
        this.mu_avatar = mu_avatar;
    }

    public String getMv_id() {
        return this.mv_id;
    }

    public void setMv_id(String mv_id) {
        this.mv_id = mv_id;
    }

    public String getMu_id() {
        return this.mu_id;
    }

    public void setMu_id(String mu_id) {
        this.mu_id = mu_id;
    }

    public String getMv_title() {
        return this.mv_title;
    }

    public void setMv_title(String mv_title) {
        this.mv_title = mv_title;
    }

    public String getMv_img_url() {
        return this.mv_img_url;
    }

    public void setMv_img_url(String mv_img_url) {
        this.mv_img_url = mv_img_url;
    }

    public String getMv_play_url() {
        return this.mv_play_url;
    }

    public void setMv_play_url(String mv_play_url) {
        this.mv_play_url = mv_play_url;
    }

    public String getMv_play_width() {
        return this.mv_play_width;
    }

    public void setMv_play_width(String mv_play_width) {
        this.mv_play_width = mv_play_width;
    }

    public String getMv_play_height() {
        return this.mv_play_height;
    }

    public void setMv_play_height(String mv_play_height) {
        this.mv_play_height = mv_play_height;
    }

    public String getMv_read() {
        return this.mv_read;
    }

    public void setMv_read(String mv_read) {
        this.mv_read = mv_read;
    }

    public String getMv_like() {
        return this.mv_like;
    }

    public void setMv_like(String mv_like) {
        this.mv_like = mv_like;
    }

    public String getMv_comment() {
        return this.mv_comment;
    }

    public void setMv_comment(String mv_comment) {
        this.mv_comment = mv_comment;
    }

    public String getMv_created() {
        return this.mv_created;
    }

    public void setMv_created(String mv_created) {
        this.mv_created = mv_created;
    }

    public String getMv_updated() {
        return this.mv_updated;
    }

    public void setMv_updated(String mv_updated) {
        this.mv_updated = mv_updated;
    }

    public String getIs_cat_ads() {
        return this.is_cat_ads;
    }

    public void setIs_cat_ads(String is_cat_ads) {
        this.is_cat_ads = is_cat_ads;
    }
}