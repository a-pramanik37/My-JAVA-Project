/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;
import java.util.*;
import project.player;

/**
 *
 * @author a_pramanik
 */
public interface PlayerDao {
    ArrayList<String> getPlayers(String teamName);
    ArrayList<String> getJerseyNo(String teamName);
    void changePlayer(String teamName,String oldPlayer, String newPlayer, String jerseyNo);
    boolean isPresentInScorersList(String nm, String jrsy);
    player getPlayerInfoFromScorersList(String nm, String jrsy);
    player getPlayerInfoFromTeam(String teamName, String nm, String jrsy);
    void updateScorersList(String teamName, String nm, String jrsy, int goals);
    void goalsUpdateOfPlayer(String teamName, String nm, String jrsy, int goals);
}
