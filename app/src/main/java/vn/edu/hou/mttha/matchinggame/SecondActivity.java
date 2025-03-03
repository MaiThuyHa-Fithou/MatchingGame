package vn.edu.hou.mttha.matchinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView tvView;
    Button btnCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getViews();
        //nhan intent tu firstActivity
        Intent intentReceiver = getIntent();
        //lay du lieu tu intentReceiver
        int so1 = Integer.parseInt(intentReceiver.getStringExtra("so1"));
        int so2 = Integer.parseInt(intentReceiver.getStringExtra("so2"));
        //thuc hien cong 2 so:
        int tong = so1+so2;
        //Hien ket qua len textView
        String kq="So 1: " + so1 +"\n"+"So 2: "+ so2
                +"\n" +"Tong: " + tong;
        tvView.setText(kq);
        btnCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khoi tao doi tuong intent nhan kq tra ve
                Intent intentResult = new Intent();
                intentResult.putExtra("kq",kq);
                //su dung phuong thuc setResult de gui kq ve firstActivity
                setResult(RESULT_OK, intentResult);
                //dong secondActivity
                finish();
            }
        });
    }

    private void getViews(){
        tvView = findViewById(R.id.tvView);
        btnCallBack = findViewById(R.id.btnSendBack);
    }
}