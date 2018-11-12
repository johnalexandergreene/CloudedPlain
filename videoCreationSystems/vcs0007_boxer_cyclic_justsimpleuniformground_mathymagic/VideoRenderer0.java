package org.fleen.cloudedPlain.videoCreationSystems.vcs0007_boxer_cyclic_justsimpleuniformground_mathymagic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunk;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

public class VideoRenderer0 implements VideoRenderer{

  public static final Color[] P_TOY_STORY_ADJUSTED2=new Color[]{
      new Color(168,67,39),
      new Color(251,206,89),
      new Color(88,184,121),
      new Color(154,94,154),
      new Color(234,61,65),
      new Color(248,237,23),
      new Color(249,139,90),
      new Color(0,146,232),
      new Color(254,178,213)};
    
    public static final Color[] P_PORCO_ROSSO=new Color[]{
      new Color(227,237,76),
      new Color(184,194,105),
      new Color(226,235,232),
      new Color(174,202,224),
      new Color(94,132,197),
      new Color(248,203,161),
      new Color(179,145,120),
      new Color(220,178,164),
      new Color(90,14,0),
      new Color(189,53,31)};
    
  static final Color[] COLORS=P_TOY_STORY_ADJUSTED2;
  
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
