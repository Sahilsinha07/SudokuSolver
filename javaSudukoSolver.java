
public class javaSudukoSolver {
    public static void solveSudoku(char[][] board) {
        // char board here is the sudoku board
        helper(board, 0, 0); // calling the helper function on 0,0

    }

    public static boolean helper(char[][] board, int row, int column) {
        // we have taken row and column as argument because we need to traverse on the
        // sudoku board.

        if (row == board.length) { // base case when we have filled the whole sudoku.
            return true;
        }
        // lets create a logic to call the next recursion that is how we are going to go
        // on new row and column

        int newRow = 0;
        int newColumn = 0;
        if (column != board.length - 1) { // java is a zero index language
            // if we have not reached till the last column of the particular row
            newRow = row;
            newColumn = column + 1;
        } else {
            // if we ahe reached to the last column of a particular row.
            newRow = row + 1;
            newColumn = 0;
        }
        // now check whether there is a number given before or not on the position
        if (board[row][column] != '.') { // . (dot) means place is empty
            if (helper(board, newRow, newColumn)) { // this will call the helper function
                return true;
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                if (isSafe(board, row, column, i)) { // this function will check whether fitting the number at this
                                                     // position is safe or not
                    board[row][column] = (char) (i + '0'); // type casting because i is a integer and board is a
                                                           // character
                    if (helper(board, newRow, newColumn)) { // this condtion tells ki aage jake kisi stage pe pata chala
                                                            // udhr jo number baithaya hae vo safe nhi tha to usko
                                                            // cahneg kro.
                        return true;
                    } else {
                        board[row][column] = '.'; // if the helper function tells that the position is not safe the we
                                                  // will make that position again null. and the loop will continue and
                                                  // the next number will sit.
                    }
                }
            }
        }
        return false;
    }

    public static boolean isSafe(char[][] board, int row, int column, int number) {
        // this is a boolean condition because if it is safe to place the number than it
        // will return true else false

        // condition for row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] == (char) (number + '0')) { // here we are checking for the row and number is integer
                                                             // where as in row we have characters so we have done type
                                                             // casting
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false; // here we are checking for column that whether we encounter the same number or
                              // not in the whole column
            }
        }

        // condition for grid.
        // first we need to find the starting point of the grid.
        int startingRow = (row / 3) * 3;
        int startingColumn = (column / 3) * 3; // to find starting row and column of the grid
        // now see we just have a 3*3 grid which is a basically a 2-d matrix and we will
        // use linear search to check if our numbe ris there or not
        for (int i = startingRow; i < startingRow + 3; i++) {
            for (int j = startingColumn; j < startingColumn + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

}
