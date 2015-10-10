import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public abstract class FileTransfer {
	static int STARTINDEX = 1;
	static int INDEX = 0;
	String inputFile;
	protected abstract void DFS(TreeNode root, TreeNode parentNode, double distance);
	public void ChangeFileFormat(String outputFile) throws FileNotFoundException, UnsupportedEncodingException{
		File fp = new File(inputFile);
		Scanner input = new Scanner(fp);
		int index, structID, parentIndex;
		float x, y, z, radius;
		Map<Integer, TreeNode> allNodes = new HashMap<Integer, TreeNode>();
		//jump to the first line of valid data
		while(!input.hasNextInt()){
			input.nextLine();
		}
		while(input.hasNext()){
			index = input.nextInt();
			structID = input.nextInt();
			x = input.nextFloat();
			y = input.nextFloat();
			z = input.nextFloat();
			radius = input.nextFloat();
			parentIndex = input.nextInt();
			
			Point3D p = new Point3D(x,y,z);
			TreeNode currentNode = new TreeNode(null, p);
			if(parentIndex >= STARTINDEX && allNodes.containsKey(parentIndex)){
				TreeNode parent = allNodes.get(parentIndex);
				currentNode.setParentPoint(parent);
				parent.addChild(currentNode);
			}
			allNodes.put(index, currentNode);
		}
		input.close();
		
		TreeNode root = allNodes.get(STARTINDEX);
		DFS(root, null, 0);
		
		List<List<Float>> points = new LinkedList<List<Float>>();
		List<List<Integer>> connectivity = new LinkedList<List<Integer>>();
		List<Double> distance = new LinkedList<Double>();
		INDEX = 0;
		BuildData(points, connectivity, distance, root);
		
		PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
		writer.println(points.size()+" "+connectivity.size());
		for(int i=0;i<points.size();i++){
			List<Float> point = points.get(i);
			writer.println(point.get(0)+" "+point.get(1)+" "+point.get(2));
		}
		for(int i=0;i<connectivity.size();i++){
			List<Integer> connect = connectivity.get(i);
			writer.println(connect.get(0)+" "+connect.get(1)+" "+distance.get(i));
		}
		writer.close();
	}
	
	private void BuildData(List<List<Float>> points, List<List<Integer>> connectivity, List<Double> distance, TreeNode root){
		if(root.getParentPoint()==null){
			root.setIndex(INDEX);
			INDEX++;
			points.add(root.getVal().getPosition());
			Set<TreeNode> children = root.getChildren();
			for(TreeNode child:children){
				BuildData(points, connectivity, distance, child);
			}
		}else{
			if(root.childrenNum()!=1){
				root.setIndex(INDEX);
				INDEX++;
				points.add(root.getVal().getPosition());
				List<Integer> connect = new LinkedList<Integer>();
				connect.add(root.getParentNode().getIndex());
				connect.add(root.getIndex());
				connectivity.add(connect);
				distance.add(root.getDistance());
				if(root.childrenNum()>1){
					Set<TreeNode> children = root.getChildren();
					for(TreeNode child: children){
						BuildData(points, connectivity, distance, child);
					}
				}
			}else{
				Set<TreeNode> children = root.getChildren();
				for(TreeNode child: children){
					BuildData(points, connectivity, distance, child);
				}
			}
		}
	}
}
