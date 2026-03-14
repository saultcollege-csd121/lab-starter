# AI Use Statement
No AI used
Replace this with your AI Use Statement, which is required **WHETHER OR NOT** you used AI.
Failure to include this statement will result in a reduced grade of up to 100%.

One challenge I had during the implementation was understanding how the Player class should be converted into an abstract class and how the subclasses would override the getNextMove method. 
At first, it was confusing how the game engine could call the same method for different player types. After working through the code, I understood that polymorphism allows the program to call the correct method implementation depending on the actual player object at runtime.