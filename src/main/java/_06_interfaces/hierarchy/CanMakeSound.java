/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _06_interfaces.hierarchy;

public interface CanMakeSound {
    
    // Interfaces can specify a default method
    // BUT the method cannot rely on any internal state (there is none from
    // within an interface)
    default void makeSound() {
        System.out.println("*grunt*");
    }
}
