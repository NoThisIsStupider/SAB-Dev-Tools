package main;

import java.util.ArrayList;


public class Main 
{
	public static String objFile;
	public static String mtlFile;
	
	public static void main(String[] args)
	{
		if (args.length == 2)
		{
			objFile = args[0];
			mtlFile = args[1];
		}
		else
		{
			System.out.println("Wrong arg count, must be the .obj followed by the .mtl.");
			return;
		}	
    	if (objFile.toLowerCase().endsWith(".obj") &&
    		mtlFile.toLowerCase().endsWith(".mtl"))
    	{
    		ArrayList<String> OBJFile = Input.openFile(objFile);
    		ArrayList<String> MTLFile = Input.openFile(mtlFile);
    		System.out.println("Getting rid of textures with identical .png's");
    		Fixer.fixMaterials(OBJFile, MTLFile);
    		System.out.println("Grouping usemtl groups in obj file");
    		Fixer.organizeOBJ(OBJFile);
    		System.out.println("Done. Writing out...");
    		Output.writeFile(OBJFile, objFile);
    		Output.writeFile(MTLFile, mtlFile);
    		System.out.println("Done");
    	}
	}
}
