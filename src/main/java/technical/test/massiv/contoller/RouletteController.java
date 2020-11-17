package technical.test.massiv.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import technical.test.massiv.exception.NotFoundRouletteException;
import technical.test.massiv.exception.NotPossibleActionException;
import technical.test.massiv.exception.RequestException;
import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.StateRequest;
import technical.test.massiv.services.RouletteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
@RestController
@RequestMapping("/v1/roulettes")
public class RouletteController {

	@Autowired
	RouletteService rouletteService;

	@GetMapping
	public ResponseEntity<?> findRoulettes() {

		List<Roulette> roulettes = rouletteService.getRoulettes();
		return new ResponseEntity<>(roulettes, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createRoulette() {

		Optional<String> idRoulette = rouletteService.createRoulette();
		return new ResponseEntity<>(idRoulette, HttpStatus.OK);
	}

	@PutMapping("/{idRoulette}/open")
	public ResponseEntity<?> openRoulette(@PathVariable String idRoulette)
			throws NotFoundRouletteException, NotPossibleActionException {

		StateRequest stateRequest = rouletteService.openRoulette(idRoulette);
		return new ResponseEntity<>(stateRequest, HttpStatus.OK);
	}

	@PutMapping("/{idRoulette}/close")
	public ResponseEntity<?> closeRoulette(@PathVariable String idRoulette)
			throws NotFoundRouletteException, NotPossibleActionException {

		Optional<List<Bet>> bets = rouletteService.closeRoulette(idRoulette);
		return new ResponseEntity<>(bets, HttpStatus.OK);
	}

	@PostMapping("/{idRoulette}/colorBet")
	public ResponseEntity<?> doColorBet(@RequestHeader("userId") String userId, @PathVariable String idRoulette,
										@RequestBody ColorBet positionBet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException {

		StateRequest stateRequest = rouletteService.doColorBet(idRoulette, userId, positionBet);
		return new ResponseEntity<>(stateRequest, HttpStatus.OK);
	}

	@PostMapping("/{idRoulette}/positionBet")
	public ResponseEntity<?> doPositionBet(@RequestHeader("userId") String userId,
										   @PathVariable String idRoulette,
										   @RequestBody PositionBet positionBet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException {

		StateRequest stateRequest = rouletteService.doPositionBet(idRoulette, userId, positionBet);
		return new ResponseEntity<>(stateRequest, HttpStatus.OK);
	}

}
