package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.fleen.cloudedPlain.core.exportVideo.VideoExporter0;
import org.fleen.cloudedPlain.core.renderAudio.AudioRenderer;
import org.fleen.cloudedPlain.core.renderVideo.VideoRenderer;
import org.fleen.cloudedPlain.core.stripeGenerator.StripeGenerator;
import org.fleen.cloudedPlain.core.stripeSystem.Chunk;
import org.fleen.cloudedPlain.core.stripeSystem.SSRectangle;
import org.fleen.cloudedPlain.core.stripeSystem.Stripe;

/*
 * a system of 
 *   1 stage rectangle
 *   0..n stripe rectangles
 *   0..n stripe generators
 *   a time parameter (an integer. A frame index.)
 * 
 * We increment the time (t) from 0 to whatever
 * 
 * t is a parameter for the stripes' definition, governing their location, value, etc
 * stripes generally hang out for a while, maybe sweeping across the stage, maybe just standing there or whatever, then get destroyed.
 * 
 * t also governs the behavior of our stripe generators. Telling them to create a stripe or another generator or kill themselves or something
 * 
 * we also provide various analysis methods for the system, for example getting the intersection squares
 * 
 */
public class CloudedPlain2 implements SSRectangle{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public CloudedPlain2(){}
  
  public CloudedPlain2(
    int w,int h,int d,
    StripeGenerator cc,
    VideoRenderer rg,AudioRenderer rs,
    File exportdir,
    ProgressListener rendererlistener){
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
   * this is the plain's root stripe generator. The final authority on stripe generation.
   * the plain contains one generator, which may refer to other generators, and so on, treewise
   */
  public StripeGenerator generator=null;
  /*
   * All the Stripes generated by the generator, or the generator's generators, etc
   * They are generated, do their thing, then signal to be destroyed
   */
  public List<Stripe> stripes=new ArrayList<Stripe>();
  
  public void setGenerator(StripeGenerator g){
    generator=g;
    g.setStripeSystem(this);}
  
  /*
   * for each stripe generator, conditionally generate 0..n stripes
   */
  protected List<Stripe> generateStripes(){
    List<Stripe> newstripes=generator.generate();
    stripes.addAll(newstripes);
    return newstripes;}
  
  /*
   * get the stripes that are ready to be destroyed
   * remove them from the stripes list
   */
  protected void destroyStripes(){
    Iterator<Stripe> i=stripes.iterator();
    Stripe c;
    int d=0;
    while(i.hasNext()){
      c=i.next();
      if(c.destroyMe()){
        i.remove();
        d++;}}
    System.out.println("destroyed : "+d);}
  
  /*
   * ################################
   * FRAME
   * a 1/60th of a second piece of our process
   * a frame image
   * The slice is a 2d array of integers
   * Upon this array the Stripes manifest themselves
   * then we render the array as graphics and sound
   * ################################
   */
  
  public int frameindex;
  
  
  /*
   * increment frame index
   * generate new stripes with stripe generators
   * destroy any stripes that are ready to be destroyed 
   */
  public boolean incrementFrame(){
    if(frameindex==duration)return null;
    //according to each Stripe's logic, discard as necessary
    getStripesToDestroy();
    //according to logic particular to each stripe generators in the stripegenerators list, generate 0..n stripes
    generateStripes();
    destroyStripes();
    frameindex++;}
  
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
   * 720=1*2*3*4*5*6
   * 720 has lots of factors, that's why I chose it
   * we do 60 frames per second
   * 60*720=43200
   * So 43200 is our per-second sample rate
   */
  public static final int
    //slices per second. aka video frame rate
    SLICERATE=60,
    //sound sample rate over a single slice. 
    FRAMESOUNDSAMPLERATE=720,
    //
    SOUNDSAMPLERATE=SLICERATE*FRAMESOUNDSAMPLERATE,
    SOUNDTICKMAXVAL=65535;
  
  public VideoRenderer renderergraphics=null;
  public AudioRenderer renderersound=null;
  public ProgressListener progresslistener=null;
  //the sound data for the plain. A sound of duration equal to plain duration
  public int[] plainsound;
  
  public void setRendererGraphics(VideoRenderer r){
    renderergraphics=r;
    renderergraphics.setStripeSystem(this);}
  
  public void setRendererSound(AudioRenderer r){
    renderersound=r;
    renderersound.setStripeSystem(this);}
  
  public void setRendererListener(ProgressListener l){
    progresslistener=l;}
  
  public void render(){
    System.out.println("render start");
    BufferedImage frameimage;
    plainsound=new int[duration*FRAMESOUNDSAMPLERATE];
    //the sound spanning a single slice. Concactenate them all to get the plain sound 
    int[] framesound=null;
    //init the frame iterator and get the first frame
    initFrameIterator();
    frame=incrementFrame();
    //MAIN FRAME GETTING AND RENDERING LOOP
    while(frame!=null){
      //render the image and sound
      frameimage=renderergraphics.render(frame);
      framesound=renderersound.render(frame);
      rasterexporter.export(frameimage,frameindex,exportdir);
      System.arraycopy(framesound,0,plainsound,(frameindex-1)*FRAMESOUNDSAMPLERATE,framesound.length);
      progresslistener.notify(this,frameimage,framesound);
      frame=incrementFrame();}
    System.out.println("export sound");
    exportSound();
    System.out.println("render end");}
  
  /*
   * ################################
   * EXPORT
   * ################################
   */
  
  VideoExporter0 rasterexporter=new VideoExporter0();
  
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
   * GET CHUNKS
   * ################################
   * Given this system of plain and stripes
   * get all of the chunks formed via their intersections
   * 
   * A NEW IDEA
   * first intersect all of the vertical stripes (with each other and a plain-shaped chunk)
   * then intersect all the horizontal (with each other and a plain-shaped chunk) 
   * this gives us 2 skeins of nonoverlapping chunks
   * now intersect the two in some kind of orderly way
   * that should do it.
   * 
   * 
   */
  
  public List<Chunk> getChunks(){
    //sort the stripes into vertical and horizontal groups
    List<Stripe> 
      vstripes=new ArrayList<Stripe>(),
      hstripes=new ArrayList<Stripe>();
    for(Stripe stripe:stripes){
      if(stripe.isHorizontal())
        hstripes.add(stripe);
      else
        vstripes.add(stripe);}
    System.out.println("vstripes count = "+vstripes.size());
    //intersect each group, resolve overlaps, get chunks
    List<Chunk> 
      vchunks=getVerticalChunks(hstripes),
      hchunks=getHorizontalChunks(vstripes),
      //intersect our 2 chunk groups
      finalchunks=getFinalChunks(vchunks,hchunks);
//    return finalchunks;
    
    System.out.println("vchunks count = "+vchunks.size());
    return vchunks;//TEST
    
  
  
  }
  
  List<Chunk> getHorizontalChunks(List<Stripe> vstripes){
    return null;
  }
  
  List<Chunk> getFinalChunks(List<Chunk> vchunks,List<Chunk> hchunks){
    return null;
  }
  
  /*
   * convert the list of vertical stripes and the plain-rectangle into a sequence of chunk terminuses (VChunkTerminus class object)
   * each VChunkTerminus 
   *   has an X coordinate, describing its location on the horizontal span of the plain
   *   holds a list of all the stripes that stop or start there 
   * 
   *  
   */
  List<Chunk> getVerticalChunks(List<Stripe> vstripes){
    List<VChunkTerminus> terminusses=getOrderedTermunsesses(vstripes);
    System.out.println("terminusses count = "+terminusses.size());
    List<Chunk> chunks=convertTerminussesToVerticalChunks(terminusses);
    return chunks;}
  
  /*
   * address the sequence of terminusses
   * 
   * for each terminus
   *   create a chunk, unless this terminus is the last one
   *   add all stripes in the stripeeastedge to the stripecoverage set
   *   given the present chunk (the one we just made). Set its stripes list to what's in the stripecoverage set. 
   *   remove all stripes in the stripewestedge from the stripecoverage set
   *   (doing these last 3 steps in this order should handle chunks that start and stop at the same location. 
   *     ie, a chunk with width=1)
   *    
   * 
   */
  private List<Chunk> convertTerminussesToVerticalChunks(List<VChunkTerminus> terminusses){
    List<Chunk> chunks=new ArrayList<Chunk>();
    Set<Stripe> stripecoverage=new HashSet<Stripe>();
    Chunk chunk;
    int 
      tc=terminusses.size(),
      plainheight=getHeight();
    VChunkTerminus t,tnext;
    for(int i=0;i<tc-1;i++){
      t=terminusses.get(i);
      tnext=terminusses.get(i+1);
      chunk=new Chunk(t.xcoor,0,tnext.xcoor-1,plainheight);
      stripecoverage.addAll(t.stripeeastedge);
      chunk.coveringstripes=new ArrayList<Stripe>(stripecoverage);
      stripecoverage.removeAll(t.stripewestedge);}
    return chunks;}
  
  /*
   * convert the list of vertical stripes to an ordered list of vertical chunk terminusses
   * for each stripe
   *   get a terminus
   *   first check the termsbyx map.
   *   if it isn't there then create one and stick it in the map
   * now er have our terminusesses, unordered
   * sort them by xcoors
   * that's it
   */
  List<VChunkTerminus> getOrderedTermunsesses(List<Stripe> vstripes){
    Map<Integer,VChunkTerminus> termsbyx=new HashMap<Integer,VChunkTerminus>();
    VChunkTerminus t;
    int xmin,xmax;
    for(Stripe s:vstripes){
      //do terminus for east edge of stripe
      xmin=s.getXMin();
      t=termsbyx.get(xmin);
      if(t==null){
        t=new VChunkTerminus(this,xmin);
        termsbyx.put(xmin,t);}
      t.stripeeastedge.add(s);
      //do terminus for west edge of stripe
      xmax=s.getXMax();
      t=termsbyx.get(xmax);
      if(t==null){
        t=new VChunkTerminus(this,xmax);
        termsbyx.put(xmax,t);}
      t.stripewestedge.add(s);}
    //sort the terminusses
    List<VChunkTerminus> ordered=new ArrayList<VChunkTerminus>(termsbyx.values());
    System.out.println("ordered="+ordered.size());
    Collections.sort(ordered);
    //
    return ordered;}
  
  
  class VChunkTerminus implements Comparable<VChunkTerminus>{
    
    CloudedPlain2 plain;
    int xcoor;
    
    List<Stripe>
      //all stripes in the list have their west edge here
      stripewestedge=new ArrayList<Stripe>(),
      //all stripes in the list have their east edge here
      stripeeastedge=new ArrayList<Stripe>();
    
    VChunkTerminus(CloudedPlain2 plain,int xcoor){
      this.plain=plain;
      this.xcoor=xcoor;}
    
    boolean isPlainWestEdge(){
      return xcoor==0;}
    
    boolean isPlainEastEdge(){
      return xcoor==plain.getWidth()-1;}

    public int compareTo(VChunkTerminus a){
      if(xcoor==a.xcoor){
        throw new IllegalArgumentException("2 VChunkTerminus can't have the same xcoor : "+xcoor);
      }else if(xcoor>a.xcoor){
        return 1;
      }else{
        return 0;}}
    
    
    
    
  }


  
  /*
   * A little geometry
   */
  
  public int getXMin(){
    return 0;}

  public int getXMax(){
    return getWidth()-1;}

  public int getYMin(){
    return 0;}

  public int getYMax(){
    return getHeight()-1;}

}
