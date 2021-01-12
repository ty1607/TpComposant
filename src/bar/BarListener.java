/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

import java.util.EventListener;

/**
 *
 * @author Thomas
 */
public interface BarListener extends EventListener {
    void barClicked(final BarEvent event);
}
