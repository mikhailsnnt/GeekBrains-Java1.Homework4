import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        int n = getFieldSize();
    }
    static int getFieldSize()
    {
        int n;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Enter field size(odd):");
            n = scanner.nextInt();
        }
        while(n%2==0);
        scanner.close();
        return n;
    }
}
