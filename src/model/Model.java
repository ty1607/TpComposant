/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComponent;


/**
 *
 * @author tyrel
 */
public class Model extends JComponent {
    private int maxI, minI;
    private float maxF, minF;
    private int length = 20; 

    public Model(int maxI, int minI) {
        super();
        this.maxI = maxI;
        this.minI = minI;
    }

    public Model(float maxF, float minF) {
        super();
        this.maxF = maxF;
        this.minF = minF;
    }

    public int getMaxI() {
        return maxI;
    }

    public void setMaxI(int maxI) {
        this.maxI = maxI;
    }

    public int getMinI() {
        return minI;
    }

    public void setMinI(int minI) {
        this.minI = minI;
    }

    public float getMaxF() {
        return maxF;
    }

    public void setMaxF(float maxF) {
        this.maxF = maxF;
    }

    public float getMinF() {
        return minF;
    }

    public void setMinF(float minF) {
        this.minF = minF;
    }
    
    
}
