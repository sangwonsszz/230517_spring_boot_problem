package io.playdata.spring_boot_test.repository;

import io.playdata.spring_boot_test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}