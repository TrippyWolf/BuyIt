package com.example.buyit;

import static com.example.buyit.CartAdapter.total;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buyit.model.CartModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    TextView textView;
    @Inject
    CartRepository cartRepository;

    CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recyclerview);
        textView = findViewById(R.id.total);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(CartViewModel.class)) {
                    return (T) new CartViewModel(cartRepository);
                }
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        }).get(CartViewModel.class);


        total.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText("Total " + integer);

            }
        });

        recyclerView.setAdapter(cartViewModel.cartAdapter);
        cartViewModel.getCartAdapter(recyclerView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CartActivity.this,MainActivity.class));
        finish();
    }
}