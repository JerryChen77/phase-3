package com.shine.entity;

import java.util.Date;
import java.util.List;

public class JsonRootBean {

    private String _id;
    private int group;
    private int score;
    private int step;
    private String phone;
    private Date time;
    private String type;
    private List<User_answer> user_answer;
    private String _openid;
    private String match;
    private String match_id;
    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_id() {
        return _id;
    }

    public void setGroup(int group) {
        this.group = group;
    }
    public int getGroup() {
        return group;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setStep(int step) {
        this.step = step;
    }
    public int getStep() {
        return step;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public Date getTime() {
        return time;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setUser_answer(List<User_answer> user_answer) {
        this.user_answer = user_answer;
    }
    public List<User_answer> getUser_answer() {
        return user_answer;
    }

    public void set_openid(String _openid) {
        this._openid = _openid;
    }
    public String get_openid() {
        return _openid;
    }

    public void setMatch(String match) {
        this.match = match;
    }
    public String getMatch() {
        return match;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }
    public String getMatch_id() {
        return match_id;
    }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "_id='" + _id + '\'' +
                ", group=" + group +
                ", score=" + score +
                ", step=" + step +
                ", phone='" + phone + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", user_answer=" + user_answer +
                ", _openid='" + _openid + '\'' +
                ", match='" + match + '\'' +
                ", match_id='" + match_id + '\'' +
                '}';
    }
}