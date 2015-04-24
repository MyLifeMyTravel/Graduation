package com.lion.graduation2.bean.json;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Lion on 2015/4/23.
 */
@Table(name = "cdlx")
public class CdlxBean {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CdlxBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
