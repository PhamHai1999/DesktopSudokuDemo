package Sudoku.UserInterface;

import Sudoku.problemDomain.SudokuGame;

public interface IUserInterfaceContract {
    interface EventListener {
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }

    interface View {
        void setListener(EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBroad(SudokuGame game);
        void showDialog(String Message);
        void showError(String Message);
    }
}
