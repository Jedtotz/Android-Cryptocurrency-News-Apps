package com.example.cryptonomist;

import java.io.Serializable;

public class News implements Serializable {
    String title, body, coin, source, time, domain;
    int id, flag_done;



    public News(String title, String body, String coin, String source, int id, int flag_done, String time, String domain) {
        this.title = title;
        this.body = body;
        this.coin = coin;
        this.source = source;
        this.id = id;
        this.flag_done = flag_done;
        this.time = time;
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag_done() {
        return flag_done;
    }

    public void setFlag_done(int flag_done) {
        this.flag_done = flag_done;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
