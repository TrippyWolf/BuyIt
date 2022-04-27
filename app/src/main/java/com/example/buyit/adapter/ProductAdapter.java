package com.example.buyit.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyit.CartRepository;
import com.example.buyit.R;
import com.example.buyit.databinding.MyProductItemBinding;
import com.example.buyit.model.CartModel;
import com.example.buyit.model.ProductModel;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<ProductModel> productList;
    private CartRepository cartRepository;




    public ProductAdapter(ArrayList<ProductModel> productList, CartRepository cartRepository) {
        this.productList = productList;
        this.cartRepository = cartRepository;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_product_item,parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final ProductModel myProductModel=productList.get(position);
        holder.bind(myProductModel);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MyProductItemBinding myProductItemBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myProductItemBinding= DataBindingUtil.bind(itemView);
        }


        public void bind(ProductModel productModel) {
            myProductItemBinding.setItemModel(productModel);

            LiveData<CartModel> cart_productData = cartRepository.hasProduct(productModel.getProductId());

            cart_productData.observeForever(new Observer<CartModel>() {
                @Override
                public void onChanged(CartModel cart_model) {
                    if(cart_model != null){
                        myProductItemBinding.addToCart.setBackgroundTintList(ColorStateList.valueOf(itemView.getResources().getColor(R.color.purple_100)));
                    }
                }
            });


            myProductItemBinding.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LiveData<CartModel> cart_productLiveData = cartRepository.hasProduct(productModel.getProductId());
                    cart_productLiveData.observeForever(new Observer<CartModel>() {
                     @Override
                     public void onChanged(CartModel cart_model) {
                         if(cart_model != null){
                             cartRepository.deleteNote(new CartModel(productModel.getProductId(),productModel.getImageId(),productModel.getProductName(),productModel.getProductPrice(),4));
                            cart_productLiveData.removeObserver(this);
                             myProductItemBinding.addToCart.setBackgroundTintList(ColorStateList.valueOf(itemView.getResources().getColor(R.color.grey)));
                         }else{
                             cartRepository.insert(new CartModel(productModel.getProductId(),productModel.getImageId(),productModel.getProductName(),productModel.getProductPrice(),1));
                             cart_productLiveData.removeObserver(this);
                         }
                     }
                 });
                }
            });


        }
    }
}
