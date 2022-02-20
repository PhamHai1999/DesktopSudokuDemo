package Sudoku.persistence;

import Sudoku.problemDomain.IStorage;
import Sudoku.problemDomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {
    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gameData.txt"
    );

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
       try {
           FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
           objectOutputStream.writeObject(game);
           objectOutputStream.close();
       }catch (IOException e){
           throw new IOException("Unable to access game data");
       }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
        }catch (ClassNotFoundException e){
            throw new IOException("File not found");
        }
        return null;
    }
}
