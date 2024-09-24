import java.util.*;
import java.io.*;

public class TicTacToe {
   private char[][] board;
   
   //starting new game
   public TicTacToe() {
      board = new char[3][3];
      setUpBoard();
   }//end TicTacToe
   
   //initializes the board
   public void setUpBoard() {
      for (int i=0; i < 3; i++) {
         for (int j=0; j < 3; j++){
            board[i][j] = '-';
         }
      }
   }//end setUpBoard
   
   
   //displays board
   public void printBoard() {
      System.out.println("\nCurrent Board: ");
      for (int i=0; i < 3; i++) {
         for (int j=0; j < 3; j++) {
            System.out.print(board[i][j] + " ");
         }
         System.out.print("\n");
      }
      
   }//end printBoard
   
   //checks if move is allowed
   public boolean isValid(int row, int col) {
      if (row >= 0 && row < 3 && col >= 0 && col < 3) {
         return board[row][col] == '-';
      }
      else {
         return false;
      }
      
   }//end validMove
   
   //performs next move
   public void currMove(int row, int col, char p) {
       if (isValid(row, col)) {
         board[row][col] = p;
       }
   }//end currMove
   
   public void resetGame() {
      setUpBoard();
      playingGame();
   }//end resetGame
   
   public boolean checkWinner(char p) {
      //check columns
      for (int i = 0; i < 3; i++) {
         if (board[0][i] == p && board[1][i] == p && board[2][i] == p) {
            return true;
         }
      }
      
      //check rows
      for (int i = 0; i < 3; i++) {
         if (board[i][0] == p && board[i][1] == p && board[i][2] == p) {
            return true;
         }
      }
      
      //check diagonals
      if (board[0][0] == p && board[1][1] == p && board[2][2] == p) {
         return true;
      }
      
      if (board[0][2] == p && board[1][1] == p && board[2][0] == p) {
         return true;
      }
      return false;
   }//end checkWinner
   
   public boolean isTie(){
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (board[i][j] == '-') {
               return false;
            }
         }
      }
      return true;
   }//end isTie
   
   public void playingGame() {
      Scanner sc = new Scanner(System.in);
      char currPlayer = 'X';  //player 1 begins with X
      boolean isPlaying = true;
      
      //game loop
      while (isPlaying) {
         printBoard();
         System.out.println("Player " + (currPlayer == 'X' ? "1 (X)" 
         : "2 (O)") + ", enter your move (row[0-2] and col[0-2]:");
         
         int row = -1, col = -1;
         boolean validInput = false;

         // Keep prompting for integer
         while (!validInput) {
            try {
               System.out.print("Row: ");
               row = sc.nextInt(); // Get row
               System.out.print("Column: ");
               col = sc.nextInt(); // Get column

               // If integers, break out of loop
               validInput = true;
            } catch (InputMismatchException e) {
               System.out.println("Invalid input. Please enter integers only.");
               sc.next(); // Clear the invalid input from the scanner
            }
         }         //check if move is valid
         
         if (isValid(row, col)) {
            currMove(row, col, currPlayer);  //performs move
         
         //check if current player won
         if (checkWinner(currPlayer)) {
            printBoard();
            System.out.println("Player " + (currPlayer == 'X' ? "1 (X)"
            : "2 (O)") + " Wins!");
            
            isPlaying = false; //ends game
         }
         //check if tie
         else if (isTie()) {
            printBoard();
            System.out.println("The Game is a Tie!");
            isPlaying = false; //ends game
         }
         else {
            currPlayer = (currPlayer == 'X') ? 'O' : 'X';
         }
       } else {
            System.out.println("Invalid move. Try Again: ");
         }  
      }
      
      //prompt for new game
      System.out.println("Would you like to play again? Input Yes or No.");
      sc.nextLine();
      String answer = sc.nextLine().trim().toLowerCase();
      
      if (answer.equals("yes")) {
         resetGame();
      }
      else {
         System.out.println("Thank you for playing!");
      }
      
      sc.close(); //close scanner
   }//end play
      
      public static void main(String[] args) {
         TicTacToe game = new TicTacToe();   
         
         game.playingGame();
      }
}//end program