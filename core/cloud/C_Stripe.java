package org.fleen.cloudedPlain.core.cloud;

import org.fleen.cloudedPlain.core.Plain;

/*
 * a stripe shaped cloud
 * a rectangle that spans edge to edge. 
 * A stripe that sweeps the surface of the plain once, from an edge to the opposite edge
 * it can have a pattern
 * it can strobe 
 * it generally gets created, sweeps the plain, then gets discarded
 * 
 * color is an index in the renderer's colorarray. generally 1. could be other values too of course
 */
public class C_Stripe implements Cloud{
  
  public C_Stripe(
    Plain plain,int heading,int thickness,int speed,int fillpattern,
    int strobepattern,boolean invertfillpattern,int color){
    this.plain=plain;
    this.heading=heading;
    this.thickness=thickness;
    this.speed=speed;
    this.fillpattern=fillpattern;
    this.strobepattern=strobepattern;
    this.invertfillpattern=invertfillpattern;
    this.color=color;
    //
    birthday=plain.sliceindex;
    
    
  }

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
    return plain.sliceindex-birthday;}
  
  /*
   * ################################
   * PLAIN
   * ################################
   */
  
  public Plain plain;
  
  public void setPlain(Plain plain){
    this.plain=plain;}
  
  /*
   * ################################
   * COLOR
   * an index to a color in the renderer's color array
   * ################################
   */

  public static final int COLOR_DEFAULT=0;
  
  public int color=COLOR_DEFAULT;
  
  /*
   * return the color of the cell at the specified coors, in terms of plain coors
   * TODO
   * for test we just return 1
   * 
   */
  public int getColor(int x,int y){
    return 1;}
  
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
   * FILL PATTERN
   * 
   * TODO
   * 
   * for test we do solid fill
   * 
   * we will be using those 1-bit fill textures.
   * we will need a scale param
   * ################################
   */
  
  public int fillpattern;
  public boolean invertfillpattern;
  
  /*
   * ################################
   * STROBE PATTERN
   * 
   * TODO
   * 
   * for test we just do no strobe
   * ################################
   */
  
  public int strobepattern;
  
  /*
   * ################################
   * FINISHED
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
    int color;
    for(int x=getXMin();x<=getXMax();x++){
      for(int y=getYMin();y<=getYMax();y++){
        color=getColor(x,y);
        if(x>-1&&x<plain.width&&y>-1&&y<plain.height)
          plain.slice[x][y]+=color;}}}

}
