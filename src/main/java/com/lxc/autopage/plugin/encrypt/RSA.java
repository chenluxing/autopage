package com.lxc.autopage.plugin.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA非对称加密算法
 *
 */
public class RSA {

	private static final Logger log = LoggerFactory.getLogger(RSA.class);

	/**
	 * 安全服务提供者
	 */
	private static final Provider PROVIDER = new BouncyCastleProvider();

	/**
	 * 密钥大小
	 */
	private static final int KEY_SIZE = 1024;

    /**
     *
     * 公钥
     *
     */

    private static RSAPublicKey publicKey;

    /**
     *
     * 私钥
     *
     */

    private static RSAPrivateKey privateKey;

	/**
	 * 不可实例化
	 */
	private RSA() {
	}

    /**
	 * 生成密钥对
	 *
	 * @return 密钥对
	 */
	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER);
			keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			log.error("生成密钥对出错！", e);
		}
		return null;
	}

	/**
	 * 加密
	 *
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            数据
	 * @return 加密后的数据
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA", PROVIDER);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
            e.printStackTrace();
			log.error("加密公钥出错！", e);
		}
		return null;
	}

	/**
	 * 加密
	 *
	 * @param publicKey
	 *            公钥
	 * @param text
	 *            字符串
	 * @return Base64编码字符串
	 */
	public static String encrypt(PublicKey publicKey, String text) {
		byte[] data = encrypt(publicKey, text.getBytes());
		return data != null ? Base64.encodeBase64String(data) : null;
	}

	/**
	 * 解密
	 *
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            数据
	 * @return 解密后的数据
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", PROVIDER);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("解密私钥出错！", e);
		}
		return null;
	}

	/**
	 * 解密
	 *
	 * @param privateKey
	 *            私钥
	 * @param text
	 *            Base64编码字符串
	 * @return 解密后的数据
	 */
	public static String decrypt(PrivateKey privateKey, String text) {
		byte[] data = decrypt(privateKey, Base64.decodeBase64(text));
		return data != null ? new String(data) : null;
	}
    /**
     * 解密
     *
     * @param publicKey
     *            公钥
     * @param data
     *            数据
     * @return 解密后的数据
     */
    public static byte[] decryptPub(PublicKey publicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("解密公钥出错！", e);
        }
        return null;
    }
    /**
     * 解密
     *
     * @param publicKey
     *            公钥
     * @param text
     *            Base64编码字符串
     * @return 解密后的数据
     */
    public static String decryptPub(PublicKey publicKey, String text) {
        byte[] data = decryptPub(publicKey, Base64.decodeBase64(text));
        return data != null ? new String(data) : null;
    }

    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyStr
     * @throws Exception
     */
    public static void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decodeBase64(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateCrtKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     *
     * 从字符串中加载公钥
     *
     * @param
     *
     * @throws
     *
     */

    public static void loadPublicKey(String publicKeyStr) throws Exception {

        try {

            BASE64Decoder base64Decoder = new BASE64Decoder();

            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);

            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {

            throw new Exception("无此算法");

        } catch (InvalidKeySpecException e) {

            throw new Exception("公钥非法");

        } catch (IOException e) {

            throw new Exception("公钥数据内容读取错误");

        } catch (NullPointerException e) {

            throw new Exception("公钥数据为空");

        }

    }

    /**
     *
     * 获取公钥
     *
     * @return当前的公钥对象
     *
     */

    public static RSAPublicKey getPublicKey() {

        return publicKey;

    }

    /**
     *
     * 获取私钥
     *
     * @return当前的私钥对象
     *
     */

    public static RSAPrivateKey getPrivateKey() {

        return privateKey;

    }


    /**
     * * 公钥加密 *
     *
     * @param
     *
     * @param data
     *            待加密的明文数据 *
     * @return 加密后的数据 *
     * @throws Exception
     */
    public static byte[] encryptOpen(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            // 加密块大小为127
            // byte,加密后为128个byte;因此共有2个加密块，第一个127
            // byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i
                            * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i
                            * blockSize, raw, i * outputSize);
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
                // ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
                // OutputSize所以只好用dofinal方法。

                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * * 私钥解密 *
     *
     * @param
     *
     * @param raw
     *            已经加密的数据 *
     * @return 解密后的明文 *
     * @throws Exception
     */
    public static byte[] decryptOpen(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
