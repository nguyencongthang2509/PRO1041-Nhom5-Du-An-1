
package core.quanly.viewmodel;

import infrastructure.constant.VaiTro;
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
    private String gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private VaiTro vaitro;
    

    public Object[] toDataRow(int index){
    return new Object[]{index,ma,ten,gioitinh,ngaysinh,diachi,sdt,email,vaitro};
    }
    
}
