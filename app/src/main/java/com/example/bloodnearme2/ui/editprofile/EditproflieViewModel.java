package com.example.bloodnearme2.ui.editprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditproflieViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EditproflieViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is editproflie fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}