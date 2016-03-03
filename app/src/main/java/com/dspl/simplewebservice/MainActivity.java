package com.dspl.simplewebservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    EditText editTextName,editTextEmail,editTextMobile;
    Button buttonSend,buttonFetch;
    String name,email,mobile;
    TextView textViewResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeContent();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();
                SendingData sendingData = new SendingData(new CallBackMethod() {
                    @Override
                    public void onTaskComplete(String result) {
                        textViewResponse.setText(result);
                    }
                });
                sendingData.execute(name, email, mobile);
            }
        });
        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowingFetchingData.class);
                startActivity(intent);
            }
        });
    }

    private void InitializeContent() {
        editTextName = (EditText)this.findViewById(R.id.edit_text_name);
        editTextEmail = (EditText)this.findViewById(R.id.edit_text_email);
        editTextMobile = (EditText)this.findViewById(R.id.edit_text_mobile);
        buttonSend = (Button)findViewById(R.id.button_send);
        textViewResponse = (TextView)this.findViewById(R.id.text_view_response);
        buttonFetch = (Button)this.findViewById(R.id.button_fetch);
    }
}
class SendingData extends AsyncTask<String,Integer,String>{
   CallBackMethod callBackMethod =null;

    public SendingData(CallBackMethod callBackMethod) {
      this.callBackMethod = callBackMethod;
    }

    @Override
    protected String doInBackground(String... params) {
       String result =  postData(params);
        return result;
    }



    private String postData(String[] values) {
        String result="";
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://10.0.0.8:85/chandan/insert.php");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("name", values[0]));
            list.add(new BasicNameValuePair("email", values[1]));
            list.add(new BasicNameValuePair("mobile", values[2]));

            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
       callBackMethod.onTaskComplete(s);
    }
}
