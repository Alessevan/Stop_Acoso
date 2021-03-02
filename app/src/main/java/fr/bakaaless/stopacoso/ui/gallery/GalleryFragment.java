package fr.bakaaless.stopacoso.ui.gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import fr.bakaaless.stopacoso.R;
import fr.bakaaless.stopacoso.adapter.ImageAdapter;
import fr.bakaaless.stopacoso.adapter.VideoAdapter;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(final @NonNull LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        this.galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ((RecyclerView) root.findViewById(R.id.image_vertical_recycler)).setAdapter(new ImageAdapter());
        return root;
    }
}