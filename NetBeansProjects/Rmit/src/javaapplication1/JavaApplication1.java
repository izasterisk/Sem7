/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Author Name: ");
        String AuthorName = sc.nextLine();

        System.out.print("Year:");
        String Year = sc.nextLine();

        System.out.print("Title Of Article:");
        String TitleOfArticle = sc.nextLine();

        System.out.print("Name Of Journal");
        String NameOfJournal = sc.nextLine();

        System.out.print("volume:");
        String volume = sc.nextLine();

        System.out.print("nameWebsite:");
        String nameWebsite = sc.nextLine();

        System.out.print("doi:");
        String doi = sc.nextLine();

        System.out.println(AuthorName + " (" + Year + ") " + "'" + TitleOfArticle + "', " + NameOfJournal + ", " + volume + ", "
                + nameWebsite + ".\n" + doi);
    }

}
