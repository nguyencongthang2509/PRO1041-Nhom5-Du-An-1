/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import infrastructure.constant.TrangThaiXoa;
import java.util.UUID;
import java.util.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author HP
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamViewModel {

    private UUID id;
    private String ma;
    private String ten;
    private TrangThaiXoa trangThai;
    private Long createDate;
    private Long LastModifiedDate;

    public Object[] toDataRow() {
        return new Object[]{id, ma, ten, trangThai, createDate, LastModifiedDate};
    }

}
