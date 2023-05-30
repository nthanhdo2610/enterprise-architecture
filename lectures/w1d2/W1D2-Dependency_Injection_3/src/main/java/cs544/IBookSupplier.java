package cs544;

public interface IBookSupplier {
	public double computePrice(String isbn);
	public void order(Book book);
}
