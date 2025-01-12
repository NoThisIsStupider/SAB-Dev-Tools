#!/bin/bash

CleanOBJPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/CleanOBJCLI/src/" #java main/Main (args) in this dir to run
MTLFixerPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/MTLFixer/" #lua main.lua (args) in this dir to run
OBJToBinOBJPath="/home/nothisisstupider/SAB/SAB-Dev-Tools/OBJtoBinOBJ/makebuild/" #a.out (args) in this dir to run

OBJNAMEANDPATH=$1
OBJNAME=$(basename "${OBJNAMEANDPATH}")
OBJPATH=$(dirname "${OBJNAMEANDPATH}")

FILENAME="${OBJNAME:0:((${#OBJNAME}-4))}"

printf "\nMove to ${CleanOBJPath}\n"
cd "${CleanOBJPath}"
printf "Run CleanOBJ with args ${OBJPATH}/${FILENAME}.obj ${OBJPATH}/${FILENAME}.mtl\n"
java main/Main "${OBJPATH}/${FILENAME}.obj" "${OBJPATH}/${FILENAME}.mtl"

printf "\nMove to ${MTLFixerPath}\n"
cd "${MTLFixerPath}"
printf "Run MTLFixer with args ${OBJPATH}/${FILENAME}.mtl\n"
lua main.lua "${OBJPATH}/${FILENAME}.mtl"
printf "Move ./output/${FILENAME}.mtl to ${OBJPATH}\n"
cp "./output/${FILENAME}.mtl" "${OBJPATH}/${FILENAME}.mtl"

printf "\nMove to ${OBJToBinOBJPath}\n"
cd "${OBJToBinOBJPath}"
printf "Run binOBJ with args ${OBJPATH}/${FILENAME}.obj\n"
./a.out "${OBJPATH}/${FILENAME}.obj"

printf "\nDONE\n"
