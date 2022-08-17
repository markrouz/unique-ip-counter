import org.mgerman.IpUniqueCounter;

import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        final String fileName = parseFileName(args);
        System.out.println(IpUniqueCounter.countUniqueIpAddresses(fileName));
    }


    private static String parseFileName(String[] args) {
        Objects.requireNonNull(args, "you need to specify name of file with IP addresses");
        if (args.length != 1) {
            throw new IllegalArgumentException("Must be one (and only one) program argument - path to file with IP addresses");
        }
        return args[0];
    }

}
