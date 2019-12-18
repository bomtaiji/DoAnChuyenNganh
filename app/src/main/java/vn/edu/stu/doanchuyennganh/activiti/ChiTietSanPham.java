package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.model.GioHang;
import vn.edu.stu.doanchuyennganh.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {

    Toolbar toolbarchitietsanpham;
    ImageView imageViewChitiet;
    TextView txtTen,txtGia,txtMota;
    Spinner spinner;
    Button btnDatMua;

    int id=0;
    String TenChiTiet = "";
    int GiaChiTiet =0;
    String HinhAnh="";
    String Mota="";
    int Loaisanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        addControls();
        ActionToolbar();
        LayThongTin();
        SKSpinner();
        addEvents();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent= new Intent(getApplicationContext(), vn.edu.stu.doanchuyennganh.activiti.GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void addEvents() {

        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Home.manggiohang.size()>0) {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists= false;
                    for(int i=0;i<Home.manggiohang.size();i++) {
                        if(Home.manggiohang.get(i).getIdsp()==id) {
                            Home.manggiohang.get(i).setSoluong(Home.manggiohang.get(i).getSoluong()+ sl);
                                if(Home.manggiohang.get(i).getSoluong()>=10) {
                                    Home.manggiohang.get(i).setSoluong(10);
                                }
                                Home.manggiohang.get(i).setGiasp(GiaChiTiet* Home.manggiohang.get(i).getSoluong());
                                exists= true;
                            }
                    }
                    if(exists==false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long GiaMoi= soluong * GiaChiTiet;
                        Home.manggiohang.add(new GioHang(id,TenChiTiet,GiaMoi,HinhAnh,soluong));

                    }

                }else {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long GiaMoi= soluong * GiaChiTiet;
                    Home.manggiohang.add(new GioHang(id,TenChiTiet,GiaMoi,HinhAnh,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), vn.edu.stu.doanchuyennganh.activiti.GioHang.class);
                startActivity(intent);
            }
        });
    }

    private void SKSpinner() {
        Integer[] soluong= new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter= new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void LayThongTin() {
        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id= sanPham.getId();
        TenChiTiet=sanPham.getTensp();
        GiaChiTiet=sanPham.getGiasp();
        HinhAnh=sanPham.getHinhanh();
        Mota= sanPham.getMota();
        Loaisanpham= sanPham.getIdloaisp();

        txtTen.setText(TenChiTiet);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " +decimalFormat.format(GiaChiTiet)+ "đồng");
        txtMota.setText(Mota);
        Picasso.with(getApplicationContext()).load(HinhAnh)
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(imageViewChitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
    toolbarchitietsanpham= findViewById(R.id.toolchitietsanpham);
    imageViewChitiet= findViewById(R.id.imagechitietsanpham);
    txtTen= findViewById(R.id.textviewTenCTSP);
    txtGia= findViewById(R.id.textviewGiaCTSP);
    txtMota= findViewById(R.id.textviewMotachitietsanpham);
    spinner= findViewById(R.id.spinner);
    btnDatMua= findViewById(R.id.buttonThemSanphamvaogiohang);


    }
}
