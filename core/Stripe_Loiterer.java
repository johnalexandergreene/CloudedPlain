package org.fleen.cloudedPlain.core;

import org.fleen.cloudedPlain.core.geom.Stripe;

/*
 * stays where its put till the timer runs out
 * TEST
 */
public class Stripe_Loiterer extends Stripe{
  
  public Stripe_Loiterer(CloudedPlain plain){
    super(plain);}

  public int getCoorX(){
    return 100;}

  public int getCoorY(){
    return 0;}

  public int getWidth(){
    return 150;}

  public int getHeight(){
    return plain.getHeight();}

  public int getOrientation(){
    return ORIENTATION_VERTICAL;}

  public boolean destroyMe(){
    return false;}

}
