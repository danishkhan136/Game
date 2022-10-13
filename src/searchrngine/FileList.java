package searchrngine;

import  java.io.*;

public class FileList {
    private int size;  //attributes for the data structure
    private File[] bin;

    public FileList() {  //initialising the attributes
        this.size = 0;
        this.bin = new File[0];
    }

    public int size(){
        return this.size;  //gettin the size of the current array
    }

    public void add(File file){  //adding files to the data structure

        File[] temp=new File[size];  //creat a temporary storing array
        for(int i=0; i<size; i++){
            temp[i]=bin[i];
        }
        size++;  //increase the size for the new input
        bin=new File[size];  //creat a dynamic array
        for(int i=0; i<size-1; i++){  //restore the values
            bin[i]=temp[i];
        }
        bin[size-1]=file;  //add the new element

    }

    public File get(int index){  //getting out the files
        if(index<=size)  //check the index
            return bin[index];
        else
            return null;
    }
}
