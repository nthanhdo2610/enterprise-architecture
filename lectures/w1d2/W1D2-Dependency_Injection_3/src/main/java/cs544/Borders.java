package cs544;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("suppliers")
public class Borders implements IBookSupplier {
    @Override
    public double computePrice(String isbn) {
        return 0;
    }

    @Override
    public void order(Book book) {

    }
}
