package com.example.dell.demohttpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        //this.textView = (TextView) this.findViewById(R.id.textView);
    }


    private boolean checkInternetConnection() {
        // Lấy ra bộ quản lý kết nối.
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Thông tin mạng đang kích hoạt.
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }

    // Khi người dùng click vào nút "Download Image".
    public void downloadAndShowImage(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        String imageUrl = "http://www.elle.vn/wp-content/uploads/2017/07/25/hinh-anh-dep-10.jpg";
        // Tạo một đối tượng làm nhiệm vụ download image.
        DownloadImageTask task = new DownloadImageTask(this.imageView);
        // Thực thi nhiệm vụ (Truyền vào URL).
        task.execute(imageUrl);
    }

//    // Khi người dùng click vào nút "Download Json".
//    public void downloadAndShowJson(View view) {
//        boolean networkOK = this.checkInternetConnection();
//        if (!networkOK) {
//            return;
//        }
//        String jsonUrl = "https://graph.facebook.com/nguyen.soan.948/pictrue";
//
//        // Tạo một đối tượng làm nhiệm vụ download nội dung json từ URL.
//        DownloadJsonTask task = new DownloadJsonTask(this.textView);
//
//        // Thực thi nhiệm vụ (Truyền vào URL).
//        task.execute(jsonUrl);
//    }


}