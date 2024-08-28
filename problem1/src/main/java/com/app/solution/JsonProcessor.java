/*
 * package com.app.solution;
 * 
 * import org.json.JSONObject; import org.json.JSONTokener;
 * 
 * import java.io.FileInputStream; import java.io.IOException; import
 * java.nio.charset.StandardCharsets; import java.security.MessageDigest; import
 * java.security.NoSuchAlgorithmException; import java.util.Random; import
 * java.util.Scanner;
 * 
 * public class JsonProcessor {
 * 
 * public static void main(String[] args) { if (args.length != 2) { System.err.
 * println("Usage: java -jar <jarfile> <PRN Number> <path to JSON file>");
 * System.exit(1); }
 * 
 * String prnNumber = args[0].toLowerCase(); String jsonFilePath = args[1];
 * 
 * String destinationValue = getDestinationValue(jsonFilePath); if
 * (destinationValue == null) {
 * System.err.println("Key 'destination' not found in the JSON file.");
 * System.exit(1); }
 * 
 * String randomString = generateRandomString(8); String concatenatedString =
 * prnNumber + destinationValue + randomString;
 * 
 * String md5Hash = generateMD5Hash(concatenatedString);
 * 
 * System.out.println(md5Hash + ";" + randomString); }
 * 
 * private static String getDestinationValue(String filePath) { try
 * (FileInputStream fis = new FileInputStream(filePath)) { JSONTokener tokener =
 * new JSONTokener(fis); JSONObject jsonObject = new JSONObject(tokener); return
 * findDestination(jsonObject); } catch (IOException e) { e.printStackTrace();
 * return null; } }
 * 
 * private static String findDestination(JSONObject jsonObject) { for (String
 * key : jsonObject.keySet()) { if (key.equals("destination")) { return
 * jsonObject.getString(key); } else { Object value = jsonObject.get(key); if
 * (value instanceof JSONObject) { String result = findDestination((JSONObject)
 * value); if (result != null) { return result; } } } } return null; }
 * 
 * private static String generateRandomString(int length) { String characters =
 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; Random
 * random = new Random(); StringBuilder sb = new StringBuilder(); for (int i =
 * 0; i < length; i++) { int index = random.nextInt(characters.length());
 * sb.append(characters.charAt(index)); } return sb.toString(); }
 * 
 * private static String generateMD5Hash(String input) { try { MessageDigest md
 * = MessageDigest.getInstance("MD5"); byte[] hashBytes =
 * md.digest(input.getBytes(StandardCharsets.UTF_8)); StringBuilder sb = new
 * StringBuilder(); for (byte b : hashBytes) { sb.append(String.format("%02x",
 * b)); } return sb.toString(); } catch (NoSuchAlgorithmException e) {
 * e.printStackTrace(); return null; } } }
 */