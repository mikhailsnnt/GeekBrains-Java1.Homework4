import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import javafx.util.Pair;

import java.util.Scanner;

public class TicTacToeGame {
    private int x;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getFieldSize(scanner);
        char[][] field = new char[n][n];
        fillField(field);
        printField(field);
        int optimalRecursionDepth = 1;//  Cколько ходов в глубину прощитывать
        if (n == 3)
            optimalRecursionDepth = 3;
        if (n == 5)
            optimalRecursionDepth = 2;
        ComputerLogic AI = new ComputerLogic(optimalRecursionDepth);
        while (true) {
            playerMove(field, scanner);
            if(manageGameResult(field))
                break;
            computerMove(field,AI);
            if(manageGameResult(field))
                break;
        }
    }

    static void printField(char[][] field) {
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field.length; ++j)
                System.out.print(field[i][j] + " ");
            System.out.println();
        }
    }

    static void printMadeMoveMessage(String subject, char[][] field) {
        System.out.printf("%s made his move:%n", subject);
        printField(field);
    }

    static void fillField(char[][] field) {
        for (int i = 0; i < field.length; ++i)
            for (int j = 0; j < field.length; ++j)
                field[i][j] = '*';
    }

    static boolean cellIsEmpty(int x, int y, char[][] field) {
        return field[y][x] == '*';
    }

    static void playerMove(char[][] field, Scanner scanner) {
        boolean isFirstTry = true;
        int x, y;
        do {
            if (isFirstTry)
                System.out.println("It's your turn...");
            else
                System.out.println("Try again...");
            x = getCoordinate("Horizontal", field.length, scanner);
            y = field.length - 1 -  getCoordinate("Vertical", field.length, scanner);
            isFirstTry = false;
        }
        while (!cellIsEmpty(x, y, field));
        field[y][x] = 'X';
        printMadeMoveMessage("Player", field);
    }

    static int getCoordinate(String name, int fieldSize, Scanner scanner) {
        int coordinate ;
        do {
            System.out.printf("Enter %s-coordinate ([1,%s]): %n", name, fieldSize);
            coordinate = scanner.nextInt() - 1;
        }
        while (coordinate < 0 || coordinate >= fieldSize);
        return  coordinate;
    }

    static int getFieldSize(Scanner scanner) {
        int n;
        do {
            System.out.println("Enter field size(odd):");
            n = scanner.nextInt();
        }
        while (n<3 || (n % 2 == 0));
        return n;
    }

    static int getGameResult(char[][] field) {
        int gameResult = gameWon(field);
        if (gameResult !=0 )
            return gameResult;
        if (noEmptyCells(field)) {
            return 1;
        }
        return 0;
    }
    static void computerMove(char[][] field, ComputerLogic AI)
    {
        Pair<Integer,Integer> move = AI.getOptimalMove(field);
        System.out.printf("Computer goes : %s %s %n",move.getKey() + 1 , move.getValue() + 1);
        field[move.getValue()][move.getKey()] = '0';
        printField(field);
    }
    static boolean manageGameResult(char[][] field) {
        int gameResult = getGameResult(field);
        switch (gameResult){
            case 0:
                return false;
            case -1:
                System.out.println("Computer won!!");
                break;
            case 2:
                System.out.println("Player won!!");
                break;
            default:
                System.out.println("Tie!!!");
                break;
        }
        return true;

    }

    static boolean noEmptyCells(char[][] field) {
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field.length; ++j) {
                if (cellIsEmpty(i, j, field))
                    return false;
            }
        }
        return true;
    }

    static int gameWon(char[][] field) {
        for(int i = 0;i<field.length;++i)
        {
            if(checkColumn(i,field))
                return codeOfCell(i,0,field);
            if(checkRow(i,field))
                return codeOfCell(0,i,field);
        }
        if( checkDiagonals(field))
            return codeOfCell(field.length/2 , field.length/2,field );
        return 0;
    }

    static boolean checkRow(int rowNum, char[][] field){
        for(int i = 1;i<field.length;++i)
        {
            if (cellIsEmpty(i, rowNum,field) || (field[rowNum][i] != field[rowNum][0]))
                return false;
        }
        return true;
    }
    static boolean checkColumn(int columnNum, char[][] field){
        for(int i = 1;i<field.length;++i)
        {
            if (cellIsEmpty(columnNum,i,field) || field[i][columnNum] != field[0][columnNum])
                return false;
        }
        return true;
    }
    static boolean checkDiagonals(char[][] field)
    {
        boolean winningDiagonal = true;
        for(int i = 1;(i<field.length)&&winningDiagonal;++i)
        {
            if(cellIsEmpty(i,i,field) ||  field[i][i]!=field[0][0])
                winningDiagonal = false;
        }
        if (winningDiagonal)
            return true;
        winningDiagonal = true;
        for(int i = 1;(i<field.length)&&winningDiagonal;++i)
        {
            if(cellIsEmpty(field.length-1-i,i,field) ||  field[field.length-1-i][i]!=field[field.length-1][0])
                winningDiagonal = false;
        }
        return winningDiagonal;
    }
    static int codeOfCell(int x , int y, char[][] field)
    {
        if (field[y][x] == 'X')
            return 2;
        else if (field[y][x] == '0')
            return -1;
        else
            return 0;
    }


}
