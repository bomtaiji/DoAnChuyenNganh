package vn.edu.stu.doanchuyennganh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.activiti.ChiTietSanPham;
import vn.edu.stu.doanchuyennganh.model.SanPham;
import vn.edu.stu.doanchuyennganh.ultil.Kiemtraketnoi;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {

    Context context;

    public SanphamAdapter(Context context, ArrayList<SanPham> arrSanPham) {
        this.context = context;
        this.arrSanPham = arrSanPham;
    }

    ArrayList<SanPham> arrSanPham;
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoi,null);
        ItemHolder itemHolder= new ItemHolder(v);
        return itemHolder;

    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        SanPham sanPham= arrSanPham.get(position);
        holder.txtTensp.setText(sanPham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Giá : "+decimalFormat.format(sanPham.getGiasp())+"đồng");
        Picasso.with(context).load(sanPham.getHinhanh())
                .placeholder(R.drawable.hinhmoi)
                .error(R.drawable.error)
                .into(holder.imageviewsp);

    }

    @Override
    public int getItemCount() {
        return arrSanPham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
    public ImageView imageviewsp;
    public TextView txtTensp,txtgiasp;

        public ItemHolder(View itemView) {
            super(itemView);
            imageviewsp =(ImageView) itemView.findViewById(R.id.imgsanphammoi);
            txtTensp=(TextView) itemView.findViewById(R.id.textViewtensp);
            txtgiasp=(TextView) itemView.findViewById(R.id.textViewgiasp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("thongtinsanpham",arrSanPham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Kiemtraketnoi.Show(context,arrSanPham.get(getPosition()).getTensp());
                    context.startActivity(intent);

                }
            });
        }
    }
}
