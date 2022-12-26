
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.repository.NhanVienRepository;
import core.quanly.service.impl.NhanVienServiceImpl;
import core.quanly.viewmodel.NhanVienNghiResponse;
import domainmodels.NhanVien;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quynhncph26201
 */
public class SendToPassWord extends Thread {

    private static String[] toEmailInput;
    private static String title;
    private static String body;
    NhanVienServiceImpl nn = new NhanVienServiceImpl();
 private NhanVienRepository nvr = new NhanVienRepository();
    @Override
    public void run() {
        try {
            final String fromEmail = "thangncph26123@fpt.edu.vn";
            // Mat khai email cua ban
            final String password = "ngct25092003";
            // dia chi email nguoi nhan
            final String toEmail[] = toEmailInput;
            final String subject = "Java Example Test";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            List<NhanVien> list = new ArrayList<>();
            for (String xx : toEmail) {
                NhanVien nv = nn.getNhanVienByEmail2(xx);
                System.out.println(nv.getId());
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(xx, false));
                message.setSubject(subject);
                message.setSubject(title);
                String password1 = String.valueOf((int) (Math.random() * 100000000));
                message.setSubject(title, "UTF-8");
                body = "Thông báo mật khẩu mới của nhân viên: " + password1;
                message.setText(body, "UTF-8");
//            String htmlContent = "<h1>" + body + "</h1>";
//            message.setContent(htmlContent, "text/html");
                Transport.send(message);
                System.out.println(nv.getEmail());

                //Mã Hóa Mật Khẩu
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password1.getBytes());
                byte[] digest = md.digest();
                String myHash = DatatypeConverter
                        .printHexBinary(digest).toUpperCase();
                nv.setMatKhau(myHash);
                list.add(nv);

            }
            nvr.saveAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String args[]) {
//        MyThread t1 = new MyThread();
//        t1.start();
//    }
    public static void emailSender(String toEmail[], String tit, String bod) {
        toEmailInput = toEmail;
        System.out.println(toEmail.length);
        title = tit;
        body = bod;
        SendToPassWord emailSender = new SendToPassWord();
        emailSender.start();
    }

    public static void main(String[] args) {
//        String[] array = {"congthang25092003@gmail.com", "quynhncph26201@fpt.edu.vn", "trangptqph26218@fpt.edu.vn", "longnhph26222@fpt.edu.vn", "thiennvtph26140@fpt.edu.vn", "congq9834@gmail.com"};
//        new EmailSender().emailSender(array, "cccc", "cccc");
    }
}
