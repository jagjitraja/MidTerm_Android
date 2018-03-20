package com.example.t00533766.mid_term;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by T00533766 on 3/19/2018.
 */
@Entity
public class NumbersData {

    @PrimaryKey(autoGenerate = true)
    private int number_id;
    @ColumnInfo
    private String text;

    @ColumnInfo
    private String number;

    public NumbersData(String text, String number) {
        this.text = text;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumber_id() {
        return number_id;
    }

    public void setNumber_id(int number_id) {
        this.number_id = number_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
