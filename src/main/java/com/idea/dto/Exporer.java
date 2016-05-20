package com.idea.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/21.
 */
public class Exporer {

    private boolean isExporer;

    private long seckillId;

    private String md5;

    private long setartTime;

    private long endTime;

    private long now;

    public Exporer() {
    }

    public Exporer(boolean isExporer, String md5) {
        this.isExporer = isExporer;
        this.md5 = md5;
    }

    public Exporer(boolean isExporer, long seckillId) {
        this.isExporer = isExporer;
        this.seckillId = seckillId;
    }

    public Exporer(boolean isExporer, long setartTime, long endTime, long now){
        this.isExporer = isExporer;
        this.setartTime = setartTime;
        this.endTime = endTime;
        this.now = now;
    }

    public boolean isExporer() {
        return isExporer;
    }

    public void setExporer(boolean exporer) {
        isExporer = exporer;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSetartTime() {
        return setartTime;
    }

    public void setSetartTime(long setartTime) {
        this.setartTime = setartTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
}
