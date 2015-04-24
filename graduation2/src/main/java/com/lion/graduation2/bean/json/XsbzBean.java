package com.lion.graduation2.bean.json;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Lion on 2015/4/23.
 */
@Table(name = "xsbz")
public class XsbzBean {

    private int id;
    private int sblx_id;
    private int xsnr_id;
    private String description;
    private String note;

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

    public int getXsnr_id() {
        return xsnr_id;
    }

    public void setXsnr_id(int xsnr_id) {
        this.xsnr_id = xsnr_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "XsbzBean{" +
                "id=" + id +
                ", sblx_id=" + sblx_id +
                ", xsnr_id=" + xsnr_id +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
