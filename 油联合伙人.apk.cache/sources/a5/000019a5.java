package com.yltx.oil.partner.modules.profit.activity;

/* loaded from: classes.dex */
public class Sy_Sett_recyclerview_Bean {
    public double money;
    public int month;
    public int year;

    public Sy_Sett_recyclerview_Bean() {
    }

    public Sy_Sett_recyclerview_Bean(int i, int i2, double d) {
        this.year = i;
        this.month = i2;
        this.money = d;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double d) {
        this.money = d;
    }
}