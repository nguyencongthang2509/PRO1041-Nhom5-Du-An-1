/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author HP
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CTSanPhamViewModel {

    private String idctsp;
    private String masp;
    private String tensp;
    private Integer soLuongTon;
    private BigDecimal giaBan;
    private String moTa;
    private String mauSac;
    private String kichThuoc;
    private String hang;
    private String khuyenMai;

    public Object[] toDateRow() {
        return new Object[]{idctsp, masp, tensp, soLuongTon, giaBan, moTa
        };
    }

}
