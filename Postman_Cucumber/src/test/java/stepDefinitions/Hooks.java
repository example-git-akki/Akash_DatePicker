package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@submitOrder")
    public void beforeScenario() {
        Get_Status_BookStepdefs m = new Get_Status_BookStepdefs();
        if (Get_Status_BookStepdefs.accessToken == null) {
            m.userExtractsTheAuthenticationCode();
        }
    }

    @Before("@GetAnOrder")
    public void beforeGetAnOrderScenario() throws IOException {
        Get_Status_BookStepdefs m = new Get_Status_BookStepdefs();
        if (Get_Status_BookStepdefs.orderID == null) {
            m.userExtractsTheOrderId();
        }
    }

    @Before("@UpdateOrderName")
    public void beforeUpdateOrder() throws IOException {
        Get_Status_BookStepdefs m = new Get_Status_BookStepdefs();
        if (Get_Status_BookStepdefs.orderID == null) {
            m.userExtractsTheOrderId();
        }

    }


    @Before("@DeleteAnOrder")
    public void updateAnOrderScenario() throws IOException {
        Get_Status_BookStepdefs m = new Get_Status_BookStepdefs();
        if (Get_Status_BookStepdefs.orderID == null) {
            m.userExtractsTheOrderId();
        }
    }
}
