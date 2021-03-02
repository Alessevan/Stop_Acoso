package fr.bakaaless.stopacoso.videos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Video {

    private final File[] videos;

    private final String name;

    private boolean running;
    private boolean reading;

    public Video(final String name, final File... videos) {
        this.name = name;
        this.videos = new File[2];
        this.running = false;
        this.reading = false;
        for (int index = 0; index < videos.length; index++) {
            if (index == 2)
                break;
            this.videos[index] = videos[index];
        }
    }

    public String getRawName() {
        return this.name;
    }

    public String getName() {
        return " el " + this.name.replace("$", " a las ")
                .replace("_", "/")
                .replace("-", ":");
    }

    public File[] getVideos() {
        return this.videos;
    }

    public String[] getRaw() {
        final String[] output = new String[2];
        for (int index = 0; index < this.videos.length; index++) {
            if (this.videos[index] == null)
                continue;
            final StringBuilder builder = new StringBuilder();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.videos[index])))) {
                String line;
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    if (firstLine)
                        firstLine = false;
                    else
                        builder.append("\n");
                    builder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output[index] = builder.toString();
        }
        return output;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public boolean isReading() {
        return this.reading;
    }

    public void setReading(final boolean reading) {
        this.reading = reading;
    }
}
