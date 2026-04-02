# REFLECTIONS
## Question 1
Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” to explain why Main.run works correctly for ANY of your Logger implementations.

Main.run takes a Logger parameter, so any class that implements it is a subtype and can be passed in. Thats polymorphism you don't need a different run for each logger. At runtime Java figures out which actual log to call based on the real object, not the declared type. That's dynamic method dispatch doing its thing.
##  Question 2
Why can the Logger.formatMessage method have a default implementation? Why must the Logger.log method be abstract?

formatMessage gets a default implementation becuase every logger formats the timestamp the exact same way, so there's no point repeating that code in each class. log has to be abstract because each logger does something completley different storing, printing, shouting there's nothing to share. There's no shared implementation that works for all of them.
## Question 3
Among all the Loggers you have implemented, ONLY MemoryLogger objects can be passed to the Main.export method. Why? Why MUST the declaration for the MemoryLogger use ‘MemoryLogger’ instead of just ‘Logger’ as its type? (Try changing the declaration line to ‘Logger’ and see what happens.)

Only MemoryLogger implements exportable, so its teh only one the export method will accept. If you declare it as Logger the compiler loses track of the fact that its also Exportable it only sees Logger, which doesn't have exportTo. The declaration has to be MemoryLogger so the compiler knows both interfaces are in play.

