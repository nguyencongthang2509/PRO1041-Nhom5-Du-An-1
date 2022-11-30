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
@Table(name = "ctsp_khuyen_mai")
public class ChiTietSPKhuyenMai extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ctsp", nullable = false)
    private ChiTietSP chiTietSPId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khuyen_mai", nullable = false)
    private KhuyenMai khuyenMaiId;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "don_gia_con_lai")
    private BigDecimal donGiaConLai;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
