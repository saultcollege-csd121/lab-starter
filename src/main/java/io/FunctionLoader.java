package io;

import core.MathFunction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FunctionLoader - Network IO class
 *
 * This class fetches mathematical function definitions from a remote
 * GitHub Gist (JSON file hosted on the internet).
 *
 * Key concepts to understand:
 * 1. URL - represents the web address of the JSON file
 * 2. HttpURLConnection - Java's built-in class for making HTTP requests
 * 3. BufferedReader - efficiently reads text from the network stream
 * 4. JSON parsing - converts JSON text into Java objects
 */
public class FunctionLoader {

    // This is a RAW URL - it returns pure JSON text
    private static final String GIST_URL =
            "https://gist.githubusercontent.com/CristianSault/6b4062b2822e018983c478659062f5c2/raw/784a643b8c864a29390e27d55acc3fa9e1808dd7/functions.json";

    /**
     * Loads function definitions from the network
     *
     * Process:
     * 1. Open connection to the URL
     * 2. Send HTTP GET request
     * 3. Read the response (JSON text)
     * 4. Parse JSON into MathFunction objects
     * 5. Return array of functions
     *
     * @return Array of MathFunction objects loaded from network
     * @throws Exception if network fails or JSON is malformed
     */
    public MathFunction[] loadFunctionsFromNetwork() throws Exception {

        // STEP 1: Create URL object
        // This represents the web address we want to fetch from
        URL url = new URL(GIST_URL);

        // STEP 2: Open HTTP connection
        // HttpURLConnection manages the network communication
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // STEP 3: Configure the HTTP request
        connection.setRequestMethod("GET");  // We're requesting data (not sending)
        connection.setConnectTimeout(5000);   // Max 5 seconds to connect
        connection.setReadTimeout(5000);      // Max 5 seconds to read data

        // STEP 4: Check if request was successful
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("HTTP request failed with code: " + responseCode);
        }

        // STEP 5: Read the response
        // BufferedReader wraps the input stream for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        // Read all lines into a single string
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();
        connection.disconnect();

        // STEP 6: Parse JSON and create MathFunction objects
        return parseFunctions(jsonResponse.toString());
    }

    /**
     * Parses JSON text into MathFunction array
     *
     * Expected JSON format:
     * {
     *   "functions": [
     *     {
     *       "name": "Complex Wave",
     *       "type": "complex_wave",
     *       "defaultAmplitude": 1.0,
     *       "defaultParameterA": 37.35,
     *       "defaultParameterC": 6.92
     *     },
     *     ...
     *   ]
     * }
     */
    private MathFunction[] parseFunctions(String jsonText) throws Exception {

        // Parse the JSON text into a JSONObject
        JSONObject root = new JSONObject(jsonText);

        // Get the "functions" array from the JSON
        JSONArray functionsArray = root.getJSONArray("functions");

        // Create array to hold MathFunction objects
        MathFunction[] functions = new MathFunction[functionsArray.length()];

        // Convert each JSON object into a MathFunction
        for (int i = 0; i < functionsArray.length(); i++) {
            JSONObject funcObj = functionsArray.getJSONObject(i);

            String name = funcObj.getString("name");
            String type = funcObj.getString("type");
            double amplitude = funcObj.getDouble("defaultAmplitude");
            double paramA = funcObj.optDouble("defaultParameterA", 1.0);
            double paramC = funcObj.optDouble("defaultParameterC", 1.0);

            functions[i] = new MathFunction(name, type, amplitude, paramA, paramC);
        }

        return functions;
    }

    /**
     * Alternative method: Load from local fallback if network fails
     * This is good practice - always have a backup!
     */
    public MathFunction[] loadFallbackFunctions() {
        // Hardcoded functions as backup if network fails
        return new MathFunction[] {
                new MathFunction("Complex Wave", "complex_wave", 1.0, 37.35, 6.92),
                new MathFunction("Parabola", "parabola", 1.0, 0.1, 1.0),
                new MathFunction("Exponential", "exponential", 1.0, 1.0, 1.0)
        };
    }
}
