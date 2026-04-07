 ## 1.12. REFLECTION: Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” to explain why Main.run works correctly for ANY of your Logger implementations.
Subtype - CensoriousLogger is a subtype in relation to the Logger interface. It uses the keyword implements

Polymorphism - Main.run is flexible in the sense it can pass any Logger since its implemented as an interface, polymorphism allows for creation and use of subtypes that  have the logger signature. Run only cares that it receives a  Logger object.

Dynamic Method Dispatch - This allows CensoriousLogger or MemoryLogger to be referred to as a Logger  in the code, but at the runtime, the JVM uses the actual object instance, along with its overridden methods.


## 1.13. REFLECTION: Why can the Logger.formatMessage method have a default implementation? Why must the Logger.log method be abstract?
The Logger.formatMessage has a default implementation for the fact that it formats the messages the same way for every logger.
Makes sense to keep it in one place rather than repeating it everywhere (D.R.Y.). Logger.log has to be abstact because every logger does something different with the message, so there is no actual default method here, hence it's abstraction. 

## 1.14.3. REFLECTION: Among all the Loggers you have implemented, ONLY MemoryLogger objects can be passed to the Main.export method. Why? Why MUST the declaration for the MemoryLogger use ‘MemoryLogger’ instead of just ‘Logger’ as its type? (Try changing the declaration line to ‘Logger’ and see what happens.)
MemoryLogger is the only type that can be passed to export, since it implements the exportable interface. Logger on its own doesn't include this implementation, so it throws a compiler error. (java: incompatible types: part1.logging.Logger cannot be converted to part1.logging.Exportable) Meaning that the compiler sees it only as a Logger, not an Exportable also. Memorylogger implements both and satisfies the required types for export.  