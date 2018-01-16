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
public interface AdminServiceDao {
    void inputMatchResult();
    void dismissCurrentTournament();
    void startTournament();
    void changePlayer();
    void seePlayers();
    ArrayList<String> getTeamsOfCurrentTournament();
    
}
