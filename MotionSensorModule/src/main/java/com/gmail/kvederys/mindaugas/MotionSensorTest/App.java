package com.gmail.kvederys.mindaugas.MotionSensorTest;

import com.pi4j.Pi4J; // Import Pi4J library core functionality
import com.pi4j.context.Context; // Import Pi4J context for managing resources
import com.pi4j.io.gpio.digital.DigitalInput; // Import interface for digital input
import com.pi4j.io.gpio.digital.DigitalInputConfig; // Import configuration for digital input
import com.pi4j.io.gpio.digital.DigitalState; // Import enum for digital input states (HIGH/LOW)
import com.pi4j.io.gpio.digital.PullResistance; // Import enum for pull-up/pull-down resistor configuration

/**
 * <h2>HC-SR501 PIR Motion Sensor Test Application</h2>
 * <p>
 * This application demonstrates how to interface with an HC-SR501 Passive Infrared (PIR) motion sensor module
 * using the Pi4J library on a Raspberry Pi or similar single-board computer.
 * The HC-SR501 sensor is used to detect motion by sensing changes in infrared radiation.
 * When motion is detected, the sensor's output pin transitions to a HIGH state.
 * </p>
 * <p>
 * This program initializes the Pi4J context, configures a digital input pin for the PIR sensor,
 * and then enters a continuous loop to monitor the sensor's state.
 * Whenever motion is detected (sensor output is HIGH), a "Motion detected!" message is printed to the console.
 * </p>
 * <p>
 * <b>Hardware Setup:</b>
 * <ul>
 *     <li><b>5V [Pin 2]</b>      >> POWER - Connect to 5V power supply on Raspberry Pi.</li>
 *     <li><b>GPIO17 [Pin 11]</b> >> OUTPUT - Connect to GPIO pin 17 (or any other available GPIO) on Raspberry Pi for signal input.</li>
 *     <li><b>GND [Pin 6]</b>     >> GROUND - Connect to a common ground on Raspberry Pi.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Important Note:</b> Ensure that you have correctly wired the HC-SR501 PIR sensor module to your Raspberry Pi
 * according to the pinout described above. Using the proper GPIO pin and a reliable ground connection is essential
 * for the application to function correctly.
 * </p>
 * <p>
 * <b>Software Dependencies:</b>
 * This application requires the Pi4J library. Make sure Pi4J is properly installed and configured in your project.
 * Refer to the Pi4J official documentation for installation instructions and setup guidance.
 * </p>
 */
public class App {

    public static void main(String[] args) {
        // Initialize Pi4J platform context.
        // This is the entry point to using Pi4J and should be created only once.
        Context pi4j = Pi4J.newAutoContext();

        // Configure Digital Input for PIR sensor using Pi4J DigitalInput Builder.
        // This configuration defines how Pi4J will interact with the GPIO pin connected to the PIR sensor.
        DigitalInputConfig pirConfig = DigitalInput.newConfigBuilder(pi4j)
                .id("PIR")                      // Unique ID for the Digital Input (optional, but good practice)
                .name("PIR Sensor")                // Descriptive name for the Digital Input (useful for logging and debugging)
                .address(17)                    // GPIO address of the pin connected to PIR sensor's output (using BCM numbering, here GPIO 17)
                .pull(PullResistance.PULL_DOWN) // Use Pull-Down resistor configuration.
                                                // PIR sensor output is typically floating when no motion, Pull-Down ensures a defined LOW state.
                .provider("pigpio-digital-input") // Explicitly set the provider for Digital Input to 'pigpio-digital-input'.
                                                // Pigpio provider is recommended for better performance and timing accuracy.
                .build();                      // Build the DigitalInputConfig object.

        // Create DigitalInput instance using the configuration defined above.
        // This 'pirSensor' object is used to read the state of the PIR sensor.
        DigitalInput pirSensor = pi4j.create(pirConfig);

        System.out.println("PIR Motion Sensor is active..."); // Informative message to indicate the sensor monitoring has started.

        // Main loop to continuously monitor the PIR sensor state.
        while (true) {
            // Check the current state of the PIR sensor.
            // 'state()' method returns the current DigitalState of the input pin.
            if (pirSensor.state() == DigitalState.HIGH) {
                System.out.println("Motion detected!"); // Print "Motion detected!" to the console when PIR sensor output is HIGH.
            }
            sleep(500); // Introduce a small delay (500 milliseconds) before the next sensor state check.
                        // This prevents excessive CPU usage and rate of 'motion detected' messages for a single motion event.
        }
    }

    // Helper method to pause the program execution for a specified time.
    // This is used to introduce delays in the main loop for manageable sensor readings.
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis); // Put the current thread to sleep for the specified milliseconds.
        } catch (InterruptedException ex) {
            // Catch InterruptedException if the sleep is interrupted.
            // In this case, we simply interrupt the current thread to properly handle the interruption.
            Thread.currentThread().interrupt();
        }
    }
}