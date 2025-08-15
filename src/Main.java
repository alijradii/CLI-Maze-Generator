import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static class Pair {
        int first;
        int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter the wall width: ");
        int offsetWidth = sc.nextInt();

        System.out.println("Enter the number of wall rows and cols: ");
        int rWalls = sc.nextInt();
        int rCols = sc.nextInt();

        int rows = rWalls * (offsetWidth + 1) + 1;
        int cols = rCols * (offsetWidth + 1) + 1;

        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = '#';
            }
        }

        boolean[][] visited = new boolean[rows][cols];
        Pair[] dirs = { new Pair(-1, 0), new Pair(0, 1), new Pair(1, 0), new Pair(0, -1) };

        ArrayList<Pair> stack = new ArrayList<>();
        Pair start = new Pair(1, 1);
        stack.add(start);

        while (!stack.isEmpty()) {
            Pair top = stack.removeLast();

            ArrayList<Pair> candidates = new ArrayList<>();
            for (Pair dir : dirs) {
                int nr = top.first + dir.first * (offsetWidth + 1);
                int nc = top.second + dir.second * (offsetWidth + 1);

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                    candidates.add(dir);
                }
            }

            if (candidates.isEmpty()) continue;

            Pair chosen = candidates.get(random.nextInt(candidates.size()));
            int nr = top.first + chosen.first * (offsetWidth + 1);
            int nc = top.second + chosen.second * (offsetWidth + 1);

            // fancy math checks
            // TODO: Refact this to make it more readable
            for (int i = 0; i < offsetWidth; i++) {
                int wr, wc;
                if (chosen.first > 0 || chosen.second > 0) {
                    wr = (chosen.first == 0) ? top.first + i : offsetWidth * chosen.first + top.first;
                    wc = (chosen.second == 0) ? top.second + i : offsetWidth * chosen.second + top.second;
                } else {
                    wr = (chosen.first == 0) ? top.first + i : chosen.first + top.first;
                    wc = (chosen.second == 0) ? top.second + i : chosen.second + top.second;
                }
                if (wr >= 0 && wr < rows && wc >= 0 && wc < cols) {
                    grid[wr][wc] = '.';
                }
            }

            visited[nr][nc] = true;

            stack.add(top);
            stack.add(new Pair(nr, nc));
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r % (offsetWidth + 1) != 0 && c % (offsetWidth + 1) != 0) {
                    grid[r][c] = '.';
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }
}
