package HomeWork2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String save = "D://Games//savegames";

        GameProgress save1 = new GameProgress(100, 10, 7, 300.9);
        GameProgress save2 = new GameProgress(90, 9, 6, 250.6);
        GameProgress save3 = new GameProgress(80, 8, 5, 200.2);

        String file1 = save + "/save1.dat";
        String file2 = save + "/save2.dat";
        String file3 = save + "/save3.dat";

        saveGame(file1, save1);
        saveGame(file2, save2);
        saveGame(file3, save3);

        List<String> filesZip = new ArrayList<>();
        filesZip.add(file1);
        filesZip.add(file2);
        filesZip.add(file3);

        String zipPath = save + "/saves.zip";

        zipGame(zipPath, filesZip);

        deleteGame(filesZip);
    }

    public static void deleteGame(List<String> filesZip) {
        for (String file : filesZip) {
            File f = new File(file);
            if (f.delete()) {
                System.out.println("Файл удален");
            } else {
                System.err.println("Не удалось удалить файл");
            }
        }
        System.out.println("Сохранения заархивированы и удалены");
    }

    public static void saveGame(String save, GameProgress gameProgress) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(save);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(gameProgress);
            System.out.println("Сохранение успешное!");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения!");
        }
    }

    public static void zipGame(String zip, List<String> files) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(zip);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            for (String fileZip : files) {
                File file = new File(fileZip);
                if (!file.exists()) {
                    System.err.println("Файл не найден");
                    continue;
                }

                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] b = new byte[1024];
                    int i;
                    while ((i = fileInputStream.read(b)) > 0) {
                        zipOutputStream.write(b, 0, i);
                    }
                    zipOutputStream.closeEntry();
                    System.out.println("Добавлен архив");
                } catch (IOException e) {
                    System.out.println("Ошибка!");
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось сделать архив!");
        }
    }
}