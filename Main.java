import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("input.json"));

            String firstName = rootNode.get("student").get("first_name").asText().toLowerCase();
            String rollNumber = rootNode.get("student").get("roll_number").asText().toLowerCase();

            String concatenatedString = firstName + rollNumber;
            String md5Hash = getMD5Hash(concatenatedString);

            FileWriter writer = new FileWriter("output.txt");
            writer.write(md5Hash);
            writer.close();

            System.out.println("MD5 hash saved in output.txt: " + md5Hash);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
