package com.example.buyit.Dagger;

import android.content.Context;

import com.example.buyit.CartRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class SingleComponent {
    @Singleton
    @Provides
    CartRepository getCartRepository(@ApplicationContext Context context){
        return new CartRepository( context);
    }

}
