package org.example.exo.student.model;

import lombok.AllArgsConstructor;
import org.example.exo.student.model.tables.Student;

import java.sql.*;

@AllArgsConstructor
public class Requests {
    Connection connection;

    public boolean createStudent(String name, String surname, int classNbr,String diplomeDate) {

        try{
            Student newStudent = Student.builder()
                    .name(name)
                    .surname(surname)
                    .classNbr(classNbr)
                    .diplomeDate(diplomeDate)
                    .build();
            String newStudentQuery = "INSERT INTO student (name,surname,classNbr,diplomeDate) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(newStudentQuery);
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setString(2, newStudent.getSurname());
            preparedStatement.setInt(3, newStudent.getClassNbr());
            preparedStatement.setString(4, newStudent.getDiplomeDate());

            int nbrRows = preparedStatement.executeUpdate();

            if(nbrRows == 1){
                return true;
            }
            return false;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getStudents(Connection connection, int classId) throws SQLException{
        String request = "SELECT * FROM student ORDER BY id";
        if (classId > 0)
            request += " WHERE classNbr=" + classId;

        Statement statement = connection.createStatement();
        return statement.executeQuery(request);
    }

    public ResultSet deleteStudent(Connection connection, int studentID) throws SQLException {
        String request = "DELETE FROM student where id=" + studentID;

        Statement statement = connection.createStatement();
        return statement.executeQuery(request);
    }
}
