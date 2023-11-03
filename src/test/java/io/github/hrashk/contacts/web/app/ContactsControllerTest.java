package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactsController.class)
class ContactsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactsService service;

    private final ContactsGenerator generator = new ContactsGenerator();

    @Test
    void show() throws Exception {
        List<Contact> sample = generator.generate(5);
        Mockito.when(service.findAllContacts()).thenReturn(sample);


        MvcResult result = mvc.perform(get("/"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_HTML),
                        content().string(stringContainsInOrder("first name", ">1<"))
                ).andReturn();

        String body = result.getResponse().getContentAsString();

        int numberOfTableRows = StringUtils.countOccurrencesOf(body, "<tr");
        int numberOfHeaderRows = 1;
        int expectedNumberOfRows = sample.size() + numberOfHeaderRows;
        assertThat(numberOfTableRows).isEqualTo(expectedNumberOfRows);
    }

    @Test
    void delete() throws Exception {
        mvc.perform(get("/delete/13"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/")
                );
        Mockito.verify(service).deleteContact(Mockito.eq(13));
    }

    @Test
    void showCreateForm() throws Exception {
        mvc.perform(get("/create"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_HTML),
                        content().string(stringContainsInOrder("<label"))
                );
    }

    @Test
    void create() throws Exception {
        mvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("id=0&fistName=Aleph&lastName=Nullseon&email=a@b.com&phone=+79031234"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/")
                );
        Mockito.verify(service).createContact(Mockito.any(Contact.class));
    }
}
