/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import crud.repository.HangRepository;
import domainmodels.Hang;
import java.util.List;

/**
 *
 * @author HP
 */
public interface HangService {

    List<Hang> getAll();

    Hang getOne(String ma);

    String add(Hang hang);

    String update(Hang hang);

    String delete(Hang hang);
}
