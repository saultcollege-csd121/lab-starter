# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
You can change the return value without changing the signature
because the signature is a superclass of the return value, and
since subtypes are just pieces of superclasses, it counts as the
"same" object. They're still two objects in their own right
but one being made out of the other, it can substitute so to
speak.


## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
This initially causes an error due to the scope of the call, where it is getting accessed through player, but the function was moved to be a subclass of a class extension of where it originally was.
As for static and dynamic, I hope I'm understanding this
correctly, the getNextMove function was initially purely dynamic,
it was the mose specific on the subclasses but because there was
no static version of this function, it wasn't able to be called.
By adding the abstract method, we introduced the possibility of
being able to call on this method, and henceforth making it accessible
in the rest of the program.


## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer

ENCAPSULATION! By making function after function,
we slowly buried the fine detailing of the code.
Not only does this hide code from the user,
but it also allows for more change to be done behind the
scenes without breaking everything!