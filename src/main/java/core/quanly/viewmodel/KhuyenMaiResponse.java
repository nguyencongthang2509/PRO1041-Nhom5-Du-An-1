/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import domainmodels.SanPham;
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
public class KhuyenMaiResponse {
    private String idkm;
    private String makm;
    private String tenkm;
    private Integer loaiKhuyenMai;
    private Double giaTri;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String moTa;
    
    
    public Object[] ToDaTa1(){
    return new Object[]{ makm, tenkm, loaiKhuyenMai== 1 ?"Giảm theo số tiền":"Giảm theo %" , loaiKhuyenMai == 1 ? giaTri+" Vnđ" : giaTri+" %",ngayBatDau,ngayKetThuc,moTa};
    }

}
