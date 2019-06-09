package me.idarkyy.common.security.files;

import me.idarkyy.common.security.CryptoException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class FileEncrpytion {
    private FileEncrpytion() {

    }

    /**
     * Encrypt a file
     *
     * @param algorithm     encryption algorithm
     * @param encryptionKey the key for decryption
     * @param input         file to encrypt
     * @param output        output file
     * @throws CryptoException
     */
    public static void encrypt(String algorithm, String encryptionKey, File input, File output) throws CryptoException {
        doJob(Cipher.ENCRYPT_MODE, algorithm, encryptionKey, input, output);
    }

    public static void encrypt(String algorithm, SecretKey encryptionKey, File input, File output) throws CryptoException {
        doJob(Cipher.ENCRYPT_MODE, algorithm, encryptionKey, input, output);
    }

    /**
     * Decrypt a file
     *
     * @param algorithm     decryption algorithm
     * @param encryptionKey the key for decryption
     * @param input         file to decrypt
     * @param output        output file
     * @throws CryptoException
     */
    public static void decrypt(String algorithm, String encryptionKey, File input, File output) throws CryptoException {
        doJob(Cipher.DECRYPT_MODE, algorithm, encryptionKey, input, output);
    }

    public static void decrypt(String algorithm, SecretKey encryptionKey, File input, File output) throws CryptoException {
        doJob(Cipher.DECRYPT_MODE, algorithm, encryptionKey, input, output);
    }

    private static void doJob(int cipherMode, String algorithm, String encryptionKey, File input, File output) throws CryptoException {
        doJob(cipherMode, algorithm, new SecretKeySpec(encryptionKey.getBytes(), algorithm), input, output);
    }

    private static void doJob(int cipherMode, String algorithm, SecretKey encryptionKey, File input, File output) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(encryptionKey.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(input);
            byte[] inputBytes = new byte[(int) input.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        } catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new CryptoException("An error occurred during " + (cipherMode == 1 ? "encryption" : "decryption"), e);
        }
    }

    public static SecretKey generateKey(String algorithm) throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance(algorithm).generateKey();
    }
}
