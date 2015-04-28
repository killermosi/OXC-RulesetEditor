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
     * The default ruleset extension
     */
    public final static String DEFAULT_RULESET_EXTENSION = "rul";
    /**
     * Singleton instance
     */
    private static ServiceConfiguration instance;
    
    /**
     * INI file handler
     */
    private final ServiceIniFile iniFile;
    
    /**
     * The INI file name
     */
    private final String iniFileName = "config.ini";
    
    /**
     * The location from which the file is read
     */
    private final String iniFileLocation;

    /**
     * The supported languages list - I don't know how or if this list
     * can be automatically built based on the available bundles
     * 
     * <b>Note</b>: en-US _MUST_ be the first element in the list
     */
    private final String[] supportedLanguages = new String[] {"en-US"};
    
    /**
     * Various options
     */
    private int windowPositionX; // No default, calculated to screen center
    private int windowPositionY; // No default, calculated to screen center
    private int windowWidth = 800;
    private int windowHeight = 600;
    private boolean windowMaximized = false;
    private String interfaceLanguage = supportedLanguages[0]; // en-US
    private int interfaceUndoLevels = 50;
    private boolean disclaimerShown = false;
    private boolean disclaimerDoNotShowAgain = false;

    /**
     * Currently selected language - stores the language that was selected
     * by the user in the configuration dialog to be applied on the next application start
     */
    private String selectedInterfaceLanguage = interfaceLanguage;
    
    /**
     * Internal stuff
     */
    private final int windowMinSize = 200;
    private final int undoLevelsMin = 0;
    private final int undoLevelsMax = 100;
    
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
        // Calculate the ini file path - the directory from where the application is is executed
        // (this means that the user should run the app from somewhere on the disk where it has write access)
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
        /* To be 100% sure, we'll run some checks on the values that were read from the ini file */
        
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
        selectedInterfaceLanguage = interfaceLanguage = interLanguage;
        
        // - UndoLevels
        interfaceUndoLevels = iniFile.ReadInteger("Interface", "UndoLevels", interfaceUndoLevels);
        if (interfaceUndoLevels < undoLevelsMin) {
            interfaceUndoLevels = undoLevelsMin;
        }
        if (interfaceUndoLevels > undoLevelsMax) {
            interfaceUndoLevels = undoLevelsMax;
        }
        
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
        iniFile.WriteString("Interface", "Language", selectedInterfaceLanguage);
        iniFile.WriteInteger("Interface", "UndoLevels", interfaceUndoLevels);
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
     * Getter for the interface language
     * 
     * @return 
     */
    public String getInterfaceLanguage() {
        return interfaceLanguage;
    }

    /**
     * Setter for the selected interface language - sets the "selectedInterfaceLanguage",
     * as the language change is supposed to happen only on application startup
     * 
     * @param language The new language
     */
    public void setSelectedInterfaceLanguage(String language) {
        selectedInterfaceLanguage = language;
    }

    /**
     * Getter for the selected interface language
     * 
     * @return 
     */
    public String getSelectedInterfaceLanguage() {
        return selectedInterfaceLanguage;
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
     * Getter for the undo levels
     * 
     * @return 
     */
    public int getUndoLevels() {
        return interfaceUndoLevels;
    }
    
    /**
     * Setter for the undo levels
     * 
     * @param undoLevels The number of undo levels
     */
    public void setUndoLevels(int undoLevels) {
        interfaceUndoLevels = undoLevels;
        
        // Just in case...
        if (interfaceUndoLevels < undoLevelsMin) {
            interfaceUndoLevels = undoLevelsMin;
        }
        
        if (interfaceUndoLevels > undoLevelsMax) {
            interfaceUndoLevels = undoLevelsMax;
        }
    }
    
    /**
     * Getter for the default application language (when needed for fallback)
     * 
     * @return 
     */
    public String getDefaultLanguage() {
        return supportedLanguages[0];
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
