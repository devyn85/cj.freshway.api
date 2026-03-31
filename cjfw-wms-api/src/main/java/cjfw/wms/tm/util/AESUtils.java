package cjfw.wms.tm.util;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES-256 기반 암호화/복호화 유틸 클래스
 * <p>
 * 역할:
 * - AES-256 암호화 및 복호화 기능 제공
 * - CBC 모드를 기본으로 사용하며, 호환성을 위해 ECB 모드도 지원
 * 위치:
 * - support-util
 */
public class AESUtils {

    private static final String ENCODED_KEY = "p/PJ4rjU8abF6bLX8KPI4bT3wNnmoi9bwR5KfQ/zbsI=";
    private static final String AES_GCM_ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int GCM_IV_LENGTH = 12;

    /**
     * 암호화 (CBC 방식)
     *
     * @param message          평문
     * @return 암호문 (Base64 인코딩)
     */
    public static String encrypt(String message) {
        return encryptCBC(Base64.getDecoder().decode(ENCODED_KEY), message);
    }

    /**
     * 복호화
     * CBC 먼저 시도하고, 실패하면 ECB 시도 (호환성 유지)
     *
     * @param encrypted        암호문 (Base64 인코딩)
     * @return 복호화된 평문
     */
    public static String decrypt(String encrypted) {
        byte[] decodedKey = Base64.getDecoder().decode(ENCODED_KEY);

        // 1. 새로운 방식(CBC) 시도
        try {
            return decryptCBC(decodedKey, encrypted);
        } catch (Exception e) {
            // 2. 실패하면 기존 방식(ECB) 시도
            try {
                return decryptECB(decodedKey, encrypted);
            } catch (Exception ex) {
                return encrypted;
            }
        }
    }

    /**
     * CBC 방식 암호화
     *
     * @param decodedKey 디코딩된 AES 키
     * @param message    평문 메시지
     * @return 암호화된 문자열 (IV + 암호문을 Base64 인코딩)
     */
    private static String encryptCBC(byte[] decodedKey, String message) {
        try {
            // IV 생성 (12바이트 - GCM 표준)
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            javax.crypto.spec.GCMParameterSpec gcmSpec = new javax.crypto.spec.GCMParameterSpec(GCM_TAG_LENGTH, iv);

            Cipher cipher = Cipher.getInstance(AES_GCM_ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(decodedKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

            byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // IV + 암호문을 함께 Base64 인코딩
            byte[] result = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);

            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * CBC 방식 복호화
     *
     * @param decodedKey 디코딩된 AES 키
     * @param encrypted  암호화된 문자열 (Base64 인코딩)
     * @return 복호화된 평문
     * @throws IllegalBlockSizeException          블록 크기 오류
     * @throws BadPaddingException                패딩 오류
     * @throws InvalidAlgorithmParameterException 알고리즘 파라미터 오류
     * @throws InvalidKeyException                잘못된 키
     * @throws NoSuchPaddingException             패딩 알고리즘 없음
     * @throws NoSuchAlgorithmException           알고리즘 없음
     */
    private static String decryptCBC(byte[] decodedKey, String encrypted)
            throws IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException {

        byte[] data = Base64.getDecoder().decode(encrypted);

        // 최소 길이 체크 (IV 12바이트 + 최소 암호문)
        if (data.length < 28) {
            throw new IllegalArgumentException("Invalid encrypted data length for GCM");
        }

        // IV 추출 (처음 12바이트)
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(data, 0, iv, 0, iv.length);
        javax.crypto.spec.GCMParameterSpec gcmSpec = new javax.crypto.spec.GCMParameterSpec(GCM_TAG_LENGTH, iv);

        // 암호문 추출
        byte[] cipherText = new byte[data.length - GCM_IV_LENGTH];
        System.arraycopy(data, GCM_IV_LENGTH, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(AES_GCM_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(decodedKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decrypted = cipher.doFinal(cipherText);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    /**
     * ECB 방식 복호화 (호환성을 위해 유지)
     *
     * @param decodedKey 디코딩된 AES 키
     * @param encrypted  암호화된 문자열 (Base64 인코딩)
     * @return 복호화된 평문
     * @throws NoSuchPaddingException    패딩 알고리즘 없음
     * @throws NoSuchAlgorithmException  알고리즘 없음
     * @throws IllegalBlockSizeException 블록 크기 오류
     * @throws BadPaddingException       패딩 오류
     * @throws InvalidKeyException       잘못된 키
     */
    private static String decryptECB(byte[] decodedKey, String encrypted)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        Cipher cipher = Cipher.getInstance(AES_GCM_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(decodedKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    /**
     * Base64 문자열 여부 검사
     *
     * @param input 검사할 문자열
     * @return Base64 형태이고 16의 배수 길이인 경우 true
     */
    public static boolean isProbablyBase64(String input) {
        try {
            byte[] decoded = Base64.getDecoder().decode(input);
            return decoded.length % 16 == 0;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * AES-256 키 생성 (32바이트)
     *
     * @return Base64 인코딩된 랜덤 키
     */
    public static String generateKey() {
        byte[] key = new byte[32]; // 256 bits
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
