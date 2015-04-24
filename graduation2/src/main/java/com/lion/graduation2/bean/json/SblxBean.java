package com.lion.graduation2.bean.json;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Lion on 2015/4/23.
 */
@Table(name = "sblx")
public class SblxBean {

    private int id;
    private int cdlx_id;
    private String name;
    private String params;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCdlx_id() {
        return cdlx_id;
    }

    public void setCdlx_id(int cdlx_id) {
        this.cdlx_id = cdlx_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SblxBean{" +
                "id=" + id +
                ", cdlx_id=" + cdlx_id +
                ", name='" + name + '\'' +
                ", params='" + params + '\'' +
                ", status=" + status +
                '}';
    }
}
