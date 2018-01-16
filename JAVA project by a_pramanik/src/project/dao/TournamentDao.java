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
public interface TournamentDao {
    
    boolean isStarted();
    //void startTournament();
    ArrayList<String> getTeams();
    ArrayList<String> getPlayingTeams();
    int gamesHeld();
    int totalGames();
    int totalTeams();
}
