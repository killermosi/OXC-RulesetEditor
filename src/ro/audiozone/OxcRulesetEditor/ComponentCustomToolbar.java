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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JToolBar;

/**
 * Custom-painted toolbar
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class ComponentCustomToolbar extends JToolBar {
    /**
     * Custom background paint job
     * @param g The graphic
     */
    @Override
    protected void paintComponent(Graphics g){
        // Create the 2D copy
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setColor(Color.WHITE);
        // Apply vertical gradient (optional)
        //g2.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.BLUE));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Dipose of copy
        g2.dispose();
    }
}
