package com.example.nadav.transactionviewer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadav.transactionviewer.Models.Transaction;
import com.example.nadav.transactionviewer.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Nadav on 13/02/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder> {

    HashMap<String,List<Transaction>> mProducts = new HashMap<>();
    private String[] mKeys;

    private final ProductAdapterOnClickHandler mClickHandler;
    private Context mContext;

    public interface ProductAdapterOnClickHandler {
        void onClick(String productName);
    }

    public ProductAdapter(Context context,ProductAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        mContext = context;
    }

    public class ProductAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mProductTextView;
        public final TextView mTransactionsTextView;

        public ProductAdapterViewHolder(View view) {
            super(view);
            mProductTextView = (TextView) view.findViewById(R.id.tv_product_name);
            mTransactionsTextView = (TextView) view.findViewById(R.id.tv_product_transactions);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String productName = mKeys[adapterPosition];
            mClickHandler.onClick(productName);
        }
    }

    @Override
    public ProductAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.product_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ProductAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapterViewHolder productAdapterViewHolder, int position) {
        String productName = mKeys[position];
        int transactions = mProducts.get(productName).size();
        productAdapterViewHolder.mProductTextView.setText(productName);
        productAdapterViewHolder.mTransactionsTextView.setText(transactions + " " + mContext.getString(R.string.transactions_amount));
    }

    @Override
    public int getItemCount() {
        if (null == mKeys) return 0;
        return mKeys.length;
    }

    public void setProductData(HashMap<String,List<Transaction>>  products) {
        mProducts = products;
        mKeys = mProducts.keySet().toArray(new String[products.size()]);
        notifyDataSetChanged();
    }
}
