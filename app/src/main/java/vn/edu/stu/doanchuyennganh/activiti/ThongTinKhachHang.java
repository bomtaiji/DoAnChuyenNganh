package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;
import vn.edu.stu.doanchuyennganh.ultil.Server;

import static vn.edu.stu.doanchuyennganh.activiti.GioHang.txtTongTien;

public class ThongTinKhachHang extends AppCompatActivity {

    EditText edttenkhachhang,edtemail,edtsdt;
    Button btnxacnhan,btntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        addControls();
        addEvents();
        if(Kiemtraketnoi.haveNetworkConnection(getApplicationContext())) {
            addEventButtonxacnhan();
        }else {
            Kiemtraketnoi.Show(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void addEventButtonxacnhan() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten= edttenkhachhang.getText().toString().trim();
                final String sdt= edtsdt.getText().toString().trim();
                final String email= edtemail.getText().toString().trim();

               if(ten.length()>0 && sdt.length()>0 && email.length()>0){
                   RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                   StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.DuongDanDonHang, new Response.Listener<String>() {
                       @Override
                       public void onResponse(final String madonhang) {
                           Log.d("madonhang",madonhang);
                           if(Integer.parseInt(madonhang) >0){
                            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                            StringRequest request = new StringRequest(Request.Method.POST, Server.ĐuonganChiTietDonHang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equals("1")){
                                        Home.manggiohang.clear();
                                        Kiemtraketnoi.Show(getApplicationContext(),"Bạn đã thêm dữ liệu thành công !!");
                                        Intent intent= new Intent(getApplicationContext(),Home.class);
                                        startActivity(intent);
                                        Kiemtraketnoi.Show(getApplicationContext(),"Mời bạn tiếp tục mua hàng !!");
                                    }
                                    else {
                                        Kiemtraketnoi.Show(getApplicationContext(),"Dữ liệu bị lỗi!!");

                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    JSONArray jsonArray = new JSONArray();
                                    for(int i=0;i<Home.manggiohang.size();i++)
                                    {
                                        JSONObject jsonObject= new JSONObject();
                                        try {
                                            jsonObject.put("Id_Don_DatHang",madonhang);
                                            jsonObject.put("Id_SanPham",Home.manggiohang.get(i).getIdsp());
                                            jsonObject.put("TenSanpham",Home.manggiohang.get(i).getTensp());
                                            jsonObject.put("So_Luong",Home.manggiohang.get(i).getSoluong());
                                            jsonObject.put("Don_gia",Home.manggiohang.get(i).getGiasp());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }
                                    HashMap<String,String> hashMap= new HashMap<String, String>();
                                    hashMap.put("json",jsonArray.toString());
                                    return hashMap;
                                }
                            };
                            queue.add(request);
                           }
                       }
                   },new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                       }
                   })
                   {
                       @Override
                       protected Map<String, String> getParams() throws AuthFailureError {
                           HashMap<String,String> hashMap= new HashMap<String, String>();
                           hashMap.put("tenkhachhang",ten);
                           hashMap.put("sodienthoai",sdt);
                           hashMap.put("email",email);
                           return hashMap;
                       }
                   };
                   requestQueue.add(stringRequest);

               }else {
                   Kiemtraketnoi.Show(getApplicationContext(),"Hãy Kiểm Tra Lại Thông Tin");
               }


            }
        });
    }

    private void addEvents() {
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void addControls() {
        edttenkhachhang= findViewById(R.id.edittextTenKhachHang);
        edtemail= findViewById(R.id.edittextEmailKhachHang);
        edtsdt= findViewById(R.id.edittextSDTKhachHang);
        btnxacnhan= findViewById(R.id.buttonXacnhan);
        btntrove= findViewById(R.id.buttonTroVe);
    }
}
