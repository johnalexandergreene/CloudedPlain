package org.fleen.cloudedPlain.core.renderVideo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;
import org.fleen.cloudedPlain.core.stripeSystem.chunks.Chunks;

/*
 * test chunks
 */
public class VideoRenderer1 implements VideoRenderer{

  //crass rainbow
  static final Color[] COLORS={
      new Color(255,0,0,64),
      new Color(255,128,0,64),
      new Color(128,255,0,64),
      new Color(0,255,0,64),
      new Color(0,255,128,64),
      new Color(0,128,255,64),
      new Color(0,0,255,64)};
  
  public BufferedImage renderFrame(StripeSystem stripesystem){
    
    Chunks c=stripesystem.getChunks();
    System.out.println("chunkscount="+c.size());
    
    int w=stripesystem.stage.getWidth(),h=stripesystem.stage.getHeight();
    BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    Graphics2D g=image.createGraphics();
    g.setPaint(Color.gray);
    g.fillRect(0,0,w,h);
    for(Stripe stripe:stripesystem.stripes){
      g.setPaint(getColor(stripe));
      g.fillRect(stripe.getCoorX(),stripe.getCoorY(),stripe.getWidth(),stripe.getHeight());}
    return image;}
  
  Map<Stripe,Color> colorbystripe=new HashMap<Stripe,Color>();
  Random random=new Random();
  
  Color getColor(Stripe stripe){
    Color c=COLORS[stripe.getValue()%COLORS.length];
    return c;
//    Color c=colorbystripe.get(stripe);
//    if(c==null){
//      c=getRandomColor();
//      colorbystripe.put(stripe,c);}
//    return c;
    }
  
  Color getRandomColor(){
    return COLORS[random.nextInt(COLORS.length)];}

}
