package com.example.anurag.m3m3;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v4.widget.PopupMenuCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MainActivity extends AppCompatActivity implements TopSectionFragment.TopSectionListener, FontAdapterClass.ItemClickListener {

    FontAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // data to populate the RecyclerView with
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new FontAdapterClass(this, animalNames);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
    }

    // this gets called by the topFragment when the user clicks the button
    @Override
    public void generateMeme(String top, String bottom) {     //shortcut to include these promised methods: ALT + Insert
        BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setMemeText(top, bottom); //calling method present in bottomFragment
    }

    public void createPopup() {

        View edit = findViewById(R.id.edit);
        final PopupMenu popup = new PopupMenu(MainActivity.this, edit);

        popup.getMenuInflater().inflate(R.menu.edit_popup, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

//              Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId())
                {
                    case R.id.font:     ArrayList<String> animalNames = new ArrayList<>(); // data to populate the RecyclerView with
                                        animalNames.add("Horse");
                                        animalNames.add("Cow");
                                        animalNames.add("Camel");
                                        animalNames.add("Sheep");
                                        animalNames.add("Goat");

                                        // set up the RecyclerView
                                        RecyclerView recyclerView = findViewById(R.id.recycle);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        adapter = new FontAdapterClass(getApplicationContext(), animalNames);
                                        adapter.setClickListener(MainActivity.this);
                                        recyclerView.setAdapter(adapter);
                                        break;
                                        
//                    case R.id.size:
//                    case R.id.color:
                }
                return true;
            }
        });
        popup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenu = getMenuInflater();
        optionMenu.inflate(R.menu.setting, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

    switch (item.getItemId()) {

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

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}