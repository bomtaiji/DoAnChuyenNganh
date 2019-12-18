package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.adapter.GioHangAdapter;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;

public class GioHang extends AppCompatActivity {
    ListView lvGioHang;
    TextView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhToan,btnTiepTucMua;
    Toolbar toolbargiohang;
    GioHangAdapter gioHangAdapter;
    int pos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        addControls();
        addEvents();
        KiemTraDuLieu();
        DoDuLieu();
        sukienListView();
    }

    private void sukienListView() {
        lvGioHang.setOnItemLongClickListener((parent, view, position, id) -> {
            pos=position;
            AlertDialog.Builder builde = new AlertDialog.Builder(GioHang.this);
            builde.setTitle("Xác nhận xoá sản phẩm");
            builde.setMessage("Bạn có chắc chắn muốn xoá sản phẩm ");
            builde.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gioHangAdapter.notifyDataSetChanged();
                    DoDuLieu();
                }
            });
            builde.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(Home.manggiohang.size()<=0)
                    {
                        txtThongBao.setVisibility(View.VISIBLE);
                    }else {
                        Home.manggiohang.remove(pos);
                        gioHangAdapter.notifyDataSetChanged();
                        DoDuLieu();
                        if(Home.manggiohang.size()<=0)
                        {
                            txtThongBao.setVisibility(View.VISIBLE);
                        }else {
                            txtThongBao.setVisibility(View.INVISIBLE);
                            gioHangAdapter.notifyDataSetChanged();
                            DoDuLieu();
                        }
                    }
                }
            });
            builde.create().show();
            return true;
        });
    }

    public static void DoDuLieu() {

        long tongtien=0;
        for( int i=0;i<Home.manggiohang.size();i++)
        {
            tongtien +=Home.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien)+" đồng");
    }

    private void KiemTraDuLieu() {
        if(Home.manggiohang.size()<=0)
        {
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }

    private void addEvents() {

        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Home.manggiohang.size()>0 )
                {
                    Intent intent= new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    startActivity(intent);

                }else {
                    Kiemtraketnoi.Show(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm nào");
                }

            }
        });
    }

    private void addControls() {
    lvGioHang= (ListView)findViewById(R.id.lvGiohang);
    txtThongBao=(TextView) findViewById(R.id.textViewthongbao);
    txtTongTien=(TextView) findViewById(R.id.textviewTongtien);
    btnThanhToan=(Button) findViewById(R.id.buttonThanhToangiohang);
    btnTiepTucMua=(Button) findViewById(R.id.buttonTieptucmuahang);
    toolbargiohang=findViewById(R.id.toolbarGiohang);

    gioHangAdapter= new GioHangAdapter(GioHang.this,Home.manggiohang);
    lvGioHang.setAdapter(gioHangAdapter);
    }
}
