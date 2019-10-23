# SAB Dev Tools
Tools to aid in developing

### Clean OBJ
For optimizing obj and mtl files exported from blender

### Entity Pos Convert
For updating coordinates of SAB objects for new model scales and positions and such

### MTL Fixer
Applies some changes to MTL files to fix things like everything being super reflective, should be used after CleanOBJ

### OBJ to binCOL
Converts a .obj and .mtl pair to binCOL, the binary format SAB2 uses for level collisions
Note: Only pass the .obj as an argument, don't pass the .mtl

### OBJ to binOBJ
Converts a .obj and .mtl pair to binOBJ, the binary format SAB2 uses for levels that don't use vertex colours
Note: Only pass the .obj as an argument, don't pass the .mtl

### OBJ to binVCL
Converts a .obj and .mtl pair to binVCL, the binary format SAB2 uses for levels that use vertex colours
Note: Only pass the .obj as an argument, don't pass the .mtl

### Set Scanner
For parsing the set files of SADX or SA2 in text format, and generating the same objects in .lvl format

### WRL to OBJ
Converts a .wrl model to a .obj and .mtl pair

## Bash Scripts

### WRL to binVCL
Automates the process of converting a .wrl to .binvcl, also generates a .obj and .mtl pair with vertex colours in the process
Requires manually updating paths to the various tools needed for it to work