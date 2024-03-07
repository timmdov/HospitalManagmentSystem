import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class hashPasswords {
    public static void main(String[] args) {
        try {
            System.out.println("Hashed Admin Password: " + hashPassword("AdminPass123"));
            System.out.println("Hashed Doctor Password: " + hashPassword("DocPass123"));
            System.out.println("Hashed DoctorTwo Password: " + hashPassword("DocPassTwo123"));
            System.out.println("Hashed Nurse Password: " + hashPassword("NursePass123"));
            System.out.println("Hashed NurseTwo Password: " + hashPassword("NursePassTwo123"));
            System.out.println("Hashed Patient Password: " + hashPassword("PatientPass123"));
            System.out.println("Hashed PatientTwo Password: " + hashPassword("PatientPassTwo123"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }
}
