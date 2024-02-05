package ru.example.BankCard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapper.PersonMapper;
import ru.example.BankCard.mapper.PersonSaveMapper;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.service.PeopleServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class PeopleServiceTests {
    @Mock
    private PeopleRepository peopleRepository;
    @InjectMocks
    private PeopleServiceImpl peopleService;
    @Mock
    private PersonMapper personMapper;
    private Person person1;

    @BeforeEach
    public void setup() {

        person1 = Person.builder()
                .id(1)
                .surname("Surname1")
                .name("Name1")
                .birthday(LocalDate.parse("2000-01-01"))
                .build();
    }

    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getPersonList method")
    @Test
    public void givenPersonList_whenGetPersonList_thenReturnPersonList(){
        // given - precondition or setup
        Person person2 = Person.builder()
                .id(2)
                .surname("Surname2")
                .name("Name2")
                .birthday(LocalDate.parse("2000-02-02"))
                .build();

        given(peopleRepository.findAll()).willReturn(List.of(person1,person2));

        // when -  action or the behaviour that we are going test
        List<PersonDto> pL= peopleService.getPersonList();
        System.out.println(pL);
        List<Person> personList = peopleService.getPersonList().stream().map(personMapper::map).collect(Collectors.toList());
        System.out.println(personList);

        // then - verify the output
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }
    // JUnit test for saveEmployee method
//    @DisplayName("JUnit test for savePersonRqDto method")
//    @Test
//    public void givenPersonObject_whenSavePerson_thenReturnPersonObject() {
//
//        // given - precondition or setup
//        given(peopleRepository.findById(person.getId()))
//                .willReturn(Optional.empty());
//
//        given(peopleRepository.save(person)).willReturn(person);
//
//        System.out.println(peopleRepository);
//        System.out.println(peopleService);
//
//        // when -  action or the behaviour that we are going test
//        Person savedPerson = personSaveMapper.map(peopleService.savePersonRqDto(personSaveMapper.map(person)));
//
//        System.out.println(savedPerson);
//        // then - verify the output
//        assertThat(savedPerson).isNotNull();
//    }
}
