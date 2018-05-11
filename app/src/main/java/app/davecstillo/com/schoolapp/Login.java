package app.davecstillo.com.schoolapp;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonElement;

public class Login extends AppCompatActivity {

    private EditText codeEdit, userEdit, passEdit;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codeEdit = (EditText) findViewById(R.id.codeEdit);
        userEdit = (EditText) findViewById(R.id.userEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);

        button = (Button) findViewById(R.id.loginBtn);

        button.setOnClickListener(view ->

                Log.d("Login","Datos: code: "+codeEdit.getText()+" user: "+userEdit.getText()+" pass: "+passEdit.getText())
            

        );



    }


    private void callList(String path){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, exception)->{
            if(exception!=null){

            }
            if(json!=null){
                Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                startActivity(intent);
            }
        }).execute();
    }

}

