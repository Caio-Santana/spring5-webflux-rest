package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void getAllVendors() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(
                        Vendor.builder().firstName("Joe").lastName("Smith").build(),
                        Vendor.builder().firstName("Billy").lastName("Idol").build(),
                        Vendor.builder().firstName("Mary").lastName("Smith").build()
                ));

        webTestClient.get()
                .uri(VendorController.BASE_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(3);
    }

    @Test
    public void getVendorById() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(
                        Vendor.builder().firstName("Mary").lastName("Smith").build()
                ));

        webTestClient.get()
                .uri(VendorController.BASE_URL + "/" + "id")
                .exchange()
                .expectBody(Vendor.class);
    }
}