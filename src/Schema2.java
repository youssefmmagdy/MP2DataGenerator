import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Schema2 {

//	CREATE TABLE Employee(Fname CHAR(20), Minit CHAR(10), Lname CHAR(20), ssn INT PRIMARY KEY, Bdate date, address CHAR(20), sex CHARACTER(1), salary INT, Super_snn INT REFERENCES Employee(ssn), dno INT);

    public static long insertEmployee(String Fname, String Minit,String Lname,int ssn, Date Bdate, String address, String sex, int salary, int superSSN, int dno, Connection conn) {
        String SQL = "INSERT INTO Employee(Fname,Minit,Lname,ssn,Bdate,address,sex,salary,Super_snn,dno) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, Fname);
            pstmt.setString(2, Minit);
            pstmt.setString(3, Lname);
            pstmt.setInt(4, ssn);
            pstmt.setDate(5, Bdate);
            pstmt.setString(6, address);
            pstmt.setString(7, sex);
            pstmt.setInt(8, salary);
            pstmt.setInt(9, superSSN);
            pstmt.setInt(10, dno);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(4);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }
//	 CREATE TABLE Department(Dname CHAR(20), Dnumber INT PRIMARY KEY, Mgr_snn int REFERENCES employee, Mgr_start_date date );

    public static long insertDepartment(String Dname, int Dnumber,int MgrSSN, Date startDate, Connection conn) {
        String SQL = "INSERT INTO Department(Dname,Dnumber,Mgr_snn,Mgr_start_date) "
                + "VALUES(?,?,?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, Dname);
            pstmt.setInt(2, Dnumber);
            pstmt.setInt(3, MgrSSN);
            pstmt.setDate(4, startDate);


            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(2);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }
    //	 CREATE TABLE Dept_locations(Dnumber integer REFERENCES Department, Dlocation CHAR(20), PRIMARY KEY(Dnumber,Dlocation));
    public static long insertDeptLocations(int Dnumber,String Dlocation, Connection conn) {
        String SQL = "INSERT INTO Dept_locations(Dnumber,Dlocation) "
                + "VALUES(?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(2, Dlocation);
            pstmt.setInt(1, Dnumber);



            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(1);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }

    //	 CREATE TABLE Project(Pname CHAR(20), Pnumber INT PRIMARY KEY, Plocation CHAR(50), Dnumber INT REFERENCES Department);
    public static long insertProject(String Pname, int Pnumber,String pLocation, int Dnumber, Connection conn) {
        String SQL = "INSERT INTO Project(Pname,Pnumber,Plocation,Dnumber) "
                + "VALUES(?,?,?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, Pname);
            pstmt.setInt(2, Pnumber);
            pstmt.setString(3, pLocation);
            pstmt.setInt(4, Dnumber);


            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(2);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }
//	 CREATE TABLE Works_on(Essn int REFERENCES Employee, Pno int REFERENCES Project, Hours int, PRIMARY KEY(Essn,Pno));

    public static long insertWorksOn(int Essn,int pNo, int hours, Connection conn) {
        String SQL = "INSERT INTO Works_on(Essn,Pno,Hours) "
                + "VALUES(?,?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(2, pNo);
            pstmt.setInt(1, Essn);
            pstmt.setInt(3, hours);



            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(1);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }
    //	 CREATE TABLE Dependent(Essn INT REFERENCES Employee, Dependent_name CHAR(20), sex CHARACTER(1), Bdate date, Relationship CHAR(20), PRIMARY KEY(Essn, Dependent_name));
    public static long insertDependent(int Essn, String dependentName,String sex, Date Bdate,String relationship, Connection conn) {
        String SQL = "INSERT INTO Dependent(Essn,Dependent_name,sex,Bdate,Relationship) "
                + "VALUES(?,?,?,?,?);";

        long id = 0;
        try{
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, Essn);
            pstmt.setString(2, dependentName);
            pstmt.setString(3, sex);
            pstmt.setDate(4, Bdate);
            pstmt.setString(5, relationship);


            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of affected rows is " + affectedRows);
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                    if (rs.next()) {
                        id = rs.getLong(1);
                        pstmt.close();
                        conn.commit();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }

    /////////////////////////////////////////////// Data Population Methods //////////////////////////////////////////////////////////////
    @SuppressWarnings("deprecation")
    public static void populateEmployee(Connection conn) {
        int salary = 40000 , empNumber = 1 , dNumber = 5;
        if (insertEmployee("Name" + 1, "M" + 1,"employee" + empNumber, 1, new Date(22,1,1999), "address" + 1
                ,"M",salary,1,5, conn) == 0) {
            System.err.println("insertion of record " + 1 + " failed");

        } else
            System.out.println("insertion was successful");
        int[] arr = new int[100];
        for(int i = 0; i < 100; i++){
            arr[i] = i+1;
            if(arr[i] == 5)
                arr[i] = 110;
        }
        for (int i = 2; i <= 16000; i++) {
            String result = "M";
            if (i > 600)
                result = "F";
            if(i > 600 && i <= 1200) {
                salary = 40010;
            }else salary = 40000;
//                dNumber = (i%150)+1;
//                if(dNumber == 5)
//                    dNumber = 6;
            if(i < 800)
                dNumber = arr[(i%100)];
            else dNumber = 7;
            if(i > 600)
                empNumber = i;
            if (insertEmployee("Name" + i, "M" + i,"employee" + empNumber, i, new Date(22,1,1999), "address" + i
                    ,result,salary,i,dNumber, conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
        }
//		 int i = 16001;
//			insertEmployee("Employee" + i, "M" + i,"Employee" + i, i, new Date(22,1,1999), "address" + i ,"M",i,i,1, conn);
//          i++;
//			insertEmployee("Employee" + i, "M" + i,"Employee" + i, i, new Date(22,1,1999), "address" + i ,"M",i,i,1, conn);

    }

    @SuppressWarnings("deprecation")
    public static void populateDepartment(Connection conn) {
        int dnumber = 5;
        int c = 0 , mgr = 700;
        for (int i = 1; i <= 150; i++) {
//             if(i > 100)
            dnumber = i;
            if(c < 7){
                mgr = 700;
            }else mgr = i+700;
            if (insertDepartment("Department" + i, dnumber,mgr,new Date(1,1,1990), conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
            c++;
        }
    }
    public static void populateDeptLocations(Connection conn) {
        for (int i = 1; i <= 150; i++) {
            if (insertDeptLocations(i, "Location" + i, conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
        }
    }

    public static void populateProject(Connection conn) {

        for (int i = 1; i <= 9200; i++) {
            if (insertProject("Project" + i, i,"Location1" + i,(i%150)+1, conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
        }
    }
    public static void populateWorksOn(Connection conn) {
        for (int i = 1; i < 10000; i++) {
            if (insertWorksOn(i, (i%9200)+1, i, conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
        }
    }
    @SuppressWarnings("deprecation")
    public static void populateDependent(Connection conn) {
        for (int i = 1; i <= 600; i++) {
            String result = "M";


            if (insertDependent(i, "Name" + i, result,new Date(1,1,1999),"child", conn) == 0) {
                System.err.println("insertion of record " + i + " failed");
                break;
            } else
                System.out.println("insertion was successful");
        }
    }

    public static void insertSchema2(Connection connection) {
        populateEmployee(connection);
        populateDepartment(connection);
        populateDeptLocations(connection);
        populateProject(connection);
        populateWorksOn(connection);
        populateDependent(connection);
    }

    public static void main(String[] argv) {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/schema2", "postgres",
                    "YOUR PASSWORD");
            insertSchema2(connection);


        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
