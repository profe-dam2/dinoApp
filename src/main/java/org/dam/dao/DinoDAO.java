package org.dam.dao;

import org.dam.database.SQLDatabaseManager;
import org.dam.models.DinoModels;
import org.dam.models.FeedingModel;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DinoDAO {
    private Connection connection;

    private boolean initDBConnection(){
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }

    private boolean closeDBConnection(){
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }

    public ArrayList<FeedingModel> getFeedings() throws SQLException {
        ArrayList<FeedingModel> feedingList = new ArrayList<>();

        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la bse de datos");
        }

        try {
            String query = "SELECT * FROM alimentacion";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                FeedingModel feedingModel = new FeedingModel();
                feedingModel.setId(resultSet.getInt("id"));
                feedingModel.setName(resultSet.getString("alimentacion"));
                feedingList.add(feedingModel);
            }

        }catch (Exception e){
            throw new SQLException("Error al consultar los datos");
        }finally {
            closeDBConnection();
        }

        return feedingList;
    }

    public boolean createDino(DinoModels dino) throws SQLException {
        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la bse de datos");
        }

        try{
            String insertQuery = "INSERT INTO dinosaurios (nombre, volador, peso, fecha, ataque, alimentacion_id) " +
                                 "VALUES (?,?,?,?,?,?)";
            PreparedStatement insertStatement =
                    connection.prepareStatement(insertQuery);
            insertStatement.setString(1, dino.getName());
            insertStatement.setBoolean(2, dino.isFlying());
            insertStatement.setInt(3,(int)dino.getWeigth());
            insertStatement.setDate(4, Date.valueOf(dino.getDate()));
            insertStatement.setString(5, dino.getAttack());
            insertStatement.setInt(6,dino.getFeeding_id());

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;

        }catch (PSQLException e){
            if(e.getSQLState().equals("23505")){
                throw new SQLException("El dino " +dino.getName() + " ya existe");
            }else{
                throw new SQLException("Error al crear el dino");
            }

        }finally{
            closeDBConnection();
        }
    }

    public ArrayList<DinoModels> getDinos(int limit, int offset) throws SQLException {
         ArrayList<DinoModels> dinoList = new ArrayList<>();
         if(!initDBConnection()){
             throw new SQLException("Error al conectar con la base de datos");
         }

         try {
             String query = "SELECT d.id, d.nombre, d.peso, d.fecha, d.ataque, d.volador, a.alimentacion\n" +
                            "FROM dinosaurios d\n" +
                            "INNER JOIN alimentacion a\n" +
                            "ON d.alimentacion_id = a.id\n" +
                            "LIMIT ? OFFSET ?;";

             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1, limit);
             preparedStatement.setInt(2, offset);
             ResultSet resultSet = preparedStatement.executeQuery();

             while(resultSet.next()){
                 DinoModels dinoModels = new DinoModels();
                 dinoModels.setId(resultSet.getInt("id"));
                 dinoModels.setName(resultSet.getString("nombre"));
                 dinoModels.setFlying(resultSet.getBoolean("volador"));
                 dinoModels.setWeigth(resultSet.getInt("peso"));
                 dinoModels.setAttack(resultSet.getString("ataque"));
                 dinoModels.setDate(resultSet.getDate("fecha").toLocalDate());
                 dinoModels.setFeeding(resultSet.getString("alimentacion"));
                 dinoList.add(dinoModels);
             }
         }catch (Exception e){
             System.err.println(e.getMessage());
             throw new SQLException("Error al consultar los dinos");
         }finally {
             closeDBConnection();
         }

         return dinoList;

    }

    public ArrayList<DinoModels> getDinoByID(int id) throws SQLException {
        ArrayList<DinoModels> dinoList = new ArrayList<>();
        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la base de datos");
        }

        try {
            String query = "SELECT d.id, d.nombre, d.peso, d.fecha, d.ataque, d.volador, a.alimentacion\n" +
                    "FROM dinosaurios d\n" +
                    "INNER JOIN alimentacion a\n" +
                    "ON d.alimentacion_id = a.id\n" +
                    "WHERE d.id = ?;";
           // String query = "SELECT * FROM dinosaurios WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DinoModels dinoModels = new DinoModels();
                dinoModels.setId(resultSet.getInt("id"));
                dinoModels.setName(resultSet.getString("nombre"));
                dinoModels.setFlying(resultSet.getBoolean("volador"));
                dinoModels.setWeigth(resultSet.getInt("peso"));
                dinoModels.setAttack(resultSet.getString("ataque"));
                dinoModels.setDate(resultSet.getDate("fecha").toLocalDate());
                dinoModels.setFeeding(resultSet.getString("alimentacion"));
                dinoList.add(dinoModels);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new SQLException("Error al consultar los dinos");
        }finally {
            closeDBConnection();
        }

        return dinoList;

    }

    public ArrayList<DinoModels> getDinoByNameAndFly(String name, boolean fly) throws SQLException {
        ArrayList<DinoModels> dinoList = new ArrayList<>();
        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la base de datos");
        }

        try {
            String query = "SELECT d.id, d.nombre, d.peso, d.fecha, d.ataque, d.volador, a.alimentacion\n" +
                           "FROM dinosaurios d\n" +
                           "INNER JOIN alimentacion a\n" +
                           "ON d.alimentacion_id = a.id\n" +
                           "WHERE LOWER(nombre) LIKE LOWER(?) AND volador = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%"+name+"%");
            preparedStatement.setBoolean(2, fly);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DinoModels dinoModels = new DinoModels();
                dinoModels.setId(resultSet.getInt("id"));
                dinoModels.setName(resultSet.getString("nombre"));
                dinoModels.setFlying(resultSet.getBoolean("volador"));
                dinoModels.setWeigth(resultSet.getInt("peso"));
                dinoModels.setAttack(resultSet.getString("ataque"));
                dinoModels.setDate(resultSet.getDate("fecha").toLocalDate());
                dinoModels.setFeeding(resultSet.getString("alimentacion"));
                dinoList.add(dinoModels);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new SQLException("Error al consultar los dinos");
        }finally {
            closeDBConnection();
        }

        return dinoList;

    }

    public ArrayList<DinoModels> getDinoByDates(LocalDate date1,
                                                LocalDate date2) throws SQLException {
        ArrayList<DinoModels> dinoList = new ArrayList<>();
        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la base de datos");
        }

        try {
            String query = "SELECT d.id, d.nombre, d.peso, d.fecha, d.ataque, d.volador, a.alimentacion\n" +
                           "FROM dinosaurios d\n" +
                           "INNER JOIN alimentacion a\n" +
                           "ON d.alimentacion_id = a.id\n" +
                           "WHERE fecha BETWEEN ? AND ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(date1));
            preparedStatement.setDate(2, Date.valueOf(date2));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DinoModels dinoModels = new DinoModels();
                dinoModels.setId(resultSet.getInt("id"));
                dinoModels.setName(resultSet.getString("nombre"));
                dinoModels.setFlying(resultSet.getBoolean("volador"));
                dinoModels.setWeigth(resultSet.getInt("peso"));
                dinoModels.setAttack(resultSet.getString("ataque"));
                dinoModels.setDate(resultSet.getDate("fecha").toLocalDate());
                dinoModels.setFeeding(resultSet.getString("alimentacion"));
                dinoList.add(dinoModels);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new SQLException("Error al consultar los dinos");
        }finally {
            closeDBConnection();
        }

        return dinoList;

    }

    public int getTotalElements() throws SQLException {
        if(!initDBConnection()){
            throw new SQLException("Error al conectar con la base de datos");
        }
        try{
            String query = "SELECT COUNT(*) AS total FROM dinosaurios;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new SQLException("Error al consultar los dinos");
        }finally {
            closeDBConnection();
        }
        return 0;
    }


}