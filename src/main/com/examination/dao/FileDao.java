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

    public static boolean storageFile(List<StringBuilder> list, String fileName) throws IOException {
        File file = new File(PATH + "\\" +fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file,false);
        String content = "";
        for (int i =0 ;i<list.size();i++){
            content = content + (i+1) + "、" + list.get(i).toString() + "\n";
        }
        fileOutputStream.write(content.getBytes());
        return true;
    }
}
