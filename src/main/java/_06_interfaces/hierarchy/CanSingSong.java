/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _06_interfaces.hierarchy;


// This interface demonstrates multiple inheritance in interfaces!
// It extends TWO other interfaces.
// This kind of inheritance hierarchy is NOT possible with regular classes
public interface CanSingSong extends CanMakeMelody, CanSayWords {
    void singSong();
}
