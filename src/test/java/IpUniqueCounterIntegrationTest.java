import org.junit.Assert;
import org.junit.Test;
import org.mgerman.IpUniqueCounter;

import java.io.IOException;

public class IpUniqueCounterIntegrationTest {

    @Test
    public void test() throws IOException {
        Assert.assertEquals(3, IpUniqueCounter.countUniqueIpAddresses("src/main/resources/test.txt"));
    }

}
