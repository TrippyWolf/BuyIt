package com.example.buyit;

import android.content.Context;

        import androidx.lifecycle.LiveData;

import com.example.buyit.data.CartDao;
import com.example.buyit.data.ProductDatabase;
import com.example.buyit.model.CartModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;


public class CartRepository {
    private CartDao mProductDao;
    private LiveData<List<CartModel>> myAllCartProduct;

@Inject
    public CartRepository(@ApplicationContext Context application) {

        ProductDatabase db = ProductDatabase.getDatabase(application);
        mProductDao = db.notesDao();
        myAllCartProduct = mProductDao.getAll();
    }

    public LiveData<List<CartModel>> getAllNotes() {
        return myAllCartProduct;
    }

    public void insert(CartModel cartModel) {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insert(cartModel);
        });
    }
    public LiveData<CartModel> hasProduct(int productid) {
      return   mProductDao.hasProduct(productid);
    }
    public LiveData<Integer> getCount(){
        return mProductDao.getRowCount();
    }

    public void deleteNote(CartModel cartModel) {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.delete( cartModel);
        });
    }
    public void updateCart(CartModel cartModel) {
        ProductDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.update( cartModel);
        });
    }
}