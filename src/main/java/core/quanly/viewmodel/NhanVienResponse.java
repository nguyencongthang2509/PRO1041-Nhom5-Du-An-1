
package core.quanly.viewmodel;

//import infrastructure.constant.VaiTro;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author quynhncph26201
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienResponse {
    private String id;
    private String ma;
    private String ten;
    private int gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private int vaitro;
    
    

    public Object[] toDataRow(int index){
    return new Object[]{index,ma,ten,gioitinh==0?"Male":"Female",ngaysinh,diachi,sdt,email,vaitro==0?"Quan Ly":"Nhan Vien"};
    }
    
}
