package cjfw.core.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.penta.scpdb.ScpDbAgent;
import com.penta.scpdb.ScpDbAgentException;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.07.04 
 * @description : 다모암호화 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Slf4j
public class DamoScpDbUtil {
	
	private static final String OPERATION_MODE = ContextUtil.getProperty("spring.config.activate.on-profile", "local");
	private static final String CONFIGNAME ="scpdbLicensePath."+OPERATION_MODE;
	private static final String INIFILEPATH = ContextUtil.getProperty(CONFIGNAME);//"C:\\LOGISONE\\scp\\conf\\scpdb_agent.ini"; //scpdb_agent.ini fullpath
	private static final Log LOG = LogFactory.getLog(DamoScpDbUtil.class);
	private static final String DAMO_KEY_NAME ="KEY1";
	private static final String DEFAULT_LOCALE ="EUC-KR";
	private DamoScpDbUtil() {
		
		
	}
	/**damoScpDecB64 DAMO B64복호화 함수 
	 * @param dataset
	 * @param columns
	 * @return FrameOneDataset dataset
	 */
	public static <T> T damoScpDecB64(T dto, String[] columns) {
		log.info("OPERATION_MODE->{}",OPERATION_MODE);
		
	    if ("local".equals(OPERATION_MODE) && "".equals(cjfw.core.utility.StringUtil.nvl(INIFILEPATH))) {
	        return dto;
	    } else {
		    try {
		        ScpDbAgent agt = new ScpDbAgent();
		        Class<?> clazz = dto.getClass();
		        for (String column : columns) {
		            try {
		                java.lang.reflect.Field field = clazz.getDeclaredField(column);
		                field.setAccessible(true);
		                Object value = field.get(dto);
		                if (value != null && !"".equals(value.toString())) {
		                    String decrypted;
		                    try {
		                        decrypted = agt.ScpDecB64(INIFILEPATH, DAMO_KEY_NAME, value.toString(), DEFAULT_LOCALE);
		                    } catch (ScpDbAgentException e1) {
		                        LOG.error("Exception", e1);
		                        LOG.error(column + ":" + e1.toString());
		                        decrypted = value.toString();
		                    } catch (Exception e) {
		                        LOG.error("Exception", e);
		                        decrypted = value.toString();
		                    }
		                    field.set(dto, decrypted);
		                }
		            } catch (NoSuchFieldException | IllegalAccessException e) {
		                LOG.error("Reflection error on field: " + column, e);
		            }
		        }
		    } catch (Exception e) {
		        LOG.error("Exception", e);
		        e.printStackTrace();
		    }
	    }

	    return dto;
	}
	

	///**damoScpDecB64 DAMO B64복호화 함수 
	// * @param dataset
	// * @param columns
	// * @return FrameOneDataset dataset
	// */
	//public static FrameOneDataset damoScpDecB64(FrameOneDataset dataset,String[] columns)  { 
	//	//개발모드시 사용가능 
	//	if("WKR".equals(OPERATION_MODE) && "".equals(INIFILEPATH)){
	//		return dataset;
	//	}
	//	try {
	//			 ScpDbAgent agt = new ScpDbAgent();
	//			 String putVal="";
	//		     StackTraceElement[] st = null;
	//			for(int i=0; i< dataset.getRowCount();i++){
	//				dataset.setActiveRow(i);
	//				for(int c =0; c < columns.length; c++){
	//					if(dataset.getColumn(i, columns[c]) != null && !"".equals(dataset.getColumnAsString(i, columns[c]))&& dataset.getColumnAsString(i, columns[c]) != null){
	//						try{
	//							
	//							putVal =	agt.ScpDecB64( INIFILEPATH, DAMO_KEY_NAME,dataset.getColumnAsString(i, columns[c]), DEFAULT_LOCALE );
	//						}catch(ScpDbAgentException e1){
	//							LOG.error("Exception", e1);
	//							LOG.error(columns[c]+":"+e1.toString());
	//							//e1.printStackTrace();
	//							putVal =	dataset.getColumnAsString(i, columns[c]);
	//							
	//						}
	//						catch(Exception e){
	//							LOG.error("Exception", e);
	//							putVal =	dataset.getColumnAsString(i, columns[c]);
	//							
	//						}
	//						dataset.put(columns[c], putVal);
	//					}
	//				}
	//			}
	//		
	//	} catch (Exception e) {
	//		LOG.error("Exception", e);
	//		e.printStackTrace();
	//	}
	//	
	//	return dataset;
	//	
	//}
	
	/**damoScpDecB64 DAMO B64복호화 함수 
	 * @param pramVal
	 * @return String DecB64 Value
	 */
	public static String damoScpDecB64(String pramVal)  {
		String resultVal ="";
		if("local".equals(OPERATION_MODE) && "".equals(INIFILEPATH)){
			return pramVal;
		}
			try {
				 ScpDbAgent agt = new ScpDbAgent();
				
				 resultVal	=agt.ScpDecB64( INIFILEPATH, DAMO_KEY_NAME,pramVal, DEFAULT_LOCALE );
				
			} catch (Exception e) {
				LOG.error("Exception", e);
				e.printStackTrace();
				return pramVal;
			}
			
			return resultVal;
	}
	
	/** damoScpEncB64 DAMO B64암호화 함수 
	 * @param pramVal
	 * @return String DecB64 Value
	 */
	public static String damoScpEncB64(String pramVal)  {
		String resultVal ="";
		if("local".equals(OPERATION_MODE) && "".equals(INIFILEPATH)){
			return pramVal;
		}
			try {
				 ScpDbAgent agt = new ScpDbAgent();
				
				 resultVal	=agt.ScpEncB64( INIFILEPATH, DAMO_KEY_NAME,pramVal, DEFAULT_LOCALE );
				 
			} catch (Exception e) {
				LOG.error("Exception", e);
				e.printStackTrace();
				return pramVal;
			}
			
			return resultVal;
	}
	
	///** damoScpEncB64 DAMO B64암호화 함수 
	// * @param dataset 
	// * @param columns
	// * @return FrameOneDataset dataset
	// */
	//public static FrameOneDataset damoScpEncB64(FrameOneDataset dataset,String[] columns)  {
	//	
	//	if("WKR".equals(OPERATION_MODE) && "".equals(INIFILEPATH)){
	//		return dataset;
	//	}
	//	try {
	//		//LOG.info("INIFILEPATH==>"+INIFILEPATH);
	//		 ScpDbAgent agt = new ScpDbAgent();
	//		 String putVal ="";
	//		for(int i=0; i< dataset.getRowCount();i++){
	//			dataset.setActiveRow(i);
	//			for(int c =0; c < columns.length; c++){
	//				if(dataset.getColumn(i, columns[c]) != null && !"".equals(dataset.getColumnAsString(i, columns[c]))){
	//					try{
	//						
	//						putVal =	agt.ScpEncB64( INIFILEPATH, DAMO_KEY_NAME,dataset.getColumnAsString(i, columns[c]), DEFAULT_LOCALE );
	//					}catch(ScpDbAgentException e1){
	//						LOG.error("Exception", e1);
	//						LOG.error(columns[c]+":"+e1.toString());
	//						//e1.printStackTrace();
	//						putVal =	dataset.getColumnAsString(i, columns[c]);
	//						
	//					}
	//					catch(Exception e){
	//						LOG.error("Exception", e);
	//						putVal =	dataset.getColumnAsString(i, columns[c]);
	//						
	//					}
	//					//dataset.put(columns[c], putVal);
	//					dataset.setColumn(i,columns[c], putVal);
	//				}
	//			}
	//		}
	//	} catch (Exception e) {
	//		LOG.error("Exception", e);
	//		e.printStackTrace();
	//	}
	//	
	//	return dataset;
	//	
	//	
	//}
	
	public static <T> T damoScpEncB64(T dto, String[] columns) {
	    if ("local".equals(OPERATION_MODE) && "".equals(INIFILEPATH)) {
	        return dto;
	    }
	    try {
	        ScpDbAgent agt = new ScpDbAgent();
	        Class<?> clazz = dto.getClass();
	        for (String column : columns) {
	            try {
	                java.lang.reflect.Field field = clazz.getDeclaredField(column);
	                field.setAccessible(true);
	                Object value = field.get(dto);
	                if (value != null && !"".equals(value.toString())) {
	                    String encrypted;
	                    try {
	                        encrypted = agt.ScpEncB64(INIFILEPATH, DAMO_KEY_NAME, value.toString(), DEFAULT_LOCALE);
	                    } catch (ScpDbAgentException e1) {
	                        LOG.error("Exception", e1);
	                        LOG.error(column + ":" + e1.toString());
	                        encrypted = value.toString();
	                    } catch (Exception e) {
	                        LOG.error("Exception", e);
	                        encrypted = value.toString();
	                    }
	                    field.set(dto, encrypted);
	                }
	            } catch (NoSuchFieldException | IllegalAccessException e) {
	                LOG.error("Reflection error on field: " + column, e);
	            }
	        }
	    } catch (Exception e) {
	        LOG.error("Exception", e);
	        e.printStackTrace();
	    }
	    return dto;
	}
	
	

}
