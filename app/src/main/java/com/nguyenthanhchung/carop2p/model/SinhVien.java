package com.nguyenthanhchung.carop2p.model;

/**
 * Created by Heo on 06/04/2018.
 */

public class SinhVien {
    private String hoTen;
    private int MSSV;
    private int hinhAnh;

    public SinhVien (String hoTen, int MSSV, int hinhAnh)
    {
        this.hoTen = hoTen;
        this.MSSV = MSSV;
        this.hinhAnh = hinhAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
