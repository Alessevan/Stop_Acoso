package fr.bakaaless.stopacoso;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fr.bakaaless.stopacoso.videos.Recorder;

public class StopAcosoActivity extends AppCompatActivity {

    private static StopAcosoActivity instance;

    private static final int VIDEO_REQUEST = 101;

    public static StopAcosoActivity get() {
        return instance;
    }

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        showMain();
    }

    public void showMain() {
        this.setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        final FloatingActionButton fab = this.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            requestPermissions(permissions, 0);
            final Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        });
        final DrawerLayout drawer = this.findViewById(R.id.drawer_layout);
        final NavigationView navigationView = this.findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        this.mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, this.mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, this.mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private String [] permissions = {Manifest.permission.DELETE_CACHE_FILES, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean accepted = true;
        for (final int granted : grantResults) {
            accepted &= granted == PackageManager.PERMISSION_GRANTED;
        }
        if (!accepted) finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            /*final Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy$HH-mm-ss");
            final File file = new File(HomeViewModel.VIDEOS_DIR_PATH + dateFormat.format(calendar.getTime()) + File.pathSeparator + "back.mp4");
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(data.getData().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();*/
        }
    }
}