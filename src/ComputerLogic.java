import javafx.util.Pair;

import java.util.Queue;

public class ComputerLogic {
    private int AIRecursionDepth;

    public  ComputerLogic(int RecursionDepth){
        AIRecursionDepth = RecursionDepth;
    }
    public Pair<Integer , Integer> getOptimalMove(char [][] field) {
        int bestOutcome = 100;
        int outcomeX = -1 ,outcomeY= -1;
        for(int x = 0;x<field.length;++x)
        {
            for(int y =  0;y<field.length;++y)
            {
                if (TicTacToeGame.cellIsEmpty(x,y,field)){
                    int analyse = analisePlayerOutcome(field,AIRecursionDepth,x,y);
                    if (bestOutcome>analyse)
                    {
                        bestOutcome = analyse;
                        outcomeX = x;
                        outcomeY = y;
                    }
                }

            }
        }
        return new Pair<>(outcomeX,outcomeY);

    }
    private int analisePlayerOutcome(char[][] field,int recursionDepth, int lastX , int lastY) {
        field[lastY][lastX] = '0';
        int currentGameResult = TicTacToeGame.getGameResult(field);
        if (currentGameResult != 0){
            field[lastY][lastX] = '*';
            return  currentGameResult;}
        int maxResult = -100;
        for(int x = 0;x<field.length;++x)
        {
            for(int y =  0;y<field.length;++y)
            {
                if (TicTacToeGame.cellIsEmpty(x,y,field))
                    maxResult = Integer.max(maxResult,analiseComputerOutcome(field,recursionDepth,x,y));
            }
        }
        field[lastY][lastX] = '*';
        return maxResult;
    }
    private int analiseComputerOutcome(char[][] field,int recursionDepth, int lastX , int lastY) {
        field[lastY][lastX] = 'X';
        int currentGameResult = TicTacToeGame.getGameResult(field);
        if (currentGameResult != 0 || (recursionDepth==0))
        {
            field[lastY][lastX] = '*';
            return  currentGameResult;
        }
        int minResult = 100;
        for(int x = 0;x<field.length;++x)
        {
            for(int y =  0;y<field.length;++y)
            {
                if (TicTacToeGame.cellIsEmpty(x,y,field))
                    minResult = Integer.min(minResult,analisePlayerOutcome(field,recursionDepth-1,x,y));
            }
        }
        field[lastY][lastX] = '*';
        return  minResult;
    }
}
