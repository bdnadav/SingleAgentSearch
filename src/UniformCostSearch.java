import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class UniformCostSearch extends ASearch {
    PriorityQueue<BlindSearchNode> openList;
    ArrayList<BlindSearchNode> closeList;

    @Override
    public String getSolverName() {
        return "UCS";
    }

    @Override
    public ASearchNode createSearchRoot(IProblemState problemState) {
        ASearchNode newNode = new BlindSearchNode(problemState);
        return newNode;
    }

    @Override
    public void initLists() {
        openList=new PriorityQueue<>((Comparator<ASearchNode>) (o1, o2) -> {
            if(o1.getG() > o2.getG()){
                return 1;
            }
            else if (o1.getG() < o2.getG()){
                return -1;
            }

            return 0;
        });
        closeList = new ArrayList<>();
    }

    @Override
    public ASearchNode getOpen(ASearchNode node) {
        BlindSearchNode ans = null;
        if (openList.contains(node)) {
            Iterator it = openList.iterator();
            while (it.hasNext()) {
                ans = (BlindSearchNode) it.next();
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
    public void addToOpen (ASearchNode node) {
        openList.add((BlindSearchNode)node);
    }

    @Override
    public void addToClosed (ASearchNode node) {
        closeList.add((BlindSearchNode)node);
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
