#!/bin/bash

# MyMathPlotter Build Script
# This script helps compile and run the JavaFX application

# IMPORTANT: Update these paths to match your system!
JAVAFX_PATH="/path/to/javafx-sdk-21/lib"
JSON_JAR="/path/to/json-20231013.jar"

echo "=== MyMathPlotter Build Script ==="
echo ""

# Compile
echo "[1/2] Compiling Java files..."
javac --module-path "$JAVAFX_PATH" \
      --add-modules javafx.controls \
      -cp "$JSON_JAR" \
      *.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo ""

    # Run
    echo "[2/2] Running MyMathPlotter..."
    java --module-path "$JAVAFX_PATH" \
         --add-modules javafx.controls \
         -cp ".:$JSON_JAR" \
         MyMathPlotter
else
    echo "✗ Compilation failed. Please check errors above."
    exit 1
fi
