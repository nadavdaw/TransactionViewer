package com.example.nadav.transactionviewer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadav on 13/02/2017.
 */

public class Rate {
    @SerializedName("from")
    @Expose
    public String from;
    @SerializedName("rate")
    @Expose
    public float rate;
    @SerializedName("to")
    @Expose
    public String to;

    public Rate(String from, float rate, String to) {
        this.from = from;
        this.rate = rate;
        this.to = to;
    }
}
