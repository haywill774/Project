import java.sql.*;
import java.util.Scanner;

public class Project{
    static PreparedStatement preparedStatement;

    static void createTable()  {
        try( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/october","root","ijumae123");
            Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS Project_one(name text, email text, age integer, location text, designation text )");
             preparedStatement = connection.prepareStatement("INSERT INTO Project_one (name, email, age, location) " +
                    "VALUES(?, ?,?,?)");
            
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    static void populateTable(){

        int count =0;

        try(Scanner scanner = new Scanner(System.in); Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/october","root","ijumae123");
            Statement statement = connection.createStatement()){
            preparedStatement = connection.prepareStatement("INSERT INTO Project_one (name, email, age, location) " +
                    "VALUES(?, ?,?,?)");

            for (int i=0; i<10; i++) {
                String name, email, location;

                    System.out.println("please enter your Name: ");
                    name = scanner.nextLine();
                    System.out.println("please enter your Email: ");
                    email = scanner.nextLine();
                    System.out.println("please enter your Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("please enter your Location: ");
                    location = scanner.nextLine();

                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setInt(3, age);
                    preparedStatement.setString(4, location);
                    preparedStatement.execute();
                count++;
            }

//            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Project_one WHERE name=?");
//            System.out.println("please enter your name: ");
//            String name = scanner.nextLine();
//            deleteStatement.setString(1,name);
//            deleteStatement.execute();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Project_one");
           while (resultSet.next()) {

               String names = resultSet.getString("name");
               String emails = resultSet.getString("email");
               int ages = resultSet.getInt("age");
               String locations = resultSet.getString("location");
               System.out.println(names + " " + emails + " " + locations + " " + ages);
           }


//            PreparedStatement updateStatement = connection.prepareStatement("UPDATE october.Project_one SET designation = \"delta\" WHERE location = ?");
//            System.out.println("please enter location: ");
//            String location = scanner.nextLine();
//            updateStatement.setString(1,location);
//            updateStatement.execute();

//            PreparedStatement populateStatement = connection.prepareStatement("ALTER TABLE october.Project_one \n" +
//                    "ADD COLUMN designation TEXT NULL AFTER location");
//            populateStatement.execute();

//            PreparedStatement populateStatement = connection.prepareStatement("ALTER TABLE october.Project_one \n" +
//                    "DROP COLUMN designation ");
//            populateStatement.execute();



        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        createTable();
        populateTable();
    }


}
