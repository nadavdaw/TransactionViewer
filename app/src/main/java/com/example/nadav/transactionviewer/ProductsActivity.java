package com.example.nadav.transactionviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nadav.transactionviewer.Adapters.ProductAdapter;

public class ProductsActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ProgressBar mLoadingIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_product);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mProductAdapter = new ProductAdapter(this,this);

        mRecyclerView.setAdapter(mProductAdapter);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

//        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(String productName) {

    }
}
