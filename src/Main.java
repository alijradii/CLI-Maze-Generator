import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows and cols: ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        System.out.println("Enter the wall width: ");
        int offsetWidth = sc.nextInt();

        boolean validRows = rows % (offsetWidth + 1) == 1;
        boolean validCols = cols % (offsetWidth + 1) == 1;

        if(!validRows || !validCols) {
            throw new RuntimeException("Invalid grid size");
        }

        int[][] grid = new int[rows][cols];
        System.out.println("Hello world");
    }
}