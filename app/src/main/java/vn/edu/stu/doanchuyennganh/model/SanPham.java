package vn.edu.stu.doanchuyennganh.model;

public class SanPham {
    public int id;
    public String tensp;
    public int giasp;
    public String hinhanh;
    public String mota;
    public int idloaisp;

    public SanPham() {
    }

    public SanPham(String tensp, int giasp, String hinhanh, String mota, int idloaisp) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.idloaisp = idloaisp;
    }

    public SanPham(int id, String tensp, int giasp, String hinhanh, String mota, int idloaisp) {
        this.id = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.idloaisp = idloaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }
}
