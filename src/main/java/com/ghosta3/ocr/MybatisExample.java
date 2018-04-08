package com.ghosta3.ocr;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisExample {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IFXPhoneVerCodeGetter ifx = IFXPhoneVerCodeGetter.getInstance();
		System.out.println(ifx.getPhoneVerCode("13523423433"));
	}

}
