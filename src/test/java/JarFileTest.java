import me.idarkyy.common.jarfile.JarfileLoader;

public class JarFileTest extends JarfileLoader {
    public static void main(String[] args) {
        new JarFileTest().doThisAndThat();
    }

    public void doThisAndThat() {
        System.out.println("We're running from " + getJarFileLocation().getPath());
    }
}
