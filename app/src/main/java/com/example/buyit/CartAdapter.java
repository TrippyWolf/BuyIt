package com.example.buyit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyit.databinding.CartItemBinding;
import com.example.buyit.model.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartModel>cartList;
    private CartRepository cartRepository;

    static MutableLiveData<Integer> total = new MutableLiveData<>(0) ;
    public CartAdapter(List<CartModel> cartList, CartRepository cartRepository) {
        this.cartList = cartList;


        this.cartRepository = cartRepository;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final CartModel cart_model =cartList.get(position);
        holder.bind(cart_model,position);
    }



    @Override
    public int getItemCount() {
        if(cartList != null)
        return cartList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CartItemBinding cartItemBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemBinding= DataBindingUtil.bind(itemView);
        }

        public void bind(CartModel cartModel, int position) {
            cartItemBinding.setItemModel(cartModel);

            cartItemBinding.addQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartModel.setQuantity(cartModel.getQuantity()+1);
                  //  total.postValue(total.getValue() + cartModel.getProductPrice());
                    cartRepository.updateCart(cartModel);
                    notifyItemChanged(position);

                }
            });
            cartItemBinding.minusQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cartModel.getQuantity()==1){
                        cartRepository.deleteNote(cartModel);
                        cartList.remove(position);
                        notifyItemRemoved(position);
                    }else {
                        cartModel.setQuantity(cartModel.getQuantity() - 1);
                        cartRepository.updateCart(cartModel);
                        notifyItemChanged(position);
                    }
               //     total.postValue(total.getValue() - cartModel.getProductPrice());

                }
            });

        }
    }
}
