package com.example.demo.manyTomany;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserTBRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    UserTBRepository userTBRepository;

    @Test
    public void insertManyToMany(){

        Author lewis = new Author("Lewis");
        Author mark = new Author("Mark");
        Author peter = new Author("Peter");

        Book spring = new Book("Spring in Action");
        spring.getAuthors().addAll(Arrays.asList(lewis,mark));

        Book springBoot = new Book("Spring Boot in Action");
        springBoot.getAuthors().addAll(Arrays.asList(lewis,peter));
        bookRepository.saveAll(Arrays.asList(spring,springBoot));
    }

    @Test
    public void selectManyToMany(){
       Book book = bookRepository.findById(1L).get();
       // Book book = bookRepository.findOne(1L);
       //Book book = bookRepository.getById(1L);
       System.out.println(book.toString());

//       book.getAuthors().stream().forEach(item->{
//           System.out.println(item.toString());
//       });
    }

    @Test
    public void updateManyToMany(){

        Author author = authorRepository.findById(1L).get();

        Book book = bookRepository.findById(1L).get();
        System.out.println(book.toString());

        book.getAuthors().add(new Author("zzg"));

        bookRepository.save(book);
    }

    @Test
    public void deleteManyToMany(){
        JSONArray array = new JSONArray();
        //userTBRepository.deleteById(1L);
        String a = "";
    }
}
