package az.charming.repository;

import az.charming.connect.MySQLConnect;
import az.charming.entity.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public void insert(StudentEntity student) {
        try (Connection connection = MySQLConnect.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into student(name,surname,age,scholarship)" +
                            " values (?,?,?,?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setBigDecimal(4, student.getScholarship());

            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void update(StudentEntity student) {
        try (Connection connection = MySQLConnect.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update student" +
                            " (set name=?,surname=?,age=?,scholarship=?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setBigDecimal(4, student.getScholarship());

            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void delete(Integer id) {
        try (Connection connection = MySQLConnect.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from student where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<StudentEntity> getAll() {
        List<StudentEntity> list = new ArrayList<>();
        try (Connection connection = MySQLConnect.connect()) {
            ResultSet rs = connection.prepareStatement("select * from student").executeQuery();
            while (rs.next()) list.add(fromResultSet(rs));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private StudentEntity fromResultSet(ResultSet rs) throws Exception {
        return new StudentEntity()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setAge(rs.getInt("age"))
                .setScholarship(rs.getBigDecimal("scholarship"));

    }

    public StudentEntity findById(int id) {
        try (Connection connection = MySQLConnect.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return fromResultSet(rs);
        } catch (Exception ex) {

        }

    }

}