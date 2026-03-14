/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _05_generalization_polymorphism.interfaces.hierarchy;

// Interfaces can extend other interfaces
// All classes that implement this interface must implement the methods in the
// hierarchy of interfaces it extends
public interface CanMakeMelody extends CanMakeSound {
    void makeMelody();
}
