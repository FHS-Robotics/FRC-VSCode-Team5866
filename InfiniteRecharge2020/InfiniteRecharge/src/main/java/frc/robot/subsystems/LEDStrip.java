/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Class for handling an LED strip attached to a PWM port
 */
public class LEDStrip extends SubsystemBase {
 
  AddressableLED m_led;
  AddressableLEDBuffer m_buffer;

  int m_rainbowFirstPixelHue = 0;

  public LEDStrip(int port, int length) {
    m_led = new AddressableLED(port);
    m_buffer = new AddressableLEDBuffer(length);
    m_led.setLength(m_buffer.getLength());

    // Set the data
    m_led.setData(m_buffer);
    m_led.start();
  }

  /**
   * Set all LED's on a strip based on red, green, and blue values
   * @param r : value from 0-255
   * @param g : value from 0-255
   * @param b : value from 0-255
   */
  public void setRGB(int r, int g, int b) {
    for(int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, r, g, b);
    }
    m_led.setData(m_buffer);
  }

  /**
   * Set an individual LED on the strip based on red, green, and blue values
   * @param r : value from 0-255
   * @param g : value from 0-255
   * @param b : value from 0-255
   * @param index : LED index to set
   */
  public void setRGB(int r, int g, int b, int index) {
    m_buffer.setRGB(index, r, g, b);
    m_led.setData(m_buffer);
  }

  public void rainbow() {
    // For every pixel
    for (var i = 0; i < m_buffer.getLength(); i++) {
      // Calculate the hue - hue is easier for rainbows because the color
      // shape is a circle so only one value needs to precess
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_buffer.getLength())) % 180;
      // Set the value
      m_buffer.setHSV(i, hue, 255, 128);
    }
    // Increase by to make the rainbow "move"
    m_rainbowFirstPixelHue += 3;
    // Check bounds
    m_rainbowFirstPixelHue %= 180;

    m_led.setData(m_buffer);
  }

  @Override
  public void periodic() {}
}
