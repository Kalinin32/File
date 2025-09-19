package HomeWork3;

import HomeWork2.GameProgress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void openZip(String zip, String path) {
        try (FileInputStream fileInputStream = new FileInputStream(zip);
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {

            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String fileName = entry.getName();
                File newFile = new File(path + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zipInputStream.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameProgress openProgress(String savePath) {
        try (FileInputStream fis = new FileInputStream(savePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // D:\Games\savegames\saves.zip\save1.dat;
    public static void main(String[] args) {
        String games = "D:\\Games";
        String gamesSave = games + "\\savegames";

        String fileZip = gamesSave + "\\saves.zip";
        String fileSave = gamesSave + "\\save1.dat";

        System.out.println("Распаковка");
        openZip(fileZip, gamesSave);

        System.out.println("сохранения");
        GameProgress progress = openProgress(fileSave);

        if (progress != null) {
            System.out.println("Загружено:");
            System.out.println(progress);
        } else {
            System.out.println("Не удалось сохранить");
        }
    }
}