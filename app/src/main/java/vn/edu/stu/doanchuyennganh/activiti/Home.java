package vn.edu.stu.doanchuyennganh.activiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;

public class Home extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewmanghinhchinh;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       addContent();
       addEvents();
       ActionViewFlipper();
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
    }

    private void addContent() {
        drawerLayout= findViewById(R.id.drawerLayout);
        toolbar =findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=(ViewFlipper) findViewById(R.id.viewflipper);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        navigationView=(NavigationView) findViewById(R.id.navigationView);
        listViewmanghinhchinh=(ListView) findViewById(R.id.listViewmanhinhchinh);

    }
}
