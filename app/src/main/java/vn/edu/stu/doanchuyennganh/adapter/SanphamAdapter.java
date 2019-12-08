package vn.edu.stu.doanchuyennganh.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SanphamAdapter extends RecyclerView.Adapter<> {
    public class ItemHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView txtTensp,txtgiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
