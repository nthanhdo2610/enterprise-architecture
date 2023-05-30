package cs544;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("suppliers")
public class Amazon implements IBookSupplier {

	public double computePrice(String isbn) {
		double price = Math.random() * 45;
		System.out.println("Amazon charges $" + price + " for book with isbn "
				+ isbn);
		return price;
	}

	public void order(Book book) {
		System.out.println("Book with isbn = " + book.getIsbn()
				+ " is ordered from Amazon");
	}
}