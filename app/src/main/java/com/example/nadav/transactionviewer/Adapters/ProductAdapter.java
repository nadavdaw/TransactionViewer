package com.example.nadav.transactionviewer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadav.transactionviewer.Models.Product;
import com.example.nadav.transactionviewer.R;

import java.util.List;


/**
 * Created by Nadav on 13/02/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder> {

    private List<Product> mProducts;

    private final ProductAdapterOnClickHandler mClickHandler;

    public interface ProductAdapterOnClickHandler {
        void onClick(String productName);
    }

    public ProductAdapter(ProductAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
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
            mClickHandler.onClick(mProductTextView.getText().toString());
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
        String weatherForThisDay = mProducts.[position];
        productAdapterViewHolder.mProductTextView.setText(weatherForThisDay);
    }

    @Override
    public int getItemCount() {
        if (null == mWeatherData) return 0;
        return mWeatherData.length;
    }

    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }
}
