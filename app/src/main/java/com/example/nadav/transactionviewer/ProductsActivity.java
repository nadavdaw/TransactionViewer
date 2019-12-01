package com.example.nadav.transactionviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nadav.transactionviewer.Adapters.ProductAdapter;
import com.example.nadav.transactionviewer.Models.Transaction;
import com.example.nadav.transactionviewer.Utils.GeneralUtils;
import com.example.nadav.transactionviewer.Utils.RatesUtils;

import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ProgressBar mLoadingIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setTitle(getString(R.string.products));
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_product);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mProductAdapter = new ProductAdapter(this,this);

        mRecyclerView.setAdapter(mProductAdapter);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        //try this
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(String productName) {
        Context context = this;
        Class destinationClass = TransactionsActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(getString(R.string.sku),productName);
        startActivity(intent);
    }
    private void loadData(){
        mLoadingIndicator.setVisibility(View.VISIBLE);
        HashMap<String,List<Transaction>> productsFromFile = GeneralUtils.getProductsFromFile(this);
        RatesUtils.getRatesFromFile(this);
        mProductAdapter.setProductData(productsFromFile);
        mLoadingIndicator.setVisibility(View.GONE);
    }
}
