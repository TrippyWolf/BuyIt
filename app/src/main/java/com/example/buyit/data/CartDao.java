package com.example.buyit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.buyit.model.CartModel;

import java.util.List;
@Dao
public interface CartDao {
    @Query("SELECT * FROM CartModel")
    LiveData<List<CartModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CartModel task);

    @Query("SELECT * FROM CartModel where id = :id")
    LiveData<CartModel> hasProduct(int id);

    @Delete
    void delete(CartModel task);

    @Update
    void update(CartModel task);

    @Query("SELECT COUNT(*) FROM CartModel")
    LiveData<Integer> getRowCount();
}
