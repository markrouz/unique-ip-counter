package org.mgerman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IpUniqueCounter {

    private IpUniqueCounter() {
    }

    /**
     * It's possible to have 2^32 IPv4 addresses
     * Let specific IP address maps to one specific bit
     * To store 2^32 bits we can use int[2^27]. 1 int = 4 bytes = 32 bits, so we have 2^27*2^5 bits.
     * 2^27 = 134217728
     */
    private static final int[] STORAGE = new int[134217728];

    private static long COUNTER = 0;

    /**
     * 2^32 = 4294967296 unique IP addresses at all
     */
    private static final long NUMBER_OF_IP_ADDRESSES = 4294967296L;


    public static long countUniqueIpAddresses(String fileName) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null && COUNTER <= NUMBER_OF_IP_ADDRESSES) {
                registerIpAddressLongRepresentationInStorage(mapIpAddressStringToLong(line));
            }
        }
        return COUNTER;
    }

    private static long mapIpAddressStringToLong(String ipString) {
        String[] ipParts = ipString.split("\\.");
        long res = 0;
        for (int i = 0; i < 4; i++) {
            int coefficient = 1;
            for (int j = 3 - i; j > 0; j--) {
                coefficient *= 256;
            }
            res += (long) Integer.parseInt(ipParts[i]) * coefficient;
        }
        return res;
    }


    private static void registerIpAddressLongRepresentationInStorage(long longValue) {
        int indexInStorage = (int) (longValue >> 5);
        int bit = 1 << (longValue & 31);
        if ((STORAGE[indexInStorage] & bit) == 0) {
            COUNTER++;
            STORAGE[indexInStorage] |= bit;
        }
    }
}
