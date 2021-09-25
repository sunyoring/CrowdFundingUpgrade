package com.kh.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kh.common.model.vo.Attachment;
import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.RecruitMember;
import com.kh.recruit.model.vo.Recruitment;

public class MailService {
	private static String user = "sunyoya2@gmail.com"; // 관리자 이메일
	private static String password = "tpal1234!!"; // 관리자 이메일 비밀번호
	
	private static Properties p = null; // 이메일 설정 값
	
	private static String pagePath = "";
	
	private static class SMTPAuthenticator extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}
	}
	
	public static void setPagePath(String path) {
		pagePath = path + "/views/recruit/emailPage.html";
	}
	
	private static Properties getSetting() {
		if (p == null) {
			// 이메일 설정
			p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.auth", "true"); 
			p.put("mail.smtp.ssl.enable", "true"); 
			p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			p.put("mail.smtp.starttls.required", "true");
			p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		}
		
		return p;
	}

	public static void send(String title, String content, String toEmail) {
		p = getSetting();
		
		try {
			
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(p, auth);
			
			session.setDebug(true);
			
			MimeMessage msg = new MimeMessage(session);

			// 메일 제목
			msg.setSubject(title);
			
			// 발송자 설정
			Address fromAddr = new InternetAddress(user, "KH"); 
			msg.setFrom(fromAddr);
			
			// 수신자 설정
			Address toAddr = new InternetAddress(toEmail); 
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			// 메일 내용
			String message = content;
			msg.setContent(message, "text/html;charset=KSC5601");
	
			Transport.send(msg);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static void sendResume(int rid, RecruitMember rm, Attachment at) {
		p = getSetting();
		
		// Recruitment 정보 가져오기
		Recruitment r = new RecruitService().selectRecruitment(rid);
		
		try {
			
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(p, auth);

			session.setDebug(true);
			
			MimeMessage msg = new MimeMessage(session);

			String title = String.format("[%s][%s] %s님 이력 지원서", r.getTitle(), r.getCode(), rm.getName());
			
			// 메일 제목
			msg.setSubject(title);
			
			// 발송자 설정
			Address fromAddr = new InternetAddress(user, "KH"); 
			msg.setFrom(fromAddr);
			
			// 수신자 설정
			Address toAddr = new InternetAddress(rm.getEmail()); 
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			// 메일 내용
			String message = "";

			// 이미지 처리 일단 기본 이미지로 사이트 대표 이미지 추가 구할 필요 있음
			// 가능하면 온라인 이미지로 메일로 첨부없이 보내야하니까 
			String imageSrc = "https://cdn.wadiz.kr/ft/images/green001/2017/0710/20170710104015666_101.jpg/wadiz/format/jpg/quality/80/optimize";
			
			// 이메일로 보낼 html 파일 읽어오기
			try {
				byte[] bytes = Files.readAllBytes(Paths.get(pagePath));
				
				message = new String(bytes).replace("{name}", rm.getName())           
				                           .replace("{title}", r.getTitle())       
				                           .replace("{phone}", rm.getPhone())
						                   .replace("{email}", rm.getEmail())     
						                   .replace("{education}", rm.getEducation())       
						                   .replace("{career}", rm.getCareer())
						                   .replace("{image}", imageSrc); // 일단 기본 이미지 처리
				
//				System.out.println(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			// 본문 
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(message, "text/html; charset=utf-8");
			
			// 파일 첨부
			MimeBodyPart mbp2 = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(at.getFilePath() + at.getChangeName());
			
			mbp2.setDataHandler(new DataHandler(fds));
			
			mbp2.setFileName(at.getOriginName());
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			
			msg.setHeader("Content-type", "text/html; charset=UTF-8");
			msg.setContent(mp, "text/html; charset=UTF-8");
	
			
			
			Transport.send(msg);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
