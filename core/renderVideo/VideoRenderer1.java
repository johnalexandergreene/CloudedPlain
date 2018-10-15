package org.fleen.cloudedPlain.core.renderVideo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

/*
 * test chunks
 */
public class VideoRenderer1 implements VideoRenderer{

//  //crass rainbow
//  static final Color[] COLORS={
//      new Color(255,0,0,64),
//      new Color(255,128,0,64),
//      new Color(128,255,0,64),
//      new Color(0,255,0,64),
//      new Color(0,255,128,64),
//      new Color(0,128,255,64),
//      new Color(0,0,255,64)};
  
  //crass rainbow
  static final Color[] COLORS={
      new Color(255,0,0),
      new Color(255,128,0),
      new Color(128,255,0),
      new Color(0,255,0),
      new Color(0,255,128),
      new Color(0,128,255),
      new Color(0,0,255)};
  
  public BufferedImage renderFrame(StripeSystem stripesystem){
    
    int w=stripesystem.stage.getWidth(),h=stripesystem.stage.getHeight();
    BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    Graphics2D g=image.createGraphics();
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