package config;

import java.sql.*;
import java.util.*;

public class dbConnect {


    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:studentclubDB.db"); 
           
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
        return con;
    }

    
    public void addRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt, values);
            pstmt.executeUpdate();
            System.out.println("✅ Record added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding record: " + e.getMessage());
        }
    }

   
    public void viewRecords(String sqlQuery, String[] headers, String[] columns) {
        if (headers.length != columns.length) {
            System.out.println("⚠️ Mismatch between headers and columns.");
            return;
        }

        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--------------------------------------------------------------------------------");
            for (String h : headers) {
                System.out.printf("%-20s", h);
            }
            System.out.println("\n--------------------------------------------------------------------------------");

            while (rs.next()) {
                for (String col : columns) {
                    System.out.printf("%-20s", rs.getString(col));
                }
                System.out.println();
            }
            System.out.println("--------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving records: " + e.getMessage());
        }
    }

   
    public void updateRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt, values);
            int rows = pstmt.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Record updated successfully!");
            else
                System.out.println("⚠️ No matching record found.");

        } catch (SQLException e) {
            System.out.println("❌ Error updating record: " + e.getMessage());
        }
    }

   
    public void deleteRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt, values);
            int rows = pstmt.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Record deleted successfully!");
            else
                System.out.println("⚠️ No record found to delete.");

        } catch (SQLException e) {
            System.out.println("❌ Error deleting record: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> fetchRecords(String sqlQuery, Object... values) {
        List<Map<String, Object>> records = new ArrayList<>();

        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            setParameters(pstmt, values);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                records.add(row);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching records: " + e.getMessage());
        }

        return records;
    }

    private void setParameters(PreparedStatement pstmt, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            Object val = values[i];
            if (val instanceof Integer) pstmt.setInt(i + 1, (Integer) val);
            else if (val instanceof Double) pstmt.setDouble(i + 1, (Double) val);
            else if (val instanceof Float) pstmt.setFloat(i + 1, (Float) val);
            else if (val instanceof Long) pstmt.setLong(i + 1, (Long) val);
            else if (val instanceof Boolean) pstmt.setBoolean(i + 1, (Boolean) val);
            else if (val instanceof java.util.Date)
                pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) val).getTime()));
            else if (val instanceof java.sql.Date) pstmt.setDate(i + 1, (java.sql.Date) val);
            else if (val instanceof java.sql.Timestamp) pstmt.setTimestamp(i + 1, (java.sql.Timestamp) val);
            else pstmt.setString(i + 1, val != null ? val.toString() : null);
        }
    }
}
