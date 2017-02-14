package com.example.nadav.transactionviewer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadav on 14/02/2017.
 */

public class nodeRate {
    @SerializedName("rate")
    @Expose
    public float rate;
    @SerializedName("to")
    @Expose
    public String to;

    public nodeRate(String to,float rate) {
        this.rate = rate;
        this.to = to;
    }
}
