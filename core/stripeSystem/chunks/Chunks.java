package org.fleen.cloudedPlain.core.stripeSystem.chunks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  
  public Chunks(StripeSystem ss){
    //sort stripes
    List<Stripe> 
      vstripes=new ArrayList<Stripe>(),
      hstripes=new ArrayList<Stripe>();
    getSortedStripes(ss,vstripes,hstripes);
    //derive stripe edges
    //that is, where each stripe begins and ends. 
    //sometimes there's no stripe. sometimes they overlap. sometimes a whole bunch overlap.
    //so we map that
    List<Edge> 
      ve=getVEdges(ss,vstripes),
      he=getHEdges(ss,hstripes);
    //derive vertical and horizontal chunks
    List<Chunk> 
      vchunks=getVChunks(ss,ve),
      hchunks=getHChunks(ss,he);
    
    //TEST
//    addAll(vchunks);
    addAll(hchunks);
    
//    //intersect our 2 stripe-chunk sets to get our final chunks
//    doIntersectionChunks(vchunks,hchunks);
    
  }
  
  private void getSortedStripes(StripeSystem ss,List<Stripe> vstripes,List<Stripe> hstripes){
    for(Stripe stripe:ss.stripes){
      if(stripe.isHorizontal())
        hstripes.add(stripe);
      else
        vstripes.add(stripe);}}
  
  /*
   * ################################
   * EDGE MAPPING
   * ################################
   */
  
  private List<Edge> getVEdges(StripeSystem ss,List<Stripe> stripes){
    Map<Integer,Edge> edgesbycoor=new HashMap<Integer,Edge>();
    int back,front;
    Edge backedge,frontedge;
    //do the edges for the edges of the stripesystem.stage
    edgesbycoor.put(0,new Edge(0));
    int stagexmax=ss.stage.getXMax()+1;
    edgesbycoor.put(stagexmax,new Edge(stagexmax));
    //do the edges for the stripes
    for(Stripe stripe:stripes){
      //do the back edge
      back=stripe.getXMin();
      backedge=edgesbycoor.get(back);
      if(backedge==null){
        backedge=new Edge(back);
        edgesbycoor.put(back,backedge);}
      backedge.backedges.add(stripe);
      //do the front edge
      front=stripe.getXMax()+1;
      frontedge=edgesbycoor.get(front);
      if(frontedge==null){
        frontedge=new Edge(front);
        edgesbycoor.put(front,frontedge);}
      frontedge.frontedges.add(stripe);}
    //get it into a list and sort
    List<Edge> edges=new ArrayList<Edge>(edgesbycoor.values());
    Collections.sort(edges);
    //
    return edges;}
  
  private List<Edge> getHEdges(StripeSystem ss,List<Stripe> stripes){
    Map<Integer,Edge> edgesbycoor=new HashMap<Integer,Edge>();
    int back,front;
    Edge backedge,frontedge;
    //do the edges for the edges of the stripesystem.stage
    edgesbycoor.put(0,new Edge(0));
    int stagexmax=ss.stage.getYMax()+1;
    edgesbycoor.put(stagexmax,new Edge(stagexmax));
    //do the edges for the stripes
    for(Stripe stripe:stripes){
      //do the back edge
      back=stripe.getYMin();
      backedge=edgesbycoor.get(back);
      if(backedge==null){
        backedge=new Edge(back);
        edgesbycoor.put(back,backedge);}
      backedge.backedges.add(stripe);
      //do the front edge
      front=stripe.getYMax()+1;
      frontedge=edgesbycoor.get(front);
      if(frontedge==null){
        frontedge=new Edge(front);
        edgesbycoor.put(front,frontedge);}
      frontedge.frontedges.add(stripe);}
    //get it into a list and sort
    List<Edge> edges=new ArrayList<Edge>(edgesbycoor.values());
    Collections.sort(edges);
    //
    return edges;}
  
  /*
   * ################################
   * GET VERTICAL AND HORIZONTAL CHUNKS
   * ################################
   */
  
  /*
   * create a new chunk for each adjacent pair of edges
   * keep a list of which stripes we are presently covered by
   *   at each edge
   *     add all the backedge stripes
   *     remove all the front edge stripes
   *   the stripes in the list are the chunk's stripes
   */
  private List<Chunk> getVChunks(StripeSystem ss,List<Edge> edges){
    int stageheight=ss.stage.getHeight();
    int stagexmax=ss.stage.getXMax()+1;
    List<Chunk> vchunks=new ArrayList<Chunk>();
    Set<Stripe> covered=new HashSet<Stripe>();
    Edge e0,e1;
    Chunk chunk;
    //create chunks
    for(int i=0;i<edges.size()-1;i++){
      e0=edges.get(i);
      e1=edges.get(i+1);
      covered.addAll(e0.backedges);
      covered.removeAll(e0.frontedges);
      if(e0.coor>=0&&e1.coor<=stagexmax){//skip offstage chunks
        chunk=new Chunk(e0.coor,0,e1.coor-e0.coor,stageheight);
        chunk.setStripes(covered);
        vchunks.add(chunk);}}
    //
    return vchunks;}
  
  private List<Chunk> getHChunks(StripeSystem ss,List<Edge> edges){
    int stagewidth=ss.stage.getWidth();
    int stageymax=ss.stage.getYMax()+1;
    List<Chunk> hchunks=new ArrayList<Chunk>();
    Set<Stripe> covered=new HashSet<Stripe>();
    Edge e0,e1;
    Chunk chunk;
    //create chunks
    for(int i=0;i<edges.size()-1;i++){
      e0=edges.get(i);
      e1=edges.get(i+1);
      covered.addAll(e0.backedges);
      covered.removeAll(e0.frontedges);
      if(e0.coor>=0&&e1.coor<=stageymax){//skip offstage chunks
        chunk=new Chunk(0,e0.coor,stagewidth,e1.coor-e0.coor);
        chunk.setStripes(covered);
        hchunks.add(chunk);}}
    //
    return hchunks;}
    
}

