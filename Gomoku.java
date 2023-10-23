import java.util.Scanner;

public class Gomoku {
    char[][] board;
    char player;
    int movesX;
    int movesO;

    public Gomoku() {
        board = new char[15][15];
        player = 'X';
        movesX=0;
        movesO=0;
        initializeBoard(board);
    }

    // Getter
    public char cell(int row, int col) {
        return board[row][col];
    }

    public char player() {
        return player;
    }
    public int moveX(){
        return movesX;
    }
    public int moveO(){
        return movesO;
    }

    // Mutator
    public void Gomoku(char [][] b, char p, int mX,int mO){
        board=b;
        player=p;
        movesX=mX;
        movesO=mO;
    }
    
    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = player;
            if (player == 'X') {
                movesX++;
            } else {
                movesO++;
            }
            
            if (checkWin(row, col)) {
                System.out.println("Player " + player() + " wins!");
                System.out.println(move());
                System.exit(0);
            }
            player = (player == 'X') ? 'O' : 'X';
        }
    }

    // Printer
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < 15) {
            int j = 0;
            while (j < 15) {
                sb.append(board[i][j]);
                if (j < 14) {
                    sb.append(" | ");
                }
                j++;
            }
            sb.append("\n");
            if (i < 14) {
                sb.append("----------------------------------------------------------\n");
            }
            i++;
        }
        return sb.toString();
    }
    
    public String move(){
        return("Total moves made by X: " + moveX() + '\n'+ 
        "Total moves made by O: " + moveO());
    }

    private static void initializeBoard(char[][] board) {
        int i = 0;
        while (i < board.length) {
            int j = 0;
            while (j < board[i].length) {
                board[i][j] = ' ';
                j++;
            }
            i++;
        }
    }

    public boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 15) &&
                (col >= 0 && col < 15) && board[row][col] == ' ';
    }

    public boolean checkWin(int row, int col) {
        char current = board[row][col];
        int count;

        // Check horizontal
        int i = row;
        count = 0;
        while (i < 15 && board[i][col] == current) {
            count++;
            i++;
        }
        i = row - 1;
        while (i >= 0 && board[i][col] == current) {
            count++;
            i--;
        }
        if (count >= 5) {
            return true;
        }

        // Check vertical
        int j = col;
        count = 0;
        while (j < 15 && board[row][j] == current) {
            count++;
            j++;
        }
        j = col - 1;
        while (j >= 0 && board[row][j] == current) {
            count++;
            j--;
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonal (top-left to bottom-right)
        i = row;
        j = col;
        count = 0;
        while (i < 15 && j < 15 && board[i][j] == current) {
            count++;
            i++;
            j++;
        }
        i = row - 1;
        j = col - 1;
        while (i >= 0 && j >= 0 && board[i][j] == current) {
            count++;
            i--;
            j--;
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonal (bottom-left to top-right)
        i = row;
        j = col;
        count = 0;
        while (i < 15 && j >= 0 && board[i][j] == current) {
            count++;
            i++;
            j--;
        }
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < 15 && board[i][j] == current) {
            count++;
            i--;
            j++;
        }
        return count >= 5;
    }

    public static void main(String[] args) {
        Gomoku game = new Gomoku();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(game.toString());
            System.out.println("Valid space for each row and column: 0 - 14");
            System.out.println("Player " + game.player() + ", enter your move (row):");
            int row = scanner.nextInt();
            System.out.println("Player " + game.player() + ", enter your move (column):");
            int col = scanner.nextInt();

            game.makeMove(row, col);
            
        }
    }
}
