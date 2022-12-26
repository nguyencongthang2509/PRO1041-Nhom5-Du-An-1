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
public class BhHoaDonResponse {

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
    
    private Double phanTramGiamGia;
    
    private Integer trangThaiThanhToan;
    
    private Date ngayMongMuon;

    private Integer trangThai;

    private String tenNguoiNhan;

    private String sdtNguoiNhan;

    private String diaChi;

    private String tenNguoiShip;

    private String sdtNguoiShip;

    private BigDecimal tienShip;

    private BigDecimal tienKhachDua;

    private BigDecimal tienKhachCk;

    private BigDecimal tienThua;

    private String getTrangThaiHoaDon() {
        return trangThai == 0 ? "Chờ thanh toán" : (trangThai == 1 ? "Đã hủy" : (trangThai == 2 ? "Đã thanh toán" : (trangThai == 3 ? "Chờ giao hàng" : (trangThai == 4 ? "Đang giao" : (trangThai == 5 ? "Đã giao" : (trangThai == 6 ? "Khách hẹn lại" : "Đã trả hàng"))))));
    }

    public Object[] toDataRow() {
        return new Object[]{maHD, ngayTao, tenNhanVien, tenKhachHang, hinhThucGiaoHang == 0 ? "Tại quầy" : "Đặt hàng", getTrangThaiHoaDon()};
    }
}
