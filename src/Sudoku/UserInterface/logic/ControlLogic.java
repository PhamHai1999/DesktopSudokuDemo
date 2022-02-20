package Sudoku.UserInterface.logic;

import Sudoku.UserInterface.IUserInterfaceContract;
import Sudoku.computationLogic.GameLogic;
import Sudoku.constants.GameState;
import Sudoku.constants.Messages;
import Sudoku.problemDomain.IStorage;
import Sudoku.problemDomain.SudokuGame;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyGridState();
            newGridState[x][y] = input;

            gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);

            view.updateSquare(x,y,input);
            if(gameData.getGameState() == GameState.COMPETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }

        }catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(GameLogic.getNewGame());
            view.updateBroad(storage.getGameData());
        }catch (IOException e){
            view.showError(Messages.ERROR);
        }

    }
}
