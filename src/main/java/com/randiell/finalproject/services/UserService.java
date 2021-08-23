/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.services;
import com.randiell.finalproject.dbadapters.DatabaseAdapter;
import com.randiell.finalproject.models.User;
import java.util.Collection;

public class UserService {
    public static final String COLLECTION_NAME = "users";

    public static void createUser(User user){
        DatabaseAdapter.instance.addItem(COLLECTION_NAME, user);
    }
    
    public static Collection<User> getAllUsers() {
        return DatabaseAdapter.instance.<User>getAllItems(COLLECTION_NAME, User.class);
    }
    
    public static void deleteUser(User user) {
        DatabaseAdapter.instance.deleteItem(COLLECTION_NAME, user);
    }

    public static void updateUser(User user) {
        DatabaseAdapter.instance.updateItem(COLLECTION_NAME, user);
    }


    public static User findUserByLogin(String username, String password) {
        for (User user : UserService.getAllUsers()) {
            if (user.getUsername().equals(username) && user.comparePassword(password)) {
                return user;
            }
        }

        return null;
//        throw new Error("Unsupported Database");
    }
    
    public static boolean usernameExists(String username) {
//        if (DatabaseAdapter.instance instanceof MemoryDatabaseAdapter) {
        for (User user : UserService.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;

//        throw new Error("Unsupported Database");
    }

    public static void seed() {
        Object[][] users = new Object[][] {
            new Object[] { "Randiell", "Siri", "20175764@itla.edu.do", "(829) 743-2724", "randiell", "welc0me" },
            new Object[] { "Trystan", "Sawayn", "Shanelle_Greenfelder7@yahoo.com", "784-512-4542", "Brook37", "p2tocBm_I5gslmm" },
            new Object[] { "Boyd", "Klocko", "Yadira.Stiedemann@hotmail.com", "1-474-376-1677 x744", "Titus80", "jvtR9LINhoeapFM" },
            new Object[] { "Holly", "Satterfield", "Arno_Mann@hotmail.com", "(403) 584-9928 x9774", "Nayeli_Purdy94", "iYqj3xc1hYZ1BNa" },
            new Object[] { "Ervin", "Konopelski", "Rafael.Haag@yahoo.com", "(976) 519-9541", "Citlalli_Will", "WqB6WihlCpBZu46" },
            new Object[] { "Peggie", "Casper", "Kelvin.Orn90@hotmail.com", "906-869-6787 x6938", "Nicholas_Gleichner", "wxb0BpwGP1IiSzI" },
            new Object[] { "Patrick", "Nitzsche", "Wilfredo.Jast@yahoo.com", "558.438.0811 x4499", "Belle14", "bYbiubfJrVjEhN6" },
            new Object[] { "Ismael", "Torphy", "Josefa_Kertzmann94@yahoo.com", "796.701.3393 x16502", "Chesley.Effertz", "PTihFUyhPV1gefK" },
            new Object[] { "Kailey", "Kautzer", "Keira_Reichert82@yahoo.com", "(600) 363-0992", "Rasheed.Dare", "k8biRi2fN2ebW93" },
            new Object[] { "Nash", "Runte", "Osvaldo_Becker58@yahoo.com", "555-229-7896 x713", "Christop_Kovacek", "IAqROnwd670vjRg" }
        };

        for (Object[] object : users) {
            var user = new User();
            user.setFirstName((String)object[0]);
            user.setLastName((String)object[1]);
            user.setEmail((String)object[2]);
            user.setPhoneNumber((String)object[3]);
            user.setUsername((String)object[4]);
            user.setPassword((String)object[5]);
            DatabaseAdapter.instance.addItem(COLLECTION_NAME, user);
        }
    }
 }
