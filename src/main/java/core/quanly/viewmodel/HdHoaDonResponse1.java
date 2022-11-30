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
 * @author thangncph26123
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HdHoaDonResponse1 {

    private String id;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Integer hinhThucThanhToan;
    private BigDecimal thanhTien;
    private BigDecimal tienKhachCK;
    private BigDecimal tienKhachTra;
    private BigDecimal tienThua;
    private String maNV;
    private String tenNV;
    private String tenKH;
    private String diaChi;
    private String sdt;
    private Integer trangThai;

    public String getTT() {
        if(trangThai == 0){
            return "Chờ thanh toán";
        }else if(trangThai == 1){
            return "Đã hủy";
        }else if(trangThai == 2){
            return "Đã thanh toán";
        }else if(trangThai == 3){
            return "Đang giao";
        }
        return "Đã giao";
    }

    public String getHinhThucTT() {
        return hinhThucThanhToan == 0 ? "Tiền mặt" : (hinhThucThanhToan == 1 ? "Chuyển khoản" : "Kết hợp");
    }

    public Object[] toDaTaRow() {
        return new Object[]{ma, ngayTao, ngayThanhToan == null ? "No" : ngayThanhToan, getHinhThucTT(), thanhTien == null ? "No" : thanhTien, tienKhachCK == null ? "0" : tienKhachCK, tienKhachTra == null ? "0" : tienKhachTra, tienThua == null ? "0" : tienThua, maNV, tenNV, tenKH == null ? "Khách bán lẻ" : tenKH, diaChi == null ? "No" : diaChi, sdt == null ? "No" : sdt, getTT()};
    }
}
