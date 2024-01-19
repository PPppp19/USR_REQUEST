/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import com.br.api.app.connect.ConnectDB2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jdesktop.application.LocalStorage;


/**
 *
 * @author Wattana
 */
public class Utility {
    
    
    private void dumpKeyPair(KeyPair keyPair) {
        PublicKey pub = keyPair.getPublic();
        System.out.println("Public Key: " + getHexString(pub.getEncoded()));

        PrivateKey priv = keyPair.getPrivate();
        System.out.println("Private Key: " + getHexString(priv.getEncoded()));
    }

    private String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Store Public Key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                publicKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(path + "/public.key");
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();

        // Store Private Key.
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
        fos = new FileOutputStream(path + "/private.key");
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }

    public static byte[] decryptText2(PrivateKey privateKey, String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedMessageBytes = decryptCipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        System.err.println("decryptedMessage: " + decryptedMessage);
        return decryptedMessageBytes;

    }

    private static byte[] txt;
    private static int Keylength = 512;

    private static PrivateKey prikey;
    private static PublicKey pubkey;

    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("-oooooooooooooooooooooo-");
        System.out.println(publicKey);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));

    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        try {
            String txt = decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
            return txt;
        } catch (Exception e) {

            return "none";
        }

    }

    public static String getCompany() throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.Company();
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }
    
    public static String EmpCodeList() throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.EmpCodeList();
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }
    
      public static String MailCreator() throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.MailCreator();
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }


    public static String getcostcenter(String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.costcenter(cono);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Report not found.");
            return mJsonObj.toString();
        }
    }

   public static String getmaildetail(String username, String eddocument, String edrefno, String esentto, String esentcc, String esentfrom, String edsubject, String createby, int cono) throws Exception {

        try {

            genRSAKeyPairAndSaveToFile(Keylength);
            Cipher encrypt = Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] encryptedMessage = encrypt.doFinal(username.getBytes(StandardCharsets.UTF_8));

            System.out.println("encryptedMessage" + encryptedMessage);
            txt = encryptedMessage;

            String encyptedCodeStr = Base64.getEncoder().encodeToString(encryptedMessage);


            byte[] enprikey = prikey.getEncoded();
            String privateKeyStr = Base64.getEncoder().encodeToString(enprikey);

            
            //String LinkCreate = "Please click this link to verify your email address : http://192.200.9.106:8080/UserRequest/?page=ResetNewPW&pp=" + encyptedCodeStr;
//            String LinkCreate = " Testpp: http://192.200.9.106:8080/UserRequest/?page=ResetNewPW&Destination=ResetPW&pp=" + urlEncry;
//            String LinkCreate = "<p>Please click this link to verify your email address : <a href=\"http://192.200.9.106:8080/UserRequest/?page=ResetNewPW&Destination=ResetPW&pp="+encyptedCodeStr+"\"><u>Click Here</U></a>.</p>";
            String LinkCreate = "<p>Please click this link to verify your email address : <a href=\"http://192.200.9.189:8080/UserRequest/?page=ResetNewPW&Destination=ResetPW&pp="+encyptedCodeStr+"\"><u>Click Here</U></a>.</p>";

            //String LinkCreate = "Please click this link to verify your email address : http://localhost:8080/UserRequest/?page=ResetNewPW&pp=" + encyptedCodeStr;
            //http://localhost:8080/UserRequest/?page=ResetNewPW&Destination=ResetPW&pp=LGZdzeQUNBzT4R9/oIUPoHBnPFmns740qCrqCGfPQZBhhSviWAj5hV33UaT0JiQwWXdvfJz5amWhWdzFqoRl+g==
            //http://192.200.9.189:8080/
            //InsertDB2.InsertPrikey(encyptedCodeStr,privateKeyStr); 
            java.util.Date date = new java.util.Date();
            long t = date.getTime();
            java.sql.Date sqlDate = new java.sql.Date(t);
            java.sql.Time sqlTime = new java.sql.Time(t);

            InsertDB2.InsertMailLog(eddocument, edrefno, esentto, esentcc, esentfrom, edsubject, LinkCreate, createby, cono, encyptedCodeStr, privateKeyStr, sqlDate, sqlTime);

            return LinkCreate;

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

        String a = "none";

        return a;
    }

    public static String getmimecode(String username) throws Exception {

        try {

            genRSAKeyPairAndSaveToFile(Keylength);
            Cipher encrypt = Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] encryptedMessage = encrypt.doFinal(username.getBytes(StandardCharsets.UTF_8));

            System.out.println("encryptedMessage" + encryptedMessage);
            txt = encryptedMessage;

            String encyptedCodeStr = Base64.getEncoder().encodeToString(encryptedMessage);
            

            byte[] enprikey = prikey.getEncoded();
            String privateKeyStr = Base64.getEncoder().encodeToString(enprikey);

            InsertDB2.InsertPrikey(encyptedCodeStr, privateKeyStr);
            return encyptedCodeStr;

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

        String a = "none";

        return a;
    }

    public static String getdecodemime(String encryptedString) throws Exception {

        try {

            encryptedString = encryptedString.replace(" ", "+");

            //String prikeytxt = SelectDB2.getprikey(encryptedString);
            String prikeytxt = SelectDB2.getprivatekey(encryptedString);
            String decryptedString1 = decrypt(encryptedString, prikeytxt);

            byte[] decodedString = Base64.getDecoder().decode(new String(encryptedString).getBytes("UTF-8"));

            return decryptedString1;

        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());

        }

        return "none";

    }


    public static void genRSAKeyPairAndSaveToFile(int keyLength) throws NoSuchAlgorithmException, Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keyLength);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        System.out.println("privateKey: " + privateKey);
        System.out.println("publicKey: " + publicKey);
        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] publicKeyBytes = privateKey.getEncoded();

        System.out.println("privateKeyBytes: " + privateKeyBytes);
        System.out.println("privateKeyBytes: " + publicKeyBytes);

        pubkey = publicKey;
        prikey = privateKey;

    }



}
