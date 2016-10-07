
public class Board{
  int numPegs;
  Peg[] pegs;
  
  public Board(){
   this.numPegs = 15;
   this.pegs = new Peg[15];
   
   //Create 15 new pegs for the board.
   for(int i = 0; i < 15; i++){
    this.pegs[i] = new Peg(i+1);;
   }
   
   /*
    *      X      pegs[0]
    *     X X     pegs[1,2]
    *    X X X    pegs[3,4,5]
    *   X X X X   pegs[6,7,8,9]
    *  X X X X X  pegs[10,11,12,13,14]
    */
   
   //first row pointers 
   pegs[0].SW = pegs[1];
   pegs[0].SE = pegs[2];
   
   //second row pointers
   pegs[1].NE = pegs[0];
   pegs[1].E  = pegs[2];
   pegs[1].SE = pegs[4];
   pegs[1].SW = pegs[3];

   
   pegs[2].NW = pegs[0];
   pegs[2].SE = pegs[5];
   pegs[2].SW = pegs[4];
   pegs[2].W  = pegs[1];
   
   //third row pointers
   pegs[3].NE = pegs[1];
   pegs[3].E  = pegs[4];
   pegs[3].SE = pegs[7];
   pegs[3].SW = pegs[6];
   
   pegs[4].NW = pegs[1];
   pegs[4].NE = pegs[2];
   pegs[4].E  = pegs[5];
   pegs[4].SE = pegs[5];
   pegs[4].SW = pegs[4];
   pegs[4].W  = pegs[3];
   
   pegs[5].NW = pegs[2];
   pegs[5].SE = pegs[9];
   pegs[5].SW = pegs[8];
   pegs[5].W  = pegs[4];
   
   //fourth row pointers
   pegs[6].NE = pegs[3];
   pegs[6].E  = pegs[7];
   pegs[6].SE = pegs[11];
   pegs[6].SW = pegs[10];
   
   pegs[7].NW = pegs[3];
   pegs[7].NE = pegs[4];
   pegs[7].E  = pegs[8];
   pegs[7].SE = pegs[12];
   pegs[7].SW = pegs[11];
   pegs[7].W  = pegs[6];
   
   pegs[8].NW = pegs[4];
   pegs[8].NE = pegs[5];
   pegs[8].E  = pegs[9];
   pegs[8].SE = pegs[13];
   pegs[8].SW = pegs[12];
   pegs[8].W  = pegs[7];
   
   pegs[9].NW = pegs[5];
   pegs[9].SE = pegs[14];
   pegs[9].SW = pegs[13];
   pegs[9].W  = pegs[8];
   
   //fifth row pointers
   pegs[10].NE = pegs[6];
   pegs[10].E  = pegs[11];
   
   pegs[11].NW = pegs[6];
   pegs[11].NE = pegs[7];
   pegs[11].E  = pegs[12];
   pegs[11].W  = pegs[10];
   
   pegs[12].NW = pegs[7];
   pegs[12].NE = pegs[8];
   pegs[12].E  = pegs[13];
   pegs[12].W  = pegs[11];
   
   pegs[13].NW = pegs[8];
   pegs[13].NE = pegs[9];
   pegs[13].E  = pegs[14];
   pegs[13].W  = pegs[12];
   
   pegs[14].NW = pegs[9];
   pegs[14].W  = pegs[13];
  }
  
  public String toString(){
   StringBuilder sb = new StringBuilder();
   sb.append(String.format("\n%15s",pegs[0].toString()));
   sb.append(String.format("\n%12s%6s",pegs[1].toString(), pegs[2].toString()));
   sb.append(String.format("\n%9s%6s%6s",pegs[3].toString(), pegs[4].toString(), pegs[5].toString()));
   sb.append(String.format("\n%7s%6s%6s%6s",pegs[6].toString(), pegs[7].toString(), pegs[8].toString(), pegs[9].toString()));
   sb.append(String.format("\n%5s%5s%6s%6s%6s",pegs[10].toString(), pegs[11].toString(), pegs[12].toString(), pegs[13].toString(), pegs[14].toString()));
   return sb.toString();
  }
  
  public boolean equals(Object o){
   for(int i = 0; i < 15; i++){
    if(this.pegs[i].isActive() != ((Board)o).pegs[i].isActive()){
     return false;
    }
   }
   return true;
  }
  
  public Board copy(){
   Board copy = new Board();
   for(int i = 0; i < 15; i++){
    copy.pegs[i].setActive(this.pegs[i].isActive());
   }
   copy.numPegs = this.getNumPegs();
   return copy;
  }
  
  public int getNumPegs(){
   return this.numPegs;
  }
 }