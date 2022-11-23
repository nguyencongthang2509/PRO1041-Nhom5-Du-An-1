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
    @JoinColumn(name = "id_hoa_hon")
    private HoaDon hoaDonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ctsp")
    private ChiTietSP chiTietSPId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khuyen_mai")
    private KhuyenMai khuyenMaiId;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;
    
    @Column(name = "trang_thai")
    private int trangThai;
}
