package com.shine.entity;

public class User_answer {
    private String wrong_b;
    private String wrong_c;
    private String _id;
    private String answer;
    private int dati_type;
    private boolean right;
    private String title;
    private int dif;
    private int seq;
    private String user_answer;
    private String wrong_a;
    public void setWrong_b(String wrong_b) {
        this.wrong_b = wrong_b;
    }
    public String getWrong_b() {
        return wrong_b;
    }

    public void setWrong_c(String wrong_c) {
        this.wrong_c = wrong_c;
    }
    public String getWrong_c() {
        return wrong_c;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_id() {
        return _id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setDati_type(int dati_type) {
        this.dati_type = dati_type;
    }
    public int getDati_type() {
        return dati_type;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean getRight() {
        return right;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDif(int dif) {
        this.dif = dif;
    }
    public int getDif() {
        return dif;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
    public int getSeq() {
        return seq;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }
    public String getUser_answer() {
        return user_answer;
    }

    public void setWrong_a(String wrong_a) {
        this.wrong_a = wrong_a;
    }
    public String getWrong_a() {
        return wrong_a;
    }

    @Override
    public String toString() {
        return "User_answer{" +
                "wrong_b='" + wrong_b + '\'' +
                ", wrong_c='" + wrong_c + '\'' +
                ", _id='" + _id + '\'' +
                ", answer='" + answer + '\'' +
                ", dati_type=" + dati_type +
                ", right=" + right +
                ", title='" + title + '\'' +
                ", dif=" + dif +
                ", seq=" + seq +
                ", user_answer='" + user_answer + '\'' +
                ", wrong_a='" + wrong_a + '\'' +
                '}';
    }
}