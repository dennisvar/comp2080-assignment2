/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2080_assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//import java.io.IOException;

/**
 *
 * @authors
 * Dennis Varghese 101020193
 * Jorrel Tigbayan 101329925
 */
public class Comp2080_assignment2 {
    /**
     * @param args the command line arguments
     */
    static dictionary d = new dictionary();
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        runProgram();
    }
    
    public static void displayMenu() {
        System.out.println(" --------- MENU --------- ");
        System.out.println("1: Add new word");
        System.out.println("2: Delete word");
        System.out.println("3: Get meaning");
        System.out.println("4: Dictionary list");
        System.out.println("5: Spell check a text file");
        System.out.println("6: Exit");
    }
    
    public static int getOption() {
        Scanner opt = new Scanner(System.in);
        int option;
        if (opt.hasNextInt()) {
            option = opt.nextInt();
        }
        else {
            option = -1;
        }
        return option;
    }
    
    public static void loadDictionary() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("wordList.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            d.add(line, "Undefined word");
        }
    }
    
    public static void runProgram() throws IOException {
        loadDictionary();
        
        boolean looping = true;
        while (looping) {
            displayMenu();
            System.out.print("\nOption: ");
            
            switch (getOption()) {
                case 1:
                    addWord();
                    break;
                case 2:
                    deleteWord();
                    break;
                case 3:
                    getMeaning();
                    break;
                case 4:
                    printWords();
                    break;
                case 5:
                    spellcheckFile();
                    break;
                case 6:
                    looping = false;
                    break;
                default:
                    System.out.println("\nThat is not a valid option.\n");
                    break;
            }
        }
    }
    
    public static void addWord() {
        System.out.print("\nEnter a word: ");
        String word = input.nextLine();
        System.out.print("Enter it's meaning: ");
        String meaning = input.nextLine();
        
        
        if (d.add(word, meaning))
            System.out.println("\n" + word + " has been added to dictionary.\n");
        else
            System.out.println("\nError: Word could not be added.\n");
    }
    
    public static void deleteWord() {
        System.out.print("\nEnter a word: ");
        String word = input.nextLine();
        
        if (d.delete(word))
            System.out.println("\n" + word + " has been deleted from dictionary.\n");
        else
            System.out.println("\nError: Word could not be deleted.\n");
    }
    
    public static void getMeaning() {
        System.out.print("\nEnter a word: ");
        String word = input.nextLine();
        
        if (d.getMeaning(word) != null)
            System.out.println("\n" + d.getMeaning(word) + "\n");
        else
            System.out.println("\nWord could not be found.\n");
    }
    
    public static void printWords() {
        System.out.println("\n" + d.printWordList());
    }
    
    public static void spellcheckFile() throws FileNotFoundException, IOException {
        System.out.print("\nEnter the name of a text file to spell check: ");
        String filename = input.nextLine();
        String[] words;
        Scanner sc = new Scanner(new File(filename));
        System.out.print("\n");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            words = line.split(" ");
            
            for (String word : words) {
                if (!d.exists(word)) {
                    System.out.println(word);
                }
            }
        }
        System.out.print("\n");
    }
    
}
