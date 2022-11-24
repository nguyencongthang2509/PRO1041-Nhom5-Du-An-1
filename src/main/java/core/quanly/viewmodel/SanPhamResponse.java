/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import domainmodels.base.StatusDeleted;
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
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamViewModel{

    private String id;
    private String ma;
    private String ten;
    private String loai;
    private String hang;

    public Object[] toDataRow() {
        return new Object[]{id, ma, ten, hang, loai};
   }

}
