package domainmodels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(HoaDonChiTietId.class)
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoaDonId", nullable = false)
    private HoaDon hoaDonId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chiTietSPId", nullable = false)
    private ChiTietSP chiTietSPId;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

}
