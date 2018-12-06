# WordRank Application

This application reports the score ranks of the text files regarding to the existence of the input words provided.

The text file formats can be adjusted from the com.bora.Constants class,  as for now the file formats to be processed are txt, rtf and log. More file types can be added.

# Building and running

Please go to the main project folder, 

## To compile

javac -d . ./src/com/bora/*.java

## To create the jar file with the manifest file which is in the META-INF folder

jar -cvfm wordrank.jar META-INF/MANIFEST.MF ./com/bora/*.class

## Finally, to run the application :

Sample files are provided in the samples folder in the project.

java -cp . -jar wordrank.jar ./samples/

