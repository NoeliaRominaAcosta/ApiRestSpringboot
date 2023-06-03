package com.itrsa.monolith;

import com.itrsa.monolith.dto.UserDTO;
import com.itrsa.monolith.exception.RandomException;
import com.itrsa.monolith.service.UserServiceBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ObjectUtils;

import static org.springframework.test.util.AssertionErrors.fail;


@AutoConfigureMockMvc
@IntegrationTest
class UserControllerIT {

    @Autowired
    private MockMvc restAccountMockMvc;

    @MockBean
    private UserServiceBusiness userServiceBusiness;

    @Test
    void testGetUser() throws Exception {
        var body = new UserDTO();
        body.setUser("user");

        Mockito.when(userServiceBusiness.getUser()).thenReturn(body);
        restAccountMockMvc
                .perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    if (!result.getResponse().getContentAsString().contains("user")) {
                        fail("Response content", ObjectUtils.nullSafeToString("user"), ObjectUtils.nullSafeToString(result.getResponse().getContentAsString()));
                    }
                });
                //.andExpect(MockMvcResultMatchers.content().string("{\"login\":\"eugenp\",\"id\":1022859,\"url\":\"https://api.github.com/users/eugenp\",\"company\":\"Baeldung\",\"blog\":\"https://www.baeldung.com\",\"email\":null}"));
    }

    @Test
    void testGetUserRandomException() throws Exception {

        Mockito.when(userServiceBusiness.getUserRandomException()).thenThrow(RandomException.class);
        restAccountMockMvc
                .perform(MockMvcRequestBuilders.get("/api/user/random/exception").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> {
                    if (!result.getResponse().getContentAsString().contains("Not Found")) {
                        fail("Response content", ObjectUtils.nullSafeToString("Not Found"), ObjectUtils.nullSafeToString(result.getResponse().getContentAsString()));
                    }
                });

    }

}
