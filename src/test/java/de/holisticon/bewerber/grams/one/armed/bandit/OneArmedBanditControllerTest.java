package de.holisticon.bewerber.grams.one.armed.bandit;

import com.google.gson.Gson;
import de.holisticon.bewerber.grams.one.armed.bandit.api.Checkin;
import de.holisticon.bewerber.grams.one.armed.bandit.api.Credit;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StarterConfig.class})
@WebMvcTest(value = OneArmedBanditController.class)
class OneArmedBanditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test to start a game as 'player a' with 10 credits. Expected the checkin process
     * accept with http status accepted(202).
     *
     * @throws Exception
     */
    @Test
    public void shouldCheckin() throws Exception {
        //given
        Checkin checkin = new Checkin();
        checkin.setPlayer("player a");
        final Credit credit = new Credit();
        credit.setValue(10);
        checkin.setCredit(credit);
        //when
        final String content = new Gson().toJson(checkin);
        mockMvc.perform(
                post("/oneArmedBandit/checkin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                //then
                .andExpect(status().isAccepted());
    }

}