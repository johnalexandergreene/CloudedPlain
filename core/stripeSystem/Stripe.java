package org.fleen.cloudedPlain.core.stripeSystem;

public abstract class Stripe implements SSRectangle{
  
  /*
   * ################################
   * CONSTRUCTORS
   * ################################
   */
  
  public Stripe(StripeSystem ss,int orientation,int thickness){
    stripesystem=ss;
    this.orientation=orientation;
    this.thickness=thickness;
    initBirthday();}
  
  public Stripe(StripeSystem ss,int orientation,int thickness,int[] valuestrobe){
    this(ss,orientation,thickness);
    setValueStrobe(valuestrobe);}
  
  /*
   * ################################
   * STRIPE SYSTEM
   * The system of which this stripe is a part
   * ################################
   */
  
  public StripeSystem stripesystem;
  
  public void setPlain(StripeSystem s){
    stripesystem=s;}
  
  /*
   * ################################
   * BIRTHDAY
   * the frame index when this object was created
   * from this we derive age
   * with speed we derive location
   * ################################
   */
  
  public int birthday;
  
  void initBirthday(){
    birthday=stripesystem.time;}
  
  public int getAge(){
    return stripesystem.time-birthday;}
  
  /*
   * ################################
   * GEOMETRY
   * ################################
   */
  
  public static final int 
    ORIENTATION_HORIZONTAL=0,
    ORIENTATION_VERTICAL=1;
  
  int orientation,thickness;
  
  /*
   * if it's horizontal then it's the ymin
   * vertical, xmin
   */
  public abstract int getLocation();
  
  public int getOrientation(){
    return orientation;}
  
  public boolean isHorizontal(){
    return getOrientation()==ORIENTATION_HORIZONTAL;}
  
  public boolean isVertical(){
    return getOrientation()==ORIENTATION_VERTICAL;}
  
  public int getXMin(){
    return getCoorX();}
  
  public int getXMax(){
    return getCoorX()+getWidth()-1;}
  
  public int getYMin(){
    return getCoorY();}
  
  public int getYMax(){
    return getCoorY()+getHeight()-1;}
  
  public int getCoorX(){
    if(isHorizontal())
      return 0;
    else
      return getLocation();}

  public int getCoorY(){
    if(isHorizontal())
      return getLocation();
    else
      return 0;}

  public int getWidth(){
    if(isHorizontal())
      return stripesystem.stage.getWidth();
    else
      return thickness;}

  public int getHeight(){
    if(isHorizontal())
      return thickness;
    else
      return stripesystem.stage.getHeight();}
  
  /*
   * ################################
   * VALUE STROBE PATTERN
   * A stripe has an array of 1..n integer values
   * Mod the time (frame index) value against the length of that array and you get a repeating pattern through time. A strobe.
   * this pattern can refer to a color or a sound or both
   * we sum overlapping rectangle values at a pixel to get the color of that pixel (the index of a color in a palette)
   * we glean a system of uniformly-coloed rectangles, and examine their location and proportions, to calculate sound 
   * ################################
   */
  
  public int[] valuestrobe=new int[]{1};//note that default. a constant value of +1
  
  public void setValueStrobe(int[] s){
    valuestrobe=s;}
  
  public int getValue(){
    return valuestrobe[stripesystem.time%valuestrobe.length];}
  
  /*
   * ################################
   * when this stripe is done doing its dance 
   * it lets us know that it is time to destroy it
   * ################################
   */
  
  public abstract boolean destroyMe();

}
