/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import model.Model;

/**
 *
 * @author Thomas
 */
public class BarComponent extends JComponent{
    
    /**
     * Name of the property that defines the shape of the Bar object.
     */
    public static final String PROPERTY_SHAPE = "bar";

    /**
     * Constant value to set the property <code>bar</code> to
     * <code>RECTANGLE</code>.
     */
    public static final int RECTANGLE = 1;
    /**
     * Default Bar object size.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(100, 50);
    /**
     * Object that holds the set of property listeners of the Bar.
     */
    private final PropertyChangeSupport support;
    /**
     * Attribute that represents the value of the property <code>bar</code>.
     */
    private int myBar;
    /**
     * Attribute that represents the value of the property <code>color</code>.
     */
    private java.awt.Color myColor;
    
    /**
     * Model where all the value are stored.
     */
    private Model model;
    /**
     * Build one default bar object.
     */
    public BarComponent() {
        this(BarComponent.RECTANGLE, Color.red);
    }

    /**
     * Build one shape object, setting its two properties.
     *
     * @param theBar an int value that must belong to {
     *      <code>BarComponenent.RECTANGLE</code>
     *  }
     * @param theColor a color used to fill the shape
     */
    public BarComponent(final int theBar, final Color theColor) {
        super();
        support = new PropertyChangeSupport(this);
        privateSetBar(theBar);
        privateSetColor(theColor);
    }

    /**
     * Set the fill color of the bar.
     * This method protects the constructor of the BarComponent class.
     *
     * @param theColor a color
     *
     * @see #setColor(java.awt.Color)
     */
    private void privateSetColor(final Color theColor) {
        Color oldColor = getColor();
        myColor = theColor;
        repaint();
        support.firePropertyChange("color", oldColor, getColor());
    }

    /**
     * Set the fill color of the bar.
     *
     * @param theColor a color
     *
     * @see #getColor()
     */
    public void setColor(final Color theColor) {
        privateSetColor(theColor);
    }

    /**
     * Provides the fill color of the Shape object.
     *
     * @return the fill color
     *
     * @see #setColor(java.awt.Color)
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * Set the shape of the shape object.
     * This method protects the constructor of the Bar class.
     *
     * @param theBar an int value that must belong to {
     *      <code>BarComponent.RECTANGLE</code>
     *  }
     *
     * @see #setShape(int)
     */
    private void privateSetBar(final int theBar) {
        int oldShape = getBar();
        if (RECTANGLE == theBar) {
            myBar = RECTANGLE;
        }
        repaint();
        support.firePropertyChange(PROPERTY_SHAPE, oldShape, getBar());
    }

    /**
     * Set the shape of the shape object.
     *
     * @param theShape an int value that must belong to
     *                 {<code>Shape.OVALE</code>, <code>Shape.RECTANGLE</code>}
     *
     * @see #getShape()
     */
    public void setBar(final int theShape) {
        privateSetBar(theShape);
    }

    /**
     * Provides a value representing the shape of the Shape object.
     * This value belongs to {<code>Shape.OVALE</code>,
     * <code>Shape.RECTANGLE</code>}
     *
     * @return the current shape value
     */
    public int getBar() {
        return myBar;
    }

    @Override
    public boolean contains(int x, int y) {
        //x²/a² + y²/b² <= 1.
        double a = getWidth() / 2;
        double b = getHeight() / 2;
        double nX = x - a;
        double nY = b - y;

        return (nX * nX) / (a * a) + (nY * nY) / (b * b) <= 1;
    }

    @Override
    public void paint(final Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(myColor);
        switch (myBar) {
            case RECTANGLE:
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            default:
                System.out.println("ERROR THIS VALUE IS NOT CORRECT" + myBar);
        }
        g.setColor(oldColor);
    }

    @Override
    public void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(
            final String propertyName,
            final PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(
            final String propertyName,
            final PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
