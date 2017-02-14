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
    @SerializedName("sku")
    @Expose
    public String sku;

    public Transaction() {
    }

    public Transaction(String amount, String currency, String sku){
        this.amount = amount;
        this.currency = currency;
        this.sku = sku;
    }
}
