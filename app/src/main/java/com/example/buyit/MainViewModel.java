package com.example.buyit;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.buyit.adapter.ProductAdapter;
import com.example.buyit.data.LocalData;
import com.example.buyit.util.EventListener;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    ProductAdapter productAdapter;
    CartRepository cartRepository;
    LocalData localData;
    EventListener eventListener;


    @Inject
    public MainViewModel(CartRepository cartRepository, LocalData localData) {
        this.cartRepository = cartRepository;
        this.localData = localData;
    }

    ProductAdapter getProductAdapter (){
        return new ProductAdapter(localData.provideData(),cartRepository);
    }

    void getCount(){
        cartRepository.getCount().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                eventListener.cartCount(integer);
            }
        });
    }
}
