package vn.edu.hou.mttha.matchinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {

    //khai bao cac doi tuong
    EditText etSo1, etSo2;
    TextView tvResult;
    Button btnAddValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //goi phuong thuc getview
        getViews();
        btnAddValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay du lieu duoc nhap vao so1 va so 2, gui sang secondActivity de xu ly
                String so1 = etSo1.getText().toString();
                String so2= etSo2.getText().toString();
                //khoi tao 1 doi tuong intent de chua du lieu so1, so2 va gui intent sang secondActivity
                Intent intentSend = new Intent(FirstActivity.this, SecondActivity.class);
                intentSend.putExtra("so1", so1);
                intentSend.putExtra("so2", so2);
                //su dung bien activityResultLauncher de gui du lieu di
                activityResultLauncher.launch(intentSend);
            }
        });
    }

    private void getViews(){
        etSo1 = findViewById(R.id.etSo1);
        etSo2 = findViewById(R.id.etSo2);
        tvResult = findViewById(R.id.tvResult);
        btnAddValues = findViewById(R.id.btnAddValues);
    }
    //khai bao activityResultLaucher de thuc hien viec gui va nhan du lieu tu 2 activty
    private final ActivityResultLauncher<Intent> activityResultLauncher
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    //xu ly du lieu duoc nhan tu secondActivity
                    if(o.getResultCode()==RESULT_OK){
                        //lay du lieu duoc truyen ve o data
                        Intent data = o.getData();
                        String ketQua = data.getStringExtra("kq");
                        //hien thi ket qua len textView
                        tvResult.setText(ketQua);
                    }
                }
            });
}