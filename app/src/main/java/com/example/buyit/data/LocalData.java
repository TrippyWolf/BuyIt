package com.example.buyit.data;

import com.example.buyit.R;
import com.example.buyit.model.ProductModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class LocalData {

    @Inject
    public LocalData() {

    }

   public ArrayList<ProductModel> provideData ( ) {
        ArrayList<ProductModel> myproducts = new ArrayList<>();
        myproducts.add((new ProductModel(1, R.drawable.watch, "Wrist Watch", 9999)));
        myproducts.add((new ProductModel(2, R.drawable.phone, "IPhone", 7999)));
        myproducts.add((new ProductModel(3, R.drawable.laptop, "Mac book", 15999)));
        myproducts.add((new ProductModel(4, R.drawable.bulb, "Syska Blub", 699)));
        myproducts.add((new ProductModel(5, R.drawable.game, "Video Game", 1699)));
        myproducts.add((new ProductModel(6, R.drawable.glasses, "Sunglasses", 2699)));

        return myproducts;
    }
}
