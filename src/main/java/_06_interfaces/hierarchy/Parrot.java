/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _06_interfaces.hierarchy;

/**
 *
 * @author rod
 */
public class Parrot implements CanSingSong {
    
    // Parrot MUST implement ALL interface methods inherited by CanSingSong
    
    // Override denotes the implementation of interface methdos
    @Override
    public void makeSound() {
        System.out.println("Squawk!");
    }
    
    @Override
    public void sayWords() {
        System.out.println("Polly want a cracker!");
    }
    
    @Override
    public void makeMelody() {
        System.out.println("♫♪♩♪");
    }
    
    @Override
    public void singSong() {
        System.out.println("♫ It's a pirate's life for me ♪");
    }
    
}
