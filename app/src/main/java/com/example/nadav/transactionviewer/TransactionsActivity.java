

package com.example.nadav.transactionviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.nadav.transactionviewer.Adapters.TransactionAdapter;

public class TransactionsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TransactionAdapter mTransactionAdapter;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_transaction);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mTransactionAdapter = new TransactionAdapter(this);

        mRecyclerView.setAdapter(mTransactionAdapter);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }
}
