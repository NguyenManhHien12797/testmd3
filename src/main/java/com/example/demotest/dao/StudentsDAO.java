package com.example.demotest.dao;

import com.example.demotest.config.ConnectionDB;
import com.example.demotest.model.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements IManagerDAO<Students>{
    public static final String SEARCH_BY_NAME = "SELECT * FROM student1 WHERE name LIKE ?;";
    public static final String SELECT_BY_ID = "SELECT * FROM student1 WHERE id=?;";
    Connection connection = ConnectionDB.getConnect();
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM student1;";

    @Override
    public List<Students> selectAll() {
        List<Students> studentsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                String classroom = resultSet.getString("classroom");
                Students students = new Students(name, dateOfBirth, address, phoneNumber,email,classroom);
                studentsList.add(students);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentsList;

    }


    @Override
    public void create(Students o) {
        try ( PreparedStatement statement = connection.prepareStatement(
                "  INSERT INTO student1 (name,dateOfBirth,address,phoneNumber,email,classroom) VALUES (?,?,?,?,?,?);");
        ) {
            statement.setString(1, o.getName());
            statement.setString(2, String.valueOf(o.getDateOfBirth()));
            statement.setString(3, o.getAddress());
            statement.setString(4, o.getPhoneNumber());
            statement.setString(5, o.getEmail());
            statement.setString(6, o.getClassroom());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Students select(int id_num) {
        Students students = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);) {
            statement.setInt(1, id_num);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                String classroom = resultSet.getString("classroom");
                students = new Students(name, dateOfBirth, address, phoneNumber,email,classroom);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(int id, Students students) {

    }


    @Override
    public void update(Students o) {
        try(PreparedStatement statement =connection.prepareStatement("UPDATE student1 SET categoryId = ?,brandId=?,image=?,name= ?, oldPrice=?, newPrice= ? WHERE id=?;")){
            statement.setString(1,o.getName());
            statement.setString(2, String.valueOf(o.getDateOfBirth()));
            statement.setString(3,o.getAddress());
            statement.setString(4,o.getPhoneNumber());
            statement.setString(5,o.getEmail());
            statement.setString(6,o.getClassroom());
            statement.setInt(7,o.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Students> search(String search) {
        return null;
    }

    @Override
    public void delete(int id) {
        try( PreparedStatement statement = connection.prepareStatement("DELETE FROM student1 WHERE id =?;")){
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
