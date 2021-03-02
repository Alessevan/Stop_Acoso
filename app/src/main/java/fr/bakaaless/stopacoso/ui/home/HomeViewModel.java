package fr.bakaaless.stopacoso.ui.home;

import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.bakaaless.stopacoso.R;
import fr.bakaaless.stopacoso.StopAcosoActivity;
import fr.bakaaless.stopacoso.videos.Video;

import static android.provider.Settings.Global.getString;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HomeViewModel extends ViewModel {

    public static final String VIDEOS_DIR_PATH = StopAcosoActivity.get().getDataDir().getAbsolutePath() + File.separator + "StopAcoso" + File.separator + "vids" + File.separator;

    private static List<Video> videos = new ArrayList<>();
    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        this.mText = new MutableLiveData<>();
        this.mText.setValue("No hay ningún vídeo grabado");
        updateVids();
    }

    public static void updateVids() {
        videos.clear();
        final File file = new File(VIDEOS_DIR_PATH);
        if (!file.exists())
            file.mkdirs();
        if (file.listFiles() == null)
            return;
        final FilenameFilter filter = (dir, name) -> name.endsWith(".mp4");
        for (final File subFile : Objects.requireNonNull(file.listFiles())) {
            final File[] videos = subFile.listFiles(filter);
            if (videos == null)
                continue;
            if (videos.length > 0)
                HomeViewModel.videos.add(new Video(subFile.getName(), videos));
        }
    }

    public LiveData<String> getText() {
        return this.mText;
    }

    public List<Video> getVideos() {
        return this.videos;
    }
}