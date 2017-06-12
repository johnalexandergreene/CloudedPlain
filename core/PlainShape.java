package org.fleen.cloudedPlain.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")
public class PlainShape extends HashSet<PlainShapeCell>{
  
  public PlainShape(Plain plain,PlainShapeCell first){
    super();
    this.plain=plain;
    addCell(first);}
  
  public Plain plain;
  
  public void addCell(PlainShapeCell cell){
    cell.shape=this;
    add(cell);}
  
  /*
   * given the first cell
   * get neighboring cells with the same color
   * remove them from the pool, add them to this shape
   * note those ne wcells as "fresh".
   * Do it again, using the fresh cells
   * keep doing that until we can't
   */
  public void aggolomerate(Set<PlainShapeCell> pool){
    Set<PlainShapeCell> 
      fresh=new HashSet<PlainShapeCell>(),
      newfresh=new HashSet<PlainShapeCell>();
    fresh.add(iterator().next());
    PlainShapeCell cell;
    while(!fresh.isEmpty()){
      cell=removeArbitraryCell(fresh);
      newfresh=getSameColorAdjacents(pool,cell);
      pool.removeAll(newfresh);
      fresh.addAll(newfresh);
      addAll(fresh);}}
  
  Set<PlainShapeCell> getSameColorAdjacents(Set<PlainShapeCell> pool,PlainShapeCell cell){
    int 
      nwx=cell.x-1,
      nwy=cell.y+1,
      nx=cell.x,
      ny=cell.y+1,
      nex=cell.x+1,
      ney=cell.y+1,
      ex=cell.x+1,
      ey=cell.y,
      sex=cell.x+1,
      sey=cell.y-1,
      sx=cell.x,
      sy=cell.y-1,
      swx=cell.x-1,
      swy=cell.y-1,
      wx=cell.x-1,
      wy=cell.y;
    Set<PlainShapeCell> a=new HashSet<PlainShapeCell>();
    PlainShapeCell k=new PlainShapeCell(),c;
    //nw
    k.x=cell.x-1;
    k.y=cell.y+1;
    c=pool.
    
    
      
  }
  
  PlainShapeCell removeArbitraryCell(Set<PlainShapeCell> cells){
    PlainShapeCell c=cells.iterator().next();
    cells.remove(c);
    return c;}
  
  


}
