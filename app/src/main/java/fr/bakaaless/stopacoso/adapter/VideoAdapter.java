package fr.bakaaless.stopacoso.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.bakaaless.stopacoso.R;
import fr.bakaaless.stopacoso.StopAcosoActivity;
import fr.bakaaless.stopacoso.videos.Recorder;
import fr.bakaaless.stopacoso.videos.Video;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private final List<Video> videos;

    public VideoAdapter(final List<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Video video = this.videos.get(position);
        holder.getText().setText(video.getName());
        holder.getButton().setOnClickListener(button -> {
            final Intent intent = new Intent(StopAcosoActivity.get(), Recorder.class);
            intent.putExtra("video", video.getRaw());
            StopAcosoActivity.get().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private Button button;

        public ViewHolder(final View view){
            super(view);
            this.text = view.findViewById(R.id.date);
            this.button = view.findViewById(R.id.see);
        }

        public TextView getText() {
            return text;
        }

        public Button getButton() {
            return button;
        }
    }
}
