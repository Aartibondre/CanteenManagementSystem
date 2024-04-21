package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.Hexaware.CMS.Model.*;

public class OrderDb {
	static Scanner sc = new Scanner(System.in);
	static int i;
	static String url = "jdbc:mysql://localhost:3306/Aarti?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "root";
	static String password = "Password123";

	public static int insertDb(int foodId, String foodName, int foodPrice, int foodQuant, int foodTotal) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO orderfood VALUES(?,?,?,?,?)");
			stmt.setInt(1, foodId);
			stmt.setString(2, foodName);
			stmt.setInt(3, foodPrice);
			stmt.setInt(4, foodQuant);
			stmt.setInt(5, foodTotal);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	// fetchDB is menuProfileDb
	// to include vendorID.
	// fetchDb
	public static Menu[] showFoodMenu() {
		Menu m[] = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM MENU");
			ArrayList<Menu> list = new ArrayList<Menu>();
			while (rs.next()) {

				Menu mnu = new Menu(rs.getInt("Food_ID"), rs.getString("Food_Name"), rs.getInt("Food_Price"),
						rs.getInt("Vendor_id"));
				list.add(mnu);
			}
			m = new Menu[list.size()];
			m = list.toArray(m);

		} catch (Exception e) {
			System.out.println(e);
		}

		return m;
	}

	// fetch the data of customer table -> array of customer

	public static Customer[] customerProfileDb() {
		Customer[] custArray = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
			ArrayList<Customer> list = new ArrayList<Customer>();
			while (rs.next()) {
				Customer cs = new Customer(rs.getString("Customer_id"), rs.getString("Customer_name"),
						rs.getInt("Customer_Login_id"), rs.getString("Customer_Password"),
						rs.getString("Customer_phone"), rs.getInt("Customer_walletbal"), rs.getString("Customer_Email")

				);
				list.add(cs);
			}
			custArray = new Customer[list.size()];
			custArray = list.toArray(custArray);
		} catch (Exception e) {
			System.out.println(e);
		}

		return custArray;

	}

	// fetch the data of vendor -> array of vendor

	public static Vendor[] vendorProfileDb() {
		Vendor[] vnArray = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT Vendor_id,Vendor_Name,Vendor_Phone,Vendor_Specs,vendor_Login_Id,vendor_Password FROM vendor");
			ArrayList<Vendor> list = new ArrayList<Vendor>();
			while (rs.next()) {
				Vendor v = new Vendor(rs.getInt("Vendor_id"), rs.getString("Vendor_Name"), rs.getString("Vendor_Phone"),
						rs.getString("Vendor_Specs"), rs.getInt("vendor_Login_Id"), rs.getString("vendor_Password"));
				list.add(v);
			}
			vnArray = new Vendor[list.size()];
			vnArray = list.toArray(vnArray);
		} catch (Exception e) {
			System.out.println(e);
		}

		return vnArray;

	}

	// fetch the data from the orderDetails where customerID =xx
	public static OrderDetails[] customerOrderHistoryDb(String custId) {
		OrderDetails[] odArr = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);

			String sql = " SELECT * FROM OrderDetails WHERE Customer_id = ?  ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, custId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
			while (rs.next()) {
				OrderDetails od = new OrderDetails(rs.getInt("Order_No"), rs.getInt("Vendor_id"),
						rs.getString("Customer_id"), rs.getInt("Food_id"), rs.getInt("Quantity"),
						rs.getString("DateandTime"), rs.getInt("Order_value"), rs.getString("Order_status"));
				list.add(od);
			}
			odArr = new OrderDetails[list.size()];
			odArr = list.toArray(odArr);

			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return odArr;
	}

	// fetch the data from orderDetails where vendorId ==x

	public static OrderDetails[] vendorOrderHistoryDb(int vendorId) {
		OrderDetails[] odArr = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);

			String sql = " SELECT * FROM OrderDetails WHERE Vendor_id = ?  ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vendorId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
			while (rs.next()) {
				OrderDetails od = new OrderDetails(rs.getInt("Order_No"), rs.getInt("Vendor_id"),
						rs.getString("Customer_id"), rs.getInt("Food_id"), rs.getInt("Quantity"),
						rs.getString("DateandTime"), rs.getInt("Order_value"), rs.getString("Order_status"));
				list.add(od);
			}
			odArr = new OrderDetails[list.size()];
			odArr = list.toArray(odArr);

			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return odArr;
	}

	public static String acceptOrRejectOrder(int od) {

		System.out.println(" Choice 1: - Accept \r\n " + "Choice 2: - Reject \n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println(" Order Accepted ! \n");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con
						.prepareStatement("update OrderDetails set Order_status='accepted' where Order_No=" + od + " ");

				// stmt.setString(1,order_status);

				stmt.executeUpdate();

				// System.out.println(i+" records inserted");
			} catch (Exception e) {
				System.out.println("Done and exiting");
			}
			break;
		case 2:
			System.out.println("Order Rejected !");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con
						.prepareStatement("update OrderDetails set Order_status='rejected' where Order_No=" + od + " ");

				stmt.executeUpdate();

				// System.out.println(i+" records inserted");
			} catch (Exception e) {
				System.out.println("Done and exiting");
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE Customer SET Customer_walletbal=Customer_walletbal+(SELECT Order_value FROM OrderDetails WHERE Order_No= ? )WHERE Customer_id=(SELECT Customer_id FROM OrderDetails WHERE Order_No= ? )");
				stmt.setInt(1, od);
				stmt.setInt(2, od);
				stmt.executeUpdate();
				System.out.println("\nAmount refunded back  to Customer Wallet\n");
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		default:
			System.out.println("Exited from site");
			break;

		}
		return "Successfully updated the placed order !";
	}

	public static int placeOrder(int vendor_id, String custId, int food_id, int quantity, int Order_value,
			int customer_walletbal) {
		String order_status = "ordered";
		int tb = customer_walletbal - Order_value;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO OrderDetails(vendor_id,customer_id,food_id,quantity,order_value,order_status) VALUES(?,?,?,?,?,?)");

			stmt.setInt(1, vendor_id);
			stmt.setString(2, custId);
			stmt.setInt(3, food_id);
			stmt.setInt(4, quantity);
			stmt.setInt(5, Order_value);
			stmt.setString(6, order_status);
			i = stmt.executeUpdate();
			// System.out.println(i + "\nrecords inserted");
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con
					.prepareStatement("UPDATE Customer SET Customer_walletbal=" + tb + " WHERE Customer_id = ? ");
			stmt.setString(1, custId);
			stmt.executeUpdate();
			System.out.println("\n Deducted amount from wallet : " + Order_value);
		} catch (Exception e) {
			System.out.println(e);
		}

		return i;

	}

	public static Customer validateCustomerLogin(int custLoginId, String custPassword) {
		Customer cs = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
			Connection con = DriverManager.getConnection(url, username, password);

			String sqlStr = " SELECT * FROM customer WHERE Customer_Login_id=? AND Customer_Password = ? ";

			PreparedStatement stmt = con.prepareStatement(sqlStr);

			stmt.setInt(1, custLoginId);
			stmt.setString(2, custPassword);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String custId = rs.getString("Customer_id");
				String custName = rs.getString("Customer_name");
				;
				String custPhone = rs.getString("Customer_phone");
				String custEmail = rs.getString("Customer_Email");
				int custWalletBal = rs.getInt("Customer_walletbal");
				int custLoginId2 = rs.getInt("Customer_Login_id");
				String custPassword2 = rs.getString("Customer_Password");
				// Creating object from single row of data of customer
				cs = new Customer(custId, custName, custLoginId2, custPassword2, custPhone, custWalletBal, custEmail);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cs;
	}

	public static OrderDetails[] validateOrderDetails(int ordId) {
		OrderDetails[] odArr = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);

			String sql = " SELECT * FROM OrderDetails WHERE Order_No = ?  ";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, ordId);

			ResultSet rs = stmt.executeQuery();
			ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
			while (rs.next()) {
				OrderDetails od = new OrderDetails(rs.getInt("Order_No"), rs.getInt("vendor_id"),
						rs.getString("customer_id"), rs.getInt("food_id"), rs.getInt("quantity"),
						rs.getString("dateandtime"), rs.getInt("order_value"), rs.getString("order_status"));
				list.add(od);
			}
			odArr = new OrderDetails[list.size()];
			odArr = list.toArray(odArr);

			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return odArr;

	}

	public static Customer validCustomer(Customer cs) {
		Customer cuObj = null;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM Customer HAVING Customer_id =? OR Customer_Login_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cs.getCustId());
			stmt.setInt(2, cs.getCustLoginId());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String custId = rs.getString("Customer_id");
				String custName = rs.getString("Customer_name");
				String custPhone = rs.getString("Customer_phone");
				String custEmail = rs.getString("Customer_Email");
				int custWalletBal = rs.getInt("Customer_walletbal");
				int custLoginId2 = rs.getInt("Customer_Login_id");
				String custPassword2 = rs.getString("Customer_Password");
				// Creating object from single row of data of customer
				cuObj = new Customer(custId, custName, custLoginId2, custPassword2, custPhone, custWalletBal,
						custEmail);
				stmt.close();
				con.close();
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e + "hello");
		}

		return cuObj;
	}

	public static int insertNewCustomer(Customer newCustomer) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)");
			stmt.setString(1, newCustomer.getCustId());
			stmt.setString(2, newCustomer.getCustName());
			stmt.setString(3, newCustomer.getCustPhone());
			stmt.setString(4, newCustomer.getCustEmail());
			stmt.setInt(5, newCustomer.getCustWalletBalance());
			stmt.setInt(6, newCustomer.getCustLoginId());
			stmt.setString(7, newCustomer.getCustPassword());
			i = stmt.executeUpdate();
			System.out.println("Successfully Registered !! \r\nYou can now login as an existing user...");
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public static void updatePassword(int loginId, String pass) {

		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String updateCostumerSql = "UPDATE Customer SET Customer_Password = ? WHERE Customer_Login_id=? ";
			PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
			stmt.setString(1, pass);
			stmt.setInt(2, loginId);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void cancelOrder(int orderId) {
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String updateCostumerSql = "DELETE FROM OrderDetails WHERE Order_No=?";
			PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
			stmt.setInt(1, orderId);
			i = stmt.executeUpdate();
			System.out.println("Order Cancelled !");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static OrderDetails[] vendorOrdersPending(int vendorId) {
		OrderDetails[] odArr = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = " SELECT * FROM OrderDetails WHERE Vendor_id = ? AND Order_status='ordered'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vendorId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
			while (rs.next()) {
				OrderDetails od = new OrderDetails(rs.getInt("Order_No"), rs.getInt("Vendor_id"),
						rs.getString("Customer_id"), rs.getInt("Food_id"), rs.getInt("Quantity"),
						rs.getString("DateandTime"), rs.getInt("Order_value"), rs.getString("Order_status"));
				list.add(od);
			}
			odArr = new OrderDetails[list.size()];
			odArr = list.toArray(odArr);
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return odArr;
	}

	public static OrderDetails[] pendingOrderDetails(String customerId) {
		OrderDetails[] odArr = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = " SELECT * FROM OrderDetails WHERE Customer_id = ? AND Order_status='ordered'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, customerId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
			while (rs.next()) {
				OrderDetails od = new OrderDetails(rs.getInt("Order_No"), rs.getInt("Vendor_id"),
						rs.getString("Customer_id"), rs.getInt("Food_id"), rs.getInt("Quantity"),
						rs.getString("DateandTime"), rs.getInt("Order_value"), rs.getString("Order_status"));
				list.add(od);
			}
			odArr = new OrderDetails[list.size()];
			odArr = list.toArray(odArr);
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return odArr;

	}

	public static OrderDetails validatePendingOrder(int orderId) {
		OrderDetails ordObj = null;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM OrderDetails WHERE Order_No=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ordObj = new OrderDetails(rs.getInt("Order_No"), rs.getInt("Vendor_id"), rs.getString("Customer_id"),
						rs.getInt("Food_id"), rs.getInt("Quantity"), rs.getString("DateandTime"),
						rs.getInt("Order_value"), rs.getString("Order_status"));
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return ordObj;

	}

	public static int customerWalletBalance(String customerId) {
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sqlStr = "SELECT * FROM Customer WHERE Customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sqlStr);
			stmt.setString(1, customerId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt("Customer_walletbal");
			}
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.print(e);
		}
		return i;
	}

	public static Vendor validateVendor(int venLoginId, String venPassword) {
		Vendor v = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
			Connection con = DriverManager.getConnection(url, username, password);

			String sqlStr = " SELECT * FROM vendor WHERE Vendor_Login_Id=? AND Vendor_Password = ? ";

			PreparedStatement stmt = con.prepareStatement(sqlStr);

			stmt.setInt(1, venLoginId);
			stmt.setString(2, venPassword);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				v = new Vendor(rs.getInt("Vendor_id"), rs.getString("Vendor_Name"), rs.getString("Vendor_Phone"),
						rs.getString("Vendor_Specs"), rs.getInt("Vendor_Login_Id"), rs.getString("Vendor_Password"));
				// Creating object from single row of data of customer

			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return v;
	}

	public static int insertNewMenu(Menu newMenu) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Customer VALUES(?,?,?)");
			stmt.setInt(1, newMenu.getFoodId());
			stmt.setString(2, newMenu.getFoodName());
			stmt.setInt(3, newMenu.getFoodPrice());

			i = stmt.executeUpdate();
			System.out.println("successfully inserted new menu");
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public static int updateOrderStatus(int id, String status) {
		int res = 0;
		String sql = "UPDATE ORDERS SET ORDERSTATUS = ? WHERE ORDERID = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);

			res = ps.executeUpdate();

			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return res;
	}
}
