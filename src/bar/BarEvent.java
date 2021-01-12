/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

import java.util.EventObject;

/**
 *
 * @author Thomas
 */
public class BarEvent extends EventObject {
    public BarEvent(final Object source) {
        super(source);
    }
}