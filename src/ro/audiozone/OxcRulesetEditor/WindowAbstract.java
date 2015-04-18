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
 * Provides basic amenities for windows
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public abstract class WindowAbstract extends javax.swing.JFrame {
    final protected ServiceConfiguration config = ServiceConfiguration.getInstance();
    
    final protected java.util.ResourceBundle lang = java.util.ResourceBundle.getBundle(
                "ro/audiozone/OxcRulesetEditor/i18n_" + config.getInterfaceLanguage()
    );
}
