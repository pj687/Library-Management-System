package com.itheima.domain;

public class Record {
    private String record_bookisbn;
    private String record_bookname;
    private String record_borrower;

    private String record_borrowtime;
    private Integer record_id;
    private String remandtime;

    public String getRecord_bookisbn() {
        return record_bookisbn;
    }

    public void setRecord_bookisbn(String record_bookisbn) {
        this.record_bookisbn = record_bookisbn;
    }

    public String getRecord_bookname() {
        return record_bookname;
    }

    public void setRecord_bookname(String record_bookname) {
        this.record_bookname = record_bookname;
    }

    public String getRecord_borrower() {
        return record_borrower;
    }

    public void setRecord_borrower(String record_borrower) {
        this.record_borrower = record_borrower;
    }

    public String getRecord_borrowtime() {
        return record_borrowtime;
    }

    public void setRecord_borrowtime(String record_borrowtime) {
        this.record_borrowtime = record_borrowtime;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public String getRemandtime() {
        return remandtime;
    }

    public void setRemandtime(String remandtime) {
        this.remandtime = remandtime;
    }
}
