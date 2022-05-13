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
        String request = "UPDATE ";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, birthDate);
            preparedStatement.setString(3, fullAddress);
            preparedStatement.setString(4, phone);
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
    public void add_parentInfo(String childs, String motherName, String motherAddress, String motherPhone,
                               String motherWork, String fatherName, String fatherAddress, String fatherPhone,
                               String fatherWork, String status, String idStudent){
        String request = "INSERT INTO parents_info(id_entrant,fullfamily_status,children_amount,mother_fullname," +
                "mother_address,mother_phone,mother_work,father_fullname,father_address,father_phone,father_work)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setInt(1, Integer.parseInt(idStudent));
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, Integer.parseInt(childs));
            preparedStatement.setString(4, motherName);
            preparedStatement.setString(5, motherAddress);
            preparedStatement.setString(6, motherPhone);
            preparedStatement.setString(7, motherWork);
            preparedStatement.setString(8, fatherName);
            preparedStatement.setString(9, fatherAddress);
            preparedStatement.setString(10, fatherPhone);
            preparedStatement.setString(11, fatherWork);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet querry(String querry) {
        try{
        Statement statement = getDbConnection().createStatement();
        resultSet = statement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
    public void delete(String querry) {
        try{
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(querry);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateStudent(int id, String fullname, String birthDate, String address, String phone, String birthPlace,
                              String passSeries, String passNumber, String issuedate, String issuer, float mark,
                              String edLvl, String specialization, String prof_name, String birthSeries, String birthNumber, String cert) {
        String request = "UPDATE entrant_info SET " +
                "fullname = '"+ fullname +"'," +
                "birthdate = '" + birthDate+"'," +
                "address = '" + address +"',"+
                "phone = '"+phone+"' " +
                "WHERE id_entrant = '"+id+"'";
        try {
            statement = getDbConnection().createStatement();
            statement.executeUpdate(request);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request = "UPDATE education SET " +
                "education_lvl = '"+ edLvl+"'," +
                "certificate_number = '"+cert+"'," +
                "certificate_mark = '"+mark+"'," +
                "profession_code = '"+specialization+"'," +
                "profession_name = '"+prof_name+"' " +
                "WHERE id_entrant = '"+id+"'";
        try {
            statement = getDbConnection().createStatement();
            statement.executeUpdate(request);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String[] name = fullname.split(" ");
        request = "UPDETE personal_data SET " +
                "lastname = '"+ name[0] +"'," +
                "firstname = '"+name[1]+"'," +
                "patronymic = '"+name[2]+"'," +
                "birthdate = '"+birthDate+"'," +
                "birthplace = '"+birthPlace+"', " +
                "passport_series = '"+passSeries+"', " +
                "passport_number = '"+passNumber+"', " +
                "issuer = '"+issuer+"', " +
                "issue_date = '"+issuedate+"', " +
                "birth_certificate_series = '"+birthSeries+"', " +
                "birth_certificate_number = '"+birthNumber+"' " +
                "WHERE id_entrant = '"+id+"'";
    }

    public void updateParentInfo(String childs, String motherName, String motherAddress, String motherPhone,
                                 String motherWork, String fatherName, String fatherAddress, String fatherPhone,
                                 String fatherWork, String status, int idStudent) {
        String request = "UPDATE parents_info SET " +
                "children_amount = '"+ childs +"'," +
                "mother_fullname = '" + motherName +"'," +
                "mother_address = '" + motherAddress +"',"+
                "mother_phone = '"+motherPhone+"', " +
                "mother_work = '"+motherWork+"', " +
                "father_fullname = '" + fatherName +"'," +
                "father_address = '" + fatherAddress +"',"+
                "father_phone = '"+fatherPhone+"', " +
                "father_work = '"+fatherWork+"', " +
                "fullfamily_status = '"+status+"' "+
                "WHERE id_entrant = '"+idStudent+"'";
        try {
            statement = getDbConnection().createStatement();
            statement.executeUpdate(request);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
