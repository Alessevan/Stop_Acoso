package fr.bakaaless.stopacoso.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import fr.bakaaless.stopacoso.R;
import fr.bakaaless.stopacoso.adapter.VideoAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(final @NonNull LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        this.homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        ((RecyclerView) root.findViewById(R.id.item_vertical_recycler)).setAdapter(new VideoAdapter(this.homeViewModel.getVideos()));
        if (this.homeViewModel.getVideos().size() == 0)
            this.homeViewModel.getText().observe(getViewLifecycleOwner(), ((TextView) root.findViewById(R.id.text_home))::setText);
        return root;
    }
}