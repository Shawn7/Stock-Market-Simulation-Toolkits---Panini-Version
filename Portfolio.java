
public class Portfolio{
	Stock[] stocks;
	public Portfolio(int number){
		stocks = new Stock[number];
		for(int i = 0; i < number; i++){
			stocks[i] = new Stock();
			stocks[i].setName(String.valueOf(i));
		}
	}
}
