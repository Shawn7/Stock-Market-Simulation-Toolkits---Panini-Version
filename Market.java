import java.util.LinkedList;
import java.util.Queue;

capsule Market{
	MarketPortfolio marketPortfolio;
	double marketCapital;

	int timesOfTrades;
	int timesOfPurchase;
	int timesOfPurchaseDeclined;
	int timesOfSale;
	int timesOfSalesDeclined;

	=> {
		marketPortfolio = new MarketPortfolio();
		marketCapital = 0;
		timesOfTrades = 0;
		timesOfPurchase = 0;
		timesOfPurchaseDeclined = 0;
		timesOfSale = 0;
		timesOfSalesDeclined = 0;
	}
	
	int requestPurchase(String name, int premium, int quantity){
		int purchasedQuantity = 0;
		if(premium < 0)
			return purchasedQuantity;
		for(int i = 0; i < MarketConstants.getNumStocks(); i++){
			if(name.compareTo(marketPortfolio.stocks[i].getName()) == 0){
				if(quantity <= marketPortfolio.stocks[i].getQuantity()){
					purchasedQuantity = quantity;
					marketPortfolio.stocks[i].setQuantity(marketPortfolio.stocks[i].getQuantity() - quantity);
					marketCapital += ((marketPortfolio.stocks[i].getPrice() + premium) * quantity);
					timesOfPurchase++;
					timesOfTrades++;
					if(timesOfTrades == 5000) printMarket();
				}
				else if(quantity > marketPortfolio.stocks[i].getQuantity() && marketPortfolio.stocks[i].getQuantity() > 0){
					quantity = marketPortfolio.stocks[i].getQuantity();
					purchasedQuantity = quantity;
					marketPortfolio.stocks[i].setQuantity(0);
					marketCapital += ((marketPortfolio.stocks[i].getPrice() + premium) * quantity);
					timesOfTrades++;
					timesOfPurchase++;
					if(timesOfTrades == 5000) printMarket();
				}
				else if(marketPortfolio.stocks[i].getQuantity() == 0){
					timesOfTrades++;
					timesOfPurchase++;
					timesOfPurchaseDeclined++;
					if(timesOfTrades == 5000) printMarket();
				}
				marketPortfolio.stocks[i].setPrice(marketPortfolio.stocks[i].getPrice() + MarketConstants.getDemandIncrement());
				return purchasedQuantity;
			}
		}
		return purchasedQuantity;
	}

	int requestSale(String name, double price, int quantity){
		int countQuantity = 0;
		for(int i = 0; i < MarketConstants.getNumStocks(); i++){
			if(name.compareTo(marketPortfolio.stocks[i].getName()) == 0){
				if(price > marketPortfolio.stocks[i].getPrice()){
					timesOfSale++;
					timesOfSalesDeclined++;
					timesOfTrades++;
					if(timesOfTrades == 5000) printMarket();
					return 0;
				}
				else{
					if(marketCapital >= price * quantity){
						marketPortfolio.stocks[i].setQuantity(marketPortfolio.stocks[i].getQuantity() + quantity);
						marketCapital -= price * quantity;
						timesOfTrades++;
						timesOfSale++;
						if(timesOfTrades == 5000) printMarket();
					}
					else{
						while(marketCapital > price){
							marketCapital -= price;
							countQuantity++;
						}
						timesOfTrades++;
						timesOfSale++;
						if(timesOfTrades == 5000) printMarket();
					}
				}
				marketPortfolio.stocks[i].setPrice(marketPortfolio.stocks[i].getPrice() - MarketConstants.getDemandIncrement());
				if(countQuantity != 0)
					quantity = countQuantity;
				return quantity;
			}
		}
		return 0;
	}

	int requestSale(int quantity, double price, String name){
		for(int i = 0; i < MarketConstants.getNumStocks(); i++){
			if(name.compareTo(marketPortfolio.stocks[i].getName()) == 0){
				if(price > marketPortfolio.stocks[i].getPrice()){
					timesOfSalesDeclined++;
					timesOfSale++;
					timesOfTrades++;
					if(timesOfTrades == 5000) printMarket();
					return 0;
				}
				else{
					if(marketCapital >= price * quantity){
						marketPortfolio.stocks[i].setQuantity(marketPortfolio.stocks[i].getQuantity() + quantity);
						marketCapital -= price * quantity;
						timesOfTrades++;
						timesOfSale++;
						if(timesOfTrades == 5000) printMarket();
					}
					else{
						timesOfSale++;
						timesOfTrades++;
						timesOfSalesDeclined++;
						if(timesOfTrades == 5000) printMarket();
						quantity = 0;
					}
				}
				marketPortfolio.stocks[i].setPrice(marketPortfolio.stocks[i].getPrice() - MarketConstants.getDemandIncrement());
				return quantity;
			}
		}
		return 0;
	}

	void printMarket(){
		System.out.printf("Event: (Stats - Trades 5000, Sales %d / %d(declined), Purchases %d / %d(decline), Capital %f)\n\n", timesOfSale, timesOfSalesDeclined, timesOfPurchase, timesOfPurchaseDeclined, marketCapital);
		timesOfPurchase = 0;
		timesOfSale = 0;
		timesOfTrades = 0;
		timesOfSalesDeclined = 0;
		timesOfPurchaseDeclined = 0;
	}

	double requestStockPrice(int number){
		return marketPortfolio.stocks[number].getPrice();
	}
}