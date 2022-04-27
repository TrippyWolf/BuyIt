package com.example.buyit;

import static com.example.buyit.CartAdapter.total;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyit.model.CartModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CartViewModel extends ViewModel {

    CartRepository cartRepository;
    List<CartModel> data = new ArrayList<>();

    LifecycleOwner lifecycleOwner;
    CartAdapter cartAdapter = new CartAdapter(data, cartRepository);

    @Inject
    public CartViewModel(CartRepository cartRepository) {

        this.cartRepository = cartRepository;
    }

    void getCartAdapter(RecyclerView recyclerView) {

        cartRepository.getAllNotes().observeForever(cart_models -> {
            data.clear();
            data.addAll(cart_models);
            int sum = 0;
            for (int i = 0; i < cart_models.size(); i++) {
                sum = sum + (cart_models.get(i).getQuantity() * cart_models.get(i).getProductPrice());
            }

            total.postValue(sum);
            cartAdapter = new CartAdapter(data, cartRepository);
            recyclerView.setAdapter(cartAdapter);
        });

    }
}
