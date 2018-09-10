package org.fleen.cloudedPlain.core.rectangles;

import org.fleen.cloudedPlain.core.Plain;

/*
 * a rectangle that spans edge to edge. 
 * A stripe that sweeps the surface of the plain once, from an edge to the opposite edge
 * it generally gets created, sweeps the plain, then gets discarded
 * 
 * color is an index in the renderer's colorarray. generally 1. could be other values too of course
 * 
 * STRIPE VALUE
 * 
 * the plain has a palette. An array. color=index
 * 
 * a cell is covered by 1..n stripes
 * 
 * each stripe has a value pattern. A strobe of values. We can translate these into color and sound
 * specified by a list of integers. ex : 1,2,2,1, or 1,2 or 4,1,2,2,1,4
 * or even just 1 value, if the value is constant
 * 
 * at each tick of the plain's clock we specify the value for a stripe
 * that value is gotten from the stripe's valuepattern array
 * the value = valuepattern[tick%valuepatternlength] 
 * 
 * when we render a cell we get all of the stripes that cover the cell and sum their values at that tick
 * thus we get the summed value at that cell
 * then we can use that value to get a color (from the plain palette) 
 */
public class Stripe{
  
  public Stripe(
    Plain plain,int heading,int thickness,int speed,int[] valuepattern){
    this.plain=plain;
    this.heading=heading;
    this.thickness=thickness;
    this.speed=speed;
    this.valuepattern=valuepattern;
    //
    birthday=plain.frameindex;}

  /*
   * ################################
   * BIRTHDAY
   * the slice index when this object was created
   * from this we derive age
   * with speed we derive location
   * ################################
   */
  
  public int birthday;
  
  public int getAge(){
    return plain.frameindex-birthday;}
  
  /*
   * ################################
   * PLAIN
   * The Plain that this stripe expresses itself upon
   * ################################
   */
  
  public Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}
  
  /*
   * ################################
   * VALUE PATTERN
   * ################################
   */

  public int[] valuepattern;
  
  /*
   * This gets translated into color, strobe, sound...
   */
  public int getValue(){
    return valuepattern[getAge()%valuepattern.length];}
  
  /*
   * ################################
   * GEOMETRY, HEADING, THICKNESS, LOCATION, MOVEMENT, SPEED
   * ################################
   */
  public static final int
    HEADING_NORTH=0,//starts just off south edge, moves north till exiting plain via north edge
    HEADING_EAST=1,//starts at west edge, goes east
    HEADING_SOUTH=2,
    HEADING_WEST=3;
  
  /*
   * TODO
   * it's gonna be either 1 pixel movement = 1 slice (aka 1 frame. should we just change the name?)
   *   ie : speed=1
   * or slower, a fraction of that
   *   1/2, 1/3... 1/6
   * or faster, a multiple of that
   *   2,3,4,5...
   *   
   * we will do it with a collection of proper speed settings. each associated with a speed function
   * or something like that
   */
  public int speed;
  
  public int 
    thickness,
    heading=-1;
  
  //x and y of the southwest corner point of the stripe rectangle
  public int[] getSWCorner(){
    int[] xy=null;
    if(heading==HEADING_NORTH){
      xy=new int[]{
        0,
        speed*getAge()-thickness};
    }else if(heading==HEADING_EAST){
      xy=new int[]{
        speed*getAge()-thickness,
        0};
    }else if(heading==HEADING_SOUTH){
      xy=new int[]{
        0,
        plain.height-(speed*getAge())};
    }else if(heading==HEADING_WEST){
      xy=new int[]{
          plain.width-(speed*getAge()),
          0};}
    return xy;}
  
  public int getXMin(){
    return getSWCorner()[0];}
  
  public int getXMax(){
    int xmax=-1;
    if(heading==HEADING_NORTH||heading==HEADING_SOUTH){
      xmax=plain.width;
    }else if(heading==HEADING_EAST||heading==HEADING_WEST){
      xmax=getSWCorner()[0]+thickness;}
    return xmax;}
  
  public int getYMin(){
    return getSWCorner()[1];}
  
  public int getYMax(){
    int ymax=-1;
    if(heading==HEADING_NORTH||heading==HEADING_SOUTH){
      ymax=getSWCorner()[1]+thickness;
    }else if(heading==HEADING_EAST||heading==HEADING_WEST){
      ymax=plain.height;}
    return ymax;}
  
  /*
   * ################################
   * FINISHED
   * When this Cloud is finished drifting over the plain, signal to the system that it should be removed.
   * ################################
   */
  
  /*
   * The method here flags true when the moving stripe passes off the edge of the plain
   * If the stripe is unmoving then it is never discarded. 
   * Maybe this method could be subbed to have finished happen on some other condition, like maybe a timer 
   * 
   * TODO
   *  
   */
  public boolean finished(){
    boolean finished=false;
    if(heading==HEADING_NORTH){
      finished=getSWCorner()[1]>plain.height;
    }else if(heading==HEADING_EAST){
      finished=getSWCorner()[0]>plain.width;
    }else if(heading==HEADING_SOUTH){
      finished=getSWCorner()[1]<(-thickness);
    }else if(heading==HEADING_WEST){
      finished=getSWCorner()[0]<(-thickness);}
    return finished;}

  /*
   * ################################
   * MANIFEST
   * manifest this stripe upon the plain
   * refer to the plain for params like slice and sliceindex
   * 
   * the thing this method does is this :
   *   add integer value to 0..n cells in the plain
   *     value is usually 1 (a monochrome effect) but that added value could be any integer. 
   *     thus generating 1 frame in our (60 fps) video 
   *   incrementally generate sound array
   *     set values over a 1/60 second long increment of the sound data array
   *   we could change this value over time. Do a strobe or flicker or something 
   * ################################
   */
  
  /*
   * a filled rectangle spanning the plain either vertically or horizontally 
   * 
   * scan rectangle area with a nested loop
   * for each cell
   *   get the value for that cell
   *   what about fill, strobe, invertfillpattern TODO
   *   
   *   TEST
   *   just a fill
   * 
   */
  public void manifest(){
    int v;
    for(int x=getXMin();x<=getXMax();x++){
      for(int y=getYMin();y<=getYMax();y++){
        v=getValue();
        if(x>-1&&x<plain.width&&y>-1&&y<plain.height)
          plain.frame[x][y]+=v;}}}

}
