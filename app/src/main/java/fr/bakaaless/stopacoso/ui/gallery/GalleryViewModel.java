package fr.bakaaless.stopacoso.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        this.mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}