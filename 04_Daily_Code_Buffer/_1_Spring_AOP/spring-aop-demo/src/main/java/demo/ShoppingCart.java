package demo;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    public void checkout(String status){
        //Logging - LoggingAspect Class Created
        //Authentication & Authorization - AuthenticationAspect Class Created
        //sanitize the Data -
        System.out.println("Checkout Method From ShoppingCart Called");
    }

    public int quantity () {
        return 2;
    }
}
