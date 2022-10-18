import java.util.Scanner;

public class battleship{
  public static void main(String[] args){

  final int SHIP_SIZE = 4; // constant for the size of the ship
  final int DIMENSION = 10; // constant for the size of the board (square)

  char[][] board = new char[DIMENSION][DIMENSION]; //create multidimensional array to display to player
  char[][] hidden_board = new char[DIMENSION][DIMENSION]; //create multidimensional array to keep track
  int score = 0; //keep score
  initialize_board(board, DIMENSION); //initialize the playing board
  initialize_board(hidden_board, DIMENSION); //initialize the hidden board used to control the playing board
  place_ship(hidden_board, SHIP_SIZE, DIMENSION); //randomly place the ship somewhere on the board (has to be on hidden board since we don't want the player to see it)

  while (true){ //game begins
    String value1 = " ";
    String value2 = " ";

    //check to see if every position on the two boards are equal. If they are not, keep asking the player to target. If they are, end the game the show the score
    for (int i = 0; i < DIMENSION; i++){
      for (int j = 0; j < DIMENSION; j++){
        value1 = value1 + board[i][j];
        value2 = value2 + hidden_board[i][j];}}

    if (value1.compareTo(value2) != 0) //if every position on the two boards are not equal (if targets hasn't been destroyed)
      {
        print_board(board,DIMENSION);

        Scanner input = new Scanner(System.in); //targeting system
        System.out.println("Enter coordinate to target (eg, A1)");
        String coordinates = input.nextLine();
        char[] array2 = coordinates.toCharArray();
        System.out.println(coordinates);
        System.out.println(array2);
        if (coordinates != "")//checking for blank inputs
         {
          if (((int)array2[0]>=65 && (int)array2[0]<=90) && (((int)array2[1])<=57 && (int)array2[1]>=48)) // checking for invalid inputs
          {
            char a = '*';
            char b = 'x';
            char c = '#';
            //determine if a hit is scored by comparing positions on the hidden_board to the player's coordinate position
            if (hidden_board[array2[1]-48][(((int)array2[0])-65)] == a)
            {
              board[array2[1]-48][(((int)array2[0])-65)] = 'x';
              hidden_board[array2[1]-48][(((int)array2[0])-65)] = 'x';
              System.out.println("A hit is scored!");
              score = score + 1;
            }
            else if (hidden_board[array2[1]-48][(((int)array2[0])-65)] == b || hidden_board[array2[1]-48][(((int)array2[0])-65)] == c)
            {
              System.out.println("You already targeted this location. Select another");
            }
            else if (hidden_board[array2[1]-48][(((int)array2[0])-65)] != a)
            {
              board[array2[1]-48][(((int)array2[0])-65)] = '#';
              hidden_board[array2[1]-48][(((int)array2[0])-65)] = '#';
              System.out.println("A hit is not scored. Try again.");
              score = score + 1;
            }
          }
          else
          {
            System.out.println("Invalid Input. Please Try again.");
          }
        }
        else
        {System.out.println("Invalid Input. Please Try again.");}
      }
      else if (value1.compareTo(value2) == 0)// if every position on the two boards are equal
        {
          print_board(board,DIMENSION);
          System.out.println("You destroyed all the targets. You win!");
          System.out.println("Your score is:" + score);
          System.exit(0); //exit the game
        }
    }
  }
  public static void initialize_board(char[][] board, int DIMENSION) //initalize position of array
  {
    for(int i = 0; i < DIMENSION; i++){
      for(int j = 0; j < DIMENSION; j++){
        board[i][j] = ' ';
      }}
  }
  public static void print_board(char[][] board, int DIMENSION)
  {
    //print out current state of the board for the user to see
    System.out.print("  ");
    for(int a = 65; a < 65+DIMENSION; a++)
    {
      System.out.print(" " + (char)a + "  ");
    }
    for(int b = 0; b < DIMENSION; b++){
      System.out.print("\n" + " ");
      for(int c = 0; c < DIMENSION; c++){
        System.out.print("+---");
      }
      System.out.print("+" + "\n" + b);
      for (int d = 0; d < DIMENSION; d++){
        System.out.print("| " + board[b][d] + " ");
      }
      System.out.print("|");
    }
    System.out.print("\n" + " ");
    for(int c = 0; c < DIMENSION; c++){
      System.out.print("+---");
    }
    System.out.print("+" + "\n");
  }

  public static void place_ship(char[][] board, int shipsize, int dimension)
  {
    //place a ship vertical or horizontally onto the board
    double rand_orientation = (Math.random());
    char a = '*';
    if (rand_orientation >= 0.5) //horizontal
    {
      int random_horizontal = (int) (Math.random()*(dimension-shipsize));
      int random_vertical = (int) (Math.random()*dimension);
      for(int i = 0; i < shipsize; i++)
      {
        //System.out.println(random_horizontal +" "+ random_vertical);
        board[random_vertical][random_horizontal] = a;
        random_horizontal = random_horizontal + 1;
      }
    }
    else if (rand_orientation <= 0.5) //vertical
    {
      int random_horizontal = (int) (Math.random()*dimension);
      int random_vertical = (int) (Math.random()*(dimension-shipsize));
      for(int j = 0; j < shipsize; j++)
      {
        //System.out.println(random_horizontal +" "+ random_vertical);
        board[random_vertical][random_horizontal] = a;
        random_vertical = random_vertical + 1;
      }
    }

  }

}
