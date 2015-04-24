package com.lion.graduation2.bean.json;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Lion on 2015/4/23.
 */
@Table(name = "xsnr")
public class XsnrBean {

    private int id;
    private int sblx_id;
    private String nr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSblx_id() {
        return sblx_id;
    }

    public void setSblx_id(int sblx_id) {
        this.sblx_id = sblx_id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "XsnrBean{" +
                "id=" + id +
                ", sblx_id=" + sblx_id +
                ", nr='" + nr + '\'' +
                '}';
    }
}
