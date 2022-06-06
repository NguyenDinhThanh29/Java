import java.sql.*;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Cinema", "root", "");
                PreparedStatement pInsert = conn.prepareStatement(
                        "insert into film value (?, ?, ?, ?, ?)");
                PreparedStatement pDelete = conn.prepareStatement(
                        "delete from film where Name = ?");
                PreparedStatement pUpdate = conn.prepareStatement(
                        "update film set Id = ? where Name = ?");
                PreparedStatement pSelect = conn.prepareStatement(
                        "select * from film where Name = ?");
        ) {
            conn.setAutoCommit(false);
            try {
                int A;
                int Type = 0;
                int Id;
                String Name = null;
                String ShowTime;
                String Author;
                String Time;
                Scanner i = new Scanner(System.in);
                do {
                    System.out.println("What do you want to do?");
                    System.out.println("1.More\t 2.Fix\t 3.Delete\t 4.Search");
                    A = i.nextInt();
                    if (A == 1) {
                        System.out.println("-- More movies --");
                        i.nextLine();
                        System.out.println("\n Nhap ID phim: ");
                        Id = i.nextInt();
                        System.out.println("Nhap ten phim: ");
                        Name = i.nextLine();
                        System.out.println("Nhap thoi gian chieu: ");
                        ShowTime = i.nextLine();
                        System.out.println("Nhap ten tac gia phim: ");
                        Author = i.nextLine();
                        System.out.println("Nhap thoi luong bo phim chieu: ");
                        Time = i.nextLine();
                        pInsert.setInt(1, Id);
                        pInsert.setString(2, Name);
                        pInsert.setString(3, ShowTime);
                        pInsert.setString(4, Author);
                        pInsert.setString(5, Time);
                        pInsert.executeUpdate();
                        System.out.println("Successful!");
                    } else if (A == 2) {
                        System.out.println("-- Edit Information --");
                        i.nextLine();
                        System.out.println("Nhap ID phim can sua: ");
                        Id = i.nextInt();
                        System.out.println("\nThong tin ban muon sua o trong phim la gi: ");
                        System.out.println("1.Ten Phim\t 2.Thoi gian chieu\t 3.Ten tac gia");
                        if (Type == 1) {
                            System.out.println("--Sua Ten Phim --");
                            i.nextLine();
                            System.out.println("Nhap ten moi cua phim: ");
                            Name = i.nextLine();
                            pUpdate.setInt(1, Id);
                            pUpdate.setString(2, Name);
                        }
                        if (Type == 2) {
                            System.out.println("--Sua Thoi Gian Chieu Phim --");
                            i.nextLine();
                            System.out.println("Nhap thoi gian moi cua phim: ");
                            ShowTime = i.nextLine();
                            pUpdate.setString(1, ShowTime);
                            pUpdate.setString(2, Name);
                        }
                        if (Type == 3) {
                            System.out.println("--Sua Ten Tac Gia Cua Phim --");
                            i.nextLine();
                            System.out.println("Nhap ten tac gia moi cua phim: ");
                            Author = i.nextLine();
                            pUpdate.setString(1, Name);
                            pUpdate.setString(2, Author);
                        }
                    } else if (A == 3) {
                        System.out.println("-- Delete Movies --");
                        i.nextLine();
                        System.out.println("\nPhim ban muon xoa: ");
                        Name = i.nextLine();
                        pDelete.setString(1, Name);
                        pDelete.executeUpdate();
                    } else if (A == 4) {
                        System.out.println("-- Search Movies --");
                        i.nextLine();
                        System.out.println("\nPhim ban muon tim: ");
                        Name = i.nextLine();
                        pSelect.setString(1, Name);
                        ResultSet rset = pSelect.executeQuery();
                        while (rset.next()) {
                            System.out.println(rset.getInt("ID") + ", "
                                    + rset.getString("Name") + ", "
                                    + rset.getString("ShowTime") + ","
                                    + rset.getString("Author") + ", "
                                    + rset.getInt("Time"));
                        }
                    }
                    conn.commit();
                }
                while (A != 1 || A != 2 || A != 3 || A != 4);
            } catch (SQLException e) {
                System.out.println("Sai Thong Tin");
                e.printStackTrace();
            }
        }
    }
}
