package encryptdecrypt;

import java.io.*;

import java.nio.file.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String mode;
        int key;
        String data = "";

        String alg = "";

        for (int i = 0, j = 1; j < args.length; i += 2, j += 2) {
            map.put(args[i], args[j]);

        }

        //map.forEach((x, y) -> System.out.println(x + " = " + y));

        if(map.containsKey("-alg")){
            alg = map.get("-alg");
        }else{
            alg = "shift";
        }

        if (map.get("-mode") != null) {
            mode = map.get("-mode");
        } else {
            mode = "enc";
        }

        if (map.get("-key") != null) {
            key = Integer.parseInt(map.get("-key"));
        } else {
            key = 0;
        }

        if(map.containsKey("-data") && !map.containsKey("-in")){
            data = map.get("-data");
        }else if(!map.containsKey("-data") && map.containsKey("-in")){
            try {
                data = readInFile(map.get("-in"));
            } catch (IOException e) {
                System.out.println("Error : Cannot read file: " + e.getMessage());
            }
        }else if(map.containsKey("-data") && map.containsKey("-in")){
            data = map.get("-data");
        }else{
            data = "";
        }

        EncryptionFactory encryptionFactory = new EncryptionFactory();
        Encryption encryption = encryptionFactory.getEncryption(alg);

        String result;
        if(mode.equals("enc")){
            result = encryption.encryptMessage(data, key);
        }else{
            result = encryption.decryptMessage(data, key);
        }


        if(map.containsKey("-out")){
            writeOutFile(map.get("-out"), result);
        }else {
            System.out.println(result);
        }



    }

    // Читаем файл
    public static String readInFile(String filePathIn) throws IOException, IOException {
        return new String(Files.readAllBytes(Paths.get(filePathIn)));
    }

    // Пишем в файл
    public static void writeOutFile(String filePathOut, String decString){
        File file = new File(filePathOut);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(decString); // Пишем в файл полученную зашифрованную строку
        } catch (IOException e) {
            System.out.printf("Error : An exception occurs %s", e.getMessage());
        }
    }
}
