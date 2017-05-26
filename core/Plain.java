package org.fleen.cloudedPlain.core;

/*
 * A rectangular array of cells
 * stored as integer values
 */
public class Plain{
  
  public int[][] cells=null;
  
  /*
   * set value of all cells to zero in preparation for receiving cloud manifestations 
   */
  public void zeroCells(){
    for(int x=0;x<cells.length;x++){
      for(int y=0;y<cells[0].length;y++){
        cells[x][y]=0;}}}

}
