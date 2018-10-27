package org.fleen.cloudedPlain.videoCreationSystems;

import javax.swing.JFrame;

import org.fleen.cloudedPlain.core.CloudedPlain;

@SuppressWarnings("serial")
public class UI extends JFrame{

  public ImagePanel imagepanel;
  
  public UI(CloudedPlain cloudedplain){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(50,50,cloudedplain.stripesystem.stage.getWidth()+100,cloudedplain.stripesystem.stage.getHeight()+100);
    imagepanel=new ImagePanel(cloudedplain);
    setContentPane(imagepanel);
    setVisible(true);}

}
