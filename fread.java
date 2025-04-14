import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fread {

    public static String read(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            content = content.replace("\n", "").replace("\t", "");
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO file");
            return;
        }
        String code = read(args[0]);
        long start = System.nanoTime();
        atom a = new atom(cleaned);
        long end = System.nanoTime();
        System.out.println(a.exec());
        System.out.println((end - start)+" : Nanosecs");
    }
}
