package org.fleen.cloudedPlain.test;

import javax.swing.JFrame;

import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

@SuppressWarnings("serial")
public class UI extends JFrame{

  public ImagePanel imagepanel;

  public UI(StripeSystem plain){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0,0,plain.width+100,plain.height+100);
    imagepanel=new ImagePanel();
    setContentPane(imagepanel);
    setVisible(true);}

}
