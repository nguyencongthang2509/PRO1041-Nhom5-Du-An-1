/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.SPChatLieuResponse;
import domainmodels.ChatLieu;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SPChatLieuService {
    
    List<SPChatLieuResponse> getAllViewModel();

    ChatLieu getOne(String ma);

    String add(ChatLieu chatLieu);

    String update(ChatLieu chatLieu);

    List<ChatLieu> getAll();

}
