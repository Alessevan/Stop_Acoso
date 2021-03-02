package fr.bakaaless.stopacoso.adapter;

import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import fr.bakaaless.stopacoso.R;
import fr.bakaaless.stopacoso.StopAcosoActivity;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_vertical, parent, false);
        return new ImageAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        int id;
        switch (position) {
            case 1: {
                id = R.drawable.acoso_image_2;
                break;
            }
            case 2: {
                id = R.drawable.acoso_image_3;
                break;
            }
            case 3: {
                id = R.drawable.acoso_image_4;
                break;
            }
            default:
                id = R.drawable.acoso_image_1;
        }
        holder.getImage().setImageDrawable(StopAcosoActivity.get().getResources().getDrawable(id, StopAcosoActivity.get().getTheme()));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;

        public ViewHolder(final View view){
            super(view);
            this.image = view.findViewById(R.id.imageView);
        }

        public ImageView getImage() {
            return image;
        }
    }

}
