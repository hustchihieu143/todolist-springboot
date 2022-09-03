package hieuphan.oop;

import com.example.demo.DemoApplication;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    ArrayList<Student> listStudent = new ArrayList<>();
    public void inputStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap so sinh vien:");
        int n = sc.nextInt();
        sc.nextLine();
        Student newStudent = new Student();
        for(int i = 0; i < n; i++) {
            System.out.println("cho xin cai ten:");
            newStudent.setName(sc.nextLine());
            sc.nextLine();


            listStudent.add(newStudent);
        }
    }

    public void showInfo() {
        System.out.println("length: "+ listStudent.size());
        for(Student student: listStudent) {

                System.out.println("student: " +student.getName());

        }
    }
    public static void main(String[] args) {
//        Main main = new Main();
//        main.inputStudent();
//        main.showInfo();
        Student student = new Student(12, "hieu");
        System.out.println(student.getScore() + " " +student.getName());
    }
}
