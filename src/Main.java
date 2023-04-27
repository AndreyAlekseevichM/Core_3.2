import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(80, 95, 5, 25.5);
        GameProgress game2 = new GameProgress(70, 120, 10, 53.8);
        GameProgress game3 = new GameProgress(50, 150, 20, 94.6);
        saveGame("D://myGames//savegames//save1.dat", game1);
        saveGame("D://myGames//savegames//save2.dat", game2);
        saveGame("D://myGames//savegames//save3.dat", game3);

        ArrayList<String> list = new ArrayList<>();
        list.add("D://myGames//savegames//save1.dat");
        list.add("D://myGames//savegames//save2.dat");
        list.add("D://myGames//savegames//save3.dat");

        zipFiles("D://myGames//savegames//zip.zip", list);


        File save1 = new File("D://myGames//savegames//save1.dat");
        File save2 = new File("D://myGames//savegames//save2.dat");
        File save3 = new File("D://myGames//savegames//save3.dat");

        if (save1.delete()) {
            System.out.println("Файл save1.dat удален");
        }
        if (save2.delete()) {
            System.out.println("Файл save2.dat удален");
        }
        if (save3.delete()) {
            System.out.println("Файл save3.dat удален");
        }
    }

    private static void saveGame(String link, GameProgress game) {
        try (FileOutputStream out = new FileOutputStream(link);
             ObjectOutputStream object = new ObjectOutputStream(out)) {
            object.writeObject(game);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("создано сохранение " + game + " в " + link);
    }

    private static void zipFiles(String link, ArrayList<String> list) {

        try (FileOutputStream fos = new FileOutputStream(link); FileInputStream fis = new FileInputStream(link)) {

            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (String srcFile : list) {
                File fileToZip = new File(srcFile);

                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[fis.available()];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
            zipOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list + " записан в архив " + link);
    }
}






