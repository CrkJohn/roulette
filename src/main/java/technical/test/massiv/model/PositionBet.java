package technical.test.massiv.model;


public class PositionBet  extends Bet{

	private Integer position;

	public PositionBet(Integer money, String userId) {

		super(money, userId);
	}

	@Override
	public boolean isWinner(Integer winnerNumber) {

		return position.equals(winnerNumber);
	}

	@Override
	public void giveProfit() {

		super.gain = money * 5;
	}

	public Integer getPosition() {

		return position;
	}

	public void setPosition(Integer position) {

		this.position = position;
	}


}
