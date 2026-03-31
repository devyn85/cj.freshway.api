package cjfw.wms.common.util;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.UncategorizedSQLException;

import cjfw.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDatabaseException;

/**
 * The class EtcUtil
 * 
 * EtcUtil 클래스<br>
 * 
 *
 * @author 공두경
 * @version 1.0
 * @since 2025. 7. 17.
 *
 */
@Slf4j
public class EtcUtil  {
	private EtcUtil() {super();} //private 생성자
	
	
    /** 요청한 정보가 처리되지 않았습니다. */
    private final static String MESSEGGE_EXCUTE_DETAUL_ERROR = "요청한 정보가 처리되지 않았습니다.";	
	
	/**
	 * columnsDto의 컬럼과 1:1 맵핑되는 columnsEntity 컬럼에 값을 set
	 * 
	 * @param dto
	 * @param entity
	 * @param columnsDto
	 * @param columnsEntity
	 * @return entity
	 */	
	public static <T> T conversionEntity(T dto, T entity, String[] columnsDto, String[] columnsEntity) {
	    try {
	        Class<?> clsDto = dto.getClass();
	        Class<?> clsEntity = entity.getClass();
        	for (int i = 0; i < columnsDto.length; i++) {
        	    String columnDtoName = columnsDto[i];	
        	    String columnEntityName = columnsEntity[i];
	            try {
	                java.lang.reflect.Field dtoField = clsDto.getDeclaredField(columnDtoName);
	                dtoField.setAccessible(true);
	                Object value = dtoField.get(dto);
	                if (value != null && !"".equals(value.toString())) {
	                    try {
	                    	java.lang.reflect.Field entityField = clsEntity.getDeclaredField(columnEntityName);
	                    	entityField.setAccessible(true);
	                    	entityField.set(entity, value);	                        
	                    } catch (NoSuchFieldException | IllegalAccessException e) {
	                    	log.error("Reflection error on field: {}", columnDtoName, e);
	                    }
	                }
	            } catch (NoSuchFieldException | IllegalAccessException e) {
	            	log.error("Reflection error on field: " + columnDtoName, e);
	            }
	        }
        	log.info("entity->{}",MaskingUtil.maskLog(entity.toString()));
	    } catch (Exception e) {
	    	log.error("Unexpected exception during conversion", e);
	        e.printStackTrace();
	    }

	    return entity;
	}
	
	/**
	 * columnsDto의 컬럼과 1:1 맵핑되는 columnsEntity 컬럼에 값을 set
	 * ASIS에서 사용된 방식을 변환하기 위함.(예 : CCODE|STORERKEY|SLIPDT 형태)
	 * @param dto
	 * @param entity
	 * @param columnsDto
	 * @param columnsEntity
	 * @return entity
	 */	
	public static <T> T conversionEntityToAsis(T dto, T entity, String colsDto, String colsEntity) {
		try {
			Class<?> clsDto = dto.getClass();
			Class<?> clsEntity = entity.getClass();
			
			String[] columnsDto = parseAndConvertCamel(colsDto, "|");
			String[] columnsEntity = parseAndConvertCamel(colsEntity, "|");
			
			for (int i = 0; i < columnsDto.length; i++) {
				String columnDtoName = columnsDto[i];	
				String columnEntityName = columnsEntity[i];
				try {
					java.lang.reflect.Field dtoField = clsDto.getDeclaredField(columnDtoName);
					dtoField.setAccessible(true);
					Object value = dtoField.get(dto);
					if (value != null && !"".equals(value.toString())) {
						try {
							java.lang.reflect.Field entityField = clsEntity.getDeclaredField(columnEntityName);
							entityField.setAccessible(true);
							entityField.set(entity, value);	                        
						} catch (NoSuchFieldException | IllegalAccessException e) {
							log.error("Reflection error on field: {}", columnDtoName, e);
						}
					}
				} catch (NoSuchFieldException | IllegalAccessException e) {
					log.error("Reflection error on field: " + columnDtoName, e);
				}
			}
		} catch (Exception e) {
			log.error("Unexpected exception during conversion", e);
		}
		
		return entity;
	}
	
	/**
	 * columnsDto의 컬럼을 delimiter로 분리하여 String[]로 변환
	 * '_'가 포함된 단어는 카멜케이스로 변환
	 * '_'가 포함되지 않은 단어는 전부 소문자로 변환
	 * 
	 * @param columnsDto
	 * @param delimiter
	 * @return String[]
	 */
	public static String[] parseAndConvertCamel(String columnsDto, String delimiter) {
	    if (columnsDto == null || columnsDto.isEmpty()) {
	        return new String[0];
	    }

	    String[] parts = columnsDto.split("\\Q" + delimiter + "\\E");
	    String[] result = new String[parts.length];

	    for (int i = 0; i < parts.length; i++) {
	        String word = parts[i].trim(); // Trim whitespace
	        if (word.contains("_")) {
	            result[i] = toCamelCase(word);
	        } else {
	            result[i] = word.toLowerCase();
	        }
	    }
	    return result;
	}


	// 
	
	/**
	 * '_' 포함된 문자열을 카멜케이스로 변환
	 * 
	 * @param str the input string (e.g., "example_string")
	 * @return the camelCase version of the string (e.g., "exampleString")
	 */
	private static String toCamelCase(String str) {
	    String[] tokens = str.toLowerCase().split("_");
	    StringBuilder sb = new StringBuilder(tokens[0]);
	    for (int i = 1; i < tokens.length; i++) {
	        sb.append(tokens[i].substring(0, 1).toUpperCase())
	          .append(tokens[i].substring(1));
	    }
	    return sb.toString();
	}


	/**
	 * 사용된 방식의 메시지에서 ORA-20001: 제거 후 메시지 반환
	 *   ->CustomExceptionAdvice.java 
	 * 
	 * @param message
	 * @return
	 */
	public static String getMessage(Exception e) {
		String statusMessage = MESSEGGE_EXCUTE_DETAUL_ERROR; // 요청한 정보가 처리되지 않았습니다.
		
		// START.Custom.Exception
		if (e instanceof SystemException) {
        	UncategorizedSQLException ex7 = null; // Spring Exception
        	
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
        	} else if (e.getCause() instanceof UncategorizedSQLException) { // dao call 시 exception 
				log.error("case :: UncategorizedSQLException1->"+e.getCause());
				ex7 = (UncategorizedSQLException)  e.getCause();
        	} 
        	
			if (ex7 != null && ex7.getCause() instanceof SQLException) {
				SQLException ex = (SQLException)  ex7.getCause(); // service -> service
				
				if (ex.getCause() instanceof OracleDatabaseException) {
					// 오라클 오류 대응
					log.error("case :: OracleDatabaseException1 ");
					OracleDatabaseException oraEx = (OracleDatabaseException) ex.getCause();
					statusMessage = getStatusMessage(oraEx.getOracleErrorNumber(), oraEx.getMessage());
				}
				// END.Custom.Exception
			}
		}
		// END.Custom.Exception
		
		return statusMessage;
	}
	
    /**
	 * 오라클 오류 문구 변경
	 * @param errorNumber 오류 번호
	 * @param message 오류 메세지
	 * @return String 노출시킬 메세지
	 */
    public static String getStatusMessage(int errorNumber, String message) {
    	String statusMessage = MESSEGGE_EXCUTE_DETAUL_ERROR; // 요청한 정보가 처리되지 않았습니다.
    	
    	switch (errorNumber) {
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
				statusMessage = MESSEGGE_EXCUTE_DETAUL_ERROR; // 요청한 정보가 처리되지 않았습니다.
				break;
		}
    	
    	return statusMessage;
    }

	/**
	 * 파일 경로의 위변조 문자를 치환한다.
	 * 
	 * @param exception
	 * @return
	 */
    public static String setFilePathFilter(String mySrcFile) {
        if (mySrcFile == null || mySrcFile.trim().isEmpty()) {
            return "";
        }

        String srcFile = cjfw.core.utility.StringUtil.nvl(mySrcFile, "");

        try {
            // S5361 준수: 정규식이 필요 없는 단순 문자 제거는 replace() 사용
            // 역슬래시(\)는 문자열 리터럴에서 "\\"로 표현해야 합니다.
            srcFile = srcFile.replace("\\", "");
            
            // 정규식에서 "."은 '모든 문자'를 뜻하므로, 
            // 점(dot) 자체를 지우려면 replace(".")를 써야 안전합니다.
            srcFile = srcFile.replace(".", "");
            srcFile = srcFile.replace("&", "");
            
            srcFile = srcFile.replace("/","");
            srcFile = srcFile.replace("\\","");
            srcFile = srcFile.replace(".","");
            srcFile = srcFile.replace("&","");

            // Path Traversal (../ , ..\) 대응
            // 반복적인 replace를 통해 중첩된 패턴(....//)까지 제거합니다.
            while (srcFile.contains("../") || srcFile.contains("..\\")) {
                srcFile = srcFile.replace("../", "").replace("..\\", "");
            }
        } catch (Exception e) {
            // 예외 발생 시 원본을 반환하거나 안전한 기본값을 반환
            return mySrcFile;
        }
        
        return srcFile;
    }
	
	
}