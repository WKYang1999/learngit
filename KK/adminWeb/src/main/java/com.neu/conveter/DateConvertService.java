package com.neu.conveter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConvertService implements Converter<String,Date> {

	@Override
	public Date convert(String source) {
		 
		if(source!=null && !"".equals(source.trim())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return simpleDateFormat.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
