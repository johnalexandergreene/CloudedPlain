package org.fleen.cloudedPlain.core;

import java.util.ArrayList;
import java.util.List;

/*
 * An abstract plain
 * everything but cloud creator
 * 
 * A rectangular array of cells
 * stored as integer values
 * 
 * A bunch of clouds
 */
public abstract class Plain{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public Plain(int w,int h){
    initCells(w,h);}
  
  /*
   * ################################
   * CELLS
   * ################################
   */
  
  public int[][] cells=null;
  
  private void initCells(int w,int h){
    cells=new int[w][h];
    zeroCells();}
  
  public int getWidth(){
    return cells.length;}
  
  public int getHeight(){
    return cells[0].length;}
  
  /*
   * set value of all cells to zero in preparation for receiving cloud manifestations 
   */
  public void zeroCells(){
    for(int x=0;x<cells.length;x++){
      for(int y=0;y<cells[0].length;y++){
        cells[x][y]=0;}}}
  
  /*
   * ################################
   * CLOUDS
   * ################################
   */
  
  List<Cloud> clouds=new ArrayList<Cloud>();
  
  void addCloud(Cloud cloud){
    cloud.setPlain(this);
    clouds.add(cloud);}
  
  /*
   * ################################
   * SLICE CONTROL
   * ################################
   */
  
  //better than 0, used for test
  private static final int TSPANDEFAULT=60;
  
  public int
    //the span of this plain in the dimension t
    tspan=TSPANDEFAULT,
    //the t index of the next slice. incremented after the slice is manifested
    tslice=0;
  
  /*
   * manifest all of the clouds, using tslice as param
   * returns true if this is the last slice
   */
  boolean manifestSlice(){
    if(tspan==tslice)throw new IllegalArgumentException("post last slice");
    for(Cloud cloud:clouds)
      cloud.manifest();
    tslice++;
    return tspan==tslice;}
  
  /*
   * ################################
   * CLOUD CREATOR
   * create 0..n clouds
   * refer to tslice and tspan perhaps
   * ################################
   */
  
  protected abstract void createClouds();

}
