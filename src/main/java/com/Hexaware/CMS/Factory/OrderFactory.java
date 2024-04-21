package com.Hexaware.CMS.Factory;

//import com.Hexaware.CMS.Inotification.*;
import com.Hexaware.CMS.Persistence.OrderDb;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

public class OrderFactory {
	public static int OrderFood(int fid, String fname, int fprice, int fquan) {
		int foodTotal = fquan * fprice;
		int result = OrderDb.insertDb(fid, fname, fprice, fquan, foodTotal);
		return result;
	}

	public static Menu[] showFoodMenu() {
		Menu menu[] = OrderDb.showFoodMenu();
		return menu;
	}

	public static Customer[] customerProfile() {
		return OrderDb.customerProfileDb();
	}

	public static Vendor[] vendorProfile() {
		return OrderDb.vendorProfileDb();
	}

	// validate the VendorId

	public static Vendor validateVendor(int venLoginId, String venPassword) {
		return OrderDb.validateVendor(venLoginId, venPassword);
	}

	public static OrderDetails[] customerOrderHistory(String custId) {
		return OrderDb.customerOrderHistoryDb(custId);
	}

	public static OrderDetails[] vendorOrderHistory(int vendorId) {
		return OrderDb.vendorOrderHistoryDb(vendorId);
	}

	public static String acceptRejectOrder(int ordId) {
		return OrderDb.acceptOrRejectOrder(ordId);
	}

	public static Customer validateCustomerLogin(int custLoginId, String custPassword) {
		return OrderDb.validateCustomerLogin(custLoginId, custPassword);
	}

	public static int placeOrder(int vendor_id, String custId, int food_id, int quantity, int Order_value,
			int customer_walletbal) {
		return OrderDb.placeOrder(vendor_id, custId, food_id, quantity, Order_value, customer_walletbal);

	}

	public static OrderDetails[] pendingOrderDetails(String customerId) {
		return OrderDb.pendingOrderDetails(customerId);
	}

	public static OrderDetails validatePendingOrder(int orderId) {
		return OrderDb.validatePendingOrder(orderId);

	}

	public static OrderDetails[] vendorOrdersPending(int venderId) {
		return OrderDb.vendorOrdersPending(venderId);
	}

	public static int newCustomer(Customer newCustomer) {
		return OrderDb.insertNewCustomer(newCustomer);
	}

	public static int newMenu(Menu newMenu) {
		return OrderDb.insertNewMenu(newMenu);
	}

	public static void updatePassword(int lid, String cnpwd) {
		OrderDb.updatePassword(lid, cnpwd);
	}

	public static void cancelOrder(int orderId) {
		OrderDb.cancelOrder(orderId);
	}

	public static Customer validCustomer(Customer cs) {
		return OrderDb.validCustomer(cs);
	}

	public static OrderDetails[] validateOrderDetails(int ordId) {
		return OrderDb.validateOrderDetails(ordId);
	}

	public static int customerWalletBalance(String customerId) {
		return OrderDb.customerWalletBalance(customerId);
	}

	
}