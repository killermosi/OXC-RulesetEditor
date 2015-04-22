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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

/**
 * A special colored label used for hints
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class ComponentHintLabel extends JLabel {
    /**
     * Custom paint job
     * @param g The graphic
     */
    @Override
    protected void paintComponent(Graphics g){
        // Create the 2D copy
        Graphics2D g2 = (Graphics2D)g.create();
        Color originalColor = g2.getColor();
        
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 50);
        g2.setPaint(Color.YELLOW);
        g2.setPaint(new Color(244, 217, 65));
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 50));

        g2.setColor(originalColor);
        super.paintComponent(g);
        
        // Dipose of copy
        g2.dispose();
    }
}
