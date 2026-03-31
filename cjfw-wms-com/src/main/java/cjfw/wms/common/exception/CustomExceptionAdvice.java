package cjfw.wms.common.exception;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.reflection.ReflectionException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UnAuthorizedException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.web.exception.ExceptionAdvice;
import cjfw.core.web.exception.ValidFailMessage;
import cjfw.wms.common.exception.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDatabaseException;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.30 
 * @description : Exception 기능을 구현한 @RestControllerAdvice Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.31 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class CustomExceptionAdvice extends ExceptionAdvice {
	private final CommonDao commonDao;

	public CustomExceptionAdvice(CommonDao commonDao) {
		super(commonDao);
		this.commonDao = null;
	}

    /** 파라미터 정보가 잘못되어 처리할 수 없습니다. */
    private final static String MESSEGGE_PARAMETOR_DEFAULT_ERROR = "파라미터 정보가 잘못되어 처리할 수 없습니다.";
    /** 파라미터 정보에 Null 정보가 존재하여 처리할 수 없습니다.. */
    private final static String MESSEGGE_PARAMETOR_NULL_ERROR = "파라미터 정보에 Null 정보가 존재하여 처리할 수 없습니다.";
    /** 숫자형에 문자형 데이터를 사용할 수 없습니다. */
    private final static String MESSEGGE_PARAMETOR_FORMAT_ERROR = "숫자형에 문자형 데이터를 사용할 수 없습니다.";    
    /** 요청정보가 잘못되어 처리할 수 없습니다. */
    private final static String MESSEGGE_PARAMETOR_INVALID_ERROR = "요청정보가 잘못되어 처리할 수 없습니다.";
    /** 요청한 정보가 처리되지 않았습니다. */
    private final static String MESSEGGE_EXCUTE_DETAUL_ERROR = "요청한 정보가 처리되지 않았습니다.";

    /** DBMS 문법오류가 발생하였습니다. */
    private final static String MESSEGGE_DBMS_DEFAULT_ERROR = "DBMS 문법오류가 발생하였습니다.";
    ///** 무결성 제약 조건에 위배되는 정보가 있어 처리할 수 없습니다. */
    private final static String MESSEGGE_DBMS_VIOLATION_ERROR = "무결성 제약 조건에 위배되는 정보가 있어 처리할 수 없습니다.";
    /** IO 오류:현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다 */
    private final static String MESSEGGE_DBMS_IO_ERROR = "IO 오류:현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다";
    /** The Network Adapter could not establish the connection */
    private final static String MESSEGGE_DBMS_NETWORK_ERROR = "The Network Adapter could not establish the connection";
    /** 실행할 질의문은 비어 있거나 널일 수 없습니다. */
    private final static String MESSEGGE_DBMS_EXECUTING_ERROR = "실행할 질의문은 비어 있거나 널일 수 없습니다.";
    /** DBMS 질의 시 정상적으로 처리되지 않았습니다. */
    private final static String MESSEGGE_DBMS_INVALID_ERROR = "DBMS 질의 시 정상적으로 처리되지 않았습니다.";
    /** 입력된 값의 길이가 날짜 형식에 비해 부족합니다. */
    private final static String MESSEGGE_DBMS_DATE_ERROR = "입력된 값의 길이가 날짜 형식에 비해 부족합니다.";
    /** 날짜 형변환이 명확히 처리되지 않았습니다.(literal does not match format string)  */
    private final static String MESSEGGE_DBMS_DATE2_ERROR = "날짜 형변환이 명확히 처리되지 않았습니다.(literal does not match format string) ";
    /** 중복된 정보가 존재합니다. */
    private final static String MESSEGGE_DBMS_DULICATION_ERROR = "중복된 정보가 존재합니다.";    
    /** 자원이 사용 중이고, nowait가 지정되어 있습니다.  */
    private final static String MESSEGGE_DBMS_NOWAIT_ERROR = "자원이 사용 중이고, nowait가 지정되어 있습니다.";
    /** 데이터 형에 지정된 길이거 너무 깁니다.  */
    private final static String MESSEGGE_DBMS_TOOLONG_ERROR = "데이터 형에 지정된 길이거 너무 깁니다.";
    /** 조회한 데이터가 없습니다. */
    private final static String MESSEGGE_DBMS_NODATA_ERROR = "조회한 데이터가 없습니다.";
    /** 행의 입력으로 필수 열(NOT NULL)에 값이 지정되지 않았습니다. */
    private final static String MESSEGGE_DBMS_ESSENTIAL_ERROR = "행의 입력으로 필수 열(NOT NULL)에 값이 지정되지 않았습니다.";
    /** 제수가 0 입니다. */
    private final static String MESSEGGE_DBMS_ZERO_ERROR = "제수가 0 입니다.";
    
    /**
     * 
     * @description : systemException 기능을 구현한 Method
     * 				  SystemException, 기타 RuntimeException
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     * 2025.05.30        sangsu.sung        WMS에 맞게 SystemException @Override 처리
     */
    @Override
    @ExceptionHandler({SystemException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<Object>  systemException(HttpServletRequest request, Exception e) {
        return exception(request, e);
    }    
    
    /**
     * 
     * @description : methodArgumentNotValidException 기능을 구현한 Method
     * 			  	  400 - Valid 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     * 2025.09.17        sangsu.sung        WMS에 맞게 메세지 커스트마이징 </pre>
     */
    @Override
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult methodArgumentNotValidException(HttpServletRequest request, BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ValidFailMessage> validFailMessages = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ValidFailMessage validFailMessage = ValidFailMessage.builder()
                    .rejectField(fieldError.getField())
                    .rejectValue(fieldError.getRejectedValue())
                    .message(fieldError.getDefaultMessage())
                    .build();
            validFailMessages.add(validFailMessage);
        }
        return ApiResult.createResult(validFailMessages, MESSEGGE_PARAMETOR_INVALID_ERROR, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     *
     * @description : missingServletRequestPartException 기능을 구현한 Method
     * 			  	  400 - 필수 Multipart 파라미터(파일 등) 누락 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.19        han@wemeetmobility.com 생성
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult missingServletRequestPartException
	(HttpServletRequest request, MissingServletRequestPartException e) {
        log.warn("Multipart 요청에서 필수 파라미터 누락 - RequestPart: '{}', ContentType: {}, URI: {}",
                e.getRequestPartName(),
                request.getContentType(),
                request.getRequestURI());
        String errorMessage = String.format("필수 Multipart 파라미터 '%s'가(이) 누락되었습니다.", e.getRequestPartName());
        return ApiResult.createResult(errorMessage, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     *
     * @description : missingServletRequestParameterException 기능을 구현한 Method
     * 			  	  400 - 필수 Request 파라미터 누락 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.19        han@wemeetmobility.com 생성
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult missingServletRequestParameterException
	(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.warn("필수 Request 파라미터 누락 - Parameter: '{}', Type: {}, URI: {}",
                e.getParameterName(),
                e.getParameterType(),
                request.getRequestURI());
        String errorMessage = String.format("필수 파라미터 '%s'가(이) 누락되었습니다.", e.getParameterName());
        return ApiResult.createResult(errorMessage, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * 
     * @description :ExceptionAdvice을 상속받아 exception 를 처리하는 Method
     * @issues      :<pre>
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     * 2025.05.30        sangsu.sung        WMS에 맞게 커스트마이징 </pre>
     */
    @Override
    @SuppressWarnings("unchecked")
	@ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<Object> exception(HttpServletRequest request, Exception e) {
    	ExceptionDto exceptionDto = new ExceptionDto();
    	
    	/*
    	ERROR_CODE_USEREXCEP              = -1;    // 처리 결과코드 - UserHandleException
    	ERROR_CODE_SYSEXCEP              = -1000;  // 처리 결과코드 - SystemException
    	ERROR_CODE_ACCESSTOKEN_EXPIRED   = -1001;  // 처리 결과코드 - AccessToken 토큰 만료
    	ERROR_CODE_REFRESHTOKEN_NOTVALID = -1002;  // 처리 결과코드 - RefreshToken 비정상
    	*/
    	/** 상태코드 */
    	int status = CanalFrameConstants.ERROR_CODE_USEREXCEP;
    	/** 상태메세지 */
    	String statusMessage = e.getMessage();
    	/** stack메세지 */
    	String stackMsg      = e.getMessage();
    	exceptionDto.setErrorCode(CanalFrameConstants.ERROR_CODE_ACCESSTOKEN_EXPIRED);
    	
    	log.error("ExceptionAdvice : ", e);
        
        if (e instanceof MethodArgumentNotValidException || e instanceof BindException) {
        	return methodArgumentNotValidException(request,(MethodArgumentNotValidException)e);
        } else if (e instanceof UnAuthorizedException) {
        	return unAuthorizedException(request,(UnAuthorizedException)e);
        } else if (e instanceof NoHandlerFoundException) {
        	return noHandlerFoundException(request,(NoHandlerFoundException)e);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
        	return httpRequestMethodNotSupportedException(request,(HttpRequestMethodNotSupportedException)e);
		} 
        // START.Custom.Exception
        else if (e instanceof UserHandleException) {
        	log.error("case :: UserHandleException->{}",getStackMessage(e));
		} else if (e instanceof NullPointerException) {
			log.error("case :: NullPointerException->{}",getStackMessage(e));
			statusMessage = MESSEGGE_PARAMETOR_NULL_ERROR;
		} else if (e.getCause() instanceof SQLRecoverableException) { // 연결host 오류
			log.error("case :: SQLRecoverableException->{}",getStackMessage(e));
			statusMessage = MESSEGGE_DBMS_IO_ERROR;
		}
        else if (e instanceof SystemException) {
        	UncategorizedSQLException ex7 = null; // Spring Exception
        	
        	statusMessage = MESSEGGE_EXCUTE_DETAUL_ERROR;
        	exceptionDto.setIsLogWrite(true);
        	
        	if (e.getCause() instanceof SystemException) { // Service->Service call 시 exception 
        	    SystemException ex1 = (SystemException) e.getCause();
        	    if (ex1.getCause() instanceof UncategorizedSQLException) {
        	    	ex7 = (UncategorizedSQLException)  ex1.getCause();
        	    } else if (ex1.getMessage().contains("ORA-")) {
        	    	// 오라클 오류 문구 노출용 문구로 변경
        	    	Matcher matcher = Pattern.compile("\\d+").matcher(ex1.getMessage());
        	    	if (matcher.find()) {
        	            String firstNumber = matcher.group(); // 첫 번째 숫자 추출
        	            statusMessage = getStatusMessage(Integer.parseInt(firstNumber), ex1.getMessage());
        	        }
        	    }
        	} else if (e.getCause() instanceof DuplicateKeyException) { // 중복 키 오류        		
        		log.error("case :: DuplicateKeyException->"+e.getCause());
				statusMessage = MESSEGGE_DBMS_DULICATION_ERROR;
        	} else if (e.getCause() instanceof UncategorizedSQLException) { // dao call 시 exception 
				log.error("case :: UncategorizedSQLException1->"+e.getCause());
				ex7 = (UncategorizedSQLException)  e.getCause();
        	} else if (e.getCause() instanceof BadSqlGrammarException) { // dao call 시 exception 
				log.error("case :: BadSqlGrammarException->"+e.getCause());
				statusMessage = MESSEGGE_DBMS_DEFAULT_ERROR;
        	} else if (e.getCause() instanceof MyBatisSystemException) {
				log.error("case :: MyBatisSystemException1 ");
				MyBatisSystemException ex = (MyBatisSystemException) e.getCause();
				 
				if (ex.getCause() instanceof ReflectionException) {
					log.info("case :: ReflectionException");
					statusMessage = MESSEGGE_PARAMETOR_DEFAULT_ERROR;
				} else if (ex.getCause() instanceof PersistenceException) {
					log.info("case :: PersistenceException");
					PersistenceException ex2 = (PersistenceException) ex.getCause();
					statusMessage = MESSEGGE_PARAMETOR_DEFAULT_ERROR;
					
					if (ex2.getCause() instanceof NumberFormatException) {
						log.info("case :: NumberFormatException");
						statusMessage = MESSEGGE_PARAMETOR_FORMAT_ERROR;
					}
					
				}
			}   
        	
			if (ex7 != null && ex7.getCause() instanceof SQLException) {
				SQLException ex = (SQLException)  ex7.getCause(); // service -> service
				
				if (ex.getCause() instanceof SQLSyntaxErrorException) {
					log.error("case :: SQLSyntaxErrorException->{}",getStackMessage(ex));
					statusMessage = MESSEGGE_DBMS_DEFAULT_ERROR;
				} else if (ex.getCause() instanceof OracleDatabaseException) {
					// 오라클 오류 대응
					log.error("case :: OracleDatabaseException1 ");
					OracleDatabaseException oraEx = (OracleDatabaseException) ex.getCause();
					statusMessage = getStatusMessage(oraEx.getOracleErrorNumber(), oraEx.getMessage());
				}
				// END.Custom.Exception
			}
		} else {
			status = CanalFrameConstants.ERROR_CODE_SYSEXCEP;
			statusMessage = MESSEGGE_EXCUTE_DETAUL_ERROR; // 요청한 정보가 처리되지 않았습니다.
		}
        
        exceptionDto.setErrorCode(status);      // 사용자 Exception -> -1
        exceptionDto.setErrorMsg(statusMessage);
        exceptionDto.setStackMsg(stackMsg);
        log.error("▶▶▶▶message->{}",statusMessage);
        log.error("▶▶▶▶exceptionDto->{}",exceptionDto.toString());
        
       	
        // 로그기록
        if(exceptionDto.getIsLogWrite())  {
        	super.insertExceptionLog(request, e);
        }
        exceptionDto.setStackMsg("");
        
        return ApiResult.createResult(exceptionDto,statusMessage, status);
    }
    
    /**
	 * 오라클 오류 문구 변경
	 * @param errorNumber 오류 번호
	 * @param message 오류 메세지
	 * @return String 노출시킬 메세지
	 */
    public String getStatusMessage(int errorNumber, String message) {
    	String statusMessage = MESSEGGE_DBMS_INVALID_ERROR; // DBMS 질의 시 정상적으로 처리되지 않았습니다.
    	
    	switch (errorNumber) {
			case 1: // 유일성제약조건 
				statusMessage = MESSEGGE_DBMS_DULICATION_ERROR;
				break;
			case 54: // 자원이 사용 중이고, nowait가 지정되어 있습니다. 
			case 30006: // resource busy : accuire with WAIT timeout expired 
				statusMessage = MESSEGGE_DBMS_NOWAIT_ERROR;
				break;
			case 910: // 데이터 형에 지정된 길이거 너무 깁니다.
			case 1438: // 데이터 형에 지정된 길이거 너무 깁니다. 
				statusMessage = MESSEGGE_DBMS_TOOLONG_ERROR;
				break;
			case 1400: // 행의 입력으로 필수 열(NOT NULL)에 값이 지정되지 않았습니다.
			case 1407: // 입력 필수 열(NOT NULL)은 NULL로 갱신할 수 없습니다.
				statusMessage = MESSEGGE_DBMS_ESSENTIAL_ERROR;
				break;
			case 1403: // 데이터가 없습니다.
				statusMessage = MESSEGGE_DBMS_NODATA_ERROR;
				break;
			case 1476: // 제수가 0 입니다. (S0027)
				statusMessage = MESSEGGE_DBMS_ZERO_ERROR;
				break;
			case 1841: // 년은 영이 아닌 -4713 과 +4713 사이의 값으로 지정해야 합니다
				statusMessage =  "[01841]"+MESSEGGE_DBMS_INVALID_ERROR; // DBMS 질의 시 정상적으로 처리되지 않았습니다.
				break;
			case 17104: // 17104:실행할 SQL 문은 비어 있거나 널일 수 없음
				statusMessage = "[17104]"+MESSEGGE_DBMS_EXECUTING_ERROR;
				break;
			case 1840: // ORA-01840: 입력된 값의 길이가 날짜 형식에 비해 부족합니다
				statusMessage = "[01840]"+MESSEGGE_DBMS_DATE_ERROR;
				break;
			case 1861: // ORA-01861: literal does not match format string
				statusMessage = "[01861]"+MESSEGGE_DBMS_DATE2_ERROR;
				break;
			case 20923: // ORA-20923: PL/SQL 개발 시 사용자 발생 오류(To-be 요건)
				String tempStr = message.replace("ORA-20923: ", "").trim();
				String[] tempStrArr = tempStr.split("\n");
				String msg20923Code = tempStrArr[0];
				String[] msgAparam = msg20923Code.split("\\$");
				String msg20923Nm   = msgAparam[0];
	
				if(msgAparam.length > 1) {
					for(int i = 1 ; i < msgAparam.length ; i++) {
						msg20923Nm = msg20923Nm.replaceAll("\\{"+(i-1)+"\\}", msgAparam[i]);
					}
				}
	
				statusMessage = msg20923Nm;
				break;
			case 20001: // ORA-20001: PL/SQL 사용자 발생 오류
				String tempStr1 = message.replace("ORA-20001: ", "").trim();
				String[] tempStrArr1 = tempStr1.split("\n");
				String msg200001Code = tempStrArr1[0];
				String[] msgAparam1 = msg200001Code.split("\\$");
				String msg20001Nm   = msgAparam1[0];
	
				if(msgAparam1.length > 1) {
					for(int i = 1 ; i < msgAparam1.length ; i++) {
						msg20001Nm = msg20001Nm.replaceAll("\\{"+(i-1)+"\\}", msgAparam1[i]);
					}
				}
				statusMessage = msg20001Nm;	
				
				break;
			default:
				statusMessage = MESSEGGE_DBMS_INVALID_ERROR; // DBMS 질의 시 정상적으로 처리되지 않았습니다.
				break;
		}
    	
    	return statusMessage;
    }



	/**
	 * Stack 메세지를 조회한다.
	 * @param e
	 * @return String
	 */
	public String getStackMessage(Exception e) {
		String msg = "";
		try {
			if (e.getCause() != null) {
				msg = e.getCause().toString();
			} else {
				msg = e.toString();
			}
		} catch(Exception e11) {
			log.error("{}",e);
		}

		if(msg.indexOf("ORA-") > -1) {
			msg = msg.replace("ORA-", "");
		}

		return msg;
	}    
}
