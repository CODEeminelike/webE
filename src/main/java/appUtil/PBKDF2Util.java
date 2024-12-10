/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appUtil;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBKDF2Util {

    // Băm mật khẩu sử dụng PBKDF2
    public static String hashPassword(String password) throws Exception {
        byte[] salt = getSalt(); // Tạo salt ngẫu nhiên
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256); // 10000 vòng lặp, 256-bit hash
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        // Trả về hash và salt đã mã hóa, lưu trữ dưới dạng hash:salt
        return Base64.getEncoder().encodeToString(hash) + ":" + Base64.getEncoder().encodeToString(salt);
    }

    // Lấy salt ngẫu nhiên
    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16]; // Salt có độ dài 16 byte
        sr.nextBytes(salt);
        return salt;
    }

    // Kiểm tra mật khẩu với PBKDF2
    public static boolean checkPassword(String originalPassword, String storedPassword) throws Exception {
        // Lấy phần hash và salt từ mật khẩu đã lưu trong cơ sở dữ liệu
        String[] parts = storedPassword.split(":");
        String storedHash = parts[0];
        byte[] storedSalt = Base64.getDecoder().decode(parts[1]);

        // Băm lại mật khẩu nhập vào với salt lưu trong CSDL và so sánh
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), storedSalt, 10000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String computedHash = Base64.getEncoder().encodeToString(hash);

        return computedHash.equals(storedHash);
    }
}
