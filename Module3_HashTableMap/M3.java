package DataStructure.Module3_HashTableMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class M3 {

    // task-1: HashTable.java.
    public static void hashTableTest(String[] s){
        HashTable set = new HashTable(1009);
        for(int i = 0; i < s.length; i++){
            set.insert(s[i]);
        }
        // print all of the elements.
        set.printTable();
    }

    public static int wordFrequency(String path){
        HashTable set = new HashTable(1009);
        String filePath = path;
        try{
            Files.lines(Paths.get(filePath))
            .forEach(line -> {
                String[] str = line.toLowerCase().split("[^a-zA-Z0-9]");
                // System.out.println(index.getAndIncrement() + ": " + line + " " + Arrays.toString(str));
                for(String s: str){
                    // filter the word whose length <= 1;
                    // capacity = 1009
                    if(s.length() == 0) continue;
                    char[] chars = s.toCharArray();
                    Arrays.sort(chars);
                    String temp = new String(chars);
                    if(!set.contains(temp)) set.insert(temp);
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        // System.out.println(set.size());
        set.printTable();
        return set.size();
    }


    public static void main(String[] args){
        System.out.println(System.getProperty("user.dir"));

        String[] strs = {"12ufe0w90eu209r", "0", "300", "3", "1","2", "owdjwio", "2901e"};
        String path = "F:\\UCIrvine-MSWE\\Courses\\DataStructure\\Module3_HashTableMap\\pride-and-prejudice.txt";
        // String testPath = "F:\\UCIrvine-MSWE\\Courses\\DataStructure\\Module3_HashTableMap\\test.txt";
        System.out.println(wordFrequency(path));
        // System.out.println(wordFrequency(testPath));
        hashTableTest(strs);

    }


}
