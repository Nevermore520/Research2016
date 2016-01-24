import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO 1.Change {folder} to the one that contains all the original swc neuron files that need to be compared.
		//		2.Modify the {outputFile} if needed. This file will contains the transfered neuron file, which has a 
		//			different file format from the original one. By default, I add index as prefix to the file name, 
		//			which is useful when compute distance matrix using C++ code.
		//		3.Use {EuclideanFileTransfer} or {GeodesicFileTransfer} according to your needs. 
		//			{GeodesicFileTransfer} will compute distance between tree node using geodesic distance, 
		//			while {EuclideanFileTransfer} will compute the distance using Euclidean distance.
		//		4.run this java program
		List<String> fileNames = new LinkedList<String>();
		final File folder = new File("data/Giorgio_trees reclassed version/Original_data/"); //need to change folder for new data
		FileFunctions.listFilesForFolder(folder,fileNames);
		System.out.println(fileNames.size()+" files read.");
		FileTransfer fileTransfer;
		int controller = 0;
		for(int i=0;i<fileNames.size();i++){
			System.out.println("process file: "+fileNames.get(i));
			String inputFile = fileNames.get(i);
			fileTransfer = new EuclideanFileTransfer(inputFile);
			File next = new File(inputFile);
			if(controller == 0){
				// perform file transfer function
				//String outputFile = "data/output/modified_"+next.getName(); // need to change folder for new data
				//String outputFile = "data/output/modified_geodesic_"+next.getName();
				//String outputFile = "data/FourClassHippoBreakOutput/Geodesic/"+(i+1)+"_Geodesic_"+next.getName();
				//String outputFile = "data/Zhao_trees/output_Euclidean/"+(i+1)+"_Euclidean_"+next.getName();	// i+1 is the index of this file
				String outputFile = "data/Giorgio_trees reclassed version/transfered_data/"+(i+1)+"_Euclidean_"+next.getName();	// i+1 is the index of this file
				//fileTransfer = new GeodesicFileTransfer(inputFile);
				fileTransfer.ChangeFileFormat(outputFile);
			}else if(controller == 1){
				// perform density count function
				String densityOutputFile = "data/Zhao_trees/output_Density/"+(i+1)+"_density_"+next.getName();
				fileTransfer.printDensityCount(densityOutputFile);
			}
		}
	}
}
