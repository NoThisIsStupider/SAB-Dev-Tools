package main;

import java.util.ArrayList;

public class Main
{
	public static String fileWRL = "";
	
	public static String outputFileFolder = "./";
	public static String outputFileName = "Output";
	
	public static void main(String[] args)
	{
		fileWRL = args[0];

		ArrayList<String> materialsToRemove = new ArrayList<String>();
		
		outputFileName = fileWRL.substring(fileWRL.lastIndexOf("/") + 1, fileWRL.length() - 4);

		
    	if (fileWRL.toLowerCase().endsWith(".wrl"))
    	{		
    		Converter.uvScale = 1.0f;
    		Converter.flipRB = true;
    		
    		//Converter.mirrorXMaterials.add("pum256_3.png");
    		//Converter.mirrorXMaterials.add("pum64_8.png");
    		//Converter.mirrorXMaterials.add("miu64_loco005.png");
    		
    		//Converter.mirrorYMaterials.add("pum128_11.png");

    		//Converter.mirrorXYMaterials.add("pr_2kage32.png");
    		
    		//materialsToRemove.add("p_kage32.png");
    		
    		System.out.println("Starting.");
    		ArrayList<String> WRLFile = Input.openFile(fileWRL);
    		Converter.replaceTabs(WRLFile);
    		ArrayList<String> OBJFile = new ArrayList<String>();
    		ArrayList<String> MTLFile = new ArrayList<String>();
    		
    		System.out.println("Converting from wrl to obj");
    		Converter.convert(WRLFile, OBJFile, MTLFile, outputFileName+".mtl");
    		
    		System.out.println("Getting rid of textures with identical .png's");
    		Fixer.fixMaterials(OBJFile, MTLFile);
    		
    		System.out.println("Grouping usemtl groups in obj file");
    		Fixer.organizeOBJ(OBJFile);
    		
    		System.out.println("Grouping usemtl groups in obj file");
    		Fixer.organizeOBJ(OBJFile);
    		
    		for (String matToRem : materialsToRemove)
    		{
    			System.out.println("Removing "+matToRem);
        		Fixer.removeMaterial(OBJFile, MTLFile, matToRem);
    		}
    		
    		System.out.println("Done. Writing out...");
    		Output.writeFile(OBJFile, outputFileFolder+outputFileName+".obj");
    		Output.writeFile(MTLFile, outputFileFolder+outputFileName+".mtl");
    		System.out.println("Done");
    	}
	}
}
