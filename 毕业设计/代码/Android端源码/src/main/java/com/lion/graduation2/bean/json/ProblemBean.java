package com.lion.graduation2.bean.json;

/**
 * Created by Lion on 2015/4/24.
 */
public class ProblemBean {

    /* 巡视标准ID */
    private int xsbz_id;
    /* 问题备注 */
    private String note;

    public int getXsbz_id() {
        return xsbz_id;
    }

    public void setXsbz_id(int xsbz_id) {
        this.xsbz_id = xsbz_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "xsbz_id=" + xsbz_id +
                ", note='" + note + '\'' +
                '}';
    }
}
