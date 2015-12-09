import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<String> fileNames = new LinkedList<String>();
		final File folder = new File("data/Zhao_trees/All_Cells"); //need to change folder for new data
		FileFunctions.listFilesForFolder(folder,fileNames);
		System.out.println(fileNames.size()+" files read.");
		FileTransfer fileTransfer;
		for(int i=0;i<fileNames.size();i++){
			System.out.println("process file: "+fileNames.get(i));
			String inputFile = fileNames.get(i);
			File next = new File(inputFile);
			//String outputFile = "data/output/modified_"+next.getName(); // need to change folder for new data
			//String outputFile = "data/output/modified_geodesic_"+next.getName();
			//String outputFile = "data/FourClassHippoBreakOutput/Geodesic/"+(i+1)+"_Geodesic_"+next.getName();
			String outputFile = "data/Zhao_trees/output_Euclidean/"+(i+1)+"_Euclidean_"+next.getName();
			fileTransfer = new EuclideanFileTransfer(inputFile);
			//fileTransfer.ChangeFileFormat(outputFile);
			String densityOutputFile = "data/Zhao_trees/output_Density/"+(i+1)+"_density_"+next.getName();
			fileTransfer.printDensityCount(densityOutputFile);
		}
	}
}
