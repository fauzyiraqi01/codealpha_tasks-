import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> stocks = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Add market stocks
        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2900));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1700));

        // Create user
        User user = new User("Fauzia", 100000);

        System.out.println("=================================");
        System.out.println("      STOCK TRADING PLATFORM");
        System.out.println("=================================");

        boolean running = true;

        while (running) {

            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewMarket(stocks);
                    break;

                case 2:
                    buyStock(sc, stocks, user, transactions);
                    break;

                case 3:
                    sellStock(sc, stocks, user, transactions);
                    break;

                case 4:
                    viewPortfolio(stocks, user);
                    break;

                case 5:
                    viewTransactions(transactions);
                    break;

                case 6:
                    running = false;
                    System.out.println("Thank you for using the platform!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
        


        sc.close();
       
    }
    //Add viewmarket method
    public static void viewMarket(ArrayList<Stock> stocks) {

    System.out.println("\n========== MARKET DATA ==========");

    for (Stock stock : stocks) {
        stock.displayStock();
    }
}
//Add buy operation
public static void buyStock(
        Scanner sc,
        ArrayList<Stock> stocks,
        User user,
        ArrayList<Transaction> transactions) {

    viewMarket(stocks);

    System.out.print("\nEnter stock symbol: ");
    String symbol = sc.next();

    Stock selectedStock = null;

    for (Stock stock : stocks) {

        if (stock.getSymbol().equalsIgnoreCase(symbol)) {
            selectedStock = stock;
            break;
        }
    }

    if (selectedStock == null) {
        System.out.println("Stock not found!");
        return;
    }

    System.out.print("Enter quantity: ");
    int quantity = sc.nextInt();

    if (quantity <= 0) {
        System.out.println("Invalid quantity!");
        return;
    }

    if (user.buyStock(selectedStock, quantity)) {

        transactions.add(
            new Transaction(
                "BUY",
                selectedStock.getSymbol(),
                quantity,
                selectedStock.getPrice()
            )
        );

        System.out.println("Stock purchased successfully!");

    } else {

        System.out.println("Insufficient balance!");
    }
}
//Add sell operation
public static void sellStock(
        Scanner sc,
        ArrayList<Stock> stocks,
        User user,
        ArrayList<Transaction> transactions) {

    viewPortfolio(stocks, user);

    System.out.print("\nEnter stock symbol: ");
    String symbol = sc.next();

    Stock selectedStock = null;

    for (Stock stock : stocks) {

        if (stock.getSymbol().equalsIgnoreCase(symbol)) {
            selectedStock = stock;
            break;
        }
    }

    if (selectedStock == null) {
        System.out.println("Stock not found!");
        return;
    }

    System.out.print("Enter quantity: ");
    int quantity = sc.nextInt();

    if (user.sellStock(selectedStock, quantity)) {

        transactions.add(
            new Transaction(
                "SELL",
                selectedStock.getSymbol(),
                quantity,
                selectedStock.getPrice()
            )
        );

        System.out.println("Stock sold successfully!");

    } else {

        System.out.println("You don't own enough shares!");
    }
}
//Add portfolio display
public static void viewPortfolio(
        ArrayList<Stock> stocks,
        User user) {

    System.out.println("\n========== PORTFOLIO ==========");

    System.out.println("User: " + user.getName());
    System.out.println("Cash Balance: ₹" + user.getBalance());

    if (user.getPortfolio().isEmpty()) {
        System.out.println("No stocks owned.");
        return;
    }

    double portfolioValue = 0;

    for (String symbol : user.getPortfolio().keySet()) {

        int quantity = user.getPortfolio().get(symbol);

        for (Stock stock : stocks) {

            if (stock.getSymbol().equals(symbol)) {

                double value =
                    quantity * stock.getPrice();

                portfolioValue += value;

                System.out.println(
                    symbol +
                    " | Quantity: " +
                    quantity +
                    " | Current Value: ₹" +
                    value
                );
            }
        }
    }

    System.out.println("-------------------------------");
    System.out.println(
        "Total Stock Value: ₹" + portfolioValue
    );

    System.out.println(
        "Total Portfolio Value: ₹" +
        (user.getBalance() + portfolioValue)
    );
}
//Add Transaction History
public static void viewTransactions(
        ArrayList<Transaction> transactions) {

    System.out.println("\n========== TRANSACTIONS ==========");

    if (transactions.isEmpty()) {
        System.out.println("No transactions yet.");
        return;
    }

    for (Transaction transaction : transactions) {
        transaction.displayTransaction();
    }
}
}
