package main.com.examination.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/3/31 - 1:28
 * @describe
 */
public class FileDao {

    private static final String PATH = System.getProperty("user.dir");

    public static boolean storageFile(List<StringBuilder> list, String fileName)  {
        File file = new File(PATH + "\\" +fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,false);
            String content = "";
            for (int i =0 ;i<list.size();i++){
                content = content + (i+1) + "、" + list.get(i).toString() + "\n";
            }
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static List<StringBuilder> readFile(File file) {
        List<StringBuilder> list = new ArrayList<>();
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        if (!file.exists() || !file.getName().contains("txt")){
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String raw;
            for (int i = 0; null != (raw = bufferedReader.readLine()); i++) {
                if (raw.contains("、")) {
                    list.add(new StringBuilder(raw.substring(raw.indexOf("、") + 1, raw.length() - 1)));
                } else {
                    list.add(new StringBuilder(raw));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
