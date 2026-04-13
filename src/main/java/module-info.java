/**
 * Module declaration for the Unit Converter application.
 *
 * JavaFX uses the Java module system. This file tells the JVM:
 * - which external modules we depend on (requires)
 * - which of our packages JavaFX is allowed to access (exports/opens)
 */
module unit.converter {
    requires javafx.controls;

    exports app;
    exports controllers;
    exports core;
    exports io;
    exports ui;
}
