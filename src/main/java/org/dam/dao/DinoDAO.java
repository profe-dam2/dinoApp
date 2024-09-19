package org.dam.dao;

import org.dam.database.SQLDatabaseManager;
import org.dam.models.DinoModels;
import org.dam.models.FeedingModel;
import org.postgresql.util.PSQLException;

import java.sql.*;
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

}