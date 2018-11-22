package com.example.vhlee.mygallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public static final int NUM_OF_GRID = 3;
    public static final String PATH_IMG = "/DCIM/";
    public static final int READ_EXT_REQUEST = 1;
    private ArrayList<Photo> mListPhotos;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissionRunTime();
        init();
    }

    private void checkPermissionRunTime() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permissions[ReadImage.ZERO_INDEX]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, READ_EXT_REQUEST);
            } else {
                try {
                    mListPhotos = new ReadImage().execute(PATH_IMG).get();
                } catch (InterruptedException|ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXT_REQUEST) {
            if (grantResults[ReadImage.ZERO_INDEX] == PackageManager.PERMISSION_GRANTED) {
                try {
                    mListPhotos = new ReadImage().execute(PATH_IMG).get();
                } catch (InterruptedException|ExecutionException e) {
                    e.printStackTrace();
                }
            } else if (grantResults[ReadImage.ZERO_INDEX] == PackageManager.PERMISSION_DENIED) {
                checkPermissionRunTime();
            }
        }
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recycler_photos);
        mAdapter = new PhotoAdapter(mListPhotos);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), NUM_OF_GRID));
        mRecyclerView.setAdapter(mAdapter);
    }

}
