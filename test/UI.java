package org.fleen.cloudedPlain.test;

import javax.swing.JFrame;

import org.fleen.cloudedPlain.core.Plain;

@SuppressWarnings("serial")
public class UI extends JFrame{

  public ImagePanel imagepanel;

  public UI(Plain plain){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0,0,plain.width,plain.height);
    imagepanel=new ImagePanel();
    setContentPane(imagepanel);
    setVisible(true);}

}
