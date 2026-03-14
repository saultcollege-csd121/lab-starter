# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
The promtForPlayer method is originally declared to return a Player. however, we already know that when we create a subclass 
the subclass inherits the class from their "parents", in this case, HumanPlayer is a kind of Player, that the reason why the method works. 
Also, this example shows the polymorphism concept, which allows objects of different classes to be treated as objects form the same superclass.


## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
This error occurs because the program checks the method call based on the static type of the variable. So, if in the static type the variable was declared 
as player type in the runtime the real object stored could be HumanPlayer or OptimusPlayer but the compiler is still looking at the Player type. The solution was declared 
getNextMove as abstract method in the player class, in that way each subclass can implement its own version of the method. 

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
In the first question we mentioned polymorphism and the reason why was not needed to modify the Main is also polymorphism and abstraction. 
The different subclasses that we have interact with players using the player superclass. 
This is also a dynamic method, which how we saw in the last question it can manage the correct implementation of the method depending od the actual type of the object runtime.
For instance, if the player is a HumanPlayer, the method may prompt the user for input. If the player is LinusPlayer, the method may simply choose the first available cell.