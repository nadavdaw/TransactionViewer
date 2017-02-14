

package com.example.nadav.transactionviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nadav.transactionviewer.Adapters.TransactionAdapter;
import com.example.nadav.transactionviewer.Models.Transaction;
import com.example.nadav.transactionviewer.Utils.GeneralUtils;

import java.util.List;

public class TransactionsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView tvTotalAmount;
    private TransactionAdapter mTransactionAdapter;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_transaction);
        tvTotalAmount = (TextView) findViewById(R.id.tv_total_amount);
        Bundle extras = getIntent().getExtras();
        String sku = null;
        if(extras !=null) {
            sku = extras.getString(getString(R.string.sku));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mTransactionAdapter = new TransactionAdapter(this);

        mRecyclerView.setAdapter(mTransactionAdapter);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        if(sku!=null){
            getSupportActionBar().setTitle(getString(R.string.transaction_for)+" "+sku);
            loadData(sku);
        }

    }
    private void loadData(String sku){
        mLoadingIndicator.setVisibility(View.VISIBLE);
        List<Transaction> transactionsOfProduct = GeneralUtils.getTransactionsOfProduct(this,sku);
        tvTotalAmount.setText(getString(R.string.total_amount)+" "+ GeneralUtils.calculateAmount(transactionsOfProduct));
        mTransactionAdapter.setTransactionData(transactionsOfProduct);
        mLoadingIndicator.setVisibility(View.GONE);
    }
}
