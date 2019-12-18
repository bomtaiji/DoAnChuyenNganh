package vn.edu.stu.doanchuyennganh.activiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.adapter.banhAdapter;
import vn.edu.stu.doanchuyennganh.model.SanPham;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;
import vn.edu.stu.doanchuyennganh.ultil.Server;

public class BanhActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarbanh;
    ListView lvbanh;
    banhAdapter banhAdapter;
    ArrayList<SanPham> mangbanh;
    int idBanh=0;
    int page=1;
    View footerview;
    boolean isLoading= false;
    mHandler mHandler;
    boolean limitData= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banh);
        addControls();
        if (Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
            GetIdLoaiSP();
            ActionToolbar();
            GetData(page);
            LoadMoreData();

        }else {
            Kiemtraketnoi.Show(getApplicationContext(),"BẠN HÃY KIỂM TRA LẠI INTERNET");
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
    private void addControls() {
        toolbarbanh = findViewById(R.id.toolbarBanh);
        lvbanh=(ListView) findViewById(R.id.listViewBanh);
        mangbanh= new ArrayList<>();
        banhAdapter= new banhAdapter(getApplicationContext(),mangbanh);
        lvbanh.setAdapter(banhAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview= inflater.inflate(R.layout.taithemdulieu,null);
        mHandler= new mHandler();
    }
    private void GetIdLoaiSP() {
        idBanh=getIntent().getIntExtra("idloaisanpham",-1);
        //Toast.makeText(getApplicationContext(),"Vi trí chọn"+ idloaisanpham,Toast.LENGTH_LONG).show();
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarbanh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarbanh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.DuongDanCom+String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String tenbanh="";
                String hinhanhbanh="";
                String motabanh="";
                int giabanh=0;
                int LoaiSP=0;
                if(response!=null &&response.length()!=2 )
                {
                    lvbanh.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("Id_SanPham");
                            tenbanh=jsonObject.getString("Ten_SanPham");
                            giabanh= jsonObject.getInt("Gia");
                            hinhanhbanh= jsonObject.getString("Hinh_Anh");
                            motabanh= jsonObject.getString("Mo_Ta");
                            LoaiSP=jsonObject.getInt("Id_Loai_SanPham");
                            mangbanh.add(new SanPham(id,tenbanh,giabanh,hinhanhbanh,motabanh,LoaiSP));
                            banhAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData=true;
                    lvbanh.removeFooterView(footerview);
                    Kiemtraketnoi.Show(getApplicationContext(),"Đã hết dữ liệu");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param= new HashMap<String, String>();
                param.put("idloaisanpham",String.valueOf(idBanh));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void LoadMoreData() {
        lvbanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangbanh.get(position));
                startActivity(intent);
            }
        });
        lvbanh.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount== totalItemCount && totalItemCount !=0 && isLoading==false && limitData==false)
                {
                    isLoading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    lvbanh.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message= mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
