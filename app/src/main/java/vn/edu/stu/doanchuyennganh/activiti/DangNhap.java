package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.edu.stu.doanchuyennganh.R;

public class DangNhap extends AppCompatActivity {
Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addContent();
        addEvent();
    }

    private void addContent() {
        btnDangNhap = (Button)findViewById(R.id.btnDangNhap);
    }

    private void addEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home= new Intent(DangNhap.this, Home.class);
                startActivity(home);

            }
        });

    }
}
