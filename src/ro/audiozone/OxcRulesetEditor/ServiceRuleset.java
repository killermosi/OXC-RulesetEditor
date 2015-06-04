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
 * Ruleset management
 * 
 * @author @author Silviu Ghita <killermosi@yahoo.com>
 */
public class ServiceRuleset {
    /**
     * The instance
     */
    private static ServiceRuleset instance;
    
    /**
     * The main window instance, for interface manipulation
     */
    private WindowMain mainWindow;
    
    /**
     * Singleton instance retrieval mechanism
     * 
     * @return 
     */
    public static ServiceRuleset getInstance() {
        if (instance == null) {
            synchronized (ServiceRuleset.class) {
                if (instance == null) {
                    instance = new ServiceRuleset();
                }
            }
        }
        return instance;
    }
    
    /**
     * Constructor
     */
    private ServiceRuleset() {
        
    }
    
    public void setMainWindow(WindowMain window) {
        
    }
}
