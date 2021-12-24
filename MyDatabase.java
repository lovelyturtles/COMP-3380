import java.io.*;
import java.sql.*;

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
    private final String MINIONS = "Minions";
    private final String JOBS = "Jobs";
    private final String HAVE = "have";
    private final String EARNED = "earnedThrough";
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
            Class.forName("org.sqlite.JDBC");
            //db parameter
            String url = "jdbc:sqlite:FFXIV.sqlite";
            //create a connection to the database
            this.connection = DriverManager.getConnection(url, "SA", "");
        }
        catch (ClassNotFoundException ex)
        {
	    ex.printStackTrace(System.out);
	}
        catch (SQLException ex)
        {
            ex.printStackTrace(System.out);
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
                                         + MINIONS + "\n"
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
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The quest giver who had the most variety of quest types given was:\n" + queryResult + ": " + myResult.getString(2));
        }
        catch(Exception e)
        {
            System.out.println("Error in getting questGiver - questType table.");
        }
    }

    public void OccupationActions()
    {
        //prints the table to see which occupation has the most actions
        try
        {
            String myQuery = "Select Occupations.OccupationName, count( ActionName ) As NumberOfActions From Occupations join Perform on Occupations.OccupationName = Perform.OccupationName Group By Occupations.OccupationName Order By NumberOfActions DESC;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The occupation with the most actions is:\n" + queryResult + ": " + myResult.getString(2));
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Occupation - Actions table.");
        }
    }

    /*
    public void OccupationQuests()
    {
        //prints the table to see which occupation has the most quests
        try
        {
            String myQuery = "Select OccupationName, count( questID ) As NumberOfQuests From Occupations join Quests on Occupations.questID = Quests.questID Group By OccupationName Order By NumberOfQuests Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The occupation that has the most quest types is:\n" + queryResult + ": " + myResult.getString(2));
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Occupation - Quests table.");
        }
    }
    */
	
    public void SharedActions()
    {
        //Gives the table with the actions that is the most shared among occupations
        try
        {
            String myQuery = "Select ActionName, Count(OccupationName) As SharedAmong From Perform Group By ActionName Order By SharedAmong Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The action that is shared by the most occupations is:\n" + queryResult + ": " + myResult.getString(2));
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Actions - Occupations table.");
        }
    }

    public void SharedActionsOver30()
    {
        //Gives the table with the actions that is the most shared among occupations over level 30
        try
        {
            String myQuery = "Select ActionName, COUNT(OccupationName) As SharedAmong From Perform Join Actions On Perform.ActionName = Actions.Name Where Level > 30 Group By ActionName Order By SharedAmong Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The action that is shared by the most occupations over level 30 is:\n" + queryResult + ": " + myResult.getString(2));
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Actions - Occupations over 30 table.");
        }
    }
    public void CommonMonkAction()
    {
        //Gives the table with the most common Action type for Monk
        try
        {
            String myQuery = "Select Type, Count(Actions.Type) As NumberOfActionTypes From Actions Group By Type Order By NumberOfActionTypes Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("Monk has the most \"" + queryResult + "\" action type with " + myResult.getString(2) + " quests associated with it");
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Action Type for Monk.");
        }
    }
    public void ActionMostQuest()
    {
        //Gives the table with the action type that is associated to the most number of quests
        try
        {
            String myQuery = "Select Type, Count(Actions.questID) As NumberOfActions From Actions Group By Type Order By NumberOfActions Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            PrintStream resultPrinter = System.out;
            String queryResult = myResult.getString(1);
            resultPrinter.println("The action type " + queryResult + " has the most quests associated. It has " + myResult.getString(2) + " quests associated with it.");
        }
        catch(Exception e)
        {
            System.out.println("Error in getting Action type with most number of quests.");
        }
    }
    public void WindUpMinion()
    {
        //Gives the table with how many minions with the substring 'Wind up' are given from each acquisitionType
        try
        {
            String myQuery = "Select AcquisitionType, count(Name) From Minions Where Name Like '%Wind-up%' Group By AcquisitionType Order By count(Name) Desc;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            ResultSetMetaData rsmd = myResult.getMetaData();
            int cols = rsmd.getColumnCount();
		
            //print header
            for(int i = 1; i <= cols; i++)
	    {
                if(i > 1)
                {
                        System.out.print(", ");
                }
                System.out.print(rsmd.getColumnLabel(i));
	    }
	    System.out.println("");
		
            //print data
	    while (myResult.next())
            {
                for(int i = 1; i <= cols; i++)
                {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    System.out.print(myResult.getString(i)); //print one element of a row
                }
                System.out.println("");
            }

            myResult.close();
            myStatement.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in windup minion query.");
        }
    }	
    public void MountSpecialEffect()
    {
        //Gives the table with mounts that have special effects
        try
        {
            String myQuery = "Select Name, Count(SpecialEffects) From Mounts Join MVEffect On Mounts.mountID = MVEffect.mountID Group By Name Having Count(SpecialEffects) > 0;";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            ResultSetMetaData rsmd = myResult.getMetaData();
            int cols = rsmd.getColumnCount();
		
            //print header
            for(int i = 1; i <= cols; i++)
	    {
                if(i > 1)
                {
                        System.out.print(", ");
                }
                System.out.print(rsmd.getColumnLabel(i));
	    }
	    System.out.println("");
		
            //print data
	    while (myResult.next())
            {
                for(int i = 1; i <= cols; i++)
                {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    System.out.print(myResult.getString(i)); //print one element of a row
                }
                System.out.println("");
            }

            myResult.close();
            myStatement.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in getting table with special effect mounts.");
        }
    }	
    public void AveRecastRoles()
    {
        //Gives the table with roles ranked based on their average recast time
        try
        {
            String myQuery = "Select Role.Role, Avg(RecastTime) From Role Join Occupations on Occupations.Role = Role.Role Join Perform On Occupations.OccupationName = Perform.OccupationName Join Actions on Perform.ActionName = Actions.Name Group By Role.Role Order By Avg(RecastTime);";
            PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
            ResultSet myResult = myStatement.executeQuery();
            ResultSetMetaData rsmd = myResult.getMetaData();
            int cols = rsmd.getColumnCount();
		
            //print header
            for(int i = 1; i <= cols; i++)
	    {
                if(i > 1)
                {
                        System.out.print(", ");
                }
                System.out.print(rsmd.getColumnLabel(i));
	    }
	    System.out.println("");
		
            //print data
	    while (myResult.next())
            {
                for(int i = 1; i <= cols; i++)
                {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    System.out.print(myResult.getString(i)); //print one element of a row
                }
                System.out.println("");
            }

            myResult.close();
            myStatement.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in ranking roles based on average recast time.");
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
		
            //print header
            for(int i = 1; i <= cols; i++)
	    {
                if(i > 1)
                {
                        System.out.print(", ");
                }
                System.out.print(rsmd.getColumnLabel(i));
	    }
	    System.out.println("");
		
            //print data
            while (myResult.next())
            {
                for(int i = 1; i <= cols; i++)
                {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    System.out.print(myResult.getString(i)); //print one element of a row
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


