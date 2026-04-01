1.12 -  REFLECTION: Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” to explain why Main.run works correctly for ANY of your Logger implementations.
    
    Ah, wonderful question. You see, when you have objects that seem to have a base of things in common, it is usually easiest to make a sort of mother object to separate the common features from the object-specific features. Doing this makes your original objects become subtypes of the mother object you created. Furthermore, when being able to create multiple subtypes is an example of polymorphism - being able to implement an object (the mother object) in many different ways (your subtypes). Now, when you go to run a program that is utilizing both of these methods, you run into something called dynamic method dispatch. You have a method that accepts an object (your mother type) that has many subtypes. When utilizing this, the IDE does not concern itself with exactly which object is going to be used for that method at that moment in time, because it could change, and there are many objects (your subtypes) that could be used. Simply put, when the program runs, it THEN checks what object you passed. It then checks if it matches the required type to pass. When you pass a subtype through a method, all it checks if it is a part of the object it needs, and polymorphism makes it so every subtype is technically that kind of object (the mother object). So, dynamic method dispatch simply checks 1 if it is that object, or a subtype of that object, and 2 if it is a subtype, if it has any methods that override the supertype's.

1.13 - REFLECTION: Why can the Logger.formatMessage method have a default implementation? Why must the Logger.log method be abstract?

    The logger method can have a default method because it has multiple subtypes and this way, if the subtypes do not have their own method that overrides it, it has a method that still executes if need be.
    as for the reason the log method must be abstract, I believe it is because the default method can't actually access the implementation's state, whereas the abstract method can

1.14.3 - REFLECTION: Among all the Loggers you have implemented, ONLY MemoryLogger objects can be passed to the Main.export method. Why? Why MUST the declaration for the MemoryLogger use ‘MemoryLogger’ instead of just ‘Logger’ as its type? (Try changing the declaration line to ‘Logger’ and see what happens.) 
    
    at this point in time I am still having errors with this, however I image the reason that we can ONLY send the Memory logger through is because that is the only object implementation that has an export method


AI use:
    FUCK I was gonna keep up my no AI streak but copilot had to remind me that maps can be used for formatting :))) woops