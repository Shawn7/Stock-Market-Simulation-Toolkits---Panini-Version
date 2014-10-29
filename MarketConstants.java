public class MarketConstants {
	private static final int numStocks = 1867;
	private static final int maxShares = 1000000, minShares = 1000;
	private static final int maxShareValue = 100, minShareValue = 1;
	private static final int demandDrivenPriceIncrement = 1;
	private static final int pricePremium = 2;
	private static final int maxPurchase = 10000, minPurchase = 5000;
	private static final int desireReturn = 10;

	public static int getNumStocks(){return numStocks;}
	public static int getMaxShares(){return maxShares;}
	public static int getMinShares(){return minShares;}
	public static int getMaxShareValue(){return maxShareValue;}
	public static int getMinShareValue(){return minShareValue;}
	public static int getDemandIncrement(){return demandDrivenPriceIncrement;}
	public static int getPricePremium(){return pricePremium;}
	public static int getMaxPurchase(){return maxPurchase;}
	public static int getMinPurchase(){return minPurchase;}
	public static int getDesireReturn(){return desireReturn;}
}
