# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
This works because of inheritance and polymorphism in Java. The method promptForPlayer has a return type of Player, which means it can return any object whose type is Player or any subclass of Player. 
Since HumanPlayer, RandyPlayer, LinusPlayer, and OmolaPlayer all extend the Player class, they are considered valid Player objects.

Therefore, even though the method signature says it returns Player, we can return objects of subclasses like HumanPlayer or RandyPlayer.
Java allows this because a subclass is a type of its parent class. This follows the principle that a variable of a parent type can reference objects of its child types.

## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
The variable player in TicTacToeGame.doNextTurn has the compile-time type Player, so the compiler checks whether the Player class has a getNextMove method. 
If it does not exist in Player, the compiler produces an error. By adding the abstract method to Player, we tell the compiler that all subclasses must implement it. 
At run time, the actual object might be HumanPlayer or another subclass, and Java calls the correct implementation.

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
This works because of polymorphism. The game engine only interacts with players through the Player class and calls the getNextMove method. 
Each subclass of Player, like HumanPlayer, RandyPlayer, or LinusPlayer, implements its own version of this method.

When the program runs, Java uses dynamic method dispatch to decide which version of getNextMove to execute based on the actual type of the player object.
For Example, if the player is a HumanPlayer, the human input method runs, but if it is a RandyPlayer, the random move method runs.

Because of this design, the main game loop and the TicTacToeGame class do not need to change when new player types are added. 
As long as the new class extends Player and implements getNextMove, the game engine will work with it automatically.