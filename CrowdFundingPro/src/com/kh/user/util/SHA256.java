package com.kh.user.util;

import java.security.MessageDigest;


public class SHA256 {

	// 기존에 존재하는 이메일 값 앞에 해쉬코드를 적용하여 인증코드를 생성

	public static String getSHA256(String email) { //전달받은 이메일 값에 해쉬를 적용해 반환한다.
		StringBuffer result = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();  //악의적인 공격자의 공격으로부터 방어하는 역할을 한다.
			
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(email.getBytes("UTF-8"));
			for(int i =0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
		} catch (Exception e) {
		}

		return result.toString();

	}
}