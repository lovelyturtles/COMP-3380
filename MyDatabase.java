import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDatabase
{
    private Connection connection;

    //all tableNames
    private final String WORLDS = "Worlds";
    private final String ROLES = "Roles";
    private final String REWARDED_BY = "Rewarded By";
    private final String QUESTS = "Quests";
    private final String PLAYERS = "Players";
    private final String PERFORM = "Perform";
    private final String ORCHESTRION = "Orchestrion Roll Table";
    private final String OCCUPATIONS = "Occupations";
    private final String LOGS = "Occupation Logs";
    private final String MVEffect = "MVEffect";
    private final String MOUNTS = "Mounts";
    private final String JOBS = "Jobs";
    private final String HAVE = "Have";
    private final String EARNED = "Earned Through";
    private final String DUTIES = "Duties";
    private final String DATACENTERS = "Datacenters";
    private final String COMPANIONS = "Companions";
    private final String CLASSES = "Classes";
    private final String ADVENTURES = "Adventures";
    private final String ACTIONS = "Actions";



    public MyDatabase()
    {
        try
        {
            //db parameter
            String url = "jdbc:sqlite:C:/sqlite/take401.sqlite";
            //create a connection to the database
            this.connection = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException var2)
        {
            var2.printStackTrace(System.out);
        }
        catch (SQLException var3)
        {
            var3.printStackTrace(System.out);
        }
    }

    public void allTables()
    {
        //prints list of tables in database
        System.out.println("TableName\n" + WORLDS + "\n"
                                         + ROLES + "\n"
                                         + REWARDED_BY + "\n"
                                         + QUESTS + "\n"
                                         + PLAYERS + "\n"
                                         + PERFORM + "\n"
                                         + ORCHESTRION + "\n"
                                         + OCCUPATIONS + "\n"
                                         + LOGS + "\n"
                                         + MVEffect + "\n"
                                         + MOUNTS + "\n"
                                         + JOBS + "\n"
                                         + HAVE + "\n"
                                         + EARNED + "\n"
                                         + DUTIES + "\n"
                                         + DATACENTERS + "\n"
                                         + COMPANIONS + "\n"
                                         + CLASSES + "\n"
                                         + ADVENTURES + "\n"
                                         + ACTIONS + "\n");
    }

    public void printTable(String tableName)
    {
        //prints all the contents of the specified table
        getTables(tableName);
    }

    private void getTables(String tableName)
    {
        try
        {
            PreparedStatement myStatement = this.connection.prepareStatement("Select * From ?;");
            myStatement.setString(1, tableName);
            ResultSet myResult = myStatement.executeQuery();

            while (myResult.next()) {
                System.out.println(myResult);
            }

            myResult.close();
            myStatement.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in trying to print out table.");
        }
    }
}


