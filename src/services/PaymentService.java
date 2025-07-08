package services;

import contracts.DeliveryStrategy;
import entities.Customer;
import entities.PurchasableBook;
import exceptions.InSufficientFundsException;

import java.math.BigDecimal;

public class PaymentService {
    private DeliveryStrategy deliveryStrategy;

    public void setPurchaseStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public DeliveryStrategy getPurchaseStrategy() {
        return deliveryStrategy;
    }

    public BigDecimal executePurchase(PurchasableBook book, int requestedQuantity, Customer customer)
    {

       var shippingPrice = deliveryStrategy.deliver(book, customer);
       var subTotal = book.getPrice().multiply(BigDecimal.valueOf(requestedQuantity));
       var totalPaidAmount = subTotal.add(shippingPrice);
       var customerCurrentBalance = customer.getBalance();

       if(customerCurrentBalance.compareTo(totalPaidAmount) < 0)
           throw new InSufficientFundsException();

       customer.setBalance(customerCurrentBalance.subtract(totalPaidAmount));
       System.out.println("The Book has been Purchased Successfully!");
       return totalPaidAmount;
    }
}
