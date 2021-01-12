/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

import java.awt.Color;
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
public class ButtonBarComponent extends JLayeredPane {

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
    
    private final ButtonBar buttonBar;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final EventListenerList listeners = new EventListenerList();

    public ButtonBarComponent() {
        this(Color.RED);
    }

    public ButtonBarComponent(final Color bgColor) {
        buttonBar = new ButtonBar(ButtonBar.RIGHT_ARROW, bgColor);

        super.add(buttonBar, JLayeredPane.DEFAULT_LAYER);
        super.setLayout(null);
        configureSelfListeners();
        initPropertyForwarders();
    }

    @Override
    public void setBounds(
            final int x, final int y,
            final int width, final int height) {
        super.setBounds(x, y, width, height);

        buttonBar.setBounds(0, 0, width, height);
    }

    public void setBackgroundColor(final Color bgColor) {
        buttonBar.setColor(bgColor);
    }


    public Color getBackgroundColor() {
        return buttonBar.getColor();
    }
    
    public void setOriantationArrow(final int oriantation) {
        buttonBar.setBar(oriantation);
    }

    @Override
    public boolean contains(int x, int y) {
        return buttonBar.contains(x, y);
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

    private void fireShapeLabelClicked(final ButtonBarEvent event) {
        synchronized (listeners) {
            for (ButtonBarListener listener : listeners.getListeners(ButtonBarListener.class)) {
                listener.buttonbarClicked(event);
            }
        }
    }
    
    
    private void configureSelfListeners() {
        buttonBar.addPropertyChangeListener("color", (e) -> {
            support.firePropertyChange(
                    "backgroundColor", e.getOldValue(), e.getNewValue());
        });
    }

    private void initPropertyForwarders() {
        super.addMouseListener(new MouseAdapter() {
            private Color idleColor;

            @Override
            public void mousePressed(MouseEvent e) {
                buttonBar.setColor(Color.BLACK);
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                buttonBar.setColor(Color.GREEN);
                final ButtonBarEvent event = new ButtonBarEvent(ButtonBarComponent.this);
                fireShapeLabelClicked(event);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                idleColor = buttonBar.getColor();
                buttonBar.setColor(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonBar.setColor(idleColor);
            }

        });
    }
}
