module myjavafxmodule {

    requires javafx.controls;  // Declare that this module uses the javafx.controls module
    requires org.json;

    // Every package that contains classes that are 'used' by other modules must be exported
    // This includes classes that extend other classes, implement interfaces, etc.
    // For example, all the packages below have at least one class that extends javafx.application.Application
    exports src/main/java;
    exports controllers;
    exports core;
    exports io;
    exports ui;
}