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
    private String id;
    private String ma;
    private String ten;
    private String loaiKhuyenMai;
    private String giaTri;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    
    public Object[] ToDaTa(){
    return new Object[]{id, ma, ten, loaiKhuyenMai , giaTri,ngayBatDau,ngayKetThuc};
    }
    
}
