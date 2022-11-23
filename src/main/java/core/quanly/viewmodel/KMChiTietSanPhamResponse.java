/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import domainmodels.Hang;
import domainmodels.KhuyenMai;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author thiennvtph26140
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KMChiTietSanPhamResponse {
    private String id;
    private String makm;
    private String tenkm;
    private String loaiKhuyenMai;
    private String giaTri;
    private String masanpham;
    private String sanpham;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    
    public Object[] ToDaTa1(){
    return new Object[]{makm, tenkm, loaiKhuyenMai, giaTri, sanpham, ngayBatDau, ngayKetThuc};
    }
    public Object[] ToDaTa2(){
        return new Object[]{masanpham, sanpham};
    }
}
