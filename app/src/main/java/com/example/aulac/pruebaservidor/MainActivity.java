package com.example.aulac.pruebaservidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    RelativeLayout milayout;
    EditText nombres,correo;
    Button botonenviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        milayout = findViewById(R.id.miLayout);
        nombres = findViewById(R.id.editText);
        correo = findViewById(R.id.editText2);
        botonenviar = findViewById(R.id.button);

        botonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "http://www.programadoresperuanos.com/test_app/pruebaa/index.php";
                AsyncHttpClient clientejoel = new AsyncHttpClient();
                RequestParams datos = new RequestParams();
                datos.put("datonombres",nombres.getText().toString());
                datos.put("datocorreo",correo.getText().toString());
                clientejoel.post(getApplicationContext(), URL, datos, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode == 200)
                        {
                            Toast.makeText(getApplicationContext(),String.valueOf(responseBody),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),String.valueOf(statusCode),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(),String.valueOf(statusCode)+" "+String.valueOf(error) ,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
