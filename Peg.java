
public class Peg{
  Peg W, NW, NE, E, SE, SW;
  int ID;
  boolean active;
  
  Peg(int ID){
   this.active = true;
   this.ID = ID;
  }
  
  public boolean isActive(){
   return this.active;
  }
  
  public void setActive(boolean b){
   this.active = b;
  }
  
  public void makeActive(){
   this.active = true;
  }
  
  public void makeInactive(){
   this.active = false;
  }
  
  public int getID(){
   return this.ID;
  }
  
  public boolean canMoveW(){
   if(this.W == null){return false;}
   if(this.W.W == null){return false;}
   if (this.isActive() && this.W.isActive() && !this.W.W.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public boolean canMoveNW(){ 
   if(this.NW == null){return false;}
   if(this.NW.NW == null){return false;}
   if (this.isActive() && this.NW.isActive() && !this.NW.NW.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public boolean canMoveNE(){
   if(this.NE == null){return false;}
   if(this.NE.NE == null){return false;}
   if (this.isActive() && this.NE.isActive() && !this.NE.NE.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public boolean canMoveE(){
   if(this.E == null){return false;}
   if(this.E.E == null){return false;}
   if (this.isActive() && this.E.isActive() && !this.E.E.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public boolean canMoveSE(){
   if(this.SE == null){return false;}
   if(this.SE.SE == null){return false;}
   if (this.isActive() && this.SE.isActive() && !this.SE.SE.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public boolean canMoveSW(){
   if(this.SW == null){return false;}
   if(this.SW.SW == null){return false;}
   if (this.isActive() && this.SW.isActive() && !this.SW.SW.isActive()){
    return true;
   }
   else {return false;}
  }
  
  public void moveW(){
    this.W.W.makeActive();
    this.W.makeInactive();
    this.makeInactive();
  }
  
  public void moveNW(){ 
    this.NW.NW.makeActive();
    this.NW.makeInactive();
    this.makeInactive();
  }
  
  public void moveNE(){
    this.NE.NE.makeActive();
    this.NE.makeInactive();
    this.makeInactive();
  }
  
  public void moveE(){
    this.E.E.makeActive();
    this.E.makeInactive();
    this.makeInactive();
  }
  
  public void moveSE(){
    this.SE.SE.makeActive();
    this.SE.makeInactive();
    this.makeInactive();
  }
  
  public void moveSW(){
    this.SW.SW.makeActive();
    this.SW.makeInactive();
    this.makeInactive();
  }
  
  public String toString(){
      return this.getID() + "";
  }
}
 