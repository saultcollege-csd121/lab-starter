/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _05_generalization_polymorphism.interfaces.hierarchy;

public class Ape implements CanMakeSound {
    
    // The Override annotation is optional, but it's good practice to use it.
    // It can help prevent bugs if you change the method signature of the method in the interface
    @Override
    public void makeSound() {
        System.out.println("Oook ook!");
    }
}
