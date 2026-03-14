/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _05_generalization_polymorphism.interfaces.hierarchy;

/**
 *
 * @author rod
 */
abstract public class Singer implements CanSingSong {
    
    // The Override annotation is used to denote the implementation of an 
    // interface method
    @Override
    public void makeSound() {
        System.out.println("*clears throat*");
    }
    
    @Override
    public void makeMelody() {
        System.out.println("do re mi fa so la ti do");
    }
    
    // An Abstract class doesn't need to supply implementations for all its
    // interface methods.  Here we leave the sayWords as an abstract method
    // NOTE: these lines are not necessary — the interface methods are abstract
    // by default.  Commenting these lines out will work exactly that same.
    // (See the notes for the singSong method below)
    @Override
    abstract public void sayWords();
    
    // An Abstract class doesn't need to supply implementations for all its
    // interface methods.  Here we leave the singSong as an abstract method
    // NOTE: since the interface method is abstract by default we don't even
    // need these lines.  We've commented them out here to demonstrate
    @Override
    abstract public void singSong();
    
}
