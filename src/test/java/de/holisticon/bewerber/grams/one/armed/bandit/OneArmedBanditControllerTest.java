package de.holisticon.bewerber.grams.one.armed.bandit;

import com.google.gson.Gson;
import de.holisticon.bewerber.grams.one.armed.bandit.api.CheckinPojo;
import de.holisticon.bewerber.grams.one.armed.bandit.api.CreditPojo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StarterConfig.class})
@WebMvcTest(value = OneArmedBanditController.class)
class OneArmedBanditControllerTest {

    public static final String CHECKIN_AS_PLAYER_WITH_10_CREDITS =
            new Gson().toJson(new CheckinPojo("player a", new CreditPojo(10)));
    public static final ArrayList<Wheel> WHELLS_COLLECTION = Lists.list(Wheel.APPLE, Wheel.BANANA, Wheel.CLEMENTINE);
    public static final String URL_CHECKOUT = "/oneArmedBandit/checkout";
    public static final String URL_PULLING_HANDLE = "/oneArmedBandit/pullingHandle";
    public static final String URL_CHECKIN = "/oneArmedBandit/checkin";

    private MockMvc mockMvc;

    @Mock
    private OneArmedBanditService service;

    @InjectMocks
    private OneArmedBanditController cut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cut).build();
    }

    /**
     * Test to start a game as 'player a' with 10 credits. Expected the checkin process
     * accept with http status accepted(202).
     *
     * @throws Exception unexpected test exceptions by perform http request
     */
    @Test
    public void shouldCheckin() throws Exception {
        //given
        //when
        mockMvc.perform(
                post(URL_CHECKIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CHECKIN_AS_PLAYER_WITH_10_CREDITS))
                //then
                .andExpect(status().isOk());
    }

    /**
     * Test play the first round by pulling the handle.
     *
     * @throws Exception unexpected test exceptions by perform http request
     */
    @Test
    public void shouldPullingTheHandle() throws Exception {
        //given
        when(service.pullingHandle()).thenReturn(
                new GameResult(new Credit(7),
                        WHELLS_COLLECTION, false));
        //when
        mockMvc.perform(
                get(URL_PULLING_HANDLE))
                //then
                .andDo(result -> assertThat(result.getResponse().getContentAsString())
                        .contains("{\"credit\":{\"credits\":7},\"wheels\":[\"APPLE\",\"BANANA\",\"CLEMENTINE\"],\"won\":false}"))
                .andExpect(status().isOk());
    }

    /**
     * Test to leave the game. Excepted the remaining credits get back.
     */
    @Test
    public void shouldLeaveTheGame() throws Exception {
        //given
        when(service.checkout()).thenReturn(new Credit(42));
        //when
        mockMvc.perform(
                get(URL_CHECKOUT))
                //then
                .andDo(result -> assertThat(result.getResponse().getContentAsString())
                        .contains("{\"credits\":42}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldTranslateExceptionOnCheckin() throws Exception {
        //given
        doThrow(new OneArmedBanditException("")).when(service).checkin(any(Credit.class));
        //when
        mockMvc.perform(
                post(URL_CHECKIN))
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldTranslateExceptionOnPullingHandle() throws Exception {
        //given
        when(service.pullingHandle()).thenThrow(new CreditException("Not enough credits"));
        //when
        mockMvc.perform(
                get(URL_PULLING_HANDLE))
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldTranslateExceptionOnCheckout() throws Exception {
        //given
        when(service.checkout()).thenThrow(new CreditException(""));
        //when
        mockMvc.perform(
                get(URL_CHECKOUT))
                //then
                .andExpect(status().isBadRequest());
    }
}