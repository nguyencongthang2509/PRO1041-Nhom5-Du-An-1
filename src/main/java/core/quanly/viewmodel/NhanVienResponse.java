
package core.quanly.viewmodel;

//import infrastructure.constant.VaiTro;
import domainmodels.base.StatusDeleted;
import java.text.SimpleDateFormat;
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
    private Integer gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private Integer vaitro;
    private Integer trangthaixoa;
       public String getnngaysinh(){
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(ngaysinh);
        return strDate;
             
       }

    public Object[] toDataRow(int index){
    return new Object[]{index,ma,ten,gioitinh==0?"Nam":"Nữ",getnngaysinh(),diachi,sdt,email,vaitro==0?
            "Quản Lý":"Nhân Viên",trangthaixoa==0?"Đang Làm":"Đã Nghỉ"};
    }
    
}
