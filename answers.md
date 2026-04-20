# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
Because the return type is Player, and all subclasses (like RandyPlayer, OptimusPlayer) are also Player. Java allows returning any subclass of the declared return type (polymorphism).

## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
At compile-time, the variable is typed as Player, so Java only allows calling methods defined in Player. Since getNextMove wasn’t in Player, it caused an error. After adding it as an abstract method, subclasses implement it, and at run-time the correct version is called (dynamic dispatch).

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
The game uses polymorphism. Even though the variable type is Player, each subclass (Human, Randy, Optimus) provides its own getNextMove implementation. At run-time, Java automatically calls the correct method based on the actual object type, so no changes are needed in the main game logic.