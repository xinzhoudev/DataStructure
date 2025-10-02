package DataStructure.Module3_HashTableMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class M3 {
    public static void main(String[] args){
        UnsortedTableMap<Integer, Integer> map = new UnsortedTableMap<>();
        map.put(1,2);
        map.put(2,3);
        map.put(4,10);
        for(Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
        ChainHashMap<Integer,Integer> chainMap = new ChainHashMap<>();
        chainMap.put(1, 3); 
        chainMap.put(2, 3); 
        chainMap.put(3, 100); 
        chainMap.put(9, 3);
        for(Entry<Integer,Integer> entry:chainMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        ChainHashMap<String,Integer> pap = new ChainHashMap<>();
        String filePath = "F:\\UCIrvine-MSWE\\Courses\\DataStructure\\Module3_HashTableMap\\pride-and-prejudice.txt";
        AtomicInteger index = new AtomicInteger(1);
        try{
            Files.lines(Paths.get(filePath))
            // .limit(30)
            .forEach(line -> {
                String[] str = line.split("[ \\n\\t,.:]");
                // System.out.println(index.getAndIncrement() + ": " + line + " " + Arrays.toString(str));
                for(String s: str){
                    pap.put(s, 1);  
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(pap.size());
        // calculate the anagrams of the word;
        ChainHashMap<String, Integer> wordMap = new ChainHashMap<>();
        for(Entry<String,Integer> e:pap.entrySet()){
            char[] chars = e.getKey().toCharArray();
            Arrays.sort(chars);
            wordMap.put(new String(chars), 1);
        }
        System.out.println(wordMap.size());
    }
}
