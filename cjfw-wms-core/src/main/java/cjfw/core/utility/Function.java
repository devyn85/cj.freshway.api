/**
 *<pre>
 *Function
 *유형 : User Function
 *설명 : 시스템 운영을 위한 개발자 함수 모음
 *@author  Kiwon RYU
 *@version version 1.0.0 20080101
 *@since  version 1.0.0 20080101
 *@param None
 *@return None
 *</pre>
 */  
package cjfw.core.utility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
/**
 *<pre>
 *Function
 *유형 : User Function
 *설명 : 시스템 운영을 위한 개발자 함수 모음
   * 
     SYS_getInI               : 요청한 ini 파일 정보를 가져온다.            
 *</pre>
 */  
     

public class Function  {    
    //SEED encryption algorithm block size
    private static final int SEED_BLOCK_SIZE = 16;      
    
    static String  SEEDKEY             = "0f38b99a121c54e4ef4bbaf0dc3e6c7fd0e79129310055189f3b123a3810c4fbd6141413d1d3b9b828490dec58d4fc6e986aeed885f097320639af47525b6ca5";
   
    static String[] s_Return= new String[4];      //[0]: ErrCode
    //[1]: ErrMessage
    //[2]: UserCode
    //[3]: UserMessage   
    
 	/**
     *Ini 정보를 가져온다.
     *<pre>
	 *요청한 ini 정보를 가져온다.
	 *@author  Kiwon RYU
     *@version version 1.0.0 20080101
     *@since  version 1.0.0 20080101
     *@param Name : 항목명
     *@return String 항목값
     *</pre>
     */ 	
    public static String SYS_getInI (String Name) {
    	String Returnstr = " ";
    	
		String encryptkey = SEEDKEY;
		byte[] rawKey = encryptkey.getBytes();
		
    	try {
			Returnstr=ContextUtil.getProperty(Name).trim();
			Returnstr=decrypt(Returnstr, rawKey, null);
			if(Returnstr !=null && Returnstr.length() !=0)Returnstr=Returnstr.trim();
		} catch (IOException e) {
			e.printStackTrace();
//			Function.SYS_PrintMessage("STD", "Error", e.toString());
            return "";
		}
		if(Returnstr == null) { Returnstr = " ";};
		if(Returnstr.equals("null")) {Returnstr = " ";};
		return Returnstr;
    }
    
    /**
     * SEEDARIA algorithm to decrypt the data.
     *<pre>
     * SEED algorithm to encrypt the data.
     *@author  YoungSu Kim
     *@version version 1.0.0 2012-07-30
     *@since  version 1.0.0 2012-07-30
     *@param data Target Data
     *@param keySize Masterkey Size
     *@param charset Data character set
     *@return Decrypted data
     *</pre>
     */ 	
	public static String decrypt(String data, byte[] key, String charset)
	throws UnsupportedEncodingException {
		
		int pdwRoundKey[] = new int[32];
		SEED128.SeedRoundKey(pdwRoundKey, key);
		
		byte[] decrypt = Base64.toByte(data);
		int blockCount = decrypt.length / SEED_BLOCK_SIZE;
		for( int i = 0; i < blockCount; i++ ) {
			
			byte sBuffer[] = new byte[SEED_BLOCK_SIZE];
			byte tBuffer[] = new byte[SEED_BLOCK_SIZE];
			
			System.arraycopy(decrypt, (i * SEED_BLOCK_SIZE), sBuffer, 0, SEED_BLOCK_SIZE);
			
			SEED128.SeedDecrypt(sBuffer, pdwRoundKey, tBuffer);
			
			System.arraycopy(tBuffer, 0, decrypt, (i * SEED_BLOCK_SIZE), tBuffer.length);
		}
		
		if( charset == null ) {
			return new String(BlockPadding.getInstance().removePadding(decrypt, SEED_BLOCK_SIZE));
		} else {
			return new String(BlockPadding.getInstance().removePadding(decrypt, SEED_BLOCK_SIZE), charset);
		}
	}  	
}
