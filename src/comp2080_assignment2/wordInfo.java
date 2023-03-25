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
public class wordInfo {
    public String word;
    public String meaning;
    public wordInfo left;
    public wordInfo right;
    
    public wordInfo(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        left = right = null;
    }
}
