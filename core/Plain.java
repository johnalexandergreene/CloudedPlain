package org.fleen.cloudedPlain.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * A 3D form to be interpreted as a sequence of 2D tile-patterns 
 * to be rendered as video. 
 * 
 * An abstract plain
 * everything but cloud creator
 * 
 * A rectangular array of cells
 * stored as integer values
 * 
 * A bunch of clouds
 */
public class Plain{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Plain(int w,int h,int d,CloudCreator cc,RendererGraphics rg,RendererSound rs){
    width=w;
    height=h;
    duration=d;
    setCloudCreator(cc);
    setRendererGraphics(rg);
    setRendererSound(rs);}
  
  /*
   * ################################
   * GEOMETRY
   * This plain is a 3d brick
   * the slices are rectangles
   * ################################
   */
  
  public int width,height,duration;
  
  /*
   * ################################
   * CLOUD
   * ################################
   */
  
  public CloudCreator cloudcreator=null;
  public List<Cloud> clouds=new ArrayList<Cloud>();
  
  public void setCloudCreator(CloudCreator cloudcreator){
    this.cloudcreator=cloudcreator;
    cloudcreator.setPlain(this);}
  
  /*
   * query cloudcreator logic to create 0..n clouds
   * return all created clouds in a list
   */
  protected List<Cloud> createClouds(){
    List<Cloud> newclouds=cloudcreator.create();
    clouds.addAll(newclouds);
    return newclouds;}
  
  /*
   * discard all clouds that are finished
   * return all discarded clouds in a list
   */
  protected List<Cloud> discardClouds(){
    List<Cloud> d=new ArrayList<Cloud>();
    Iterator<Cloud> i=clouds.iterator();
    Cloud c;
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
   * Upon this array the clouds manifest themselves
   * then we render the array as graphics and sound
   * ################################
   */
  
  int sliceindex;
  public int[][] slice;
  
  public int[][] getNextSlice(){
    System.out.println("getting slice : "+sliceindex);
    if(sliceindex==duration)return null;
    discardClouds();
    createClouds();
    zeroCells();
    manifestCloudsUponSlice();
    sliceindex++;
    return slice;}
  
  private void initSliceIterator(){
    sliceindex=0;
    slice=new int[width][height];
    zeroCells();}
  
  /*
   * set value of all cells to zero in preparation for receiving cloud manifestations 
   */
  public void zeroCells(){
    for(int x=0;x<width;x++)
      for(int y=0;y<height;y++)
        slice[x][y]=0;}
  
  /*
   * manifest all of the clouds, using tslice as param
   * returns true if this is the last slice
   */
  private void manifestCloudsUponSlice(){
    for(Cloud cloud:clouds)
      cloud.manifest(slice);}
  
  
  /*
   * ################################
   * RENDER
   * ################################
   */
  
  /*
   * 43200=60*720. 720=1*2*3*4*5*6
   */
  public static final int 
    GRAPHICSFRAMERATE=60,
    SLICESOUNDSAMPLERATE=720,
    SOUNDSAMPLERATE=GRAPHICSFRAMERATE*SLICESOUNDSAMPLERATE;
  
  public RendererGraphics renderergraphics=null;
  public RendererSound renderersound=null;
  public BufferedImage uiimage=null;
  
  public void setRendererGraphics(RendererGraphics r){
    renderergraphics=r;
    renderergraphics.setPlain(this);}
  
  public void setRendererSound(RendererSound r){
    renderersound=r;
    renderersound.setPlain(this);}
  
  public void render(File exportdir,RendererListener listener){
    initSliceIterator();
    int[][] slice=getNextSlice();
    BufferedImage sliceimage;
    int[] 
      plainsound=new int[duration*SLICESOUNDSAMPLERATE],
      slicesound;
    while(slice!=null){
      sliceimage=renderergraphics.render(slice);
      exportSliceImage(sliceimage);
      slicesound=renderersound.render(slice);
      System.arraycopy(slicesound,0,plainsound,(sliceindex-1)*SLICESOUNDSAMPLERATE,slicesound.length);
      listener.notify(sliceimage,slicesound);
      slice=getNextSlice();}
    //create sound file from sound array and write it to a wav or whatever
    doSound();}
  
  private void exportSliceImage(BufferedImage sliceimage){
    
  }
  
  private void doSound(){
    
  }
  
  

}
