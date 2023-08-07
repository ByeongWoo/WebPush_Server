package com.web.push.api;


/**
 * @author
 * 
 * 결과 코드가 정의 되어 있는 Enum Class
 *
 */
public enum ResultCode {

	SUCCESS("0", "성공", "성공"), 
	
	SUCCESS_FAIL("-1", "실패", "실패"), 

	G_UNKNOWN_EXCEPTION("G01", "예상치 못한 오류", "예상치 못한 오류"),
	
	G_GATE_REQUIRED_PARAMS("G02", "파라미터 오류", "파라미터 오류"),
	
	G_DB_EXCEPTION("G03", "데이터베이스 작업 오류", "데이터베이스 작업 오류"),
	
	G_ACCESS_DENIED("E01", "접근권한없음", "접근권한없음"),	
	
	G_TOKEN_TIMEOUT("E02", "토큰값만료", "토큰값만료"),
	
	G_GATE_TOKEN_EMPTY("E03", "토큰값오류", "토큰값오류"),
	
	U_USER_PROFILE_VALUE_ERROR("E04", "프로필값오류", "프로필값오류"),
	
	U_USER_NOT_FOUND("U01", "이용자 찾을 수 없음", "이용자 찾을 수 없음"),
	
	U_USER_EXIST("U02", "사원번호존재", "사원번호존재"),
	
	U_USER_PWD_DISCREPANCY("U03", "비밀번호불일치", "비밀번호불일치"),
	
	U_USER_NOT_AUTH("U04", "인증실패", "인증실패"),
	
	P_USER_FILE_NOT_MAKE("P02", "파일등록실패", "파일등록실패"),
	
	P_USER_FILE_NOT_ALLOW("P03", "파일타입오류", "파일타입오류"),
	
	P_USER_FILE_NOT_FOUND("P04", "파일없음", "파일없음"),
	
	P_PROFILE_NOT_FOUND("P01", "프로필없음", "프로필없음");
	
	/**
	 * 결과 코드
	 */
	private String code;
	
	/**
	 * 사용자에게 보여질 메세지
	 */
	private String displayMsg;
	
	/**
	 * 오류 메세지
	 */
	private String serverMsg;
	
	ResultCode(final String code, final String serverMsg, final String displayMsg) {
		this.code = code;
		this.serverMsg = serverMsg;
		this.displayMsg = displayMsg;
	}

	/**
	 * 결과 코드를 가져온다.
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 결과 코드를 설정
	 * 
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * 사용자에게 보여질 오류 메세지를 가져온다.
	 * 
	 * @return
	 */
	public String getDisplayMsg() {
		return displayMsg;
	}

	/**
	 * 사용자에게 보여질 오류 메세지를 설정한다.
	 * 
	 * @param displayMsg
	 */
	public void setDisplayMsg(final String displayMsg) {
		this.displayMsg = displayMsg;
	}

	/**
	 * 서버 오류 메세지를 가져온다.
	 * 
	 * @return
	 */
	public String getServerMsg() {
		return serverMsg;
	}

	/**
	 * @param serverMsg
	 * 서버 오류 메세지를 설정한다.
	 * rverMsg
	 */
	public void setServerMsg(final String serverMsg) {
		this.serverMsg = serverMsg;
	}
}
