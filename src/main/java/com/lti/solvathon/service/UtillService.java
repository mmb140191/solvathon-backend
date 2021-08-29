package com.lti.solvathon.service;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;

@Service
public class UtillService {

    public String getBankName(String cardNo){
        HashMap<Integer, String> bankList = new HashMap<>();
        bankList.put(11, "ICICI");
        bankList.put(22, "HDFC");
        bankList.put(33, "AXIS");
        bankList.put(44, "SBI");

        int code = Integer.parseInt(cardNo.substring(0,2));
        String bankName = bankList.get(code);
        return bankName;
    }

    public KeyPair keyGenrator(){
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            /* @see https://www.keylength.com/ */
            keyPairGenerator.initialize(4096);
            keyPair = keyPairGenerator.generateKeyPair();

        } catch (NoSuchAlgorithmException |  InvalidParameterException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return keyPair;
    }

    public byte[] encryptionData(String cardNo, PublicKey publicKey){

        String plainText = cardNo;
        byte[] cipherText = null;
        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // CONVERSION of raw bytes to BASE64 representation
            cipherText = Base64.getEncoder().encode(cipherTextBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidParameterException e) {
            System.out.println(e.getLocalizedMessage());
    }

        return cipherText;
    }

    public String decryptionData(byte[] token, PrivateKey privateKey){

        String decryptedCipherText = "";
        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedCipherTextBytes = cipher.doFinal(Base64.getDecoder().decode(token));
            decryptedCipherText = new String(decryptedCipherTextBytes,StandardCharsets.UTF_8);


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidParameterException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return decryptedCipherText;
    }

    public byte[] dynamicCVVEncyptor(Integer randomNo){
        String originalInput = randomNo.toString();
        byte[] encodedString = Base64.getEncoder().encode(originalInput.getBytes());
        return encodedString;
    }

    public Integer dynamicCVVDecryptor(byte[] randomNo){
        byte[] decodedBytes = Base64.getDecoder().decode(randomNo);
        String decodedString = new String(decodedBytes);
        return Integer.parseInt(decodedString);
    }
}
