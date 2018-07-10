package app.davecstillo.com.schoolapp;


import android.app.Fragment;
import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Login extends AppCompatActivity {

    private EditText codeEdit, userEdit, passEdit;
    Button button;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        httpHandler.instance = new httpHandler("https://dacastest.000webhostapp.com/");
        Imagenes.init();

        codeEdit = (EditText) findViewById(R.id.codeEdit);
        userEdit = (EditText) findViewById(R.id.userEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);

        button = (Button) findViewById(R.id.loginBtn);
        progressDialog  = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Verificando Datos.");
        progressDialog.setCancelable(false);
        progressDialog.hide();
        button.setOnClickListener(view ->{
            Log.i("Progres Showed",String.valueOf(progressDialog.isShowing()));

            if(!progressDialog.isShowing())
               progressDialog.show();
            login(progressDialog);

        });


    }

    private void login(ProgressDialog progressDialog) {

        StringBuilder path = new StringBuilder("login.php?user=");
        path.append(userEdit.getText());
        path.append("&pass=");
        path.append(passEdit.getText());


        String url = path.toString();
        url = url.replace(" ","%20");

        Log.d("PATH", url);
        String finalUrl = url;


        new BackgroundTask<JsonElement>(() -> httpHandler.instance.getJson(finalUrl), (json, exception) -> {
            if (exception != null) {
                Log.e("JSON ERROR", exception.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(), "No esta conectado a internet", Toast.LENGTH_LONG);
                toast.show();
                progressDialog.hide();
            }
            if (json != null) {
                Log.d("JSON", json.toString());


                JsonArray array = null;
                JsonObject objct = null;

                if (json instanceof JsonObject) {
                    objct = json.getAsJsonObject();
                } else if (json instanceof JsonArray) {
                    array = json.getAsJsonArray();
                }

                Log.d("JSON OBJECT", objct.toString());
                //Log.d("JSON ARRAY",array.toString());
                JsonArray objeto = objct.getAsJsonArray("usuario");
//                JsonObject elemento = objeto.getAsJsonObject();

                Log.d("Objeto", objeto.toString());
            //    Log.d("Elemento", elemento.toString());

                for (JsonElement res : json.getAsJsonObject().get("usuario").getAsJsonArray()) {
                    String user;
//                    Log.d("RES", res.toString());
//                    Log.d("USER", res.getAsJsonObject().get("USER").toString());
                    if(res.getAsJsonObject().get("Result")!=null) {
                    //if (json.getAsJsonObject().get("usuario").equals("No Valido")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Los datos no son correctos", Toast.LENGTH_LONG);
                        toast.show();
                        codeEdit.setText("");
                        userEdit.setText("");
                        passEdit.setText("");
                        progressDialog.hide();
                    } else {
                        user = res.getAsJsonObject().get("USER").toString();
                        Log.d("USER", user);
                        httpHandler.instance.user = user;
                        Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                        startActivity(intent);
                       progressDialog.hide();
                    }
                }
            }

        }).execute();
    }



    private JsonObject parseJObject(JsonElement jElement){


        if (jElement instanceof JsonObject) {
            JsonObject jobject = jElement.getAsJsonObject();
        return jobject;
        }else return null;
    }

    private JsonArray parseJArray(JsonElement jElement){


      if (jElement instanceof JsonArray) {
          JsonArray  jarray = jElement.getAsJsonArray();
        return jarray;
        }else return null;
          }


}

