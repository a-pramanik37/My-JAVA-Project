/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;

import java.util.*;
import project.ConnectionManager;
import java.sql.*;
import project.player;

/**
 *
 * @author a_pramanik
 */
public class PlayerDaoImp implements PlayerDao {

    @Override
    public ArrayList<String> getPlayers(String teamName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        ArrayList<String> players = new ArrayList<>();
        
        
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String ss = "SELECT * from "+teamName;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(ss);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                players.add(resultSet.getString(1));
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        
        return players;
    }

    @Override
    public void changePlayer(String teamName, String oldPlayer, String newPlayer, String jersey_No) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionManager.getConnection();
            String ss = "UPDATE "+teamName+" SET Name=?, Goals_Scored=? Where jersey_No=?";
            //System.out.println(ss);
            preparedStatement = connection.prepareStatement(ss);
            preparedStatement.setString(1, newPlayer);
            preparedStatement.setInt(2, 0);
            preparedStatement.setString(3, jersey_No);
            preparedStatement.executeUpdate();
            System.out.print("**** Player change successfull!!!\n\n");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    @Override
    public ArrayList<String> getJerseyNo(String teamName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ArrayList<String> jrsy = new ArrayList<>();
       
        
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String ss = "Select * from "+teamName;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(ss);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                jrsy.add(resultSet.getString(2));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        
        return jrsy;
    
    }

    @Override
    public boolean isPresentInScorersList(String nm, String jrsy) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean ans=false;
        try{
            connection = ConnectionManager.getConnection();
            
            preparedStatement = connection.prepareStatement("SELECT * FROM topscorerstable");
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if(nm.equals(resultSet.getString(1))  &&  jrsy.equals(resultSet.getString(2))){
                    ans=true;
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
    public player getPlayerInfoFromScorersList(String nm, String jrsy) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        player plr = new player();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM topscorerstable");
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                //System.out.print("****  bugbugbug\n");
                if(nm.equals(resultSet.getString(1))  &&  jrsy.equals(resultSet.getString(2))){
                    plr.setName(nm);
                    plr.setJerseyNo(jrsy);
                    plr.setGoalsScored(resultSet.getInt(4));
                    
                    //System.out.print("*******   "+nm+" "+jrsy+" "+resultSet.getInt(4)+"\n");
                    
                    break;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return plr;
    }

    @Override
    public void updateScorersList(String teamName, String nm, String jrsy, int goals) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        if(isPresentInScorersList(nm, jrsy)==true){
            //System.out.print("True hoise!\n");
            player plr = new player();
            plr = getPlayerInfoFromScorersList(nm, jrsy);
           //System.out.print("prev = "+plr.getGoalsScored()+"\n");
            plr.setGoalsScored(plr.getGoalsScored()+goals);
            //System.out.print("now = "+plr.getGoalsScored()+"\n");
            try{
                connection = ConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement("UPDATE topscorerstable SET Goals=? WHERE Name=? AND Jersey_No=?");
                preparedStatement.setInt(1, plr.getGoalsScored());
                preparedStatement.setString(2, plr.getName());
                preparedStatement.setString(3, plr.getJerseyNo());
                preparedStatement.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ConnectionManager.close(connection);
                ConnectionManager.close(preparedStatement);
            }
            
            
        }
        
        else{
            
             try{
                connection = ConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement("INSERT into topscorerstable values(?,?,?,?)");
                preparedStatement.setString(1, nm);
                preparedStatement.setString(2, jrsy);
                preparedStatement.setString(3, teamName);
                preparedStatement.setInt(4, goals);
                
                preparedStatement.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ConnectionManager.close(connection);
                ConnectionManager.close(preparedStatement);
            }
            
        }
        
    }

    @Override
    public player getPlayerInfoFromTeam(String teamName, String nm, String jrsy) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         player plr = new player();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionManager.getConnection();
            String ss = "SELECT * FROM "+teamName;
            preparedStatement = connection.prepareStatement(ss);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if(nm.equals(resultSet.getString(1))  &&  jrsy.equals(resultSet.getString(2))){
                    plr.setName(nm);
                    plr.setJerseyNo(jrsy);
                    plr.setGoalsScored(resultSet.getInt(4));
                    break;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return plr;
    
    }

    @Override
    public void goalsUpdateOfPlayer(String teamName, String nm, String jrsy, int goals) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        player plr = new player();
            plr = getPlayerInfoFromTeam(teamName,nm, jrsy);
           
            plr.setGoalsScored(plr.getGoalsScored()+goals);
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try{
                connection = ConnectionManager.getConnection();
                String ss = "UPDATE "+teamName+" SET Goals_Scored=? WHERE Name=? AND Jersey_No=?";
                preparedStatement = connection.prepareStatement(ss);
                preparedStatement.setInt(1, plr.getGoalsScored());
                preparedStatement.setString(2, plr.getName());
                preparedStatement.setString(3, plr.getJerseyNo());
                preparedStatement.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ConnectionManager.close(connection);
                ConnectionManager.close(preparedStatement);
            }
    
    }

    
    
}
