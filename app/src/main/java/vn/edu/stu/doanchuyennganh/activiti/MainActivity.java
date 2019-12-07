package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.edu.stu.doanchuyennganh.R;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap, btnDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContent();
        addEvent();
    }

    private void addContent() {
        btnDangKi=(Button) findViewById(R.id.btnDangKi);
        btnDangNhap=(Button) findViewById(R.id.btnDangNhap);
    }

    private void addEvent() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Dangki=new Intent(MainActivity.this, DangKi.class);
               startActivity(Dangki);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DangNhap= new Intent(MainActivity.this, vn.edu.stu.doanchuyennganh.activiti.DangNhap.class);
                startActivity(DangNhap);

            }
        });
    }
}
