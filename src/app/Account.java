package app;

/**
 * Account
 */
public class Account {
    String name;
    String surname;
    float invoice_amount = 0.0f;
    float limit = 1000000.0f;

    public Account(String name, String surname,float invoice_amount) 
    {
        this.name = name;
        this.surname = surname;
        this.invoice_amount = invoice_amount;
    }

    public String getData()
    {
        String data = "Account:" + name + "," + surname + "," + invoice_amount + "," + limit;
        return data;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    // проверка баланса
    float getInvoiceAmount() {
        return invoice_amount;
    }

    // пополнение счета
    void Put(Float sum)
    {
        if (sum > 0 && sum < limit) 
        {
            this.invoice_amount += sum;
            System.out.println("Сумма на счету составляет:" + invoice_amount);                
        } 
        else
        {
            System.out.println("Cумма пополнения не должна превышать 1 000 000 и быть меньше 0");
        }
    }

    // Снятие со счета
    void Withdraw(float sum) {
        
        if (sum > 0 && sum <= invoice_amount) 
        {
            this.invoice_amount -= sum;
            System.out.println("Сумма на счету составляет:" + invoice_amount);
        } 
        else
        {
            System.out.println("Cумма снятия не должна превышать количества денег на счете");
        }
    }
}