package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.doanchuyennganh.R;

import static vn.edu.stu.doanchuyennganh.ultil.Server.DuongDanDangKi;

public class DangKi extends AppCompatActivity {
    EditText edtTen,edtEmail,edtPass,edtPassCon;
    Button btndk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
                finish();
            }
        });
    }

    private void addControls() {
        edtTen=findViewById(R.id.edtname);
        edtEmail=findViewById(R.id.edtemaillg);
        edtPass=findViewById(R.id.edtpasslg);
        edtPassCon=findViewById(R.id.edtpassconlg);
        btndk=findViewById(R.id.btnlogin);
    }
    private void Regist(){
        final String name= this.edtTen.getText().toString().trim();
        final String email= this.edtEmail.getText().toString().trim();
        final String password= this.edtPass.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,DuongDanDangKi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success= jsonObject.getString("success");
                    if (success.equals("1")){
                        Toast.makeText(DangKi.this, "Register success!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DangKi.this, "Register err! "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangKi.this, "Err! "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
