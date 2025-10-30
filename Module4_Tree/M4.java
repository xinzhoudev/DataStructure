package DataStructure.Module4_Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class M4 {

    public static void main(String[] args){
        char operationCode = ' ';
        String studentNumber = new String();
        String studentLastName = new String();
        String homeDepartment = new String();
        String program = new String();
        String year = new String();
        Comparator<Student> comp = (a, b) -> {
            return a.studentLastName.compareTo(b.studentLastName);
        };
        // Task-1: Build a binary search tree(BST) using the data from tree-input.txt.
        // The tree should be ordered by the student's last name.(using a case-sensitive comparison)
        BinarySearchTree<Student> tree = new BinarySearchTree<>(comp);

        try{
            Path path = Paths.get(".\\DataStructure\\Module4_Tree\\tree-input.txt");
            List<String> lines = Files.readAllLines(path);
            // System.out.println(System.getProperty("user.dir"));

            for(String line: lines){
                // get the full number of it;
                operationCode = line.charAt(0);
                studentNumber = line.substring(1, 8);
                studentLastName = line.substring(8, 33);
                homeDepartment = line.substring(33, 37);
                program = line.substring(37, 41);
                year = line.substring(41);
                System.out.println(operationCode == 'I');
                Student student = new Student(operationCode, studentNumber, studentLastName, homeDepartment, program, year);
                if(student.operationCode == 'I') tree.insert(student);
                else tree.remove(student);
            }
            System.out.println(tree.size());
            File file = new File(".\\task2.txt");
            File parentDir = file.getParentFile();
            System.out.println(parentDir);
            String newline = System.lineSeparator();
            System.out.println(System.getProperty("user.dir"));
            try(FileWriter writer = new FileWriter(".\\DataStructure\\Module4_Tree\\task2.txt")){
                // Task-2: Traverse the binary search tree recursively, printing out the nodes in ascending logical order: do a depth-first, in-order tree traversal.
                for(Position<Student> node: tree.positions()){
                    writer.write(node.getElement().studentNumber + " " + node.getElement().studentLastName + " " + node.getElement().homeDepartment + " " + 
                    node.getElement().program + " " + node.getElement().year + newline);
                }
                }catch(IOException e){
                    e.printStackTrace();
                }
            // get the breath first search result.
            System.out.println();
            // Task-3: Traverse the Binary Search Tree, starting at the top level(the root node), and proceeding downwards level-by-level.
            // At each level, print out the nodes from left to right. In other words, do a BFS.(tree.BFS());
            // user Bread-Frist Search, preorder, inorder and postorder to traverse the binarySearchTree.
            Iterable<Position<Student>> it = tree.BFS();
            Iterable<Position<Student>> it_preorder = tree.preorder();
            Iterable<Position<Student>> it_inorder = tree.inorder();
            Iterable<Position<Student>> it_postorder = tree.postorder();
            System.out.println("The BFS result is: ");
            for(Position<Student> p:it){
                System.out.println(p.getElement().studentLastName);
            }
            System.out.println("The preorder result is: ");
            for(Position<Student> p:it_preorder){
                System.out.println(p.getElement().studentLastName);
            }
            System.out.println("The inorder result is: ");
            for(Position<Student> p:it_inorder){
                System.out.println(p.getElement().studentNumber + " " + p.getElement().studentLastName);
            }
            System.out.println("The postorder result is: ");
            for(Position<Student> p:it_postorder){
                System.out.println(p.getElement().studentNumber + " " + p.getElement().studentLastName);
            }



        }catch(IOException e){
            e.printStackTrace();
        }
        
    } 
}

class Student{
    char operationCode;
    String studentNumber;
    String studentLastName;
    String homeDepartment;
    String program;
    String year;
    public Student(char operationCode, String studentNumber, String studentLastName, String homeDepartment, String program, String year) {
        this.operationCode = operationCode;
        this.studentNumber = studentNumber;
        this.studentLastName = studentLastName;
        this.homeDepartment = homeDepartment;
        this.program = program;
        this.year = year;
    }
}
