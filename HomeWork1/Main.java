package HomeWork1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        String folder = "D:/Games";
        File gamesDir = new File(folder);
        if (!gamesDir.exists()) {
            boolean gamesCreated = gamesDir.mkdir();
            log.append("Создание папки Games: ").append(gamesCreated ? "OK" : "FAIL").append("\n");
        } else {
            log.append("Папка Games уже существует!");
        }

        String[] foldersGame = {"src", "res", "savegames", "temp"};
        for (String folderName : foldersGame) {
            File subfolders = new File(folder + "/" + folderName);
            boolean foldersCreated = subfolders.mkdir();
            log.append("Создание папки ").append(folderName).append(". ").append(foldersCreated ? "OK" : "FAIL").append("\n");
        }

        File scrDir = new File(folder + "/src");
        File mainDir = new File(scrDir + "/main");
        File testDir = new File(scrDir + "/test");
        boolean mainDirCreated = mainDir.mkdir();
        boolean testDirCreated = testDir.mkdir();
        log.append("Создание папки src/main: ").append(mainDirCreated ? "OK" : "FAIL").append("\n");
        log.append("Создание папки src/test: ").append(testDirCreated ? "OK" : "FAIL").append("\n");

        File mainJava = new File(mainDir, "Main.java");
        File utilsJava = new File(mainDir, "Utils.java");
        try {
            boolean mainJavaCreated = mainJava.createNewFile();
            boolean utilsJavaCreated = utilsJava.createNewFile();
            log.append("Создание файла HomeWork1.Main.java: ").append(mainJavaCreated ? "OK" : "FAIL").append("\n");
            log.append("Создание файла Utils.java: ").append(utilsJavaCreated ? "OK" : "FAIL").append("\n");
        }catch (IOException e){
            log.append("Ошибка при создании файлов!").append(e.getMessage());
        }

        File resDir = new File(folder + "/res");
        String[] resFolder = {"drawables", "vectors", "icons"};
        for (String res : resFolder) {
            File resD = new File(resDir, res);
            boolean resCreated = resD.mkdir();
            log.append("Создание папки res ").append(res).append(resCreated ? "OK" : "FAIL").append("\n");
        }

        File tempDir = new File(folder + "/temp");
        File tempFile = new File(tempDir, "temp.txt");

        try {
            boolean tempCreated = tempFile.createNewFile();
            log.append("Создание файла temp.txt: ").append(tempCreated ? "OK" : "FAIL").append("\n");
            FileWriter writer = new FileWriter(tempFile);
            writer.write(log.toString());
            writer.close();
            System.out.println("Успешно!");
        }catch (IOException e){
            log.append("Ошибка temp");
        }
    }
}