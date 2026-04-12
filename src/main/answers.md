1. Why does Main.run work for any Logger?
It works because all the logger classes implement the logger interface that means they all have the same log method
2. why can formatmessage be default but log is abstract?
formatmessage is the same for all loggers so it can be written once in the interface.
3. Why only MemoryLogger works with export?
MemoryLogger works because it implements Exportable and other loggers do not