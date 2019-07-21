package app;

/**
 * Card
 */
public class Card {

    String card_number;
    private String pin_code;
    private Account account;
    private boolean blocked; 

    public Card(String card_number, String pin_code, Account account) {
        this.card_number = card_number;
        this.pin_code = pin_code;
        this.account = account;
        this.blocked = false;
    }

    public String getData() {
        String data = "Card:" + card_number + "," + pin_code + "," + blocked + ",";
        data += account.getData();
        return data;
    }

    public void pairAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setPinCode(String pin_code) {
        this.pin_code = pin_code;
    }

    public boolean checkCode(String pin_code) {
        return this.pin_code.equals(pin_code);
    }

    public boolean checkCardNumber(String card_number) {
        return this.card_number.equals(card_number);
    }

    public void block() {
        this.blocked = true;
    }

    public void unbloc() {
        this.blocked = false;
    }

}