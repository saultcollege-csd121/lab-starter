/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _06_interfaces.hierarchy;

public class AstleySinger extends Singer {

    // A class must implement ALL methods in the interfaces it implements
    @Override
    public void sayWords() {
        System.out.println("Good evening, Cleveland!");
    }
    
    @Override
    public void singSong() {
        System.out.println("Never gonna give you up!");
        System.out.println("Never gonna let you down!");
    }
}
