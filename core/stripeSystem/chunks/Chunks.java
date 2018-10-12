package org.fleen.cloudedPlain.core.stripeSystem.chunks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fleen.cloudedPlain.core.stripeSystem.Stripe;
import org.fleen.cloudedPlain.core.stripeSystem.StripeSystem;

/*
 * Consider the stripe system
 * it's stripes, running vertically and/or horizontally
 * when we intersect them (via perpendiculars or via intersecting adjacentish overlapping parallels) we get a bunch of rectangles
 * for each rectangle, the VALUE at that rectangle is the sum of all stripes that intersect to define that rectangle
 * this is those rectangles
 * 
 * ALG
 * first intersect all of the vertical stripes (with each other and the stage)
 * then intersect all the horizontal (with each other and a plain-shaped chunk) 
 * this gives us 2 skeins of nonoverlapping chunks
 * now intersect the two in some kind of orderly way
 * that should do it.
 * 
 */

@SuppressWarnings("serial")
public class Chunks extends ArrayList<Chunk>{
  
  Chunks(StripeSystem ss){
    //sort stripes
    List<Stripe> 
      vstripes=new ArrayList<Stripe>(),
      hstripes=new ArrayList<Stripe>();
    getSortedStripes(ss,vstripes,hstripes);
    //derive stripe edges
    //that is, where each stripe begins and ends. 
    //sometimes there's no stripe. sometimes they overlap. sometimes a whole bunch overlap.
    //so we map that
    List<StripeEdge> 
      vse=getStripeEdges(vstripes),
      hse=getStripeEdges(hstripes);
    //derive vertical and horizontal chunks
    List<Chunk> 
      vchunks=getVChunks(vse),
      hchunks=getHChunks(hse);
    //intersect our 2 stripe-chunk sets to get our final chunks
    doIntersectionChunks(vchunks,hchunks);}
  
  private void getSortedStripes(StripeSystem ss,List<Stripe> vstripes,List<Stripe> hstripes){
    for(Stripe stripe:ss.stripes){
      if(stripe.isHorizontal())
        hstripes.add(stripe);
      else
        vstripes.add(stripe);}}
  
  /*
   * ################################
   * STRIPE EDGE MAPPING
   * ################################
   */
  
  private List<StripeEdge> getStripeEdges(List<Stripe> stripes){
    Map<Integer,StripeEdge> edgesbycoor=new HashMap<Integer,StripeEdge>();
    int back,front;
    StripeEdge backedge,frontedge;
    for(Stripe stripe:stripes){
      //do the back edge
      back=stripe.getXMin();
      backedge=edgesbycoor.get(back);
      if(backedge==null){
        backedge=new StripeEdge(back);
        edgesbycoor.put(back,backedge);}
      backedge.backedges.add(stripe);
      //do the front edge
      front=stripe.getXMax();
      frontedge=edgesbycoor.get(front);
      if(frontedge==null){
        frontedge=new StripeEdge(front);
        edgesbycoor.put(front,frontedge);}
      frontedge.frontedges.add(stripe);}
    //get it into a list and sort
    List<StripeEdge> edges=new ArrayList<StripeEdge>(edgesbycoor.values());
    Collections.sort(edges);
    //
    return edges;}
  
  /*
   * ################################
   * GET VERTICAL CHUNKS
   * ################################
   */
  
  private List<Chunk> getVChunks(List<StripeEdge> vse){
    List<Chunk> vchunks=new ArrayList<Chunk>();
    StripeEdge e0,e1;
    for(int i=0;i<vse.size()-1;i++){
      e0=vse.get(i);
      e1=vse.get(i+1);
      
    }
  }
  
  /*
   * ################################
   * GET HORIZONTAL CHUNKS
   * ################################
   */
  
  private List<Chunk> getHChunks(List<StripeEdge> hse){
    
  }
    
}

