import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.Scanner;

public class TicTacToeGame {
    private int x;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getFieldSize(scanner);
        char [][] field = new char[n][n];
        fillField(field);
        printField(field);
        while (true){
            playerMove(field,scanner);

        }
    }
    static void printField(char[][] field){
        for(int i = 0 ; i<field.length;++i)
        {
            for (int j = 0 ; j<field.length;++j)
                System.out.print(field[i][j]+" ");
            System.out.println();
        }
    }
    static void printMadeMoveMessage(String subject , char[][] field){
        System.out.printf("%s made his move:%n",subject);
        printField(field);
    }
    static void fillField(char[][] field){
        for(int i = 0;i<field.length;++i)
            for(int j = 0 ;j<field.length;++j)
                field[i][j] = '*';
    }
    static boolean cellIsEmpty(int x, int y , char[][] field){
        return field[y][x] == '*';
    }
    static void playerMove(char[][] field, Scanner scanner)
    {
        boolean isFirstTry = true;
        int x, y;
        do{
            if (isFirstTry)
                System.out.println("It's your turn...");
            else
                System.out.println("Try again...");
            x = getCoordinate('H', field.length, scanner);
            y = getCoordinate('V', field.length, scanner);
            isFirstTry = false;
        }
        while (!cellIsEmpty(x,y,field));
        field[y][x] = 'X';
        printMadeMoveMessage("Player", field);
    }
    static int getCoordinate(char name, int fieldSize, Scanner scanner){
        int coordinate = -1;
        do{
            System.out.printf("Enter %s-coordinate ([1,%s]): %n",name,fieldSize);
            coordinate= scanner.nextInt()-1;
        }
        while (coordinate <0 || coordinate>=fieldSize);
        return coordinate;
    }
    static int getFieldSize(Scanner scanner)
    {
        int n;
        do{
            System.out.println("Enter field size(odd):");
            n = scanner.nextInt();
        }
        while(n%2==0);
        return n;
    }


}
