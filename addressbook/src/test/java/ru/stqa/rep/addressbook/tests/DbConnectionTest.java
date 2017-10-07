package ru.stqa.rep.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.rep.addressbook.model.GroupData;
import ru.stqa.rep.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void  testDbConnection (){

    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list ");

      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));

      }
      System.out.println("Данные по группам " + groups);
        rs.close();
        st.close();
        conn.close();



    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

  }
}
