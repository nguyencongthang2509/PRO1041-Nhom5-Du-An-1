package crud.service.impl;

import crud.repository.NhanVienRepository;
import crud.service.NhanVienService;
import domainmodels.NhanVien;
import java.util.List;

/**
 *
 * @author longnhph26222
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository repo = new NhanVienRepository();

    @Override
    public List<NhanVien> getList() {
        return repo.getList();
    }

    @Override
    public String insert(NhanVien nv) {
        boolean check = repo.saveorUpdate(nv);
        if (nv.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        NhanVien nvfind = repo.findbyMa(nv.getMa());
        if (nvfind != null) {
            return "Mã không được trùng";
        }
        if (nv.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (nv.getDiaChi().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (nv.getEmail().isEmpty()) {
            return "Email không được trống";
        }
        if (nv.getMatKhau().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (nv.getSdt().isEmpty()) {
            return "SĐT không được trống";
        }
        if (nv.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(NhanVien nv) {
        if (nv.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        NhanVien nvfindbyID = repo.findbyID(nv.getId());
        if (nvfindbyID == null) {
            return "Nhân viên này không tồn tại";
        }
        if (!nv.getMa().equals(nvfindbyID.getMa())) {
            NhanVien nvfindbyMa = repo.findbyMa(nv.getMa());
            if (nvfindbyMa != null) {
                return "Mã không được trùng";
            }
            nvfindbyID.setMa(nv.getMa());
        }
        if (nv.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (nv.getDiaChi().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (nv.getEmail().isEmpty()) {
            return "Email không được trống";
        }
        if (nv.getMatKhau().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (nv.getSdt().isEmpty()) {
            return "SĐT không được trống";
        }
        if (nv.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        boolean check = repo.saveorUpdate(nvfindbyID);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(String id) {
        NhanVien nv = repo.findbyID(id);
        if(nv == null){
            return "Nhân viên này không tồn tại";
        }
        boolean check = repo.delete(nv);
        if (check) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

}
