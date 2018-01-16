/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;

/**
 *
 * @author a_pramanik
 */
public interface TableUpdateDao {
    void updatePointsTable(String teamName, int g1, int g2);
    void updateFairPlayTable(String teamName, int reds, int yellow);
    void updateFixture(String t1, String t2, int g1, int g2);
}
