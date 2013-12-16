/******************************************************************************
 *
 * EdgeNode class
 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   December 15th, 2013
 ******************************************************************************/
public class EdgeNode {
    // Invariant of EdgeNode:
    // vertexNum must be an integer
    private int vertexNum;

    /**
     * Initialize an EdgeNode() object with the spcified
     * vertexNumber input
     * @param vertexNum
     */
    public EdgeNode (int vertexNum){
            this.vertexNum = vertexNum;
    }

    /**
     * Get method to return vertexNode value
     * @return
     *  value of the vertexNode
     */
    public int getVertexNum() {
        return vertexNum;
    }


    /**
     * Set method to replace the value of the vertexNode
     * @param vertexNum
     */
    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    /**
     * Compares an input object with the current object
     * @param obj
     * @return
     *  true - if the objects have the same vertexNum
     *  false - if the objects have different vertexNum
     *  throw
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof EdgeNode && ((EdgeNode) obj).getVertexNum() == this.getVertexNum()) {
            return true;
        } else {
            return false;
        }
    }
}
