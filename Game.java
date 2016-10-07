/*
 * ALL FILES CONTAINED IN THIS PROJECT ARE IN THE PUBLIC DOMAIN.
 * FEEL FREE TO USE, COPY, MODIFY OR DISTRIBUTE ANY OF IT.
 * -- Jared Donohue.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

// The Game class is the driver for running the project.
public class Game {
 
 int numMoves;
 Board board;
 
 public Game(int numMoves,Board board){
  this.board = board;
  this.numMoves = numMoves;
 }
 
 public Game(){
  this(0,new Board());
 }
 
 // DFS
 public static ArrayList<PegMove> playGame(){
  ArrayList<PegMove> bestMoves = new ArrayList<PegMove>();
  Game bestGame;
  Stack<Game> states  = new Stack<Game>();
  HashMap<Game,PegMove> visited = new HashMap<Game,PegMove>(); 
  Game initial = new Game(0,new Board());
  
   int peg;
   Scanner s = new Scanner(System.in);
   System.out.println("Which peg would you like to remove to start the game?");
   peg = s.nextInt();
   s.close();

   //Game starts by removing any one peg
   initial.board.pegs[peg-1].makeInactive();
   initial.board.numPegs --;

   // push initial game state onto stack, and add to hash map
   visited.put(initial,null);
   states.push(initial);
   
   while(!states.isEmpty()){
   		Game g = states.pop();
    
    	// found a winning game, lets exit.
    	if(g.board.numPegs == 1){
     		bestGame = g;
     
     		// trace back moves
     		while(visited.get(bestGame) != null){
      			bestMoves.add(visited.get(bestGame));
      			bestGame = visited.get(bestGame).g;
     		}
     
     	// we're done!
     	return bestMoves;
    	}
    
    // for each peg in this board pegs list
    for(int i = 0; i <15; i++){
    	Game nextGame;
     
     	// move this peg East if possible
     	if(g.board.pegs[i].canMoveE()){
       		nextGame = g.copy();
       		nextGame.board.pegs[i].moveE();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
         		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.E)); 
         		states.push(nextGame);
       			}
     	}
     
    	// move this peg North East if possible
     	if(g.board.pegs[i].canMoveNE()){
       		nextGame = g.copy();
       		nextGame.board.pegs[i].moveNE();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
         		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.NE)); 
         		states.push(nextGame);
       		}
     	}
     
     	// move this peg West if possible
     	if(g.board.pegs[i].canMoveW()){
       		nextGame = g.copy();
       		nextGame.board.pegs[i].moveW();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
         		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.W)); 
         		states.push(nextGame);
       		}
     	}
     
     	// move this peg North West if possible
     	if(g.board.pegs[i].canMoveNW()){
       		nextGame = g.copy();
       		nextGame.board.pegs[i].moveNW();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
         		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.NW)); 
         		states.push(nextGame);
       		}
     	}
     
     	// move this peg South West if possible
     	if(g.board.pegs[i].canMoveSW()){
       		nextGame = g.copy();
       		nextGame.board.pegs[i].moveSW();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
         		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.SW)); 
         		states.push(nextGame);
       		}
     	}
     
     	// move this peg South East if possible
     	if(g.board.pegs[i].canMoveSE()){
      		nextGame = g.copy();
       		nextGame.board.pegs[i].moveSE();
       
       		// put this new game state in our HashMap
       		if(!visited.containsKey(nextGame)){
        		nextGame.board.numPegs -= 1; 
         		visited.put(nextGame, new PegMove(g,g.board.pegs[i],Direction.SE)); 
         		states.push(nextGame);
       		}
     	}
      }
	}
 return bestMoves;
}
 
 
 public int getNumMoves(){
  return this.numMoves;
 }
 
 public boolean equals(Object o){
  if((this.board.equals(((Game)o).board)) && (this.numMoves == ((Game)o).numMoves)){
   return true;
  }
 return false;
 }
 
 public Game copy(){
  Game g2 = new Game();
  g2.board = this.board.copy();
  g2.numMoves = this.getNumMoves();
  return g2;
 }
 
  public static void main(String[] args){
  Game g = new Game(0, new Board());
  ArrayList<PegMove> moves  = new ArrayList<PegMove>();
  System.out.println(g.board);
  moves = playGame();

  System.out.print("\nmove peg " + moves.get(moves.size()-1) + ",");
  for(int i = moves.size()-2; i > 0; i--){
   System.out.print(" move peg " + moves.get(i) + ",");
  }
  System.out.println("move peg " + moves.get(0) + ".\n\nWIN GAME.");
 }
}
