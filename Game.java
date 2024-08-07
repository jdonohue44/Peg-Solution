/*
 * ALL FILES CONTAINED IN THIS PROJECT ARE IN THE PUBLIC DOMAIN.
 * FEEL FREE TO USE, COPY, MODIFY OR DISTRIBUTE ANY OF IT.
 * -- Jared Donohue.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

/*
 * The Game class implements a solver for Triangle Peg Solitaire using Depth-First Search (DFS).
 * 
 * Algorithm: Depth-First Search (DFS)
 * - Time Complexity: O(b^d), where b is the branching factor and d is the depth of the solution.
 *   In this case, b varies but is bounded by the number of possible moves (max 6 per peg).
 *   The depth d is at most the number of pegs minus 1.
 * - Space Complexity: O(b^d) worst case due to visited hash map.
 *   - Stack space: In the worst case, the stack can grow to the full depth of the search, which is O(d).
 *   - Visited set: The visited hash map can potentially store all unique game states. In the worst case, this could be close to the total number of possible game states, which is O(b^d).
 * 
 * DFS Characteristics in this implementation:
 * 1. Completeness: The algorithm is complete and will find a solution if one exists, as it explores
 *    all possible game states until a solution is found or all states are exhausted.
 * 2. Optimality: This implementation finds a solution but not necessarily the optimal (shortest) one.
 *    To find the optimal solution, we would need to explore all possible paths or use iterative deepening.
 * 3. Stack usage: Utilizes a stack (via recursion) to keep track of the search path, allowing
 *    backtracking when a path doesn't lead to a solution.
 * 4. State space exploration: Systematically explores the game state space, going as deep as
 *    possible along each branch before backtracking.
 * 
 * Memory Optimization:
 * - HashMap for visited states: Prevents re-exploration of previously visited states, significantly
 *   reducing the search space and avoiding cycles in the state graph.
 * - Trade-off: Increased memory usage for faster execution and cycle prevention.
 */
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
 
 /*
     * Implements the Depth-First Search algorithm to find a solution for the Triangle Peg Solitaire game.
     * 
     * DFS Strategy:
     * 1. Initialize: Start with the initial game state (user-selected peg removed).
     * 2. Exploration: For each game state, explore all possible moves (up to 6 per peg).
     * 3. State Management: Use a stack to manage the frontier of unexplored states.
     * 4. Cycle Prevention: Maintain a set of visited states to avoid revisiting.
     * 5. Goal Check: Continually check if the current state is a winning state (1 peg left).
     * 6. Backtracking: Implicitly backtrack by popping states off the stack when a path is exhausted.
     * 7. Solution Reconstruction: When a solution is found, reconstruct the path using the visited map.
     * 
     * @return ArrayList<PegMove> representing the sequence of moves to solve the game
     */
 public static ArrayList<PegMove> playGame(){
  ArrayList<PegMove> bestMoves = new ArrayList<PegMove>();
  Game bestGame;
  Stack<Game> states  = new Stack<Game>(); // Frontier for DFS
  HashMap<Game,PegMove> visited = new HashMap<Game,PegMove>(); // Visited states and their predecessors
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
 
 /*
  * Overrides the equals method to compare Game objects.
  * Two Game objects are considered equal if they have the same board state and number of moves.
  */
 public boolean equals(Object o){
  if((this.board.equals(((Game)o).board)) && (this.numMoves == ((Game)o).numMoves)){
   return true;
  }
 return false;
 }
 
 /*
  * Creates a deep copy of the Game object.
  * Allows the DFS algorithm to explore different game states without modifying the original.
  */
 public Game copy(){
  Game g2 = new Game();
  g2.board = this.board.copy();
  g2.numMoves = this.getNumMoves();
  return g2;
 }
 
  public static void main(String[] args){
	Game g = new Game(0, new Board());
	ArrayList<PegMove> moves;
	System.out.println(g.board);
	moves = playGame();

	if (moves.isEmpty()) {
		System.out.println("No solution found.");
	} else {
		System.out.print("\nSolution found. Moves: ");
		for (int i = moves.size() - 1; i >= 0; i--) {
			System.out.print("move peg " + moves.get(i));
			if (i > 0) {
				System.out.print(", ");
			}
		}
		System.out.println(".\n\nWIN GAME.");
	}
}
}
