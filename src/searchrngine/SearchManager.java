package searchrngine;

import java.io.*;
import java.util.*;

public class SearchManager{

    private File root;  //attributes used
    private String path;  //input path
    private String query;  //input query
    private int token;  //token to identify the query
    private int matches[];  //tokens to identify matching events
    private String words[];  // array to store words
    private FileList bin=new FileList(); //data structure to keep files
    private Scanner input=new Scanner(System.in);  //scanner to get inputs

    public static void main(String[] args) {  //main method
        SearchManager search=new SearchManager();
        search.getTexts();
        search.getPath();
        search.find();
    }


    public void getTexts(){  //getting the words to be searched
        System.out.println("\n````````````````````````````Help Menu`````````````````````````````");
        System.out.println("To search the words with 'OR' : Use '|' without spaces between words");
        System.out.println("To search the words with 'AND': Use '&' without spaces between words");
        System.out.println("To search a single word       : Just type the word only             ");
        System.out.print("\nEnter the word to be searched :  ");
        query=input.next();
        query=query.trim();
        query=query.toUpperCase();

        if(query.contains("&")){  //checking the query fo "and" operator
            token=2;
            words=query.split("&");  //making the word array
            matches=new int[words.length];
        }
        else if (query.contains("|")){  //checking the query fo "or" operator
            token=1;
            words=query.split("\\|");  //making the word array
            matches=new int[words.length];
        }
        else{
            token=0;
            words=new String[]{query};  //getting the single word from the query
            matches=new int[words.length];
        }
    }

    public void getPath(){  //getting the path as a string
        System.out.print("\nEnter the path of the root folder :  ");
        path=input.next();
        path=path.trim();
        root=new File(path);  //craeting the root fille
        search();
    }

    public void search(){  // searching for the text files
        File file=root;
        File[] allFiles;

        try{
            if(file.isFile()){  //find whether a text file
                String filePath=file.getPath();
                if(filePath.endsWith(".txt") || filePath.endsWith(".TXT")|| filePath.endsWith(".doc")|| filePath.endsWith(".DOC")|| filePath.endsWith(".pdf")|| filePath.endsWith(".PDF")|| filePath.endsWith(".docx")|| filePath.endsWith(".DOCX"))
                    bin.add(file);  //store the text file in the data structure
            }
            else if(file.isDirectory()){  // find whether a folder
                allFiles = root.listFiles();

                if(allFiles!=null){
                    for(File x: allFiles){  // getting the subfolders and files
                        root=x;
                        search();  // recursively call for searching files
                    }
                }
            }
        }catch (Exception e){  // io exception handling
            System.out.println("\nError in the required path !");
            getPath();
        }
    }

    public void find(){  //finding for a matching word
        File file;  //local variables
        String text;
        BufferedReader read=null;
        int result=-1;  //tokens to identify matched words and the procedure
        int found=0;  //flag variables
        int notice=0;

        for(int i=0; i<bin.size(); i++){
            file=bin.get(i);  //getting out the files from the data structure

            for(int j=0; j<words.length; j++){
                try {
                    read = new BufferedReader(new FileReader(file));  //reading the file
                } catch (Exception e) {
                    System.out.println("\nError while opening the file !");
                }
                try {
                    while ((text = read.readLine()) != null) {
                        if(text.toUpperCase().contains(words[j])){  //matching the word
                            matches[j]=1;
                            found=1;
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\nError while reading the file !");
                }

                if(matches[j]==1 && token==1)  //speed up  for "or" operation
                    break;
            }

            if(token==2 && found==1){  //assigning the suitable values for the tokens
                result=1;
                for(int x:matches)
                    result=result*x;
            }else if(token==1 && found==1){
                result=0;
                for(int x:matches)
                    result=result+x;
            }else if(token==0 && found==1) {
                result=0;
                for(int x:matches)
                    result=result+x;
            }

            for(int x=0; x<matches.length; x++)  //restting the values for next searching
                matches[x]=0;

            if(result>0){  //display the result if found
                result=0;
                found=0;
                notice=1;
                System.out.println("\n*****************************************************");
                System.out.println("Matching Found !");
                System.out.println(file.getPath());
                System.out.println("*****************************************************\n");
            }else if (notice==0){
                result=-1;
            }
        }
        if(result<0){  //display the result if not found
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("No Matching Found !");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }
    }
}
