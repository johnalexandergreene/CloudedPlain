package org.fleen.cloudedPlain.test;

import javax.swing.JFrame;

import org.fleen.cloudedPlain.core.CloudedPlain;

@SuppressWarnings("serial")
public class UI extends JFrame{

  public ImagePanel imagepanel;

  public UI(CloudedPlain plain){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0,0,plain.width+100,plain.height+100);
    imagepanel=new ImagePanel();
    setContentPane(imagepanel);
    setVisible(true);}

}
