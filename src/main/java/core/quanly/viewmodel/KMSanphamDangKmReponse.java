/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author thiennvtph26140
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KMSanphamDangKmReponse {
    private String idctspkm;
    private String ma;
    private String ten;
    private String tenhang;
    private String mausac;
    private String kichthuoc;
    private String chatlieu;
    
    public Object[] ToDaTa(){
    return new Object[]{ma, ten, tenhang, mausac, kichthuoc, chatlieu};
    }
}
