/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public interface MauSacService {

    List<MauSac> getAll();

    String insert(MauSac mausac);

    String update(MauSac mausac);

    String delete(MauSac mausac);
}
