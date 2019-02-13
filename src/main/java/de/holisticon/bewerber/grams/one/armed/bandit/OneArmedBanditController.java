package de.holisticon.bewerber.grams.one.armed.bandit;

import de.holisticon.bewerber.grams.one.armed.bandit.api.CheckinPojo;
import de.holisticon.bewerber.grams.one.armed.bandit.api.CreditPojo;
import de.holisticon.bewerber.grams.one.armed.bandit.api.GameResultPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.stream.Collectors;

@RestController
@RequestScope
@RequestMapping("/oneArmedBandit/")
public class OneArmedBanditController {

    private Logger log = LoggerFactory.getLogger(OneArmedBanditController.class);

    @Autowired
    private OneArmedBanditService oneArmedBanditService;

    @PostMapping(value = "checkin")
    public ResponseEntity<Void> checkin(@RequestBody CheckinPojo checkin) {
        log.info("ID this " + this + " service " + oneArmedBanditService);
        oneArmedBanditService.checkin(new Credit(checkin.getCredit().getValue()));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "pullingHandle")
    public ResponseEntity<GameResultPojo> pullingHandle() {
        final GameResult gameResult = oneArmedBanditService.pullingHandle();
        return new ResponseEntity<>(convert(gameResult), HttpStatus.OK);
    }

    private GameResultPojo convert(GameResult gameResult) {
        GameResultPojo result = new GameResultPojo();
        result.setCredit(new CreditPojo(gameResult.getCreditsRemained().getValue()));
        result.setWheels(gameResult.getWheels().stream().map(w -> w.name()).collect(Collectors.toList()));
        result.setWon(gameResult.isGameWon());
        return result;
    }

}

