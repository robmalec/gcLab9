package co.grandcircus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lab9 {
	public static void main(String[] args) {
		
		System.out.println("Welcome to Rob's Heart Attack Shack\u2122!");

		Map<String, Double> menuItems = new HashMap<String, Double>();

		menuItems.put("1-paddy burger", 1.00);
		menuItems.put("2-paddy burger", 2.00);
		menuItems.put("3-paddy burger", 3.00);
		menuItems.put("4-paddy burger", 4.00);
		menuItems.put("5-paddy burger", 5.00);
		menuItems.put("6-paddy burger", 6.00);
		menuItems.put("7-paddy burger", 7.00);
		menuItems.put("8-paddy burger", 8.00);

		ArrayList<String> myCart = new ArrayList<>();

		Scanner scn = new Scanner(System.in);

		Boolean doCont = true;
		String choice = "";
		Double total = 0.0;
		
		String mostExpensive = "", leastExpensive = "";

		// While loop
		while (doCont) {
			displayMenu(menuItems);

			choice = Validator.getString(scn, "What item would you like to add to your order?");
			if (menuItems.containsKey(choice)) {
				
				//Code for if user has entered a valid menu item
				myCart.add(choice);
				System.out.println("Adding " + choice + " to cart at $" + menuItems.get(choice));
				System.out.println("Would you like to order anything else?  (y/n)");
				doCont = (scn.next().charAt(0) == 'y');
				total += menuItems.get(choice);
				
				//Handling most and least expensive options
				if (mostExpensive.length() == 0) {
					mostExpensive = choice;
					leastExpensive = choice;
				}
				else if (menuItems.get(choice) > menuItems.get(mostExpensive)) {
					mostExpensive = choice;
				}
				else if (menuItems.get(choice) < menuItems.get(leastExpensive)) {
					leastExpensive = choice;
				}

			} else {
				System.out.println("Sorry, we don't have those.  Please try again.");
			}
		}

		System.out.println("Thanks for your order!\nHere's what you got:");
		for (String s : myCart) {
			printAligned(s, formatAsPrice(menuItems.get(s)));
		}
		System.out.println("Average price per item in order was $" + (total / myCart.size()));
		
		if (!mostExpensive.equals(leastExpensive)) {
			System.out.println("Most expensive item ordered was " + mostExpensive + " at " + formatAsPrice(menuItems.get(mostExpensive)));
			System.out.println("Least expensive item ordered was " + leastExpensive + " at "+ formatAsPrice(menuItems.get(leastExpensive)));
		}
		
		scn.close();
	}
	
	public static String formatAsPrice(Double input) {
		String returnVal = String.format("%.2f", input);
		return "$" + returnVal;
	}

	public static void displayMenu(Map<String, Double> menuItems) {
		String itemName = "Item";
		String price = "Price";
		printAligned(itemName, price);

		System.out.println("#########################");

		for (String key : menuItems.keySet()) {
			printAligned(key, formatAsPrice(menuItems.get(key)));
		}
	}

	public static void printAligned(String itemName, String price) {
		System.out.format("%-15s%-10s", itemName, price);
		System.out.println("");
	}
}
