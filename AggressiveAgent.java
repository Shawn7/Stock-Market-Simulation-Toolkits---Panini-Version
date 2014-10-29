import java.util.Random;

capsule AggressiveAgent(Market me){
	double agentCapital;
	AgentPortfolio agentPortfolio;
	=> {
		agentPortfolio = new AgentPortfolio();
		agentCapital = 100000000;
	}
	void run(){
		Random random = new Random();
		int randomNumber = 0;
		String pickStock = null;
		int myPremium = 0;
		int myQuantity = 0;
		int count = 0;
		Trade t = new Trade(0, 0);
		while(true){
			randomNumber = random.nextInt(MarketConstants.getNumStocks());
			pickStock = String.valueOf(randomNumber);
			myPremium = random.nextInt(MarketConstants.getPricePremium() + 1);
			myQuantity = random.nextInt(MarketConstants.getMaxPurchase()) % (MarketConstants.getMaxPurchase() - MarketConstants.getMinPurchase() + 1) + MarketConstants.getMinPurchase();
			myQuantity = me.requestPurchase(pickStock, myPremium, myQuantity);
			if(myQuantity != 0){
				double price = me.requestStockPrice(randomNumber) - MarketConstants.getDemandIncrement();
				agentCapital -= (price + myPremium) * myQuantity;
				agentPortfolio.stocks[randomNumber].setPrice((agentPortfolio.stocks[randomNumber].getQuantity() * agentPortfolio.stocks[randomNumber].getPrice() + (price + myPremium) * myQuantity)/(agentPortfolio.stocks[randomNumber].getQuantity() + myQuantity));
				agentPortfolio.stocks[randomNumber].setQuantity(agentPortfolio.stocks[randomNumber].getQuantity() + myQuantity);
			}
			else{
				count = 0;
				while(agentPortfolio.stocks[randomNumber].getQuantity() == 0){
					randomNumber = (randomNumber + 1) % MarketConstants.getNumStocks();
					count++;
					if(count == MarketConstants.getNumStocks() + 1)
						break;
				}
				if(count != MarketConstants.getNumStocks() + 1){
					pickStock = String.valueOf(randomNumber);
					myQuantity = agentPortfolio.stocks[randomNumber].getQuantity();
					if(me.requestStockPrice(randomNumber) >= agentPortfolio.stocks[randomNumber].getPrice() + MarketConstants.getDesireReturn()){
						myQuantity = me.requestSale(myQuantity, me.requestStockPrice(randomNumber) + MarketConstants.getDesireReturn(), pickStock);
						double price = me.requestStockPrice(randomNumber) + MarketConstants.getDemandIncrement();
						agentCapital += (price * myQuantity);
						agentPortfolio.stocks[randomNumber].setPrice((agentPortfolio.stocks[randomNumber].getQuantity() * agentPortfolio.stocks[randomNumber].getPrice() - price * myQuantity)/(agentPortfolio.stocks[randomNumber].getQuantity() - myQuantity));
						agentPortfolio.stocks[randomNumber].setQuantity(agentPortfolio.stocks[randomNumber].getQuantity() - myQuantity);
					}
				}
			}
		}
	}
}