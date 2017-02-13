package com.example.nadav.transactionviewer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nadav on 13/02/2017.
 */

public class Product {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("transactions")
    @Expose
    public List<Transaction> transactions;
}
