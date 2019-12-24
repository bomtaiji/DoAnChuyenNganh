package vn.edu.stu.doanchuyennganh.activiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.adapter.LoaispAdapter;
import vn.edu.stu.doanchuyennganh.adapter.SanphamAdapter;
import vn.edu.stu.doanchuyennganh.model.GioHang;
import vn.edu.stu.doanchuyennganh.model.LoaiSanPham;
import vn.edu.stu.doanchuyennganh.model.SanPham;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;
import vn.edu.stu.doanchuyennganh.ultil.Server;

public class Home extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewmanghinhchinh;
    DrawerLayout drawerLayout;

    ArrayList<LoaiSanPham> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaiSp="";

    ArrayList<SanPham> mangsanpham;
    SanphamAdapter sanphamAdapter;

    public static ArrayList<GioHang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       addContent();
       if(Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
           addEvents();
           ActionViewFlipper();
           GetDuLieuLoaiSP();
           GetDuLieuSPMoi();
           
       }
       else {
           Kiemtraketnoi.Show(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
           finish();
       }
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

    private void GetDuLieuSPMoi() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.DuongDanSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int Id = 0;
                    String Tensp = "";
                    Integer Giasp = 0;
                    String Hinhanh = "";
                    String Mota = "";
                    int Loaisp = 0;
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            Id= jsonObject.getInt("Id_SanPham");
                            Tensp= jsonObject.getString("Ten_SanPham");
                            Giasp= jsonObject.getInt("Gia");
                            Hinhanh= jsonObject.getString("Hinh_Anh");
                            Mota= jsonObject.getString("Mo_Ta");
                            Loaisp=jsonObject.getInt("Id_Loai_SanPham");

                            mangsanpham.add(new SanPham(Id,Tensp,Giasp,Hinhanh,Mota,Loaisp));
                            sanphamAdapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaiSP() {
    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.DuongDanLoaiSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                   for (int i=0;i<response.length();i++) {
                       try {
                           JSONObject jsonObject = response.getJSONObject(i);
                           id = jsonObject.getInt("Id_Loai_SanPham");
                           tenloaisp = jsonObject.getString("Ten_Loai_SanPham");
                           hinhanhloaiSp = jsonObject.getString("hinhanh");
                           mangloaisp.add(new LoaiSanPham(id,tenloaisp, hinhanhloaiSp));
                           loaispAdapter.notifyDataSetChanged();

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
                   mangloaisp.add(5, new LoaiSanPham("Liên Hệ ","http://quanlegging.com/wp-content/uploads/2015/07/Call-icon-blue.png"));
                   mangloaisp.add(6, new LoaiSanPham("Thông Tin","https://cdn.pixabay.com/photo/2017/01/10/03/54/icon-1968237_960_720.png"));
                   mangloaisp.add(7, new LoaiSanPham("LogOut","https://previews.123rf.com/images/tmricons/tmricons1510/tmricons151000278/45809602-door-sign-icon-login-symbol-.jpg"));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Kiemtraketnoi.Show(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> quangcao= new ArrayList<>();
        quangcao.add("https://cdn.eva.vn/upload/3-2017/images/2017-07-05/nhung-mon-an-tuyet-ngon-che-bien-tu-thit-bo-mon-ngon-tu-thit-bo-1-1499264329-width500height430.jpg");
        quangcao.add("https://massageishealthy.com/wp-content/uploads/2019/06/nhung-mon-ngon-tu-trung-ga-lam-mon-gi-ngon-nhat-thumb.jpg");
        quangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzH4gGIY3qpABYsf0WriUSkOrBIkAwbK8QqTBBiuDSp6VdWEckOQ&s");
        quangcao.add("https://nhahangmaison.vn/wp-content/uploads/2018/12/Khoai-tay-chien-%E2%80%93-mot-mon-ngon-duoc-yeu-thich-rat-nhieu.png");
        quangcao.add("https://nhahangmaison.vn/wp-content/uploads/2018/12/Rosti-%E2%80%93-mon-ngon-tu-khoai-tay-duoc-yeu-thich-tai-Thuy-Si.png");
        quangcao.add("https://cdn.eva.vn//upload/1-2015/images/2015-03-27/1427447012-muc-xot-chanh9.jpg");

        for(int i=0;i<quangcao.size();i++)
        {
            ImageView imageView= new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);

    }

    private void addEvents() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        listViewmanghinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, Home.class);
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, ComActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this,BanhActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, BunActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, kemActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, LienheActivity.class);
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this, ThongTinActivity.class);
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(Home.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Kiemtraketnoi.Show(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                }
            }
        });


    }

    private void addContent() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listViewmanghinhchinh = (ListView) findViewById(R.id.listViewmanhinhchinh);

        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new LoaiSanPham("TrangChinh", "https://thuycanhmiennam.com/wp-content/uploads/2017/04/icon-home-cam.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp, getApplicationContext());
        listViewmanghinhchinh.setAdapter(loaispAdapter);

        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(sanphamAdapter);
        if(manggiohang!=null)
        {

        }
        else {
            manggiohang= new ArrayList<>(

            );

        }

    }
}
