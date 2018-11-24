import java.util.*;

public class AStarSearch extends ASearch {
    PriorityQueue<HeuristicSearchNode> openList;
    HashSet<HeuristicSearchNode> closeList;

    @Override
    public String getSolverName() {
        return "AStar";
    }

    @Override
    public ASearchNode createSearchRoot(IProblemState problemState) {
        ASearchNode newNode = new HeuristicSearchNode(problemState);
        return newNode;
    }

    @Override
    public void initLists() {
        closeList = new HashSet<>();
        //closeList = new ArrayList<>();
        openList=new PriorityQueue<>((o1, o2) -> {
            if(o1.getF() > o2.getF()){
                return 1;
            }
            else if (o1.getF() < o2.getF()){
                return -1;
            }

            return 0;
        });

    }

    @Override
    public ASearchNode getOpen(ASearchNode node) {
        HeuristicSearchNode ans = null;
        if (openList.contains(node)) {
            Iterator it = openList.iterator();
            while (it.hasNext()) {
                ans = (HeuristicSearchNode) it.next();
                if (ans.equals(node))
                    break;
            }
            return ans;
        } else
            return null;
    }

    @Override
    public boolean isOpen(ASearchNode node) {
        return openList.contains(node);
    }

    @Override
    public boolean isClosed(ASearchNode node) {
        return closeList.contains(node);
    }

    @Override
    public void addToOpen(ASearchNode node) {
        openList.add((HeuristicSearchNode)node);
    }

    @Override
    public void addToClosed(ASearchNode node) {
        closeList.add((HeuristicSearchNode)node);
    }

    @Override
    public int openSize() {
        return openList.size();
    }

    @Override
    public ASearchNode getBest() {
        return openList.poll();
    }

}
