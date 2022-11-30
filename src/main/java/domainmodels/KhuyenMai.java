package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author thangncph26123
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "khuyen_mai")
public class KhuyenMai extends PrimaryEntity implements Serializable{

    @Column(name = "ma", length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(name = "ten", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String ten;

    @Column(name = "loai_khuyen_mai")
    private Integer loaiKhuyenMai;
    
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "gia_tri")
    private Double giaTri;
    
    @Column(name = "mo_ta", length = EntityProperties.LENGTH_DESCRIPTION)
    private String moTa;

}
