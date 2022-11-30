package domainmodels;

import domainmodels.base.PrimaryEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author thangncph26123
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_hon", nullable = false)
    private HoaDon hoaDonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ctsp", nullable = false)
    private ChiTietSP chiTietSPId;

    @Column(name = "giam_gia_khuyen_mai", columnDefinition = "decimal(20,0)")
    private BigDecimal giamGiaKhuyenMai;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia", columnDefinition = "decimal(20,0)")
    private BigDecimal donGia;

    @Column(name = "gia_ban", columnDefinition = "decimal(20,0)")
    private BigDecimal giaBan;

    @Column(name = "trang_thai")
    private Integer trangThai; // 0 : Chờ thanh toán, 1: Đã hủy, 2: Đã thanh toán, 3: Đang giao, 4: Đã giao
}
