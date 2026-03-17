package bankmanagementsystem;
public class Transaction
{
    private int transactonID;
    private String transactionType;
    private String date;
    private long amount;

    public int getTransactonID() {
        return transactonID;
    }

    public void setTransactonID(int transactonID) {
        this.transactonID = transactonID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Transaction(String transactionType, long amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Transaction() {
    }
    
    
}
