# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
The ability to change the type of the returned value, without changing the return type is allowed because all the player subclasses(Linus, Optimus, and HumanPlayer) extend `Player`. This makes them players, which is a instance of polymorphism. This means the return type can be `Player`
but at runtime the object returned can be whatever subclass of player was used. 
## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
The error is fixed by adding the `abstract` method to the player class which is bascially telling the complier that "every subclass will have this method." Otherwise, the compiler is uncertain that every player will have this method, and not compile. A static type like `Player` is what the compiler sees when it checks if `getNextMove` is a correct call. Dynamic types are the subclasses(Linus, Optimus, HumanPlayer) that are the actual objects in memory when the program is ran. 
## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
The game loop and the TicTacToe class need no changes. When `getNextMove` is executed, it doesn't look at the compile-time type. (which is `Player`). It actually looks at the object in memory at run-time. This is dynamic dispatch in action. The method works correctly no matter whos turn it is because of this. `Player` becomes whatever object is in memory at run time. Polymorphism determines that we must make the `Player` class abstract so that every subclass can use `GetNextMove`.