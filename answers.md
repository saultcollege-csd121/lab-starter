1. Why Main.run works correctly for ANY of my Logger implementations. 

Main.run works perfectly because  it was created to run the Logger class, but in reality, Logger is a superclass 
that has different subclasses such as: ConsoleLogger, LoudLogger, etc. They are all subtypes and shows how polymorphism
allows my Main.run take any subclass since they are Loggers and use the dynamic method to decide during the runtime 
which log() to call based on the actual object that logger.log() is calling. 

2. Why can Logger.formatMessage method have a default implementations? Why must the Logger.log method be abstract? 

The main idea of creating a default formatMessage is essentially keep the same format for all messages, base on that
idea makes much sense use a default method for all the logger, which works perfectly. 
However, the log must be different since every log has its own version to handle the message (print it, store it, etc.)

3. Why MUST the declaration for the MemoryLogger use the 'MemoryLogger' instead of just 'Logger' as its type? 

We must declare the MemoryLogger as 'MemoryLogger' because the 'Logger' doesn't have exportTo() method, it only knows 
about the log() and the default. If we declare the MemoryLogger as 'logger' and we run the program we will get a compile 
error because 'Logger' was not created to be exportable. 
