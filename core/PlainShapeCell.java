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
  
  public boolean isEdge(){
    PlainShapeCell t=new PlainShapeCell();
    t.x=x;
    t.y=y;
    t.x--;
    boolean w=shape.contains(t);
    t.x+=2;
    boolean e=shape.contains(t);
    t.y++;
    boolean ne=shape.contains(t);
    t.x--;
    boolean n=shape.contains(t);
    t.x--;
    boolean nw=shape.contains(t);
    t.y-=2;
    boolean sw=shape.contains(t);
    t.x++;
    boolean s=shape.contains(t);
    t.x++;
    boolean se=shape.contains(t);
    return !(nw&&n&&ne&&e&&se&&s&&sw&&w);}
  
  public int hashCode(){
    return x+673*y;//673 is prime
    }
  
  public boolean equals(Object a){
    PlainShapeCell b=(PlainShapeCell)a;
    return b.x==x&&b.y==y;}

}
