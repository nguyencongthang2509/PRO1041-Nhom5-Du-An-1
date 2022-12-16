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
public class ThHoaDonResponse {

    private String id;

    private String maHD;

    private Date ngayTao;

    private Integer hinhThucGiaoHang;

    private Integer hinhThucThanhToan;

    private String tenNhanVien;

    private String maKhachHang;

    private String tenKhachHang;
    
    private String sdtKhachHang;
    
    private String diaChiKhachHang;

    private Integer capBac;

    private Integer trangThaiThanhToan;

    private Date ngayMongMuon;

    private Date ngayThanhToan;

    private Integer trangThai;

    private String tenNguoiNhan;

    private String sdtNguoiNhan;

    private String diaChi;

    private String tenNguoiShip;

    private String sdtNguoiShip;

    private BigDecimal tienShip;

    private BigDecimal tienKhachDua;

    private BigDecimal tienKhachCk;

    private BigDecimal thanhTien;

    private BigDecimal tienThua;
    
    private Double phanTramGiamGia;

    private String getTrangThaiHoaDon() {
        return trangThai == 0 ? "Chờ thanh toán" : (trangThai == 1 ? "Đã hủy" : (trangThai == 2 ? "Đã thanh toán" : (trangThai == 3 ? "Chờ giao hàng" : (trangThai == 4 ? "Đang giao" : (trangThai == 5 ? "Đã giao" : (trangThai == 6 ? "Khách hẹn giao lại" : "Đã trả hàng"))))));
    }

    public Object[] toDataRow() {
        return new Object[]{maHD, tenNhanVien, ngayThanhToan, tenKhachHang, thanhTien, getTrangThaiHoaDon()};
    }
    
    public Object[] toDataRowTraHang() {
        return new Object[]{maHD, tenNhanVien, ngayThanhToan, tenKhachHang, thanhTien};
    }
}
