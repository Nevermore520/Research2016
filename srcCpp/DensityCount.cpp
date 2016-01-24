/*
 * DensityCount.cpp
 *
 *  Created on: Jan 16, 2016
 *      Author: Yanjie
 */

#include "DensityCount.h"

vector<vector<double> > computeDensityCountDistanceMatrix(vector<string> files){
	// TODO need to test this function
	vector<vector<double> > distanceMat;
	unsigned int fileNum = files.size();
	distanceMat.resize(fileNum);
	for (size_t i = 0; i < fileNum; ++i){
		distanceMat[i].resize(fileNum);
	}

	vector<double> integral;
	integral.resize(files.size());
	for(int i=0;i<files.size();i++){
		std::ifstream ifs;
		string fileName = files[i];
		int count = 0;
		double distance = 0, nextDistance;
		int density = 0, nextDensity;
		double sum = 0;
		ifs.open(fileName.c_str());
		ifs >> count;
		while(count--){
			ifs >> nextDistance >> nextDensity;
			sum+=(nextDistance-distance) * density;
			distance = nextDistance;
			density = nextDensity;
		}
		ifs.close();
		ifs.clear();
		integral[i] = sum;
	}

	for(int i=0;i<files.size();i++){
		for(int j = 0;j<files.size();j++){
			distanceMat[i][j] = abs(integral[i]-integral[j]);
		}
	}
	return distanceMat;
}


void getDensityCountDistanceMatrixFixIndex(string filePath, string outputFile){
	//file name start with its index eg. 12_name.swc, this index start from 1
	vector<string> files;
	getFiles(filePath, "swc", files);
	vector<string> filesFixIndex = getFixIndexFiles(files);
	vector<vector<double> > distanceMat = computeDensityCountDistanceMatrix(filesFixIndex);
	printMat(outputFile,distanceMat);
}

