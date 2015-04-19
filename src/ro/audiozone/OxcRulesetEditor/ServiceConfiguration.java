/*
 * Copyright (C) 2015 Silviu Ghita <killermosi@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ro.audiozone.OxcRulesetEditor;

import java.awt.Toolkit;

/**
 * Handles configuration data and defaults
 * 
 * This singleton is more an exercise than a requirement
 * for an application of this complexity.
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class ServiceConfiguration {
    /**
     * Singleton instance
     */
    private static ServiceConfiguration instance;
    
    /**
     * Ini file handler
     */
    private final ServiceIniFile iniFile;
    
    /**
     * The ini file name
     */
    private final String iniFileName = "config.ini";
    
    /**
     * The location from which the file is read (null if the file does not exist)
     */
    private String iniFileLocation = null;
    
    /**
     * Various options
     */
    private int windowPositionX; // No default, calculated to screen center
    private int windowPositionY; // No default, calculated to screen center
    private int windowWidth = 800;
    private int windowHeight = 600;
    private boolean windowMaximized = false;
    private String interfaceLanguage = "en-US";
    private boolean disclaimerShown = false;
    private boolean disclaimerDoNotShowAgain = false;
    
    /**
     * The supported languages list - I don't know how or if this list
     * can be automatically built based on the available bundles
     */
    private final String[] supportedLanguages = new String[] {"en-US", "ro-RO"};
    
    /**
     * Internal stuff
     */
    private final int windowMinSize = 200;
    
    /**
     * Retrieve the configuration instance
     * 
     * @return
     */
    public static ServiceConfiguration getInstance() {
        if (instance == null) {
            synchronized (ServiceConfiguration.class) {
                if (instance == null) {
                    instance = new ServiceConfiguration();
                }
            }
        }
        return instance;
    }
    
    /**
     * Class initialization: read the configuration file and store the values
     */
    private ServiceConfiguration() {
        // Calculate the ini file path - current directory where the application resides, not from where it is run
        // (this means that the use should save the app somewhere on the disk where it has write access)
        iniFileLocation = System.getProperty("user.dir") + "/" + iniFileName;

        // Load the configuration from it
        iniFile = new ServiceIniFile(iniFileLocation);
        
        // And init the configuration
        initConfiguration();
    }
    
    /**
     * Initialize the internal configuration with values from the file and calculate the defaults
     */
    private void initConfiguration() {
        /* For 100% completion, we'll run some checks on the values that were read from the ini file */
        
        // Meta: determine the (virtual) screen resolution of the display to be able to place the window dead center
        // if the position values were not found in the ini file. The window may end up being split between two monitors
        // under certain circumstances, but consdering that the new position will be saved in the config file,
        // this will be a small one-time startup nuisance at most.
        int screenWidth  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Note: Java enforces some limits regarding window placement and size, thse checks will only improve the UX a bit
        
        // [Window]
        // - Width
        int winWidth = iniFile.ReadInteger("Window", "Width", windowWidth);
        if (winWidth < windowMinSize) {
            winWidth = windowWidth;
        }
        windowWidth = winWidth;
        
        // - Height
        int winHeight = iniFile.ReadInteger("Window", "Height", windowHeight);
        if (winHeight < windowMinSize) {
            winHeight = windowHeight;
        }
        windowHeight = winHeight;
        
        // - PositionX
        windowPositionX = iniFile.ReadInteger("Window", "PositionX", (screenWidth - windowWidth)/2);
        
        // - PositionY
        windowPositionY = iniFile.ReadInteger("Window", "PositionY", (screenHeight - windowHeight)/2);
        
        // - Maximized
        windowMaximized = iniFile.ReadBool("Window", "Maximized", windowMaximized);
        
        // [Interface]
        // - Language
        String interLanguage = iniFile.ReadString("Interface", "Language", interfaceLanguage);
        if (!java.util.Arrays.asList(supportedLanguages).contains(interLanguage)) {
            interLanguage = interfaceLanguage;
        }
        interfaceLanguage = interLanguage;
        
        // [Disclaimer]
        // - Shown
        disclaimerShown = iniFile.ReadBool("Disclaimer", "Shown", disclaimerShown);
        // - DoNotShowAgain
        disclaimerDoNotShowAgain = iniFile.ReadBool("Disclaimer", "DoNotShowAgain", disclaimerDoNotShowAgain);
    }
    
    /**
     * Save the configuration to file
     * 
     * @return
     */
    public boolean saveConfiguration() {
        // Update the data
        iniFile.WriteInteger("Window", "Width", windowWidth);
        iniFile.WriteInteger("Window", "Height", windowHeight);
        iniFile.WriteInteger("Window", "PositionX", windowPositionX);
        iniFile.WriteInteger("Window", "PositionY", windowPositionY);
        iniFile.WriteBool("Window", "Maximized", windowMaximized);
        iniFile.WriteString("Interface", "Language", interfaceLanguage);
        iniFile.WriteBool("Disclaimer", "Shown", disclaimerShown);
        iniFile.WriteBool("Disclaimer", "DoNotShowAgain", disclaimerDoNotShowAgain);
        
        // And write it
        return iniFile.UpdateFile();
    }
    
    /**
     * Setter for the window size
     * 
     * @param width The window width
     * @param height The window height
     */
    public void setWindowSize(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }
    
    /**
     * Getter for the window width
     * 
     * @return 
     */
    public int getWindowWidth() {
        return windowWidth;
    }
    
    /**
     * Getter for the window height
     * 
     * @return 
     */
    public int getWindowHeight() {
        return windowHeight;
    }
    
    /**
     * Setter for the window position
     * 
     * @param positionX The position on X axis (horizontal)
     * @param positionY The position on Y axis (vertical)
     */
    public void setWindowPosition(int positionX, int positionY) {
        windowPositionX = positionX;
        windowPositionY = positionY;
    }
    
    /**
     * Getter for the window X (horizontal) position
     * 
     * @return 
     */
    public int getWindowPositionX() {
        return windowPositionX;
    }
    
    /**
     * Getter for the window Y (vertical) position
     * 
     * @return 
     */
    public int getWindowPositionY() {
        return windowPositionY;
    }
    
    /**
     * Setter for the window maximized state
     * 
     * @param maximized The state
     */
    public void setWindowMaximized(boolean maximized) {
        windowMaximized = maximized;
    }
    
    /**
     * Getter for the window maximized state
     * 
     * @return 
     */
    public boolean isWindowMaximized()
    {
        return windowMaximized;
    }
    
    /**
     * Setter for the interface language
     * 
     * @param language The new language
     */
    public void setInterfaceLanguage(String language) {
        interfaceLanguage = language;
    }
    
    /**
     * Getter for the interface language
     * 
     * @return 
     */
    public String getInterfaceLanguage() {
        return interfaceLanguage;
    }
    
    /**
     * Getter for the (internal) list of supported languages (there is no setter for this item,
     * as it is hard-coded and not stored in the actual INI file).
     * 
     * @return 
     */
    public String[] getSupportedLanguages() {
        return supportedLanguages;
    }
    
    /**
     * Setter for the disclaimer shown state
     * 
     * @param shown The state
     */
    public void setDisclaimerShown(boolean shown) {
        disclaimerShown = shown;
    }
    
    /**
     * Getter for the disclaimer shown state
     * 
     * @return 
     */
    public boolean isDisclaimerShown() {
        return disclaimerShown;
    }
    
    /**
     * Setter for the disclaimer do not show again state
     * 
     * @param shown The state
     */
    public void setDisclaimerDoNotShowAgain(boolean shown) {
        disclaimerDoNotShowAgain = shown;
    }
    
    /**
     * Getter for the disclaimer do not show again state
     * 
     * @return 
     */
    public boolean isDisclaimerDoNotShowAgain() {
        return disclaimerDoNotShowAgain;
    }
    
}
