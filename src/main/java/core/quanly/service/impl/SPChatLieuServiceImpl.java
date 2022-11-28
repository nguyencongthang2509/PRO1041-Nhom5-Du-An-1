/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPChatLieuRepository;
import core.quanly.service.SPChatLieuService;
import core.quanly.viewmodel.SPChatLieuResponse;
import domainmodels.ChatLieu;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPChatLieuServiceImpl implements SPChatLieuService{
    
    private SPChatLieuRepository chatLieuRepository = new SPChatLieuRepository();

    @Override
    public List<SPChatLieuResponse> getAllViewModel() {
        return chatLieuRepository.getAllResponse();
    }

    @Override
    public ChatLieu getOne(String ma) {
        return chatLieuRepository.findByMa(ma);
    }

    @Override
    public String add(ChatLieu chatLieu) {
        if (chatLieu.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (chatLieu.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        ChatLieu chatLieuFind = chatLieuRepository.findByMa(chatLieu.getMa());
        if (chatLieuFind != null) {
            return "Mã không được trùng";
        }
        chatLieu = chatLieuRepository.saveOrUpdate(chatLieu);
        if (chatLieu != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ChatLieu chatLieu) {
        ChatLieu chatLieuFindById = chatLieuRepository.findById(chatLieu.getId());
        if (chatLieuFindById == null) {
            return "Không tìm thấy chất liệu";
        }
        if (chatLieu.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (chatLieu.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!chatLieu.getMa().equals(chatLieuFindById.getMa())) {
            ChatLieu chatLieuFindByMa = chatLieuRepository.findByMa(chatLieu.getMa());
            if (chatLieuFindByMa != null) {
                return "Mã không được trùng";
            } else {
                chatLieuFindById.setMa(chatLieu.getMa());
            }
        }
        chatLieuFindById.setTen(chatLieu.getTen());
        chatLieu = chatLieuRepository.saveOrUpdate(chatLieuFindById);
        if (chatLieu != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.getAll();
    }
    
}
