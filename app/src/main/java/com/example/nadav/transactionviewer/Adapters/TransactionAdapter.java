package com.example.nadav.transactionviewer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadav.transactionviewer.Models.Transaction;
import com.example.nadav.transactionviewer.R;

import java.util.List;

/**
 * Created by Nadav on 13/02/2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionAdapterViewHolder> {

    private List<Transaction> mTransactions;
    private Context mContext;

    public TransactionAdapter(Context context) {
        mContext = context;
    }

    public class TransactionAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mAmountTextView;
        public final TextView mConversionTextView;

        public TransactionAdapterViewHolder(View view) {
            super(view);
            mAmountTextView = (TextView) view.findViewById(R.id.tv_amount_name);
            mConversionTextView = (TextView) view.findViewById(R.id.tv_conversion_transactions);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public TransactionAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.transaction_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new TransactionAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionAdapterViewHolder TransactionAdapterViewHolder, int position) {
        String amountName = mTransactions.get(position).amount;
        String conversionName = mTransactions.get(position).conversion;
        String ccurrencyName = mTransactions.get(position).currency;

        TransactionAdapterViewHolder.mAmountTextView.setText(ccurrencyName+" "+amountName);
        TransactionAdapterViewHolder.mConversionTextView.setText(conversionName);
    }

    @Override
    public int getItemCount() {
        if (null == mTransactions) return 0;
        return mTransactions.size();
    }

    public void setTransactionData(List<Transaction> Transactions) {
        mTransactions = Transactions;
        notifyDataSetChanged();
    }
}
