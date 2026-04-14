# MyMathPlotter - JavaFX Animated Function Grapher

A JavaFX application that animates mathematical functions with a trailing fade effect, similar to an electrocardiogram (EKG) display.

## Features

- ✅ **Network IO**: Loads function definitions from a remote GitHub Gist
- ✅ **MVC Architecture**: Clean separation of Model, View, and Controller
- ✅ **Three-Index Animation**: Custom fading algorithm (33.33% → 66.66% → 100% opacity)
- ✅ **Interactive Controls**: Buttons to select functions, sliders to adjust parameters
- ✅ **Multiple Functions**: Complex wave, parabola, and exponential functions

## Project Structure

```
MyMathPlotter/
├── MyMathPlotter.java       # Main application (JavaFX setup, UI)
├── MathFunction.java         # Model (function data and calculation)
├── GraphView.java            # View (Canvas rendering)
├── AnimationController.java  # Controller (animation logic)
├── FunctionLoader.java       # Network IO (fetches JSON from Gist)
└── functions.json            # Sample JSON (to be uploaded to Gist)
```

## Setup Instructions

### Step 1: Install JavaFX

Download JavaFX SDK from: https://gluonhq.com/products/javafx/

Extract it to a location like: `C:\javafx-sdk-21` (or `/Users/you/javafx-sdk-21` on Mac)

### Step 2: Add JSON Library

This project uses `org.json` for JSON parsing. Download `json-20230227.jar` or similar from:
https://mvnrepository.com/artifact/org.json/json

Or if using Maven, add to `pom.xml`:
```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20230227</version>
</dependency>
```

### Step 3: Create GitHub Gist

1. Go to https://gist.github.com
2. Create a new Gist
3. Name the file: `functions.json`
4. Copy the contents from the included `functions.json` file
5. Click "Create public gist"
6. Click the "Raw" button to get the direct URL
7. Copy that URL

### Step 4: Update FunctionLoader.java

Replace the `GIST_URL` placeholder in `FunctionLoader.java` with your actual Gist URL:

```java
private static final String GIST_URL = 
    "https://gist.githubusercontent.com/YOUR_USERNAME/YOUR_GIST_ID/raw/functions.json";
```

### Step 5: Compile and Run

**Using Command Line:**

```bash
# Compile (adjust paths to your JavaFX and JSON library locations)
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls \
      -cp json-20230227.jar *.java

# Run
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls \
     -cp .:json-20230227.jar MyMathPlotter
```

**Using IntelliJ IDEA:**

1. File → Project Structure → Libraries → Add JavaFX SDK
2. File → Project Structure → Libraries → Add json-20230227.jar
3. Run → Edit Configurations → Add VM options:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
   ```
4. Run `MyMathPlotter.java`

**Using Eclipse:**

1. Project → Properties → Java Build Path → Libraries → Add External JARs
2. Add JavaFX JARs and json-20230227.jar
3. Run Configurations → Arguments → VM arguments:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
   ```
4. Run `MyMathPlotter.java`

## How It Works

### The Three-Index Animation System

This is the core innovation of the animation. Three indices traverse the point array:

1. **secondIdx** - Sets points to 33.33% opacity (most faded)
2. **firstIdx** - Sets points to 66.66% opacity (medium fade)
3. **currentIndex** - Sets points to 100% opacity (fully visible)

As each index passes over a point, it "paints" that point with its opacity level. The indices move in sequence, creating a trailing fade effect.

**Spawning Logic:**
- `currentIndex` starts immediately
- `firstIdx` spawns when `currentIndex` passes 1/3 of the array
- `secondIdx` spawns when `currentIndex` passes 2/3 of the array

This ensures smooth fade-in during the first iteration.

### Network IO Explained (FunctionLoader.java)

**KEY LEARNING COMPONENT FOR ASSIGNMENT REQUIREMENT #5**

The `FunctionLoader` class demonstrates network IO by:

1. Creating a `URL` object pointing to the GitHub Gist
2. Opening an `HttpURLConnection`
3. Sending an HTTP GET request
4. Reading the response using `BufferedReader`
5. Parsing the JSON text into `MathFunction` objects

**Study this class carefully!** Understand each step of the network request process.

### MVC Architecture

**Model** (`MathFunction.java`)
- Stores function parameters (amplitude, frequency, etc.)
- Calculates Point2D array for the function
- Contains hardcoded formulas for each function type

**View** (`GraphView.java`)
- Wraps the JavaFX Canvas
- Provides methods to draw points/lines with opacity
- Transforms math coordinates to screen coordinates

**Controller** (`AnimationController.java`)
- Manages the `AnimationTimer` (application heartbeat)
- Maintains the three indices and opacity array
- Coordinates between Model and View
- Handles user input from sliders/buttons

## Customization

### Adding New Functions

1. **Update `functions.json`** with new function definition:
   ```json
   {
     "name": "Sine Wave",
     "type": "sine_wave",
     "defaultAmplitude": 2.0,
     "defaultParameterA": 1.0,
     "defaultParameterC": 1.0
   }
   ```

2. **Add case to `MathFunction.evaluateFunction()`**:
   ```java
   case "sine_wave":
       return amplitude * Math.sin(parameterA * x);
   ```

3. Upload updated JSON to your Gist (Gist URLs update automatically!)

### Adjusting Animation Speed

In `AnimationController.java`, modify:
```java
private static final long FRAME_DELAY_NS = 16_000_000; // ~60 FPS
```

Increase the value to slow down, decrease to speed up.

### Changing Colors

In `GraphView.java`, modify the color in `drawPoint()` and `drawLine()`:
```java
gc.setFill(Color.color(0.2, 0.4, 0.8, opacity)); // RGB + alpha
```

## Assignment Requirements Checklist

- ✅ **#1 Coherent purpose**: Animated mathematical function plotter
- ✅ **#2 Layout panes**: BorderPane (root), VBox and HBox for controls
- ✅ **#3 Three different nodes**: Canvas (shape), Button, Slider (controls)
- ✅ **#4 EventHandler**: Button click handlers, Slider value listeners
- ✅ **#5 Network IO**: FunctionLoader fetches JSON from GitHub Gist
- ✅ **#6.1 Separation**: MathFunction is pure logic (no UI/IO references)
- ✅ **#6.2 MVC pattern**: Clear Model-View-Controller structure

## Troubleshooting

**"Module javafx.controls not found"**
- Ensure JavaFX SDK is properly added to your classpath
- Verify VM arguments include `--module-path` and `--add-modules`

**"ClassNotFoundException: org.json.JSONObject"**
- Add the org.json library to your classpath
- Download from: https://mvnrepository.com/artifact/org.json/json

**"Network error when loading functions"**
- Verify your Gist URL is correct and uses "raw" link
- Check internet connection
- The app will show an error message in the status label

**Functions appear cut off or stretched**
- The coordinate system auto-scales to fit the function
- Try adjusting amplitude slider for better visualization

## License

Educational project - free to use and modify for learning purposes.
