
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author HP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CTSanPhamResponse {

    private String ma;
    private String masp;
    private String tensp;
    private String mauSac;
    private String kichThuoc;
    private String hang;
    private String chatLieu;
    private String mactsp;
    private String moTa;
    private Integer soLuongTon;
    private BigDecimal giaBan;
    private String maVach;
    private Integer trangThai;

    public Object[] toDateRow() {
        return new Object[]{mactsp, masp, mauSac, kichThuoc, hang, chatLieu,
            moTa, soLuongTon, giaBan, maVach, trangThai == 0 ? "Đang hoạt động" : "Dừng hoạt động"
        };
    }

}
