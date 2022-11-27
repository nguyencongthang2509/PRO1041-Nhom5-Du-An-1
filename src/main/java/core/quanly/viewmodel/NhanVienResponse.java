
package core.quanly.viewmodel;

//import infrastructure.constant.VaiTro;
import domainmodels.base.StatusDeleted;
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
public class NhanVienResponse{
    private String id;
    private String ma;
    private String ten;
    private int gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private int vaitro;
    private Integer trangthaixoa;
        
        
        
    
    

   
    
    

    public Object[] toDataRow(int index){
    return new Object[]{index,ma,ten,gioitinh==0?"Nam":"Nữ",ngaysinh,diachi,sdt,email,vaitro==0?
            "Quản Lý":"Nhân Viên",trangthaixoa==0?"Đang Làm":"Đã Nghỉ"};
    }
    
}
