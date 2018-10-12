package org.fleen.cloudedPlain.core.stripeSystem.chunks;

import java.util.ArrayList;
import java.util.List;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;

/*
 * for mapping a system of parallel but possibly overlapping stripe-rectangles
 */
class StripeEdge implements Comparable<StripeEdge>{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  StripeEdge(int coor){
    this.coor=coor;}
  
  /*
   * ################################
   * ETC
   * ################################
   */
  
  /*
   * the location of this edge
   */
  int coor;
  
  /*
   * lists of stripes that have their front or back edge here.
   * front is direction coor+, back is direction coor-
   * so for vertical stripes front is east and back is west
   * and for horizontal stripes front is north and back is south 
   */
  List<Stripe> 
    frontedges=new ArrayList<Stripe>(),
    backedges=new ArrayList<Stripe>();
  
  /*
   * for sorting
   */
  public int compareTo(StripeEdge a){
    if(coor==a.coor){
      throw new IllegalArgumentException("2 StripeEdge objects can't have the same coor : "+coor);
    }else if(coor>a.coor){
      return 1;
    }else{
      return 0;}}}
