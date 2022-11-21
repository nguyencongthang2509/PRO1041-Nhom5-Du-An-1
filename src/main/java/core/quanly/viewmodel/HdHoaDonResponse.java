/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thangncph26123
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HdHoaDonResponse {

    private String id;
    private String ma;
    private int hinhThucThanhToan;
    private Date ngayTao;
    private Date ngayThanhToan;
    private String maNV;
    private String tenNV;
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String sdt;
    private int trangThai;

    public String getTT() {
        return trangThai == 0 ? "Đã hủy" : (trangThai == 1 ? "Chờ thanh toán" : "Đã thanh toán");
    }

    public String getHinhThucTT() {
        return hinhThucThanhToan == 0 ? "Tiền mặt" : "Chuyển khoản";
    }

    public Object[] toDaTaRow() {
        return new Object[]{ma, getHinhThucTT(), ngayTao, ngayThanhToan, maNV, tenNV, maKH, tenKH, sdt, diaChi, getTT()};
    }
}
