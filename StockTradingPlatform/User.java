import java.util.HashMap;

public class User {

    private String name;
    private double balance;

    private HashMap<String, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public HashMap<String, Integer> getPortfolio() {
        return portfolio;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public boolean buyStock(Stock stock, int quantity) {

        double totalCost = stock.getPrice() * quantity;

        if (totalCost > balance) {
            return false;
        }

        balance -= totalCost;

        portfolio.put(
            stock.getSymbol(),
            portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        return true;
    }

    public boolean sellStock(Stock stock, int quantity) {

        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);

        if (quantity > owned) {
            return false;
        }

        double totalValue = stock.getPrice() * quantity;

        balance += totalValue;

        int remaining = owned - quantity;

        if (remaining == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remaining);
        }

        return true;
    }
}