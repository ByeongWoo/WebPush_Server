package com.web.push.api;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author
 *
 * API 결과 데이터 중 공통으로 들어갈 결과 데이터를 정의한 Value Object
 */
@Getter
@Setter
@Builder
public class BaseResult {

	/**
	 * 결과 코드
	 */
	private LocalDateTime callTime;
	private String resultCode;

	/**
	 * 결과 메세지
	 */
	private String resultMsg;
	
	/**
	 * 빈 생성자
	 */
	public BaseResult() {
		super();
	}
	
	/**
	 * Result Code를 각 필드에 초기화 한다. 
	 * 
	 * @param resultCode ResultCode Enum class
	 */
	public BaseResult(LocalDateTime callTime, final ResultCode resultCode) {
		this.callTime = callTime;
		this.resultCode = resultCode.getCode();
		this.resultMsg = resultCode.getDisplayMsg();
	}

	/**
	 * 코드와 메세지를 각각 받아서 초기 데이터 설정
	 *
	 * @param resultCode
	 * @param resultMsg
	 */
	public BaseResult(LocalDateTime callTime, final String resultCode, final String resultMsg) {
		this.callTime = callTime;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

}
