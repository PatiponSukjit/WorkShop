package com.example.cake.chapter9ver2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        setEvent();
    }

    private boolean validate() {
        //TODO validate
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    new login(etUsername.getText().toString(),
                            etPassword.getText().toString())
                            .execute();
                } else {
                    Toast.makeText(loginActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this,registerActivity.class);
                startActivity(i);
            }
        });
    }
    private class login extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;

        public login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try {

                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1) {
                        Toast.makeText(loginActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT);
                        Intent i = new Intent(loginActivity.this, new_listActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(loginActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException ex) {

            }
        }
    }
}