import java.util.Set;


public class EuclideanFileTransfer extends FileTransfer{
	
	public EuclideanFileTransfer(String inputFile){
		this.inputFile = inputFile;
	}

	/**
	 * Compute the Euclidean distance between tree nodes
	 * @param root
	 * @param parentNode
	 */
	@Override
	protected void DFS(TreeNode root, TreeNode parentNode, double distance) {
		// TODO Auto-generated method stub
		if(parentNode==null){
			Set<TreeNode> children = root.getChildren();
			for(TreeNode child: children){
				DFS(child, root, distance);
			}
		}else{
			if(root.childrenNum()==0){
				root.setParentNode(parentNode);
				double dist = root.getVal().computeDistance(parentNode.getVal());
				root.setDistance(dist);
			}else if(root.childrenNum()==1){
				Set<TreeNode> children = root.getChildren();
				for(TreeNode child: children){
					DFS(child,parentNode, distance);
				}
			}else{
				root.setParentNode(parentNode);
				double dist = root.getVal().computeDistance(parentNode.getVal());
				root.setDistance(dist);
				Set<TreeNode> children = root.getChildren();
				for(TreeNode child: children){
					DFS(child, root, distance);
				}
			}
		}
	}

}
