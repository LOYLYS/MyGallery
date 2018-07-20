package com.example.vhlee.mygallery;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadImage extends AsyncTask<String, Void, ArrayList<Photo>> {
    public static final int ZERO_INDEX = 0;
    public static final int SPE_INDEX = 19;
    @Override
    protected ArrayList<Photo> doInBackground(String... strings) {
        return getListPhotos(strings[ZERO_INDEX]);
    }

    public ArrayList<Photo> getListPhotos(String path) {
        String pathDir = Environment.getExternalStorageDirectory().getPath() + path;
        ArrayList<Photo> photos = new ArrayList<>();
        final File fileDir = new File(pathDir);
        File[] listFile = fileDir.listFiles();
        for (File f : listFile) {
            if (f.isFile()) {
                photos.add(new Photo(f.getPath(), f.getName()));
            } else if (f.isDirectory()) {
                photos.addAll(getListPhotos(f.getPath().substring(SPE_INDEX)));
            }
        }
        return photos;
    }

}
