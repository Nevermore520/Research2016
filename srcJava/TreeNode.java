import java.util.HashSet;
import java.util.Set;


public class TreeNode {

	TreeNode parentNode;
	TreeNode parentPoint;
	Point3D val;
	Set<TreeNode> children;
	double distance;
	int index;
	
	public TreeNode(TreeNode parent, Point3D val){
		this.parentNode = null;
		this.parentPoint = parent;
		this.val = val;
		this.children = new HashSet<TreeNode>();
		this.distance = 0;
		index = 0;
	}
	
	public TreeNode getParentPoint(){
		return this.parentPoint;
	}
	public void setParentPoint(TreeNode parent){
		this.parentPoint = parent;
	}
	public TreeNode getParentNode(){
		return this.parentNode;
	}
	public void setParentNode(TreeNode parent){
		this.parentNode = parent;
	}
	
	public Point3D getVal(){
		return this.val;
	}
	
	public int childrenNum(){
		return this.children.size();
	}
//	public void deleteChild(TreeNode child){
//		this.children.remove(child);
//	}
	public void addChild(TreeNode child){
		this.children.add(child);
	}
	public Set<TreeNode> getChildren(){
		return this.children;
	}
	
	public double getDistance(){
		return this.distance;
	}
	public void setDistance(double distance){
		this.distance = distance;
	}
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index = index;
	}
}
