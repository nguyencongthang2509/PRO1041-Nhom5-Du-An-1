/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name = "hoa_don_tra_hang_chi_tiet")
public class HoaDonTraHangChiTiet extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don_tra_hang")
    private HoaDonTraHang hoaDonDoiTra;

    @Column(name = "ma_ctsp")
    private String maChiTietSanPham;

    @Column(name = "ten_san_pham")
    private String tenSP;
    
    @Column(name = "ten_hang")
    private String tenHang;
    
    @Column(name = "kich_thuoc")
    private String kichThuoc;
    
    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "so_luong_tra")
    private Integer soLuongTra;

}
