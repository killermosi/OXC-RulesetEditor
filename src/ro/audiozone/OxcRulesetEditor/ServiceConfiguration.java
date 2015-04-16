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
     * Ini file handling
     */
    private ServiceIniFile iniFile;
    
    /**
     * The ini file default
     */
    private final String iniFileName = "config.ini";
    
    /**
     * The location from which the file is read
     */
    private String iniFileLocation;
    
    /**
     * Various options
     */
    private int windowPositionX = 0;
    private int windowPositionY = 0;
    private int windowSizeX = 800;
    private int windowSizeY = 600;
    
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
        
    }
    
    /**
     * Determine the location of the ini file
     * @return 
     */
    private String getIniFileLocation() {
        return "";
    }
}
