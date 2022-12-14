/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 * @author quynhncph26201
 */
public class NhanVienNghiResponse {
    private String id;
    private String ma;
    private String ten;
    private Integer gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private Integer vaitro;
    private Integer trangthaixoa;
       public String getnngaysinh(){
           SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = formatter.format(ngaysinh);
        return strDate;
             
       }

    public Object[] toDataRow(int index){
    return new Object[]{index,ma,ten,gioitinh==0?"Nam":"Nữ",getnngaysinh(),diachi,sdt,email,vaitro==0?
            "Quản Lý":"Nhân Viên"};
    }
}
