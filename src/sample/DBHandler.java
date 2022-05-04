package sample;

import java.sql.*;

public class DBHandler{
    Connection dbConnection;
    Statement statement;
    ResultSet resultSet;

    //Соединение с БД
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://127.0.0.1:3306/comission";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "root");
        return dbConnection;
    }

    public void addStudent(String fullname, String birthDate, String fullAddress, String phone, String birthPlace,
                           String passSeries, String passNumber, String issuedate, String issuer, float mark,
                           String edLvl, String specialization, String prof_name, String birthSeries, String birthNumbrt, String cert) {
        String request = "INSERT INTO entrant_info(fullname,birthdate,address)VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, birthDate);
            preparedStatement.setString(3, fullAddress);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            String[] data = fullname.split(" ");
            request = "INSERT INTO personal_data(lastname,firstname,patronymic,birthdate,birthplace,passport_series," +
                    "passport_number,issuer,issue_date,birth_certificate_series,birth_certificate_number)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, data[0]);
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, birthDate);
            preparedStatement.setString(5, birthPlace);
            preparedStatement.setString(6, passSeries);
            preparedStatement.setString(7, passNumber);
            preparedStatement.setString(8, issuer);
            preparedStatement.setString(9, issuedate);
            preparedStatement.setString(10, birthSeries);
            preparedStatement.setString(11, birthNumbrt);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try{
            request = "INSERT INTO education(education_lvl,certificate_number,certificate_mark,profession_code,profession_name)VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, edLvl);
            preparedStatement.setString(2, cert);
            preparedStatement.setFloat(3, mark);
            preparedStatement.setString(4, specialization);
            preparedStatement.setString(5, prof_name);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void add_parentInfo(){


    }

}
