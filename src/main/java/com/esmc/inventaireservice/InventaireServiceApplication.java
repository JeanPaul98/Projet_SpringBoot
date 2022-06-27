package com.esmc.inventaireservice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @AllArgsConstructor @NoArgsConstructor @ToString
class Produit{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Produit, Long>{

}

@SpringBootApplication
public class InventaireServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaireServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(new Produit(null, "Computer HP 650",6500));
			productRepository.save(new Produit(null, "Computer DELL 400",5000));
			productRepository.save(new Produit(null, "Computer HP 745",700));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
