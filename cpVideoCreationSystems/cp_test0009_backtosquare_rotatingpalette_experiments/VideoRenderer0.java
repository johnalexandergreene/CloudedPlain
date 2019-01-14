package org.fleen.cloudedPlain.cpVideoCreationSystems.cp_test0009_backtosquare_rotatingpalette_experiments;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

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
      g.setPaint(getColor(chunk,stripesystem));
      g.fillRect(chunk.getCoorX(),chunk.getCoorY(),chunk.getWidth(),chunk.getHeight());}
    return image;}
  
  Color getColor(Chunk chunk,StripeSystem stripesystem){
    int a=0;
    Color[][] palette=getPalette();
    for(Stripe stripe:chunk.stripes)
      a+=stripe.getValue();
    
    int deltaindex=a%palette.length;
   
//    if(deltaindex<0)deltaindex=palette.length+deltaindex;
    int timeindex=stripesystem.time%palette[0].length;
    if(timeindex<0)timeindex+=palette[0].length;
    Color c=palette[deltaindex][timeindex];
    
    return c;}
  
  /*
   * CREATE A BIG PALETTE FOR FLOATY INDICES
   * A cell's delta ( range [0,1] ) is the sum of all deltas placed there by rings : summeddelta
   * This summed delta is a location on the palette. We get the specific index via summeddelta*palettelength : paletteintex
   * the palette is 1024 long. Maybe more. A big smear of colors. 
   * 
   * On the smear there is a more-or-less uniform delta-value that means 
   * "compatible but not too similar", and anotehr that means "bold contrast".
   * Every location on the palette has 2 such options (because there are 2 directions on the palette, neg and pos) 
   * 
   */
  
  Color[][] palette=null;//x is delta, y is time
  
  Color[][] getPalette(){
    if(palette==null)createPalette0();
    return palette;}
  
  void createPalette0(){
    //load it
    BufferedImage paletteimage=null;
    try{
      paletteimage=ImageIO.read(VideoRenderer0.class.getResource("palette001.png"));
    }catch(Exception x){
      x.printStackTrace();}
    //get the colors out of it
    int 
      w=paletteimage.getWidth(),
      h=paletteimage.getHeight();
    palette=new Color[w][h];
    for(int x=0;x<w;x++){
      for(int y=0;y<h;y++){
        palette[x][y]=new Color(paletteimage.getRGB(x,y));}}}

}
