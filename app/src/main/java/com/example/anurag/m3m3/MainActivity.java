package com.example.anurag.m3m3;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MainActivity extends AppCompatActivity implements TopSectionFragment.TopSectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // this gets called by the topFragment when the user clicks the button
    @Override
    public void generateMeme(String top, String bottom) {     //shortcut to include these promised methods: ALT + Insert
        BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setMemeText(top, bottom); //calling method present in bottomFragment
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenu = getMenuInflater();
        optionMenu.inflate(R.menu.setting, menu);

        menu.add(Menu.NONE, R.id.edit, Menu.NONE, "Edit");
        menu.add(Menu.NONE, R.id.share, Menu.NONE, "Share");
        menu.add(Menu.NONE, R.id.text1, Menu.NONE, "text1");
        menu.add(Menu.NONE, R.id.text2, Menu.NONE, "text2");
        menu.add(Menu.NONE, R.id.text3, Menu.NONE, "text3");


        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){

    switch (item.getItemId()) {

            case R.id.edit:
                return true;

            case R.id.share:
                return true;

            case R.id.text1:
                return true;

            case R.id.text2:
                return true;

            case R.id.text3:
                return true;

        }
        return super.onContextItemSelected(item);
    }

}