package crud.service;

import domainmodels.TrangThaiHoaDon;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public interface TrangThaiHoaDonService {

    List<TrangThaiHoaDon> getAll();

    String insert(TrangThaiHoaDon TrangThaiHoaDon);

    String update(TrangThaiHoaDon TrangThaiHoaDon);

    String delete(String ma);
}
