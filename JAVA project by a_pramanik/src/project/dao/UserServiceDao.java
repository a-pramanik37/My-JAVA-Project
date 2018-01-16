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
public interface UserServiceDao {
    void showFixture();
    void showPointsTable();
    void showTopScorersTable();
    void showFairPlayTable();
    void userSignUp(String email, String pass);
    boolean hasAccount(String email, String pass);
    boolean isValidEmail(String email);
    void changePassword(String email, String oldPass, String newPass);
    
}
