/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;
import java.sql.*;
import java.util.*;
import project.ConnectionManager;
import project.dao.*;

/**
 *
 * @author a_pramanik
 */
public class TournamentDaoImp implements TournamentDao{
    
    @Override
    public boolean isStarted() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        int flg=0;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("select * from fixture");
            resultSet = preparedStatement.executeQuery();
            
            
            if(resultSet.next())flg=1;
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    if(flg==1)return true;
    return false;
    }

    
    

    

    @Override
    public ArrayList<String> getTeams() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<String> tt = new ArrayList<String>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from teamlist");
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                tt.add(resultSet.getString(2));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return tt;
    }

    @Override
    public int gamesHeld() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        int ans=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from fixture");
            resultSet = preparedStatement.executeQuery();
            
            
            while(resultSet.next()){
                if(resultSet.getInt(6)==1){
                    ans++;
                }
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return ans;
         
    }

    @Override
    public int totalGames() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        int x = totalTeams();
        return (x*(x-1))/2;
    }

    @Override
    public int totalTeams() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;
         int ans=0;
         try{
             connection = ConnectionManager.getConnection();
             preparedStatement = connection.prepareStatement("Select * from pointsTable");
             resultSet = preparedStatement.executeQuery();
             
             while(resultSet.next()){
                 ans++;
             }
             
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             ConnectionManager.close(connection);
             ConnectionManager.close(preparedStatement);
         }
         
         return ans;
    }

    @Override
    public ArrayList<String> getPlayingTeams() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
     ArrayList<String> tt = new ArrayList<String>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from pointsTable");
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                tt.add(resultSet.getString(1));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return tt;
        
    }
    
}
