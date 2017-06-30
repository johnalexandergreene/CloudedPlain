package org.fleen.cloudedPlain.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * TODO
 * do this vectorwise
 * start at 0,0
 * grab the edge of the shape there
 * check the edge for another nonmapped pixel
 * do the shape that starts there
 * etc
 * a bunch of vector paths
 * each path is orthogonal (nesw) paths connected by pixels
 * 
 * it'll be faster to define and analyze
 * 
 */

@SuppressWarnings("serial")
public class PlainShape extends HashSet<PlainShapeCell>{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public PlainShape(Plain plain,PlainShapeCell first,Set<PlainShapeCell> unmapped){
    super();
    color=first.color;
    this.plain=plain;
    map(unmapped,first);}
  
  /*
   * ################################
   * COLOR
   * ################################
   */
  
  public int color;
  
  /*
   * ################################
   * PLAIN
   * ################################
   */
  
  public Plain plain;
  
  /*
   * ################################
   * MAP SHAPE TO CELLS
   * ################################
   */
  
  /*
   * given the first cell for this contiguous mass, get the rest
   * 
   * given a first cell
   * get the adjacents with the same color
   * remove them from pool, mark them as mapped
   * 
   * get neighboring cells with the same color
   * remove them from the pool, add them to this shape
   * note those ne wcells as "fresh".
   * Do it again, using the fresh cells
   * keep doing that until we can't
   */
  private void map(Set<PlainShapeCell> unmapped,PlainShapeCell first){
    Set<PlainShapeCell> 
      fresh=new HashSet<PlainShapeCell>(),
      newfresh=new HashSet<PlainShapeCell>();
    addCell(first);
    fresh.add(first);
    unmapped.removeAll(fresh);
    PlainShapeCell cell;
    while(!fresh.isEmpty()){
      cell=removeArbitraryCell(fresh);
      newfresh=getValidAdjacents(unmapped,cell);
      unmapped.removeAll(newfresh);
      fresh.addAll(newfresh);
      addCells(fresh);}}
  
  private void addCell(PlainShapeCell cell){
    add(cell);
    cell.shape=this;}
  
  private void addCells(Set<PlainShapeCell> cells){
    for(PlainShapeCell c:cells)
      addCell(c);}
  
  /*
   * get the cells that are adjacent to cell
   * they must be unmapped (contained in unmapped) and of the same color
   */
  Set<PlainShapeCell> getValidAdjacents(Set<PlainShapeCell> pool,PlainShapeCell cell){
    Set<PlainShapeCell> va=new HashSet<PlainShapeCell>();
    PlainShapeCell[] a=cell.getAdjacents();
    for(PlainShapeCell c:a)
      if(c!=null&&pool.contains(c)&&c.color==color)
        va.add(c);
    return va;}
  
  PlainShapeCell removeArbitraryCell(Set<PlainShapeCell> cells){
    PlainShapeCell c=cells.iterator().next();
    cells.remove(c);
    return c;}
  
  


}
