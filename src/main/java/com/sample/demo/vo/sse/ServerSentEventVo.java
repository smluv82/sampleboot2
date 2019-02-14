package com.sample.demo.vo.sse;

import java.util.Date;

import lombok.Data;

@Data
public class ServerSentEventVo {
	private String randomStr;
	private String randomNum;
	private String randomStrNum;
	private Date date;
}
