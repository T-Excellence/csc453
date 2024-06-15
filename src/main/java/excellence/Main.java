package excellence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{

    Scanner scanner;
    Statement statement;

    {
        try{

            var student = """
                    CREATE TABLE IF NOT EXISTS students
                    (std_id int PRIMARY KEY AUTO_INCREMENT,
                    firstname varchar(30),
                    lastname varchar(30),
                    matric_no varchar(8),
                    gender char,
                    level int)
                    """;

            var admin = """
                    CREATE TABLE IF NOT EXISTS admins
                    (adm_id int PRIMARY KEY AUTO_INCREMENT,
                    firstname varchar(30),
                    lastname varchar(30))
                    """;


            var gender = new char[]{'M', 'F'};
            var levels = new int[]{100, 200, 300, 400};
            var rand = new Random(System.currentTimeMillis());
            var lastNames = new String[]{"Akinola", "Muhammed", "Abdulqudri", "Mustapha", "Mark"};
            var firstNames = new String[]{"Micheal", "Samuel", "Samson", "Abdulkareem", "Olamilaken", "Charles", "Oluwadamilola"};

            scanner = new Scanner(System.in);
            statement = DriverManager.getConnection("jdbc:mysql://localhost:3306/csc454?user=root&password=root").createStatement();

            statement.execute(admin);
            statement.execute(student);

            if( !statement.executeQuery("SELECT * FROM admins").next()){

                for(int i = 0; i<12; i++){
                    statement.execute(
                            String.format("INSERT INTO admins VALUES (null, \"%s\", \"%s\");",
                            firstNames[rand.nextInt(firstNames.length - 1)],
                            lastNames[rand.nextInt(lastNames.length - 1)])
                    );

                }

            }

            if( !statement.executeQuery("SELECT * FROM students").next()){
                for(int i = 0; i<12; i++){
                    statement.execute(
                            String.format("INSERT INTO students VALUES (null, \"%s\", \"%s\", \"52HA%03d\", '%c', %d);",
                                    firstNames[rand.nextInt(firstNames.length - 1)],
                                    lastNames[rand.nextInt(lastNames.length - 1)],
                                    rand.nextInt(100),
                                    gender[rand.nextInt(gender.length - 1)],
                                    levels[rand.nextInt(levels.length - 1)])
                    );

                }

            }

        } catch (SQLException e) {
            err.println(e.getMessage());
        }
    }

    static public void main(String[] arg){

        var main = new Main();

        try{
            //There is a syntax error here. The below line need to be terminated with ";"
            main.start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void start() throws SQLException {

        //This is reference error. query1 was declared in method "showStudentInfo", but it is being reference here. The reference is out of scope.
        //Also, the line needs to be terminated with ";"
        query1.next()

        out.println("Menu");
        out.println("0. Exit program");
        out.println("1. Get a Student Info");
        out.println("2. Change a student level");

        try {
            switch (scanner.nextInt()){
                case 0 : exit(0);
                case 1 : showStudentInfo();
                case 2 : changeStudentLevel();
                default: {
                    out.println("Invalid option. Please choose from 0 to 2");
                    start();
                };
            }

        }catch (InputMismatchException e){
            out.println("Invalid option. Integer input only.");
            out.println("Please, try again later.");
        }

    }

    void showStudentInfo() throws SQLException {

        out.println("Please input the student matric number : ");
        String matricNumber = new Scanner(System.in).nextLine();
        var query1 = statement.executeQuery("SELECT * FROM students WHERE matric_no = \"" + matricNumber + "\"");

        if(query1.next()){
            out.println("Student Info\n");
            out.printf("Student Matric Number \t\t: %s%n", query1.getString("matric_no"));
            out.printf("Student fullname \t\t\t: %s %s%n", query1.getString("firstname"), query1.getString("lastname"));
            out.printf("Student Academic Level \t\t: %d%n", query1.getInt("level"));
            out.printf("Student Gender \t\t\t\t: %S%n", query1.getString("gender"));
            out.println();

        }
        else out.printf("No student with the matric number \"%s\" is found%n", matricNumber);

        start();
    }

    void changeStudentLevel() throws SQLException {

        out.println("Please input the student matric number : ");
        String matricNumber = new Scanner(System.in).nextLine();

        out.println("Please input the new level : ");
        String level = new Scanner(System.in).nextLine();

        var query2 = statement.executeUpdate( "UPDATE students SET level = " + level + "WHERE matric_no = \"" + matricNumber + "\"" );

        if(query2 > 0) out.println("Done");

        start();

    }

}
