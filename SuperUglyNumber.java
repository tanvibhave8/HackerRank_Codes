import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    public static int nthSuperUglyNumber(int n, List<Integer> primes) {

        int[] ugly = new int[n];

        ugly[0] = 1;

        int k = primes.size();

        int[] index = new int[k];
        long[] next = new long[k];

        // Initialize next multiples
        for (int i = 0; i < k; i++) {
            next[i] = primes.get(i);
        }

        // Generate super ugly numbers
        for (int i = 1; i < n; i++) {

            long min = Long.MAX_VALUE;

            // Find minimum value
            for (int j = 0; j < k; j++) {
                min = Math.min(min, next[j]);
            }

            ugly[i] = (int) min;

            // Update indexes
            for (int j = 0; j < k; j++) {

                if (next[j] == min) {

                    index[j]++;

                    next[j] = (long) ugly[index[j]] * primes.get(j);
                }
            }
        }

        return ugly[n - 1];
    }
}

public class SuperUglyNumber {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> primes = Arrays.stream(
            bufferedReader.readLine().trim().split(" ")
        )
        .map(Integer::parseInt)
        .collect(Collectors.toList());

        int result = Result.nthSuperUglyNumber(n, primes);

        System.out.println(result);

        bufferedReader.close();
    }
}