package amazed.solver;

import amazed.maze.Maze;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.*;

/**
 * <code>ForkJoinSolver</code> implements a solver for
 * <code>Maze</code> objects using a fork/join multi-thread
 * depth-first search.
 * <p>
 * Instances of <code>ForkJoinSolver</code> should be run by a
 * <code>ForkJoinPool</code> object.
 */


public class ForkJoinSolver extends SequentialSolver
{
    ForkJoinSolver childTask;
    ConcurrentSkipListSet<Integer> visited;
    /**
     * Creates a solver that searches in <code>maze</code> from the
     * start node to a goal.
     *
     * @param maze   the maze to be searched
     */
    public ForkJoinSolver(Maze maze)
    {
        super(maze);
    }

    /**
     * Creates a solver that searches in <code>maze</code> from the
     * start node to a goal, forking after a given number of visited
     * nodes.
     *
     * @param maze        the maze to be searched
     * @param forkAfter   the number of steps (visited nodes) after
     *                    which a parallel task is forked; if
     *                    <code>forkAfter &lt;= 0</code> the solver never
     *                    forks new tasks
     */
    public ForkJoinSolver(Maze maze, int forkAfter)
    {
        this(maze);
        this.forkAfter = forkAfter;
    }

    public ForkJoinSolver(Maze maze, int newStart, ConcurrentSkipListSet<Integer> visited)
    {
        this(maze);
        start = newStart;
        this.visited = visited;
    }

    /**
     * Searches for and returns the path, as a list of node
     * identifiers, that goes from the start node to a goal node in
     * the maze. If such a path cannot be found (because there are no
     * goals, or all goals are unreacheable), the method returns
     * <code>null</code>.
     *
     * @return   the list of node identifiers from the start node to a
     *           goal node in the maze; <code>null</code> if such a path cannot
     *           be found.
     */
    @Override
    public List<Integer> compute()
    {
        return parallelDepthFirstSearch();
    }

    private List<Integer> parallelDepthFirstSearch(){
      int current = start;
      int player = maze.newPlayer(current);
      while(true){
      if(maze.hasGoal(current)){
        return pathFromTo(start, current);
      }else if(maze.neighbors(current).size() == 1 && visited.contains(current)){
        visited.add(current);
        return null;
      }else if(maze.neighbors(current).size() == 2){
        predecessor.put()
        maze.move(player, current);
        }else{
        ArrayList<ForkJoinSolver> childs = new ArrayList<>();
        for(int neighbor : maze.neighbors(current)){
          if(!visited.contains(neighbor)){
          childTask = new ForkJoinSolver(maze, neighbor, visited);
          childs.add(childTask);
          childTask.fork();
          }
          for(ForkJoinSolver f: childs){
            LinkedList result = f.join();
            if(result != null){
              result.addFirst()
            }
            }
          }
        }
      }
     }
    }
}
