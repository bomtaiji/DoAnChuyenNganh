package vn.edu.stu.doanchuyennganh.model;

public class LoaiSanPham {
    public int id;
    public String TenloaiSP;
    public String HinhanhloaiSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, String tenloaiSP, String hinhanhloaiSP) {
        this.id = id;
        TenloaiSP = tenloaiSP;
        HinhanhloaiSP = hinhanhloaiSP;
    }

    public LoaiSanPham(String tenloaiSP, String hinhanhloaiSP) {
        TenloaiSP = tenloaiSP;
        HinhanhloaiSP = hinhanhloaiSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaiSP() {
        return TenloaiSP;
    }

    public void setTenloaiSP(String tenloaiSP) {
        TenloaiSP = tenloaiSP;
    }

    public String getHinhanhloaiSP() {
        return HinhanhloaiSP;
    }

    public void setHinhanhloaiSP(String hinhanhloaiSP) {
        HinhanhloaiSP = hinhanhloaiSP;
    }

}
