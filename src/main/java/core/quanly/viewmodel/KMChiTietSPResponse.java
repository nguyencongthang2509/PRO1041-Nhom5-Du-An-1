/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thiennvtph26140
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KMChiTietSPResponse {

    private String id;
    private String maCTSP;
    private String tenSP;
    private String hang;
    private String mauSac;
    private String size;
    private String chatLieu;

    public Object[] toDaTaRow() {
        return new Object[]{false, maCTSP, tenSP, hang, mauSac, size, chatLieu};
    }
}
