package com.ghosta3.ocr;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class IFXPhoneVerCodeGetter {
	private static IFXPhoneVerCodeGetter instance;
	private static SqlSessionFactory mSqlSessionFactory;

	private IFXPhoneVerCodeGetter() {

	}

	public static IFXPhoneVerCodeGetter getInstance() {
		if (instance == null) {
			instance = new IFXPhoneVerCodeGetter();
			String resource = "com/ghosta3/ocr/mybatis-config.xml";
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}

			mSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}

		return instance;
	}
	
	public SqlSession getSession() {
		return mSqlSessionFactory.openSession();
	}

	public String getPhoneVerCode(String phoneNum) {
		
		SqlSession session = getSession();
		Pattern p = Pattern.compile("\\d{4}");
		try {
			SMS sms = (SMS) session.selectOne("com.ghosta3.ocr.SMS.selectSMS", phoneNum);
			Matcher m = p.matcher(sms.getMessage());
			if (m.find()) {
				return m.group();
			}
		} finally {
			session.close();
		}
		return null;
	}
}
