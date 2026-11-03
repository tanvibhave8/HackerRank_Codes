import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static int rob(List<Integer> nums) {

        int prev1 = 0;
        int prev2 = 0;

        for (int money : nums) {
            int current = Math.max(prev1, prev2 + money);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}

public class house_robber {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        BufferedWriter bufferedWriter = new BufferedWriter(
            new FileWriter(System.getenv("OUTPUT_PATH"))
        );

        int numsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> nums = IntStream.range(0, numsCount)
            .mapToObj(i -> {
                try {
                    return bufferedReader.readLine().trim();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            })
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.rob(nums);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }}