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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactsController.class)
class ContactsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactsService service;

    @Test
    void show() throws Exception {
        List<Contact> sample = List.of(
                new Contact(1, "Loren", "Jackson", "a@b.com", "+23334446556"),
                new Contact(2, "Laren", "Jbckson", "b@b.com", "+23334446557"),
                new Contact(3, "Leren", "Jcckson", "c@b.com", "+23334446558"),
                new Contact(4, "Liren", "Jdckson", "d@b.com", "+23334446559"),
                new Contact(5, "Luren", "Jeckson", "e@b.com", "+23334446555")
        );
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
}
