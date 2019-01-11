package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0003_wanderer_justground_lushfatty;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

/*
 * test chunks
 */
public class VideoRenderer0 implements VideoRenderer{

//  //crass rainbow
//  static final Color[] COLORS={
//      new Color(255,0,0),
//      new Color(255,128,0),
//      new Color(128,255,0),
//      new Color(0,255,0),
//      new Color(0,255,128),
//      new Color(0,128,255),
//      new Color(0,0,255)};
  
  //crass rainbow
  static final Color[] COLORS={
    new Color(255,0,0),
    new Color(255,128,0),
    new Color(255,255,0),
    new Color(128,255,0),
    new Color(0,255,0),
    new Color(0,255,128),
    new Color(0,255,255),
    new Color(0,128,255),
    new Color(0,0,255),
    new Color(128,0,255),
    new Color(255,0,255),
    new Color(255,0,128),
    };
  
  public BufferedImage renderFrame(StripeSystem stripesystem){
    
    int w=stripesystem.stage.getWidth(),h=stripesystem.stage.getHeight();
    BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=image.createGraphics();
    
//flip it?
//    AffineTransform t=AffineTransform.getScaleInstance(1,-1);
//    t.translate(0,h);
//    g.setTransform(t);
    
    g.setPaint(Color.gray);
    g.fillRect(0,0,w,h);
    Chunks chunks=stripesystem.getChunks();
    for(Chunk chunk:chunks){
      g.setPaint(getColor(chunk));
      g.fillRect(chunk.getCoorX(),chunk.getCoorY(),chunk.getWidth(),chunk.getHeight());}
    return image;}
  
  Color getColor(Chunk chunk){
    int a=0;
    for(Stripe stripe:chunk.stripes)
      a+=stripe.getValue();
    return COLORS[a%COLORS.length];}

}
