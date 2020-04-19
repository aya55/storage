package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class extenral extends AppCompatActivity {
EditText filename;
EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extenral);
        filename=findViewById(R.id.et);
        text=findViewById(R.id.et1);


    }
    public void writeFile(View v) throws FileNotFoundException {
       if(isExtenralStorageWritable()&&checkPremission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
           File textFile=new File(Environment.getExternalStorageDirectory(),filename.getText().toString());
           try {
               FileOutputStream fos = new FileOutputStream(textFile);
               fos.write(text.getText().toString().getBytes());
               fos.close();
               Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else {
           Toast.makeText(this, "Cannot write in external storage", Toast.LENGTH_SHORT).show();
       }


    }
    public  boolean checkPremission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);


    }




    private  boolean isExtenralStorageWritable (){
if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
    Log.i("State","Yes");
   return true;

}
else {
    return false;
}


    }
    private  boolean isExtenralStorageReadable (){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()
               ) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
            Log.i("State","Yes");
            return true;

        }
        else {
            return false;
        }


    }

}
