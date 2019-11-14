package com.example.bloodnearme2.ui.donor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DonorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DonorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is donor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}