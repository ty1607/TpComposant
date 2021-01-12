/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLayeredPane;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Thomas
 */
public class BarComponent extends JLayeredPane {

    /**
     * Defines the margins used to set the preferred size of the ShapeLabel,
     * depending on the text to display.
     */
    private enum Margin {
        HORIZONTAL(1.2),
        VERTICAL(1.4);
        private final Double value;

        private Margin(final Double value) {
            this.value = value;
        }

        public Double getValue() {
            return value;
        }
    }
    
    private final Bar bar;
    private final ButtonBar buttonRight;
    private final ButtonBar buttonLeft;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final EventListenerList listeners = new EventListenerList();

    public BarComponent() {
        this(Color.RED);
    }

    public BarComponent(final Color bgColor) {
        bar = new Bar(Bar.RECTANGLE, bgColor);
        buttonRight = new ButtonBar(ButtonBar.RIGHT_ARROW, Color.LIGHT_GRAY);
        buttonLeft = new ButtonBar(ButtonBar.LEFT_ARROW, Color.LIGHT_GRAY);
        buttonRight.setSize(100, 100);
        buttonLeft.setSize(100, 100);
        super.add(buttonLeft, JLayeredPane.DEFAULT_LAYER);
        super.add(bar, JLayeredPane.DEFAULT_LAYER);
        super.add(buttonRight, JLayeredPane.DEFAULT_LAYER);
        super.setLayout(new GridLayout(1,3));
        configureSelfListeners();
        initPropertyForwarders();
    }

    @Override
    public void setBounds(
            final int x, final int y,
            final int width, final int height) {
        super.setBounds(x, y, width, height);

        bar.setBounds(0, 0, width, height);
    }

    public void setBackgroundColor(final Color bgColor) {
        bar.setColor(bgColor);
    }


    public Color getBackgroundColor() {
        return bar.getColor();
    }

    @Override
    public boolean contains(int x, int y) {
        return bar.contains(x, y);
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

    public void addShapeLabelListener(final BarListener listener) {
        listeners.add(BarListener.class, listener);
    }

    public void removeShapeLabelListener(final BarListener listener) {
        listeners.remove(BarListener.class, listener);
    }

    private void fireShapeLabelClicked(final BarEvent event) {
        synchronized (listeners) {
            for (BarListener listener : listeners.getListeners(BarListener.class)) {
                listener.barClicked(event);
            }
        }
    }
    
    
    private void configureSelfListeners() {
        bar.addPropertyChangeListener("color", (e) -> {
            support.firePropertyChange(
                    "backgroundColor", e.getOldValue(), e.getNewValue());
        });
    }

    private void initPropertyForwarders() {
        super.addMouseListener(new MouseAdapter() {
            private Color idleColor;

            @Override
            public void mousePressed(MouseEvent e) {
                bar.setColor(Color.BLACK);
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                bar.setColor(Color.GREEN);
                final BarEvent event = new BarEvent(BarComponent.this);
                fireShapeLabelClicked(event);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                idleColor = bar.getColor();
                bar.setColor(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bar.setColor(idleColor);
            }

        });
    }
}
