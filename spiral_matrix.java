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

    public static List<Integer> spiralOrder(List<List<Integer>> matrix) {

        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = matrix.size() - 1;
        int left = 0;
        int right = matrix.get(0).size() - 1;

        while (top <= bottom && left <= right) {

            // Traverse left to right
            for (int i = left; i <= right; i++) {
                result.add(matrix.get(top).get(i));
            }
            top++;

            // Traverse top to bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix.get(i).get(right));
            }
            right--;

            // Traverse right to left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix.get(bottom).get(i));
                }
                bottom--;
            }

            // Traverse bottom to top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix.get(i).get(left));
                }
                left++;
            }
        }

        return result;
    }
}

public class spiral_matrix {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        BufferedWriter bufferedWriter = new BufferedWriter(
            new FileWriter(System.getenv("OUTPUT_PATH"))
        );

        int matrixRows = Integer.parseInt(bufferedReader.readLine().trim());
        int matrixColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, matrixRows).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().trim().split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.spiralOrder(matrix);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n")) + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}