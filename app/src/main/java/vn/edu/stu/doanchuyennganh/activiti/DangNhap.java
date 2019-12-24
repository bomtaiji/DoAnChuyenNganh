package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.doanchuyennganh.R;

import static vn.edu.stu.doanchuyennganh.ultil.Server.DuongdanLogin;

public class DangNhap extends AppCompatActivity {
Button btnDangNhap;
EditText edtemail,edtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addContent();
        addEvent();
    }

    private void addContent() {
        btnDangNhap = (Button)findViewById(R.id.btnDangNhap);
        edtemail= findViewById(R.id.edtUser);
        edtpassword= findViewById(R.id.edtPassword);
    }

    private void addEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail= edtemail.getText().toString().trim();
                String mPassword = edtpassword.getText().toString().trim();
                if (!mEmail.isEmpty()||!mPassword.isEmpty()){
                    Login1(mEmail,mPassword);
                    Intent intent = new Intent(DangNhap.this,Home.class);
                    startActivity(intent);
                }else {
                    edtemail.setError("Please insert email!");
                    edtpassword.setError("Please insert pass!");
                }
            }
        });

    }
    private void Login1(final String email, final String pass) {
        StringRequest stringRequest= new StringRequest(Request.Method.POST,DuongdanLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success= jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("login");
                    if (success.equals("1")){
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            String name=object.getString("name").trim();
                            String email=object.getString("email").trim();
                            Toast.makeText(DangNhap.this, "Success Login.\nYour name:"+name+"\nYour email:"+email, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DangNhap.this, "Login err! "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangNhap.this, "Error! "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",pass);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
