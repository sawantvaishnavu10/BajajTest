package com.app.solution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DestinationHashGenerator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar DestinationHashGenerator.jar <PRN Number> <path to json file>");
            System.exit(1);
        }

        String prnNumber = args[0].toLowerCase();
        String jsonFilePath = args[1];

        try {
            JSONObject jsonObject = parseJsonFile(jsonFilePath);
            String destinationValue = traverseJson(jsonObject, "destination");
            String randomString = generateRandomString(8);
            String concatenatedString = prnNumber + destinationValue + randomString;
            String md5Hash = generateMd5Hash(concatenatedString);
            System.out.println(md5Hash + ";" + randomString);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static JSONObject parseJsonFile(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(new JSONTokener(jsonContent));
    }

    private static String traverseJson(JSONObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return jsonObject.getString(key);
        } else {
            for (Object obj : jsonObject.keySet()) {
            	String k = (String) obj;
                Object value = jsonObject.get(k);
                if (value instanceof JSONObject) {
                    String result = traverseJson((JSONObject) value, key);
                    if (result != null) {
                        return result;
                    }
                } else if (value instanceof JSONArray) {
                    JSONArray array = (JSONArray) value;
                    for (int i = 0; i < array.length(); i++) {
                        Object arrayValue = array.get(i);
                        if (arrayValue instanceof JSONObject) {
                            String result = traverseJson((JSONObject) arrayValue, key);
                            if (result != null) {
                                return result;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(chars.charAt(random.nextInt(chars.length())));
        }
        return randomString.toString();
    }

    private static String generateMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}