/**
 * Module declaration for the Unit Converter application
 *
 * his file tells the JVM:
 * - which external modules we depend on
 * - which of our packages JavaFX is allowed to access
 */
module unit.converter {
    requires javafx.controls;

    exports app;
    exports controllers;
    exports core;
    exports io;
    exports ui;
}
