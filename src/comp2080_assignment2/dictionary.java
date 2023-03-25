/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2080_assignment2;

/**
 *
 * @authors
 * Dennis Varghese 101020193
 * Jorrel Tigbayan 101329925
 */
public class dictionary {
    wordInfo root;
    
    public dictionary() {
        root = null;
    }
    
    public boolean add(String word, String meaning) {
        wordInfo newWord = new wordInfo(word.toLowerCase(), meaning);
        if (root == null) {
            root = newWord;
            return true;
        }
        wordInfo parent, current;
        parent = current = root;
        while (current != null) {
            parent = current;
            if (word.compareTo(current.word) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (word.compareTo(parent.word) < 0 && word.equals(root.word) == false) {
            parent.left = newWord;
            return true;
        }
        else if (word.compareTo(parent.word) > 0 && word.equals(root.word) == false) {
            parent.right = newWord;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean delete(String word) {
        boolean val = exists(word);
        root = deleteRecursive(root, word);
        return val;
    }
    
    private wordInfo deleteRecursive (wordInfo root, String word) {
        if (root == null)
            return root;
        if (word.compareTo(root.word) < 0)
            root.left = deleteRecursive(root.left, word);
        else if (word.compareTo(root.word) > 0) {
            root.right = deleteRecursive(root.right, word);
        }
        else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            wordInfo successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.word = successor.word;
            root.right = deleteRecursive(root.right, successor.word);
        }
        return root;
    }
    
    public boolean exists(String word) {
        wordInfo current = root;
        while (current != null && !current.word.equals(word)) {
            if (word.compareTo(current.word) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (current != null)
            return true;
        return false;
    }
    
    public String getMeaning(String word) {
        wordInfo current = root;
        while (current != null && !current.word.equals(word)) {
            if (word.compareTo(current.word) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (current != null)
            return current.meaning;
        return null;
    }
    
    public int getCount() {
        if (root == null)
            return 0;
        return 1 + getCount(root.left) + getCount(root.right);
    }
    
    private int getCount(wordInfo current) {
        if (current == null)
            return 0;
        
        return 1 + getCount(current.left) + getCount(current.right);
    }
    
    public String printWordList() {
        return printWordList(root);
    }
    
    private String printWordList(wordInfo current) {
        if (current != null) {
            return printWordList(current.left) + current.word + "\n" + printWordList(current.right);
        }
        return "";
    }
    
    public void printDictionary() {
        printDictionary(root);
    }
    
    private void printDictionary(wordInfo current) {
        if (current != null) {
            printDictionary(current.left);
            System.out.println(current.word + ": " + current.meaning);
            printDictionary(current.right);
        }
    }
}