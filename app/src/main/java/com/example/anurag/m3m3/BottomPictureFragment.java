package com.example.anurag.m3m3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static android.app.Activity.RESULT_OK;


public class BottomPictureFragment extends Fragment {

    private static TextView topMemeText, bottomMemeText;
    private RelativeLayout bf;
    ImageView memeImage;
    int PICK_IMAGE = 0, CAMERA = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_picture_fragment, container, false);

        bf = view.findViewById(R.id.bottomFragment);
        registerForContextMenu(bf);

        topMemeText = view.findViewById(R.id.topMemeText);
        bottomMemeText = view.findViewById(R.id.bottomMemeText);

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

 //how to disabling touch outside the context menu//

        menu.add(Menu.NONE, R.id.browse, Menu.NONE, "Browse");
        menu.add(Menu.NONE, R.id.camera, Menu.NONE, "Camera");
        menu.add(Menu.NONE, R.id.dismiss, Menu.NONE, "Dismiss");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.browse:   Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                //intent.setType("image/*");   // to show only specific type of file. Eg. hiding the videos.
                                intent.setAction(Intent.ACTION_GET_CONTENT); // allows to access any file from anywhere
                                startActivityForResult(intent, PICK_IMAGE);
                                return true;

            case R.id.camera:   Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(i, CAMERA);
                                return true;

            case R.id.dismiss:
                                return true;
        }
        return super.onContextItemSelected(item);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        memeImage = getView().findViewById(R.id.memeImage);

        if (requestCode == CAMERA && resultCode == RESULT_OK ) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Toast.makeText(getActivity(), "here here", Toast.LENGTH_SHORT).show();
            memeImage.setImageBitmap(photo);
        }
        else{

            try {
                if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    Toast.makeText(getActivity(), "" + uri, Toast.LENGTH_LONG).show();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                    memeImage.setImageBitmap(bitmap);
//                Toast.makeText(getActivity(), ""+bitmap.getByteCount(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                System.out.print(e.toString());
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void setMemeText(String top, String bottom)
    {
        topMemeText.setText(top);
        bottomMemeText.setText(bottom);
    }
}





// code to access specific

//    private ArrayList<File> m_list = new ArrayList<File>();
//
//    String folderpath = Environment.getExternalStorageDirectory()
//            + File.separator + "folder_name/";
//    File dir = new File(folderpath);
//         m_list.clear();
//                 if (dir.isDirectory()) {
//                 File[] files = dir.listFiles();
//                 for (File file : files) {
//                 if (!file.getPath().contains("Not_Found"))
//                 m_list.add(file);
//                 }
//                 }
//
//
// code to pick from gallery
//
//    Intent intent = new Intent();
//intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);