


import java.util.*;
 // Main entry point
public class Oracle{
    
   
    public static void main(String[] args) throws Exception 
    {
        // filesMap will contain all import files
        Map<String,FileTraitement> FilesMap=new HashMap<String,FileTraitement>();   
        
        SelectStatement sqlQuery;
    
        Scanner sc = new Scanner(System.in);        
      
        System.out.println("#####################\t CSV Query \t#####################\n");
        System.out.println("Those are the rules :\n\t* To import file write : 1\n\t* To excute a select query write : 2\n\t* To exit system write : 3");

       boolean exit=false;
       while (!exit) {
        System.out.println("what do you want ? (1,2,3)");
        String choice = sc.nextLine(); 
            switch(choice){
                case "1": 
                    System.out.println("ok you want to import ! write your import statement :");
                    String str = sc.nextLine();
                    ImportStatement imp = new ImportStatement(str);
                    FilesMap.put(imp.getFilePath(), new FileTraitement(imp.getFilePath()));
                    System.out.println("done !");
                    break;
                case "2" :
                    System.out.println("ok you want to excute query ! write your query :");
                    String query = sc.nextLine();
                    sqlQuery = new SelectStatement(query);
                    if(FilesMap.containsKey(sqlQuery.getFromFilePath())){
                        System.out.println("\n");
                        sqlQuery.query(FilesMap.get(sqlQuery.getFromFilePath() ));
                        System.out.println("\ndone !");

                    }else{
                        System.out.println("Sorry you should import " + sqlQuery.getFromFilePath() + " then excute queries on it");
                    }
                    
                    break;
                case "3" :
                    System.out.println("good bye ! I'm going to sleep now ...");
                    exit=true;
                    break;
                default :
                System.out.println("Please play by the rules ...\nThose are the rules :\n\t* To import file write : 1\n\t* To excute a select query write : 2\n\t* To exit system write : 3");
                    break;
                
            }
           
       }
       sc.close();
        
    }
}

// IMPORT ../document.csv;
// SELECT ID FROM ../document.csv;