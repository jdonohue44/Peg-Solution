
public class PegMove {

 Peg p;
 Direction d;
 Game g;
 
 public PegMove(Peg p, Direction d){
  this.p = p;
  this.d = d;
 }
 
 public PegMove(){
 }
 
 public PegMove(Game g, Peg p, Direction d){
  this.g = g;
  this.p = p;
  this.d = d;
 }
 
 public String toString(){
  String s = this.p + " " + this.d;
  return s;
 }
}
