package ru.example.BankCard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapper.PersonMapper;
import ru.example.BankCard.mapper.PersonSaveMapper;
import ru.example.BankCard.mapper.ShowCardsMapper;
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
    @Spy
    @InjectMocks
    PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Spy
    @InjectMocks
    PersonSaveMapper personSaveMapper = Mappers.getMapper(PersonSaveMapper.class);
    private Person person1;
    private PersonSaveDto personSaveDto;

    @BeforeEach
    public void setup() {

        person1 = Person.builder()
                .id(1)
                .surname("Surname1")
                .name("Name1")
                .birthday(LocalDate.parse("2000-01-01"))
                .build();
        personSaveDto = PersonSaveDto.builder()
                .id(1)
                .surname("Surname1")
                .name("Name1")
                .birthday(LocalDate.parse("2000-01-01"))
                .build();

    }

    @DisplayName("JUnit test for getPersonList method")
    @Test    //TODO Подумать над названием, да и вообще о том, что функция делает и проверяет. Сейчас отрабатывает.
    public void givenPersonList_whenGetPersonList_thenReturnPersonList() {

        // given - precondition or setup
        Person person2 = Person.builder()
                .id(2)
                .surname("Surname2")
                .name("Name2")
                .birthday(LocalDate.parse("2000-02-02"))
                .build();
        given(peopleRepository.findAll()).willReturn(List.of(person1, person2));

        // when -  action or the behaviour that we are going test
        List<Person> personList = peopleService.getPersonList().stream().map(personMapper::map).collect(Collectors.toList());

        // then - verify the output
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
        assertThat(personList.get(0).getName()).isEqualTo("Name1"); // TODO это тут не нужно, удалить потом, после создания теста на Маппер
        assertThat(personList.get(1).getName()).isEqualTo("Name2");
    }
//    @DisplayName("JUnit test for savePersonRqDto method")
//    @MockitoSettings(strictness = Strictness.WARN)
//    @Test
//    public void add_success() {
////        given
//        Mockito.doReturn(personSaveDto)
//                .when(peopleRepository)
//                .save(person1);
//
////        when
//        var actual = peopleService.savePersonRqDto(personSaveDto);
//        //var actual = personSaveMapper.map(peopleService.savePersonRqDto(personSaveMapper.map(person1)));
//
////        then
//        assertEquals(personSaveDto, actual);
//
//        Mockito.verify(peopleRepository)
//                .save(person1);
//
//    }


//    @DisplayName("JUnit test for savePersonRqDto method")
//    @Test
//    public void givenPersonObject_whenSavePerson_thenReturnPersonObject() {
//
//        // given - precondition or setup
////        given(peopleRepository.findById(person1.getId()))
////                .willReturn(Optional.empty());
//
//        given(peopleRepository.save(person1)).willReturn(person1);
////
////        System.out.println(peopleRepository);
////        System.out.println(peopleService);
//
//
//        // when -  action or the behaviour that we are going test
//        //Person savedPerson = personSaveMapper.map(peopleService.savePersonRqDto(personSaveMapper.map(person1)));
//        PersonSaveDto savedPerson = peopleService.savePersonRqDto(personSaveMapper.map(person1));
//        System.out.println(savedPerson);
//
//        // then - verify the output
//        assertThat(savedPerson).isNotNull();
//    }
}
