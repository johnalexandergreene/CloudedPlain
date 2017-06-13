package org.fleen.cloudedPlain.core;

public class PlainShapeCell{
  
  /*
   * ################################
   * CONSTRUCTORS
   * ################################
   */
      
  PlainShapeCell(){}
  
  PlainShapeCell(int x,int y,int color){
    this.x=x;
    this.y=y;
    this.color=color;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  public PlainShape shape=null; 
  public int x,y,color;
  
  public int getDistanceToPlainCenter(){
    return (int)Math.sqrt(getSqDistanceToPlainCenter());}
  
  public int getSqDistanceToPlainCenter(){
    int 
      dx=x-shape.plain.centerx,
      dy=y-shape.plain.centery;
    return dx*dx+dy*dy;}
  
  /*
   * returns the 4 cells in the plain adjacent to this cell
   * N E S W
   * if the cell is off-plain then null
   */
  public PlainShapeCell[] getAdjacents(){
    PlainShapeCell[] c=new PlainShapeCell[4];
    c[0]=shape.plain.getPlainShapeCell(x,y+1);
    c[1]=shape.plain.getPlainShapeCell(x+1,y);
    c[2]=shape.plain.getPlainShapeCell(x,y-1);
    c[3]=shape.plain.getPlainShapeCell(x-1,y);
    return c;}
  
  /*
   * is this cell on the edge of the shape?
   * TODO should be set once
   */
  public boolean isShapeEdge(){
    PlainShapeCell[] a=getAdjacents();
    for(PlainShapeCell c:a)
      if(c.color!=color)
        return true;
    return false;}
  
  public int hashCode(){
    return x+673*y;}//673 is prime
  
  public boolean equals(Object a){
    PlainShapeCell b=(PlainShapeCell)a;
    return b.x==x&&b.y==y;}
  
  public String toString(){
    return "("+x+","+y+","+color+")";}

}
