package com.test2.superservice;

import GUI.JFrameDeposito;
import java.awt.Dimension;

/**
 * Main
 */
public class SuperService {

    public static void main(String[] args) {

        JFrameDeposito jFrameDeposito = new JFrameDeposito();
        //jFrameDeposito.setExtendedState(JFrameDeposito.MAXIMIZED_BOTH);
        jFrameDeposito.setMinimumSize(new Dimension(900, 590));
        jFrameDeposito.setVisible(true);
    }
}
