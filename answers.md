# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
Because HumanPlayer is a subclass of Player, so its still a Player. The return type says Player and HumanPlayer is a Player, so java is ok with it. Its like saying you'll return a fruit but returning an apple instead

## Question 2
Explain why the call to getNextMove initially causes an error until you add the abstract method to the Player class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
Your answer here
The variable player in doNextTurn has a compiletime type of Player. When java compiles the code it looks at that type to check getNextMove on it. The compiler doesnt see it on Player because we moved getNextMove only to HumanPlayer. At runtime it would work fine, but the compiler dosent care about that. Adding the abstract method to Player fixes this becuase now the compiler can see that all Players will have getNextMove.

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
Because of polymorphism. At runtime java uses dynamic dispatch to figure out witch actual class the object is. So we never need to change the game logic, we just add new subclasses and java handles the rest automaticaly.