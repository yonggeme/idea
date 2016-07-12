package com.idea.dto;

/**
 * Created by Administrator on 2016/5/21.
 */
public class Exposer {

    private boolean isExporer;

    private long seckillId;

    private String md5;

    private long startTime;

    private long endTime;

    private long now;

    public Exposer() {
    }

    public Exposer(boolean isExporer, String md5, long seckillId) {
        this.isExporer = isExporer;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean isExporer, long seckillId) {
        this.isExporer = isExporer;
        this.seckillId = seckillId;
    }

    public Exposer(boolean isExporer, long startTime, long endTime, long now){
        this.isExporer = isExporer;
        this.startTime = startTime;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
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

    @Override
    public String toString() {
        return "Exposer{" +
                "isExporer=" + isExporer +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", now=" + now +
                '}';
    }
}
