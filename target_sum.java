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

    public static int findTargetSumWays(List<Integer> nums, int target) {

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);

        for (int num : nums) {

            Map<Integer, Integer> next = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {

                int sum = entry.getKey();
                int count = entry.getValue();

                next.put(sum + num,
                    next.getOrDefault(sum + num, 0) + count);

                next.put(sum - num,
                    next.getOrDefault(sum - num, 0) + count);
            }

            dp = next;
        }

        return dp.getOrDefault(target, 0);
    }
}

public class target_sum {
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

        int target = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.findTargetSumWays(nums, target);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}