package com.lion.graduation.model;

/**
 * 泛型，用于RecyclerView添加View，其中type属性代表View的类型，T属性代表View相关数据
 * Created by Lion on 2015/3/19.
 */
public class General<T> {

    private int type;
    private T ob;

    public General(int type, T ob) {
        this.type = type;
        this.ob = ob;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
}
