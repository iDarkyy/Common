import me.idarkyy.common.encryption.BCrypt;

public class BCryptTest {
    public static void main(String[] args) {
        String rawPassword = "test123";

        String hashedPassword = BCrypt.hash(rawPassword, BCrypt.generateSalt());

        String inputPassword1 = "password69";
        String inputPassword2 = "test123";

        System.out.println(BCrypt.check(inputPassword1, hashedPassword)); // Will print 'false'

        System.out.println(BCrypt.check(inputPassword2, hashedPassword)); // Will print 'true'
    }
}
