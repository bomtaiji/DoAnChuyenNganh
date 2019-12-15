package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

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
import vn.edu.stu.doanchuyennganh.adapter.ComAdapter;
import vn.edu.stu.doanchuyennganh.model.SanPham;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;
import vn.edu.stu.doanchuyennganh.ultil.Server;

public class ComActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbarcom;
    ListView lvCom;
    ComAdapter comAdapter;
    ArrayList<SanPham> mangcom;
    int idloaisanpham=0;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        addControls();
        addEvents();
       if(Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
           GetIdLoaiSP();
           ActionToolbar();
           GetData(page);
       }
       else {
           Kiemtraketnoi.Show(getApplicationContext(),"Bạn hãy kiểm tra kết nối");
           finish();
       }
    }

    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.DuongDanCom+String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String tenCom="";
                String hinhanhcom="";
                String motacom="";
                int giacom=0;
                int LoaiSP=0;
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("Id_SanPham");
                            tenCom=jsonObject.getString("Ten_SanPham");
                            giacom= jsonObject.getInt("Gia");
                            hinhanhcom= jsonObject.getString("Hinh_Anh");
                            motacom= jsonObject.getString("Mo_Ta");
                            LoaiSP=jsonObject.getInt("Id_Loai_SanPham");
                            mangcom.add(new SanPham(id,tenCom,giacom,hinhanhcom,motacom,LoaiSP));
                            comAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                param.put("idloaisanpham",String.valueOf(idloaisanpham));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarcom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdLoaiSP() {
        idloaisanpham=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaiSP",idloaisanpham+"");

    }

    private void addEvents() {
    }

    private void addControls() {
        toolbarcom = findViewById(R.id.toolbarCom);
        lvCom=(ListView) findViewById(R.id.listViewCom);
        mangcom= new ArrayList<>();
        comAdapter= new ComAdapter(getApplicationContext(),mangcom);
        lvCom.setAdapter(comAdapter);
    }
}
