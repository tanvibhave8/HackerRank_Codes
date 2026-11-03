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

    public static boolean canJump(List<Integer> nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.size(); i++) {

            if (i > maxReach) {
                return false;
            }

            maxReach = Math.max(maxReach, i + nums.get(i));

            if (maxReach >= nums.size() - 1) {
                return true;
            }
        }

        return true;
    }
}

public class jump_game {
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

        boolean result = Result.canJump(nums);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}