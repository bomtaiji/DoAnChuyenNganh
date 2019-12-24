package vn.edu.stu.doanchuyennganh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.activiti.Home;
import vn.edu.stu.doanchuyennganh.model.GioHang;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arrGioHang;


    public GioHangAdapter(Context context, ArrayList<GioHang> arrGioHang) {
        this.context = context;
        this.arrGioHang = arrGioHang;
    }

    @Override
    public int getCount() {
        return arrGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenGiohang,txtGiagiohang;
        public ImageView imageGiohang;
        public Button btnCong,btnTru,btnGiatri;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if(convertView==null) {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView= inflater.inflate(R.layout.dong_giohang,null);
             viewHolder.txtTenGiohang=(TextView) convertView.findViewById(R.id.textviewtengiohang);
             viewHolder.txtGiagiohang=(TextView) convertView.findViewById(R.id.textviewgiagiohang);
             viewHolder.imageGiohang=(ImageView) convertView.findViewById(R.id.imageGiohang);
             viewHolder.btnTru=(Button) convertView.findViewById(R.id.buttontru);
            viewHolder.btnCong=(Button) convertView.findViewById(R.id.buttoncong);
            viewHolder.btnGiatri=(Button) convertView.findViewById(R.id.buttongiatri);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GioHang gioHang= (GioHang) getItem(position);
        viewHolder.txtTenGiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiagiohang.setText(decimalFormat.format(gioHang.getGiasp())+" đồng");
        Picasso.with(context).load(gioHang.getHinhanh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imageGiohang);

        viewHolder.btnGiatri.setText(gioHang.getSoluong()+"");

        int sl= Integer.parseInt(viewHolder.btnGiatri.getText().toString());
        if(sl>=10)
        {
            viewHolder.btnCong.setVisibility(View.INVISIBLE);
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }else if(sl<=1) {
            viewHolder.btnTru.setVisibility(View.INVISIBLE);
            viewHolder.btnCong.setVisibility(View.VISIBLE);
        }else if(sl>=1) {
            viewHolder.btnTru.setVisibility(View.VISIBLE);
            viewHolder.btnCong.setVisibility(View.VISIBLE);
        }
        //sư kiên nút button them san pham
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnGiatri.getText().toString())+ 1;
                int slhientai= Home.manggiohang.get(position).getSoluong();
                long giaht=Home.manggiohang.get(position).getGiasp();

                Home.manggiohang.get(position).setSoluong(slmoinhat);
                long giamoinhat=(giaht * slmoinhat ) / slhientai;
                Home.manggiohang.get(position).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiagiohang.setText(decimalFormat.format(giamoinhat)+" đồng");

                vn.edu.stu.doanchuyennganh.activiti.GioHang.DoDuLieu();
                if(slmoinhat>9){
                    finalViewHolder.btnCong.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnGiatri.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnGiatri.setText(String.valueOf(slmoinhat));
                }
            }
        });

        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnGiatri.getText().toString()) - 1;
                int slhientai= Home.manggiohang.get(position).getSoluong();
                long giaht=Home.manggiohang.get(position).getGiasp();
                Home.manggiohang.get(position).setSoluong(slmoinhat);
                long giamoinhat=(giaht * slmoinhat ) / slhientai;
                Home.manggiohang.get(position).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiagiohang.setText(decimalFormat.format(giamoinhat)+" đồng");

                vn.edu.stu.doanchuyennganh.activiti.GioHang.DoDuLieu();
                if(slmoinhat < 2){
                    finalViewHolder.btnTru.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnGiatri.setText(String.valueOf(slmoinhat));
                }else {
                finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                finalViewHolder.btnGiatri.setText(String.valueOf(slmoinhat));
            }
            }
        });

        return convertView;
    }
}
