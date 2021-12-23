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
    private final String REWARDED_BY = "RewardedBy";
    private final String QUESTS = "Quests";
    private final String PLAYERS = "Players";
    private final String PERFORM = "Perform";
    private final String ORCHESTRION = "OrchestrionRolls";
    private final String OCCUPATIONS = "Occupations";
    private final String LOGS = "OccupationLogs";
    private final String MVEffect = "MVEffect";
    private final String MOUNTS = "Mounts";
    private final String MOUNTS = "Minions";
    private final String JOBS = "Jobs";
    private final String HAVE = "have";
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
            String url = "jdbc:sqlite:FFXIV.db";
            //create a connection to the database
            this.connection = DriverManager.getConnection(url, "SA", "");
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
    
    public void QuestGiverType()
    {
        //prints the table to see which quest giver has the most variety in the types of quests they give
        try
        {
            String myQuery = "Select QuestGiver, count(type) AS QuestTypes From Quests Group By QuestGiver Order By QuestTypes Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();

            while (myResult.next())
            {
                System.out.println(myResult);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getting questGiver - questType table.");
        }
    }

    public void OccupationActions()
    {
        //prints the table to see which quest giver has the most variety in the types of quests they give
        try
        {
            String myQuery = "Select OccupationName, count( ActionName ) As NumberOfActions From Perform Group By OccupationName Order By NumberOfActions Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();

            while (myResult.next())
            {
                System.out.println(myResult);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Occupation - Actions table.");
        }
    }

    public void OccupationQuests()
    {
        //prints the table to see which quest giver has the most variety in the types of quests they give
        try
        {
            String myQuery = "Select OccupationName, count( questID ) As NumberOfQuests From Occupations join Quests on Occupations.questID = Quests.questID Group By OccupationName Order By NumberOfQuests Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();

            while (myResult.next())
            {
                System.out.println(myResult);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Occupation - Quests table.");
        }
    }

    private void getTables(String tableName)
    {
        try
        {
            String query = "Select * From " + tableName + ";";
            Statement myStatement = this.connection.createStatement();
            ResultSet myResult = myStatement.executeQuery(query);
            ResultSetMetaData rsmd = myResult.getMetaData();
            int cols = rsmd.getColumnCount();

            while (myResult.next())
            {
                for(int i = 0; i < cols; i++)
                {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    String colValue = myResult.getString(i);
                    System.out.print(colValue);
                }
                System.out.println("");
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


