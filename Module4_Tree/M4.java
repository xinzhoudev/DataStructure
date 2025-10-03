package DataStructure.Module4_Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class M4 {


    

    public static void main(String[] args){

        // char operationCode = '*';
        String studentNumber = new String();
        String studentLastName = new String();
        String homeDepartment = new String();
        String program = new String();
        String year = new String();
        Comparator<Student> comp = (a, b) -> {
            return a.studentLastName.compareTo(b.studentLastName);
        };
        BinarySearchTree<Student> tree = new BinarySearchTree<>(comp);

        try{
            Path path = Paths.get(".\\DataStructure\\Module4_Tree\\tree-input.txt");
            List<String> lines = Files.readAllLines(path);
            // System.out.println(System.getProperty("user.dir"));

            for(String line: lines){
                // get the full number of it;
                String[] temp = line.split("[ ]");
                int count = 0;
                for(String s:temp){
                    if(s.isEmpty()) continue;
                    if(count == 0){
                        // operationCode = s.charAt(0);
                        studentNumber = s.substring(1, 8);
                        studentLastName = s.substring(8, s.length());
                    }
                    if(count == 1){
                        homeDepartment = s.substring(0, 5);
                        program = s.substring(5, s.length());
                    }
                    if(count == 2){
                        year = s;
                    }
                    count++;
                }
                Student student = new Student(studentNumber, studentLastName, homeDepartment, program, year);
                tree.insert(student);
            }
            System.out.println(tree.size() + " " + tree.positions());
            for(Position<Student> node: tree.positions()){
                System.out.println(node.getElement().studentNumber + " " + node.getElement().studentLastName);
            }
            // get the breath first search result.
            System.out.println();
            System.out.println("The BFS result is: ");
            Iterable<Position<Student>> it = tree.BFS();
            for(Position<Student> p:it){
                System.out.println(p.getElement().studentNumber + " " + p.getElement().studentLastName);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    } 
}




class Student{
    // char operationCode;
    String studentNumber;
    String studentLastName;
    String homeDepartment;
    String program;
    String year;
    public Student(String studentNumber, String studentLastName, String homeDepartment, String program, String year) {
        // this.operationCode = operationCode;
        this.studentNumber = studentNumber;
        this.studentLastName = studentLastName;
        this.homeDepartment = homeDepartment;
        this.program = program;
        this.year = year;
    }
}
