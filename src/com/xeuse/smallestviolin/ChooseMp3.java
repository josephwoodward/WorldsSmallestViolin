package com.xeuse.smallestviolin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;

public class ChooseMp3 extends SherlockActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mpeg");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.Choose_custom_mp3)), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == Activity.RESULT_OK){
            if ((data != null) && (data.getData() != null)){
                Uri audioFileUri = data.getData();
                // Now you can use that Uri to get the file path, or upload it, ...
                SaveMp3ToPreferences(audioFileUri);
            }
        }
    }

    private void SaveMp3ToPreferences(Uri audioFileUri) {
        SharedPreferences prefs = this.getSharedPreferences("com.xeuse.smallestviolin", Context.MODE_PRIVATE);
        prefs.edit().putString("mp3Path", audioFileUri.toString()).commit();
    }



}