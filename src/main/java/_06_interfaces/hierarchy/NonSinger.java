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
public class NonSinger implements CanSayWords {
    
    // We MUST implement all interface methods inherited by CanSayWords
    
    
    // The makeNoise method has a default implementation, so we don't need to
    // implement it here (unless we wanted to override it)
    
    @Override
    public void sayWords() {
        System.out.println("I couldn't hold a tune to save my life");
    }

    @Override
    public void makeSound() {
        System.out.println("I'm not a singer, but I can make noise");
    }
}
