/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;
import java.sql.*;
import java.util.*;
import project.ConnectionManager;

/**
 *
 * @author a_pramanik
 */
public class ClearTablesDaoImp implements ClearTablesDao{

    @Override
    public void clearPointsTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
     
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Delete From pointsTable");
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    @Override
    public void clearFixture() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Connection connection = null;
        PreparedStatement preparedStatement = null;
     
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Delete From fixture");
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    @Override
    public void clearScorersTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         Connection connection = null;
        PreparedStatement preparedStatement = null;
     
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Delete From TopScorersTable");
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    }

    @Override
    public void clearFairPlayTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         Connection connection = null;
        PreparedStatement preparedStatement = null;
     
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Delete From fairplaytable");
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    }

    @Override
    public void initializePlayersToTeams() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ArrayList<String> tt =null;
        TournamentDaoImp xx = new TournamentDaoImp();
        tt=xx.getTeams();
        
        /*for(int i=0; i<tt.size(); i++){
            System.out.println(tt.get(i));
        }*/
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            
           for(int i=0; i<tt.size(); i++){
            try{
            connection = ConnectionManager.getConnection();
            String ss = "UPDATE "+tt.get(i)+" SET Goals_Scored=?";
            //System.out.println(ss);
            preparedStatement = connection.prepareStatement(ss);
            preparedStatement.setInt(1, 0);
            preparedStatement.executeUpdate();
            }catch(Exception e){
                   e.printStackTrace();
                   }
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    }

 

   

    @Override
    public void initializePointsTable(ArrayList<String> ss) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        /*System.out.print("Pointstable er ta : \n");
        
        for(int i=0; i<ss.size(); i++){
            System.out.println(ss.get(i));
        }*/
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            for(int i=0; i<ss.size(); i++){
                try{
                    connection = ConnectionManager.getConnection();
                   
                    preparedStatement = connection.prepareStatement("INSERT into pointstable values(?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setString(1, ss.get(i));
                    preparedStatement.setInt(2, 0);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, 0);
                    preparedStatement.setInt(5, 0);
                    preparedStatement.setInt(6, 0);
                    preparedStatement.setInt(7, 0);
                    preparedStatement.setInt(8, 0);
                    preparedStatement.setInt(9, 0);
                    preparedStatement.executeUpdate();
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    ConnectionManager.close(connection);
                    ConnectionManager.close(preparedStatement);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    
    }

    @Override
    public void intitializeFairPlayTable(ArrayList<String> ss) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         /*System.out.print("fair table er ta : \n");
        
        for(int i=0; i<ss.size(); i++){
            System.out.println(ss.get(i));
        }*/
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            for(int i=0; i<ss.size(); i++){
                try{
                    connection = ConnectionManager.getConnection();
                   
                    preparedStatement = connection.prepareStatement("INSERT into fairplaytable values(?,?,?,?)");
                    preparedStatement.setString(1, ss.get(i));
                    preparedStatement.setInt(2, 0);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, 0);
                    preparedStatement.executeUpdate();
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    ConnectionManager.close(connection);
                    ConnectionManager.close(preparedStatement);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }
    
}
