package com.naucratis.naucratis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRedirectToLogin() throws Exception {
        mockMvc.perform(get("/estadisticas")).andExpect(redirectedUrl("http://localhost/login"));
    }

    public HttpSession makeSesion () throws Exception {
        RequestBuilder requestBuilder = formLogin().user("admin@admin.com").password("AB@12abc");
        return mockMvc.perform(requestBuilder)
                .andExpect(redirectedUrl("/")).andReturn().getRequest().getSession();
    }

    @Test
    public void shouldGetStatisticsPage() throws Exception {

        HttpSession session = makeSesion();

        assert session != null;

        mockMvc.perform(get("/estadisticas").session((MockHttpSession) session))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldExecuteFirstStatistic() throws Exception {

        HttpSession session = makeSesion();

        assert session != null;

        session = mockMvc.perform(get("/estadisticas").session((MockHttpSession) session)
                .param("selectedYearsYearlySales", "2021", "2012"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();

    }

    @Test
    public void shouldExecuteSecondStatistic() throws Exception {

        HttpSession session = makeSesion();

        assert session != null;

        session = mockMvc.perform(get("/estadisticas").session((MockHttpSession) session)
                .param("selectedBookSellingPerMonth", "2021"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();

    }

    @Test
    public void shouldExecuteThirdStatistic() throws Exception {

        HttpSession session = makeSesion();

        assert session != null;

        session = mockMvc.perform(get("/estadisticas").session((MockHttpSession) session)
                .param("selectedBookSellingPerMonth", "2021")
                .param("selectedBookIsbn", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();

    }

    @Test
    public void shouldExecuteAllStatistics() throws Exception {

        HttpSession session = makeSesion();

        assert session != null;

        session = mockMvc.perform(get("/estadisticas").session((MockHttpSession) session)
                .param("selectedYearsYearlySales", "2021", "2012")
                .param("selectedBookSellingPerMonth", "2021")
                .param("selectedBookIsbn", "100")
                .param("selectedBestSellingCategories", "2021"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();

    }

}
