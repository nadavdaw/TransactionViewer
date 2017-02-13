package com.example.nadav.transactionviewer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nadav on 13/02/2017.
 */

public class Transaction {
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("conversion")
    @Expose
    public String conversion;

}
