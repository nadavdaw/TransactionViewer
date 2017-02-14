package com.example.nadav.transactionviewer.Utils;

import android.content.Context;

import com.example.nadav.transactionviewer.Models.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nadav on 13/02/2017.
 */

public class GeneralUtils {
    private static final String TRANSACTIONS = "transactions.json";

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static ArrayList<Transaction> getJsonArrayFromString(String list) {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(list);
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String amount = jsonObject.getString("amount");
                    String currency = jsonObject.getString("currency");
                    String sku = jsonObject.getString("sku");
                    if (amount != null && currency != null && sku != null) {
                        Transaction transaction = new Transaction(amount, currency, sku);
                        transactionArrayList.add(transaction);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transactionArrayList;
    }

    private static HashMap<String, List<Transaction>> getProductList(ArrayList<Transaction> list) {
        HashMap<String, List<Transaction>> map = new HashMap<>();
        for (Transaction transaction : list) {
            String key  = transaction.sku;
            if(map.containsKey(key)){
                List<Transaction> listTmp = map.get(key);
                listTmp.add(transaction);

            }else{
                List<Transaction> listTmp = new ArrayList<Transaction>();
                listTmp.add(transaction);
                map.put(key, listTmp);
            }

        }
        return map;
    }

    public static HashMap<String, List<Transaction>> getProductsFromFile(Context context) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        HashMap<String, List<Transaction>> products = new HashMap<>();
        String loadFromFile = loadJSONFromAsset(context, TRANSACTIONS);
        if (loadFromFile != null) {
            transactions = getJsonArrayFromString(loadFromFile);
        }
        if (!transactions.isEmpty()) {
            products = getProductList(transactions);
        }
        return products;
    }
    public static List<Transaction> getTransactionsOfProduct(Context context,String sku) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        HashMap<String, List<Transaction>> products = new HashMap<>();
        List<Transaction> transactionOfProduct = new ArrayList<>();
        String loadFromFile = loadJSONFromAsset(context, TRANSACTIONS);
        if (loadFromFile != null) {
            transactions = getJsonArrayFromString(loadFromFile);
        }
        if (!transactions.isEmpty()) {
            products = getProductList(transactions);
        }
        if(!products.isEmpty()){
            transactionOfProduct = products.get(sku);
        }
        return transactionOfProduct;
    }
    public static float calculateAmount(List<Transaction>list){
        int sum = 0;
        if(!list.isEmpty()){
            for (Transaction transaction: list){
                sum += Float.valueOf(transaction.amount);
            }
        }
        return sum;
    }

}
