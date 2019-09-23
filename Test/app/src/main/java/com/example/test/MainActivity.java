package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    int flag;
    Button download;
    Button deleteUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteUpdate = findViewById(R.id.delete_download);
        download = findViewById(R.id.download);
        final TextView textView = findViewById(R.id.xianzai);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                HttpUrl.sendOkHttp("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2019.exe", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final long length = response.body().contentLength();
                        long count = 0;
                        InputStream inputStream = response.body().byteStream();
                        try {
                            final File file = new File(Environment.getExternalStorageDirectory(),"data.exe");
                            if(file.exists()){
                                file.delete();
                            }
                            file.createNewFile();
                            OutputStream outputStream = new FileOutputStream(file);
                            byte[] arr = new byte[1024 * 8];
                            int len;
                            while((len = inputStream.read(arr)) != -1) {
                                outputStream.write(arr,0,len);
                                count += len;
                                final long finalCount = count;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(flag == 1)
                                            textView.setText(String.format("%.2f",(finalCount + 0.0) / length * 100) + "%");
                                        else{
                                            textView.setText("取消下载,并删除本地文件");
                                            file.delete();
                                        }

                                    }
                                });
                                outputStream.flush();
                                if(flag == 0){
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        deleteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    textView.setText("未下载");
                }else
                    flag = 0;
            }
        });
    }
}

