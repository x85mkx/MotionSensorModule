## Pi4J HC-SR501 Motion Sensor Example

This Java application demonstrates how to interface with an HC-SR501 Passive Infrared (PIR) motion sensor module using the Pi4J library on a Raspberry Pi.

**Purpose:**

The `App.java` class provides a simple example of interacting with an HC-SR501 PIR motion sensor connected to a Raspberry Pi using Pi4J v2. It showcases how to initialize Pi4J, configure a digital input pin to read the sensor's output, and detect motion events.  This serves as a starting point for anyone wanting to experiment with PIR motion sensors and Pi4J.

**Functionality:**

* **Initialization:** Sets up the Pi4J context (`Pi4J.newAutoContext()`) which is essential for interacting with Raspberry Pi hardware.
* **GPIO Configuration:** Configures a digital input pin (GPIO 17 in BCM numbering) to read the output from the HC-SR501 PIR motion sensor. It uses the Pi4J `DigitalInput.Builder` for a clear and structured configuration, including setting a pull-down resistor.
* **Motion Detection:** Continuously monitors the state of the configured digital input pin.
* **Console Output:** When motion is detected (sensor output goes HIGH), the program prints "Motion detected!" to the console.
* **Polling Loop with Delay:** Uses a `while(true)` loop to continuously check for motion, with a short delay (`sleep(500)`) to prevent excessive CPU usage.
* **Shutdown:**  While not explicitly shutting down Pi4J in this basic example loop, proper Pi4J shutdown should be implemented in more complex applications to release resources.

**Hardware Requirements:**

* **Raspberry Pi:** Any Raspberry Pi model supported by Pi4J.
* **HC-SR501 PIR Motion Sensor Module:** A common and inexpensive PIR motion sensor.
* **Wiring:** Jumper wires to connect the PIR motion sensor module to the Raspberry Pi GPIO pins as follows:
    * **POWER:** 5V (Pin 2)
    * **OUTPUT:** GPIO17 (Pin 11)
    * **GROUND:** GND (Pin 6 or any other GND pin)

**Software Requirements:**

* **Java Development Kit (JDK):** Required to compile and run the Java code.
* **Pi4J Library:** This project is built using Pi4J v2. You'll need to include Pi4J as a dependency in your project (e.g., using Maven or Gradle).
* **Operating System:** Raspberry Pi OS (or any OS supported by Pi4J).

**How to Use:**

1. **Clone this repository.**
2. **Ensure you have Pi4J v2 set up in your project.** If you are using Maven, include the Pi4J core dependency in your `pom.xml`.
3. **Compile the `App.java` class.**
4. **Connect the HC-SR501 PIR motion sensor module to your Raspberry Pi as described above.**
5. **Run the compiled `App` class on your Raspberry Pi.**

**Important Notes:**

* **GPIO Pin Numbering:** The code uses BCM (Broadcom) GPIO pin numbering. Make sure your wiring matches this numbering scheme.
* **Pull-down Resistor:** The code configures a pull-down resistor for the digital input. This is important for PIR sensors as their output can be floating when no motion is detected. The pull-down resistor ensures a defined LOW state in the absence of motion.
* **Permissions:** Ensure that the user running the Java application has the necessary permissions to access the Raspberry Pi's GPIO pins. You might need to run the application with `sudo`.
* **Sensor Sensitivity:** The HC-SR501 sensor typically has potentiometers for adjusting sensitivity and trigger time. Refer to the sensor's documentation for details on tuning these settings.

**Keywords:** Raspberry Pi, Pi4J, HC-SR501, PIR Sensor, Motion Sensor, GPIO, Java, IoT, Hardware, Example, Tutorial, Digital Input, Motion Detection.
