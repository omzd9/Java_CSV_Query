Those are the steps to follow :

    - Open cmd and cd to my src folder
    - javac *.java
    - java Oracle
    - now will interacte with program via cmd

Rules to avoid bugs :

    - Don't give fake pathfile 
    - SQL words should be in upperCase : SELECT FROM IMPORT WHERE
    - Import Stataement ex : IMPORT ../document.csv;
    - Select Statement ex : 
           
            * SELECT ID, Name FROM ../document.csv ;
            * SELECT ID,Age FROM ../document.csv WHERE ID = 1 ;
            * SELECT ID, age,name FROM ../document.csv WHERE ID > 1 AND Name != 'OMAR';
            * SELECT ID FROM ../document.csv WHERE ID > 1 or Name != 'OMAR';

NB : this program can't check more then two conditions in predicat => (ID < 10 AND ID > 3) or id = 5 will not work
           
    

