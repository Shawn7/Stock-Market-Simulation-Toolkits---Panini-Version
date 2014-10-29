import java.util.Random;

public class MarketPortfolio extends Portfolio{
	MarketPortfolio(){
		super(MarketConstants.getNumStocks());
		double price = 0;
		int quantity = 0;
		Random random = new Random();
		for(int i = 0; i < MarketConstants.getNumStocks(); i++){
			price = random.nextDouble() * (MarketConstants.getMaxShareValue() - MarketConstants.getMinShareValue()) + MarketConstants.getMinShareValue();
			quantity = random.nextInt(MarketConstants.getMaxShares()) % (MarketConstants.getMaxShares() - MarketConstants.getMinShares() + 1) + MarketConstants.getMinShares();
			stocks[i].setPrice(price);
			stocks[i].setQuantity(quantity);
		}
	}
}
