package technical.test.massiv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import technical.test.massiv.model.utils.State;

/**
 * Roulette is used to represent the online casino's roulette.
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public class Roulette {

	@Id
	private String id;

	private State state;

	@JsonIgnore
	private Integer numberWinner;

	@JsonIgnore
	private LocalDateTime dateOpenRoulette;

	@JsonIgnore
	private LocalDateTime dateCreatedRoulette;

	@JsonIgnore
	private LocalDateTime dateCloseRoulette;

	@JsonIgnore
	private List<Bet> bets;

	public Roulette() {

		this.dateCreatedRoulette = LocalDateTime.now();
	}

	public LocalDateTime getDateOpenRoulette() {

		return dateOpenRoulette;
	}

	public LocalDateTime getDateCloseRoulette() {

		return dateCloseRoulette;
	}

	@JsonIgnore
	public boolean isOpenRoulette(){

		return state.equals(State.OPENED);
	}

	@JsonIgnore
	public boolean isCloseRoulette(){

		return state.equals(State.CLOSED);
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public LocalDateTime getDateCreatedRoulette() {

		return dateCreatedRoulette;
	}

	public void setDateCreatedRoulette(LocalDateTime dateCreatedRoulette) {

		this.dateCreatedRoulette = dateCreatedRoulette;
	}

	public State getState() {

		return state;
	}

	public void setState(State state) {

		this.state = state;
	}

	public Integer getNumberWinner() {

		return numberWinner;
	}

	public void setNumberWinner(Integer numberWinner) {

		this.numberWinner = numberWinner;
	}

	public List<Bet> getBets() {

		return bets;
	}

	public void setBets(List<Bet> bets) {

		this.bets = bets;
	}

	public void addBet(Bet bet) {

		if(this.bets == null){
			this.bets = new ArrayList<>();
		}
		bets.add(bet);
	}

	public void openRoulette() {

		this.dateOpenRoulette = LocalDateTime.now();
		this.state = State.OPENED;
	}

	public void closeRoulette() {

		this.dateCloseRoulette = LocalDateTime.now();
		this.state = State.CLOSED;
	}

	public void defineWinners(Integer winnerNumber) {

		bets.stream()
			.filter(bet -> bet.isWinner(winnerNumber))
			.forEach(Bet::giveProfit);
	}

	public Optional<List<Bet>> obtainProfitsFromTheBets() {

		return Optional.of(
				bets.stream()
					.map(Bet::getProfit)
					.collect(Collectors.toList()));

	}
}
