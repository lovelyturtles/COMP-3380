import java.sql.Connection;
import java.util.Scanner;

public class MyFrontEnd
{
    static Connection connection;

    public MyFrontEnd()
    {
    }

    public static void main(String[] input) throws Exception
    {
        MyDatabase db = new MyDatabase();
        runConsole(db);
        System.out.println("Exiting...");
    }

    public static void runConsole(MyDatabase toRun)
    {
        Scanner userInput = new Scanner(System.in); //reads the user's input on terminal
        System.out.print("db > ");
        String userInLine = userInput.nextLine(); //current user input line

        for(String currLine = ""; userInLine != null && !userInLine.equals("q"); userInLine = userInput.nextLine())
        {
            //get an array of string from the user input
            String[] var0 = userInLine.split("\\s+");

            //cut unnecessary blanks
            if (userInLine.indexOf(" ") > 0)
            {
                currLine = userInLine.substring(userInLine.indexOf(" ")).trim();
            }

            //check user inputs
            if (var0[0].equals("h"))
            {
                //print the help
                printHelp();
            }
            else if (var0[0].equals("t"))
            {
                //print the list of tables
                toRun.allTables();
            }
            else if (var0[0].equals("show"))
            {
                //print all the contents of the specified table
                if(var0.length >= 2)
                {
                    toRun.printTable(currLine);
                }
                else
                {
                    System.out.println("This command needs an argument");
                }
            }
            else
            {
                System.out.println("Read the help with h.");
            }

            System.out.print("db > ");
        }

        userInput.close();
    }

    private static void printHelp()
    {
        System.out.println("Final Fantasy XIV mini game database.");
        System.out.println("Commands:");
        System.out.println("h - Get help");
        System.out.println("show tablename - Prints the table all the contents of the specified table.");
        System.out.println("t - Prints the list of all the tables");
        System.out.println("q - Exit the program");
        System.out.println("---- end help ----- ");
    }
}
