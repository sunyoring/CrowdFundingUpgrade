package com.kh.user.util;

import java.util.Random;

public class GenerateCertNumber {
// 숫자 인증번호 생성
	private int certNumLength = 6;

	public String excuteGenerate() {
		Random random = new Random(System.currentTimeMillis());

		int range = (int) Math.pow(10, certNumLength);
		int trim = (int) Math.pow(10, certNumLength - 1);
		int result = random.nextInt(range) + trim;

		if (result > range) {
			result = result - trim;
		}

		return String.valueOf(result);
	}

	public int getCertNumLength() {
		return certNumLength;
	}

	public void setCertNumLength(int certNumLength) {
		this.certNumLength = certNumLength;
	}

}
