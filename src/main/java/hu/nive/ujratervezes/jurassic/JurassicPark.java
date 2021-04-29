package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> result = new ArrayList<>();
        String query = "SELECT breed FROM dinosaur GROUP BY breed HAVING actual > expected ORDER BY breed";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
