package com.example.tp4_android.ui.foto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp4_android.R;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<Integer> mFoto;

    public GalleryViewModel() {

    }

    public LiveData<Integer> getFoto() {
        if (mFoto == null) {
            mFoto = new MutableLiveData<>();
            mFoto.setValue(R.drawable.user);
        }

        return mFoto;
    }
}