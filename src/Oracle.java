import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.util.*;

public class Oracle{
    
 
    public static void main(String[] args) throws Exception 
    {
        Map<String,FileTraitement> FileMap=new HashMap<String,FileTraitement>();   
       FileTraitement csv ;//= new FileTraitement(new ImportStatement("import ../document.csv; ").getFilePath());
        SelectStatement sqlQuery; //= new SelectStatement(" select Age,Name,ID,Num from document  ;");
        Scanner sc = new Scanner(System.in);        
      
        System.out.println("#####################\t Java Query \t#####################\n");
        System.out.println("Those are the rules :\n\t* To import file write : 1\n\t* To excute a select query write : 2\n\t* To exit system write : 3");

       boolean exit=false;
       while (!exit) {
        System.out.println("what do you want ? (1,2,3)");
        String choice = sc.nextLine(); 
            switch(Integer.parseInt(choice)){
                case 1:
                    System.out.println("ok you want to import ! write your import statement :");
                    String str = sc.nextLine();
                    ImportStatement imp = new ImportStatement(str);
                    FileMap.put(imp.getFilePath(), new FileTraitement(imp.getFilePath()));
                    System.out.println("done !");
                    break;
                case 2 :
                    System.out.println("ok you want to excute query ! write your query :");
                    String query = sc.nextLine();
                    sqlQuery = new SelectStatement(query);
                    if(FileMap.containsKey(sqlQuery.getFromFilePath())){
                        System.out.println("\n");
                        sqlQuery.query(FileMap.get(sqlQuery.getFromFilePath() ));
                        System.out.println("\ndone !");

                    }else{
                        System.out.println("Sorry you should import " + sqlQuery.getFromFilePath() + " then excute queries on it");
                    }
                    
                    break;
                case 3 :
                    System.out.println("good bye ! I'm going to sleep now ...");
                    exit=true;
                    break;
                default :
                System.out.println("This's the rule :\n\t* To import file write : 1\n\t* To excute a select query write : 2\n\t* To exit system write : 3");
                    break;
                
            }
           
       }
       
        
    }
}

// import ../document.csv;
// select ID from ../document.csv;