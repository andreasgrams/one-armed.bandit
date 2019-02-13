package de.dreamnetworx.one.armed.bandit;

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
        log.info("checkin {} ", checkin);
        oneArmedBanditService.checkin(toValueObject(checkin));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "pullingHandle")
    public ResponseEntity<GameResultPojo> pullingHandle() {
        final GameResult gameResult = oneArmedBanditService.pullingHandle();
        return new ResponseEntity<>(toPojo(gameResult), HttpStatus.OK);
    }

    @GetMapping(value = "checkout")
    public ResponseEntity<CreditPojo> checkout() {
        final Credit remainingCredits = oneArmedBanditService.checkout();
        return new ResponseEntity<>(toPojo(remainingCredits), HttpStatus.OK);
    }

    private CreditPojo toPojo(final Credit remainingCredits) {
        return new CreditPojo(remainingCredits.getValue());
    }

    private GameResultPojo toPojo(GameResult gameResult) {
        GameResultPojo result = new GameResultPojo();
        result.setCredit(toPojo(gameResult.getCreditsRemained()));
        result.setWheels(gameResult.getWheels().stream()
                .map(w -> w.name())
                .collect(Collectors.toList()));
        result.setWon(gameResult.isGameWon());
        return result;
    }

    private Credit toValueObject(final CheckinPojo checkin) {
        return new Credit(checkin.getCredit().getCredits());
    }

}
