/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence;

import Model.Profile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Mumin
 */
public abstract class dbessentials extends Profile {
    private String query;
    private ResultSet rs;
    private Statement smt;
    private PreparedStatement psmt;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Statement getSmt() {
        return smt;
    }

    public void setSmt(Statement smt) {
        this.smt = smt;
    }

    public PreparedStatement getPsmt() {
        return psmt;
    }

    public void setPsmt(PreparedStatement psmt) {
        this.psmt = psmt;
    }
    
    
}
