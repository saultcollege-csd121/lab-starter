I mainly worked with it with a tutor, but I also reviewed documentation as well. 



Questions/Answers

1.12 
- Main.run works because all logger classes implement the Logger interface,
making them subtypes. This allows polymorphism, so different logger objects can be used.

1.13
formatMessage is default because all loggers share the same formatting. 
log is abstract because each logger handles messages differently.

1.14.3
Only MemoryLogger can be exported because it implements the Exportable interface, which includes the exportTo method.

Why must it be declared as MemoryLogger?
It must be declared as MemoryLogger because the Logger type does not include exportTo. Using MemoryLogger allows access to that method.