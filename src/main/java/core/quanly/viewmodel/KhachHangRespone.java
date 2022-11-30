/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

/**
 *
 * @author NgocAnh
 */
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KhachHangRespone {

    private String id;
    private String ma;
    private String hoTen;
    private Integer gioiTinh;
    private String sdt;
    private String diaChi;
    private String email;
    private Date ngaySinh;
    
    public Object[] toDataRow(){      
        return new  Object[]{ma,hoTen,gioiTinh,sdt,diaChi,email,ngaySinh};         
    }

}
