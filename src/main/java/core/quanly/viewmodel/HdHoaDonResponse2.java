/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author longnhph26222
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HdHoaDonResponse2 {

    private String id;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Integer hinhThucThanhToan;
    private BigDecimal thanhTien;
    private BigDecimal tienKhachTra;
    private BigDecimal tienThua;
    private String maNV;
    private String tenNV;
    private String diaChi;
    private String sdt;
    private String sdtship;
    private String tennguoiship;
    private BigDecimal tienship;
    private Date ngayship;
    private Date ngaynhan;
    private Integer trangThai;

    public String getTT() {
        if (trangThai == 0) {
            return "Chờ thanh toán";
        } else if (trangThai == 1) {
            return "Đã hủy";
        } else if (trangThai == 2) {
            return "Đã thanh toán";
        } else if (trangThai == 3) {
            return "Đang giao";
        }
        return "Đã giao";
    }

    public String getHinhThucTT() {
        return hinhThucThanhToan == 0 ? "Tiền mặt" : "Chuyển khoản";
    }

    public Object[] toDaTaRow() {
        return new Object[]{ma, ngayTao, ngayThanhToan, getHinhThucTT(), thanhTien, tienKhachTra, tienThua, maNV,
            tenNV, diaChi, sdt, sdtship, tennguoiship, tienship, ngayship, ngaynhan, getTT()};
    }
}
