package com.lxc.autopage.plugin.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 安全相关的工具类
 */
public class SecurityUtil {

	/**
	 * "私钥"参数名称
	 */
	private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "RSA-PRIVATE";

	/**
	 * 生成密钥(添加私钥至Session并返回公钥)
	 *
	 * @param request
	 *            httpServletRequest
	 * @return 公钥
	 */
	public static RSAPublicKey RSAEncrypt(HttpServletRequest request) {
		Assert.notNull(request);
		KeyPair keyPair = RSA.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		HttpSession session = request.getSession();
		session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
		return publicKey;
	}

	/**
	 * 解密参数
	 *
	 * @param parameter
	 *            参数值
	 * @param request
	 *            httpServletRequest
	 * @return 解密内容
	 */
	public static String RSADecrypt(String parameter, HttpServletRequest request) {
		Assert.notNull(request);
		if (StringUtils.isNotEmpty(parameter)) {
			HttpSession session = request.getSession();
			RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
			if (privateKey != null) {
				session.removeAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
				return RSA.decrypt(privateKey, parameter);
			}
		}
		return null;
	}

	private static final String aesKey = "cgtz" + "o92x7NOaqH2R";
	private static final String TOKEN_SEPERATOR = "-";

	/**
	 * 混淆密码算法
	 *
	 * @param salt
	 * @param inputPassword
	 * @return
	 */
	public static String confusePassword(String salt, String inputPassword) {
		return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(inputPassword));
	}

	/**
	 * MD5的算法在RFC1321 中定义 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：<BR />
	 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e <BR />
	 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661 <BR />
	 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72 <BR />
	 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0 <BR />
	 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
	 * <p>
	 * 传入参数：一个字节数组 传出参数：字节数组的 MD5 结果字符串
	 *
	 * @author liang.liang
	 */
	public static String md5(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			byte tmp[] = md.digest();
			// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
			char str[] = new char[16 * 2];
			// 表示转换结果中对应的字符位置
			int k = 0;
			// 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
			for (int i = 0; i < 16; i++) {
				// 取第 i 个字节
				byte byte0 = tmp[i];
				// 取字节中高 4位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// 换后的结果转换为字符串
			s = new String(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 根据user_id生成用户的Token
	 *
	 * @param appUserId
	 * @return
	 */
	public static String generateUserToken(String appUserId) {
		return SecurityUtil.aesEncrypt(appUserId + generateUserShortKey(appUserId) + getCurrentTimestampStr());
	}

	/**
	 * 解析用户的token信息
	 *
	 * @param token
	 * @return
	 */
	public static boolean isTokenValid(String token, String pUserId) {
		String parsedStr = SecurityUtil.aesDecrypt(token);

		String useridStr = parsedStr.substring(0, 12);
		String keyStr = parsedStr.substring(12, 15);

		if (keyStr.equals(generateUserShortKey(pUserId)) && useridStr.equals(pUserId)) {
			return true;
		} else {
			return false;
		}
	}

	public static String generateUserPassword(String pUserName, String pPassword) {
		return SecurityUtil.md5(pUserName + pPassword);
	}

	public static String generateSalt() {
		return String.valueOf(Math.round((Math.random() * 9 + 1) * 100000));
	}

	public static String generateUserShortUrl(String pUserName) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "afbx";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = (md5(pUserName + key));
		String hex = sMD5EncryptResult;
		String[] resUrl = new String[4];
		// 得到 4组短链接字符串
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Inteper.parseInt() 只能处理 31 位 , 首位为符号位 ,
			// 如果不用long ，则会越界
			long lHexLong = Long.valueOf("3FFFFFFF", 16) & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			// 循环获得每组6位的字符串
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				// (具体需要看chars数组的长度 以防下标溢出，注意起点为0)
				long index = Long.valueOf("0000003D", 16) & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl[3];
	}

	public static String generateUserShortKey(String pUserId) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "http://www.cgtz.com/";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = (md5(key + pUserId));
		String hex = sMD5EncryptResult;
		String[] resUrl = new String[4];
		// 得到 4组短链接字符串
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Inteper.parseInt() 只能处理 31 位 , 首位为符号位 ,
			// 如果不用long ，则会越界
			long lHexLong = 0xFFFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			// 循环获得每组6位的字符串
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				// (具体需要看chars数组的长度 以防下标溢出，注意起点为0)
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl[0];
	}

	private static String getCurrentTimestampStr() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	// 密码字典，用于创建短链接
	private final static char[] digits = { 'J', 'e', 's', 'C', 'x', 'z', 'i', 'm', 'A', 'j', 'a', 'b', 'c', 'D', 'B',
			'f', 'd', 'h', 'I', 'G', 'k', 'n', 'H', 'l', 'o', 'u', 'q', 'r', 'v', 't', 'g', 'p', 'w', 'E', 'y', 'F', };

	public static String generateShortUrlByNum(long mobile) {
		long mobile1 = mobile % 100000000;
		long mobile2 = mobile / 100000000 - 100;
		mobile = mobile1 * 100 + 10000000000L + mobile2;

		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << 5;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (mobile & mask)];
			mobile >>>= 5;
		} while (mobile != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 对user_id进行AES加密，返回的密文进行编码后，作为用户token返回
	 *
	 * @param plainText
	 * @return
	 */
	private static String aesEncrypt(String plainText) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(plainText.getBytes());

			return Base64.encodeBase64URLSafeString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 对用户的Token使用AES算法进行解密
	 *
	 * @param cipherText
	 * @return
	 */
	private static String aesDecrypt(String cipherText) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] cipherBytes = Base64.decodeBase64(cipherText);
			byte[] plainBytes = cipher.doFinal(cipherBytes);
			String originalString = new String(plainBytes);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 创建指定数量的随机字符串
	 *
	 * @return
	 */
	public static String createRandomMobileCode() {
		String retStr = "";
		String strTable = "1234567890";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < 6; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	public static String getUUIDStr() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-", "");
		return str;
	}

}
