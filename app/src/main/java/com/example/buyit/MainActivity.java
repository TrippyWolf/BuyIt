package com.example.buyit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.buyit.data.LocalData;
import com.example.buyit.util.Converter;
import com.example.buyit.util.EventListener;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements EventListener {

    RecyclerView recyclerView ;

    int countNumber =0;

    @Inject
    CartRepository cartRepository;
    @Inject
    LocalData localData;

    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(MainViewModel.class)) {
                    return (T) new MainViewModel(cartRepository,localData);
                }
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        }).get(MainViewModel.class);


        recyclerView = findViewById(R.id.products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(mainViewModel.getProductAdapter());

        mainViewModel.eventListener= this;
        mainViewModel.getCount();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);

        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,countNumber,R.drawable.ic_baseline_shopping_bag_24));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         //only one menuitem present no need to check id
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        return true;
    }

    @Override
    public void addedTocart() {

    }

    @Override
    public void cartCount(int count) {
        countNumber = count;
        this.invalidateOptionsMenu();
    }


}