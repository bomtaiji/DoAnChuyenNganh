package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.edu.stu.doanchuyennganh.R;

public class ThongTinActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbarThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        toolbarThongTin= findViewById(R.id.toolbarThongtin);
        addEvents();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addEvents() {
        setSupportActionBar(toolbarThongTin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThongTin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng stu = new LatLng(10.738001, 106.677975);
        mMap.addMarker(new MarkerOptions().position(stu).title("Trường Đại Học Công Nghệ Sài Gòn").snippet("180 Cao Lỗ Phường 4 Quận 8 Thành Phố Hồ Chí Minh").icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        CameraPosition cameraPosition=new CameraPosition.Builder().target(stu).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
