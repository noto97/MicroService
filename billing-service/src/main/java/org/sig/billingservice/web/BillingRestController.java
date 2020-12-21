package org.sig.billingservice.web;


import org.sig.billingservice.entities.Bill;
import org.sig.billingservice.feign.CustomerRestClient;
import org.sig.billingservice.feign.ProductItemRestClient;
import org.sig.billingservice.model.Customer;
import org.sig.billingservice.model.Product;
import org.sig.billingservice.repositories.BillRepository;
import org.sig.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }


    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill=billRepository.findById(id).get();
        Customer customer=customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            Product product=productItemRestClient.getProductById(pi.getProductID());
            // pi.setProduct(product);
            pi.setProductName(product.getName());
        });

        return bill;
    }
}
