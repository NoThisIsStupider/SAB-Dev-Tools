#!/bin/bash

#ok so, what needs to happen is the .wrl gets run through the wrl to obj, then the obj through CleanOBJ, and then the mtl through my
#lua mtl thing, and then finally, the obj through binvcl

WRLtoOBJPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/WRLtoOBJ/" #java main/Main wrl in this dir to run
CleanOBJPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/CleanOBJ/src/" #java main/Main obj mtl in this dir to run
MTLFixerPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/MTLFixer/" #lua main.lua mtl in this dir to run
OBJToBinVCLPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/OBJtoBinVCL/" #a.out obj in this dir to run

WRLNAMEANDPATH=$1
WRLNAME=$(basename "${WRLNAMEANDPATH}")
WRLPATH=$(dirname "${WRLNAMEANDPATH}")

FILENAME="${WRLNAME:0:((${#WRLNAME}-4))}"

printf "Move to ${WRLtoOBJPath}\n"
cd "${WRLtoOBJPath}"
printf "run WRLtoOBJ with arg ${WRLNAMEANDPATH}\n"
java main/Main "${WRLNAMEANDPATH}"

printf "\nEdit line 1 of obj to mtllib ${FILENAME}.mtl\n"
sed "1s/.*/mtllib ${FILENAME}.mtl/" ${FILENAME}.obj > ${FILENAME}1.obj
printf "Copy ${FILENAME}.obj to ${WRLPATH}/${FILENAME}.obj\n"
cp ./${FILENAME}1.obj "${WRLPATH}/${FILENAME}.obj"
printf "Copy ${FILENAME}.mtl to ${WRLPATH}/${FILENAME}.mtl\n"
cp ./${FILENAME}.mtl "${WRLPATH}/${FILENAME}.mtl"

printf "\nMove to ${CleanOBJPath}\n"
cd "${CleanOBJPath}"
printf "Run CleanOBJ with args ${WRLPATH}/${FILENAME}.obj ${WRLPATH}/${FILENAME}.mtl\n"
java main/Main "${WRLPATH}/${FILENAME}.obj" "${WRLPATH}/${FILENAME}.mtl"

printf "\nMove to ${MTLFixerPath}\n"
cd "${MTLFixerPath}"
printf "Run MTLFixer with args ${WRLPATH}/${FILENAME}.mtl\n"
lua main.lua "${WRLPATH}/${FILENAME}.mtl"
printf "Move ./output/${FILENAME}.mtl to ${WRLPATH}\n"
cp "./output/${FILENAME}.mtl" "${WRLPATH}/${FILENAME}.mtl"

printf "\nMove to ${OBJToBinVCLPath}\n"
cd "${OBJToBinVCLPath}"
printf "Run binVCL with args ${WRLPATH}/${FILENAME}.obj\n"
./a.out "${WRLPATH}/${FILENAME}.obj"

printf "\nDONE\n"
