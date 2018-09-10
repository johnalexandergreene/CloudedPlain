package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.fleen.cloudedPlain.core.renderAudio.RendererSound;
import org.fleen.cloudedPlain.core.renderVideo.RasterExporter;
import org.fleen.cloudedPlain.core.renderVideo.RendererGraphics;
import org.fleen.cloudedPlain.core.stripeGenerators.StripeGenerator;

/*
 * A 3D form to be interpreted as a sequence of 2D tile-patterns 
 * to be rendered as video. 
 * 
 * An abstract plain
 * everything but Stripe creator
 * 
 * A rectangular array of cells
 * stored as integer values
 * 
 * A bunch of Stripes
 */
public class Plain{
  
  /*
   * ################################
   * CONSTRUCTORS
   * ################################
   */
  
  public Plain(){}
  
  public Plain(
    int w,int h,int d,
    StripeGenerator cc,
    RendererGraphics rg,RendererSound rs,
    File exportdir,
    PlainProgressListener rendererlistener){
    setDims(w,h,d);
    setGenerator(cc);
    setRendererGraphics(rg);
    setRendererSound(rs);
    setExportDir(exportdir);
    setRendererListener(rendererlistener);}
  
  /*
   * ################################
   * IMPLEMENTATION OF CPRectangle
   * ################################
   */
  
  public int getCoorX(){
    return 0;}
  
  public int getCoorY(){
    return 0;}

  public int getWidth(){
    return width;}

  public int getHeight(){
    return height;}
  
  /*
   * ################################
   * GEOMETRY
   * This plain is a 3d brick
   * the slices are rectangles
   * ################################
   */
  
  //all integers because our pixels are so dense they may as well be floats
  //and int math is fast. and precise. and to scale. 
  public int width,height,duration,centerx,centery;
  
  public void setDims(int w,int h,int d){
    width=w;
    height=h;
    duration =d;
    centerx=w/2;
    centery=h/2;}
  
  /*
   * ################################
   * Stripe Generators
   * ################################
   */
  
  /*
   * the plain contains one generator, which may refer to other generators, and so on, treewise
   * There's gotta be a final authority governing Stripe generation for this plain. So that's this generator here.
   */
  public StripeGenerator generator=null;
  /*
   * All the Stripes generated by the generator
   * They are generated, do their thing, then signal to be discarded
   */
  public List<Stripe> stripes=new ArrayList<Stripe>();
  
  public void setGenerator(StripeGenerator g){
    this.generator=g;
    g.setPlain(this);}
  
  /*
   * query Stripecreator logic to create 0..n Stripes
   * return all created Stripes in a list
   */
  protected List<Stripe> generateStripes(){
    List<Stripe> newstripes=generator.generate();
    stripes.addAll(newstripes);
    return newstripes;}
  
  /*
   * discard all Stripes that are finished
   * return all discarded Stripes in a list
   */
  protected List<Stripe> discardStripes(){
    List<Stripe> d=new ArrayList<Stripe>();
    Iterator<Stripe> i=stripes.iterator();
    Stripe c;
    while(i.hasNext()){
      c=i.next();
      if(c.finished()){
        i.remove();
        d.add(c);}}
    return d;}
  
  /*
   * ################################
   * SLICE
   * a 2D slice of our 3D plain
   * The slice is a 2d array of integers
   * Upon this array the Stripes manifest themselves
   * then we render the array as graphics and sound
   * ################################
   */
  
  public int frameindex;
  public int[][] frame;//the present slice
  
  public int[][] getNextFrame(){
    if(frameindex==duration)return null;
    //according to each Stripe's logic, discard as necessary
    discardStripes();
    //according to Stripe creator logic, create Stripes
    generateStripes();
    //set all the slice cells' values to 0 in prep for Stripe manifestation
    zeroCells();
    //
    manifestStripesUponSlice();//map Stripes to plain cells
//    shapes=null;//invalidate shapes so they get remapped
    frameindex++;
    return frame;}
  
  private void initFrameIterator(){
    frameindex=0;
    frame=new int[width][height];
    zeroCells();}
  
  /*
   * set value of all cells to zero in preparation for receiving Stripe manifestations 
   */
  public void zeroCells(){
    for(int x=0;x<width;x++)
      for(int y=0;y<height;y++)
        frame[x][y]=0;}
  
  /*
   * manifest all of the Stripes, using tslice as param
   * returns true if this is the last slice
   */
  private void manifestStripesUponSlice(){
    for(Stripe Stripe:stripes)
      Stripe.manifest();}
  
  /*
   * ################################
   * EXPORT DIR
   * ################################
   */
  
  File exportdir;
  
  public void setExportDir(File d){
    exportdir=d;}
  
  /*
   * ################################
   * RENDER
   * ################################
   */
  
  /*
   * 43200=60*720. 720=1*2*3*4*5*6
   */
  public static final int
    //slices per second. aka video frame rate
    SLICERATE=60,
    //sound sample rate over a single slice. 
    FRAMESOUNDSAMPLERATE=720,
    //
    SOUNDSAMPLERATE=SLICERATE*FRAMESOUNDSAMPLERATE,
    SOUNDTICKMAXVAL=65535;
  
  public RendererGraphics renderergraphics=null;
  public RendererSound renderersound=null;
  public PlainProgressListener progresslistener=null;
  //the sound data for the plain. A sound of duration equal to plain duration
  public int[] plainsound;
  
  public void setRendererGraphics(RendererGraphics r){
    renderergraphics=r;
    renderergraphics.setPlain(this);}
  
  public void setRendererSound(RendererSound r){
    renderersound=r;
    renderersound.setPlain(this);}
  
  public void setRendererListener(PlainProgressListener l){
    progresslistener=l;}
  
  public void render(){
    System.out.println("render start");
    BufferedImage frameimage;
    plainsound=new int[duration*FRAMESOUNDSAMPLERATE];
    //the sound spanning a single slice. Concactenate them all to get the plain sound 
    int[] framesound=null;
    //init the frame iterator and get the first frame
    initFrameIterator();
    frame=getNextFrame();
    //MAIN FRAME GETTING AND RENDERING LOOP
    while(frame!=null){
      //render the image and sound
      frameimage=renderergraphics.render(frame);
      framesound=renderersound.render(frame);
      rasterexporter.export(frameimage,frameindex,exportdir);
      System.arraycopy(framesound,0,plainsound,(frameindex-1)*FRAMESOUNDSAMPLERATE,framesound.length);
      progresslistener.notify(this,frameimage,framesound);
      frame=getNextFrame();}
    System.out.println("export sound");
    exportSound();
    System.out.println("render end");}
  
  /*
   * ################################
   * EXPORT
   * ################################
   */
  
  RasterExporter rasterexporter=new RasterExporter();
  
  private void exportSound(){
    //convert int array to byte array
    //2 bytes per sound tick. so max sound tick value is 65536
    final byte[] soundbytes = new byte[plainsound.length*2];
    for(int i=0;i<plainsound.length;i++){
      soundbytes[i*2]=(byte)plainsound[i];
      soundbytes[i*2+1]=(byte)(plainsound[i]>>>8);}
    //output the file
    File out = new File(exportdir.getAbsolutePath()+"/plain.wav");
    boolean bigEndian = false;
    boolean signed = true;
    int bits = 16;
    int channels = 1;
    AudioFormat format;
    format = new AudioFormat((float)SOUNDSAMPLERATE, bits, channels, signed, bigEndian);
    ByteArrayInputStream bais = new ByteArrayInputStream(soundbytes);
    AudioInputStream audioInputStream;
    audioInputStream = new AudioInputStream(bais,format,plainsound.length);
    try{
      AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
      audioInputStream.close();
    }catch(Exception x){
      x.printStackTrace();}}
  
  /*
   * ################################
   * GET INTERSECTION RECTANGLES
   * ################################
   * Given the rectangle that is this plain and given the list of stripes
   * get all of the intersection rectangles formed amongst the plain and stripes
   * 
   * ---
   * ALG
   * ---
   * 
   * Given the Plain rectangle and a list of Stripe rectangles
   * 
   * get an arbitrary stripe. Intersect it with the plain. This gives us 1..3 Intersection class rectangles
   *   If the stripe is off the plain then the intersection is the plain, and that intersection has no parent-stripes
   *   If it's right on the edge of the plain then we get 2 intersection rectangles, 
   *   the part of the plain under the stripe and the rest of the plain.
   *   If it's somewhere in the middle of the plain then we get 3 intersection rectangles
   *   
   * Now we have a list of intersection rectangles : r0
   * 
   * For each stripe
   * intersect it with each rectangle in the list of intersection rectangles
   * (store the stripes involved with each intersection, summing stripes when we intersect stripes with intersections) 
   * keep any new intersection rectangles created, discard the stripe
   * 
   * keep doing that until we run out of stripes
   * 
   * Now we have the intersection rectangles.
   * 
   *  
   * 
   * 
   *    
   * 
   */
  
  public List<Intersection> getIntersectionRectangles(){
    List<Intersection> 
      intersections=new ArrayList<Intersection>(),
      intersectionstoadd=new ArrayList<Intersection>(),
      intersectionstoremove=new ArrayList<Intersection>();
    //get list of stripes
    Iterator<Stripe> stripeiterator=stripes.iterator();
    //get first stripe
    Stripe stripe=stripeiterator.next();
    //intersect with plain
    intersections.addAll(CPRectangle.getIntersection(stripe,this));
    //this gives us at least 1 intersection rectangle
    //get next stripe and so on, 
    //  create more intersections by intersecting stripes with intersections 
    List<Intersection> newintersections=new ArrayList<Intersection>();
    while(stripeiterator.hasNext()){
      stripe=stripeiterator.next();
      //intersect the stripe with each Intersection class rectangle in intersections
      //if intersection occurs then put the newly create intersection class objects in the intersectionstoadd list 
      // and put the old intersections that were involved in the intersection with the stripe into intersectionstoremove list
      intersectionstoadd.clear();
      intersectionstoremove.clear();
      for(Intersection n:intersections){
        newintersections=CPRectangle.getIntersection(stripe,n);
        if(!newintersections.isEmpty()){
          intersectionstoadd.addAll(newintersections);
          intersectionstoremove.add(n);}}
      //adjust the list
      intersections.removeAll(intersectionstoremove);
      intersections.addAll(intersectionstoadd);
    }//keep doing this until we run out of stripes
    //return intersections
    return intersections;}

}
