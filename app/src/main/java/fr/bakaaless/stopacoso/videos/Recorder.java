package fr.bakaaless.stopacoso.videos;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import fr.bakaaless.stopacoso.R;

@RequiresApi(api = Build.VERSION_CODES.R)
public class Recorder extends AppCompatActivity {

    private VideoView view;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.fragment_record);
        this.view = this.findViewById(R.id.videoView);
        final String[] video = getIntent().getExtras().getStringArray("video");
        this.view.setVideoURI(Uri.parse(video[0]));
        this.view.start();
        this.findViewById(R.id.exit).setOnClickListener(view -> {
            onStop();
        });
    }

    public VideoView getView() {
        return this.view;
    }

    @Override
    public void onStop() {
        super.onStop();
        this.view.pause();
    }
}

