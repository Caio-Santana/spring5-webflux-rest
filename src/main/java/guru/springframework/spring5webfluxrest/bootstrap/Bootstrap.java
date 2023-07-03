package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadVendors();
    }

    private void loadVendors() {
        if (vendorRepository.count().block() > 0) {
            return;
        }

        Vendor vendor1 = Vendor.builder().firstName("Joe").lastName("Buck").build();
        Vendor vendor2 = Vendor.builder().firstName("Michael").lastName("Weston").build();
        Vendor vendor3 = Vendor.builder().firstName("Jessie").lastName("Waters").build();
        Vendor vendor4 = Vendor.builder().firstName("Bill").lastName("Murray").build();
        Vendor vendor5 = Vendor.builder().firstName("Mary").lastName("Smith").build();

        vendorRepository.save(vendor1).block();
        vendorRepository.save(vendor2).block();
        vendorRepository.save(vendor3).block();
        vendorRepository.save(vendor4).block();
        vendorRepository.save(vendor5).block();

        System.out.println("Loaded Vendors: " + vendorRepository.count().block());
    }

    private void loadCategories() {
        if (categoryRepository.count().block() > 0) {
            return;
        }

        Category category1 = Category.builder().description("Fruits").build();
        Category category2 = Category.builder().description("Nuts").build();
        Category category3 = Category.builder().description("Breads").build();
        Category category4 = Category.builder().description("Meats").build();
        Category category5 = Category.builder().description("Eggs").build();

        categoryRepository.save(category1).block();
        categoryRepository.save(category2).block();
        categoryRepository.save(category3).block();
        categoryRepository.save(category4).block();
        categoryRepository.save(category5).block();

        System.out.println("Loaded Categories: " + categoryRepository.count().block());
    }
}
