package de.holisticon.bewerber.grams.one.armed.bandit;

import de.holisticon.bewerber.grams.one.armed.bandit.api.Checkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oneArmedBandit/")
public class OneArmedBanditController {

    private Logger log = LoggerFactory.getLogger(OneArmedBanditController.class);

    @Autowired
    private OneArmedBanditService oneArmedBanditService;

    @PostMapping(value = "checkin")
    public ResponseEntity<Void> checkin(@RequestBody Checkin checkin) {
        log.info("ID this " + this + " service " + oneArmedBanditService);
        oneArmedBanditService.checkin(new Credit(checkin.getCredit().getValue()));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
