/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;
import java.util.*;

/**
 *
 * @author a_pramanik
 */
public interface ClearTablesDao {
    void clearPointsTable();
    void clearFixture();
    void clearScorersTable();
    void clearFairPlayTable();
    void initializePlayersToTeams();
    void initializePointsTable(ArrayList<String> ss);
    void intitializeFairPlayTable(ArrayList<String> ss);
    
}
