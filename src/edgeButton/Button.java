/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edgeButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import model.Model;

/**
 *
 * @author tyrel
 */
public class Button extends JComponent{
    
    
    /**
     * Name of the property that defines the shape of the Shape object.
     */
    public static final String PROPERTY_SHAPE = "shape";

    /**
     * Constant value to set the property <code>shape</code> to
     * <code>SQUARE</code>.
     */
    public static final int SQUARE = 1;
    /**
     * Default Shape object size.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(50, 50);
    /**
     * Object that holds the set of property listeners of the Shape.
     */
    private final PropertyChangeSupport support;
    /**
     * Attribute that represents the value of the property <code>shape</code>.
     */
    private int myButton;
    /**
     * Attribute that represents the value of the property <code>color</code>.
     */
    private java.awt.Color myColor;

    /**
     * Build one default shape object.
     */
    public Button() {
        this(Button.SQUARE, Color.red);
    }

    /**
     * Build one shape object, setting its two properties.
     *
     * @param theShape an int value that must belong to
     *                 {<code>Shape.OVALE</code>, <code>Shape.RECTANGLE</code>}
     * @param theColor a color used to fill the shape
     */
    public Button(final int theShape, final Color theColor) {
        super();
        support = new PropertyChangeSupport(this);
        privateSetShape(theShape);
        privateSetColor(theColor);
    }

    /**
     * Set the fill color of the shape.
     * This method protects the constructor of the Shape class.
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
     * Set the fill color of the shape.
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
     * This method protects the constructor of the Shape class.
     *
     * @param theShape an int value that must belong to
     *                 {<code>Shape.OVALE</code>, <code>Shape.RECTANGLE</code>}
     *
     * @see #setShape(int)
     */
    private void privateSetShape(final int theShape) {
        int oldShape = getButton();
        if (SQUARE == theShape) {
            myButton = SQUARE;
        }
        repaint();
        support.firePropertyChange(PROPERTY_SHAPE, oldShape, getButton());
    }

    /**
     * Set the shape of the shape object.
     *
     * @param theShape an int value that must belong to
     *                 {<code>Shape.OVALE</code>, <code>Shape.RECTANGLE</code>}
     *
     * @see #getShape()
     */
    public void setShape(final int theShape) {
        privateSetShape(theShape);
    }

    /**
     * Provides a value representing the shape of the Shape object.
     * This value belongs to {<code>Shape.OVALE</code>,
     * <code>Shape.RECTANGLE</code>}
     *
     * @return the current shape value
     */
    public int getButton() {
        return myButton;
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
        switch (myButton) {
            case SQUARE:
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            default:
                System.out.println("ERROR THIS VALUE IS NOT CORRECT" + myButton);
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
