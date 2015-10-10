import java.util.Set;


public class GeodesicFileTransfer extends FileTransfer{
	
	public GeodesicFileTransfer(String inputFile){
		this.inputFile = inputFile;
	}

	/**
	 * Compute geodesic distance between tree nodes
	 * @param root
	 * @param parentNode
	 * @param distance
	 */
	@Override
	protected void DFS(TreeNode root, TreeNode parentNode, double distance) {
		// TODO Auto-generated method stub
		if(parentNode==null){
			Set<TreeNode> children = root.getChildren();
			for(TreeNode child: children){
				double dist = root.getVal().computeDistance(child.getVal());
				DFS(child,root, dist);
			}
		}else{
			if(root.childrenNum()==0){
				root.setParentNode(parentNode);
				root.setDistance(distance);
			}else if(root.childrenNum()==1){
				Set<TreeNode> children = root.getChildren();
				for(TreeNode child: children){
					double dist = root.getVal().computeDistance(child.getVal())+distance;
					DFS(child,parentNode, dist);
				}
			}else{
				root.setParentNode(parentNode);
				root.setDistance(distance);
				Set<TreeNode> children = root.getChildren();
				for(TreeNode child: children){
					double dist = root.getVal().computeDistance(child.getVal());
					DFS(child,root, dist);
				}
			}
		}
	}

}
