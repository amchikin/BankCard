package ru.example.BankCard;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.function.Executable;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.BankCard.dto.*;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapper.*;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.service.impl.PeopleServiceImpl;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
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
    PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
    @Spy
    PersonSaveRequestMapper personSaveRequestMapper = Mappers.getMapper(PersonSaveRequestMapper.class);
    @Spy
    ShowAllAccountMapper showAllAccountMapper = Mappers.getMapper(ShowAllAccountMapper.class);
    @Spy
    PersonSaveResponseMapper personSaveResponseMapper = Mappers.getMapper(PersonSaveResponseMapper.class);
    @Spy
    ShowCardsMapper showCardsMapper = Mappers.getMapper(ShowCardsMapper.class);
    @Test
    void getPersonDtoList_test() {
        //Arrange
        PersonDto personDto1 = PersonDto.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        PersonDto personDto2 = PersonDto.builder().surname("Surname2").name("Name2").birthday(LocalDate.parse("2000-02-02")).build();
        List<PersonDto> expectedPersonDtoList = new ArrayList<>();
        expectedPersonDtoList.add(personDto1);
        expectedPersonDtoList.add(personDto2);

        Person person1 = Person.builder().id(1).surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        Person person2 = Person.builder().id(2).surname("Surname2").name("Name2").birthday(LocalDate.parse("2000-02-02")).build();
        given(peopleRepository.findAll()).willReturn(List.of(person1, person2));

        //Aсt
        List<PersonDto> actualPersonDtoList = peopleService.getPersonDtoList();

        //Assert
        assertThat(actualPersonDtoList).hasSameElementsAs(expectedPersonDtoList);
    }

    @Test
    void getPersonByIdOrThrow_findCase_test() {
        //Arrange
        PersonDto expectedPersonDto = PersonDto.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();

        Person person1 = Person.builder().id(1).surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        given(peopleRepository.findById(1)).willReturn(Optional.of(person1));

        //Aсt
        PersonDto actualPersonDto = peopleService.getPersonByIdOrThrow(1);

        //Assert
        assertThat(actualPersonDto).isEqualTo(expectedPersonDto);
    }

    @Test
    void getPersonByIdOrThrow_notFindCase_test() {      //TODO консультация с Богданом про данный тест
        //Arrange
        given(peopleRepository.findById(1)).willReturn(null);

        //Aсt
        Executable actual = () -> peopleService.getPersonByIdOrThrow(1);

        //Assert
        assertThrows(RuntimeException.class, actual);
    }

    @Test
    void savePersonRqDto_test() {
        //Arrange
        PersonSaveResponseDto expectedPersonSaveResponseDto = PersonSaveResponseDto.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        PersonSaveRequestDto personSaveRequestDto = PersonSaveRequestDto.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        Person person1 = Person.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        given(peopleRepository.save(personSaveRequestMapper.map(personSaveRequestDto))).willReturn(person1);

        //Aсt
        PersonSaveResponseDto actualPersonSaveResponseDto = peopleService.savePersonRequestDto(personSaveRequestDto);

        //Assert
        assertThat(actualPersonSaveResponseDto).isEqualTo(expectedPersonSaveResponseDto);
    }
    @Test
    void getCardsByPersonId_test() {
        //Arrange
        ShowAllAccountDto showAllAccountDto1 = ShowAllAccountDto.builder().cardNumber("1111888888888888").balance(new BigInteger("10")).build();
        ShowAllAccountDto showAllAccountDto2 = ShowAllAccountDto.builder().cardNumber("2222888888888888").balance(new BigInteger("5")).build();
        ShowAllAccountDto showAllAccountDto3 = ShowAllAccountDto.builder().cardNumber("3333888888888888").balance(new BigInteger("1")).build();
        List<ShowAllAccountDto> showAllAccountDtoList = new ArrayList<>();
        showAllAccountDtoList.add(showAllAccountDto1);
        showAllAccountDtoList.add(showAllAccountDto2);
        showAllAccountDtoList.add(showAllAccountDto3);

        Person person = Person.builder().
                id(1).surname("TestSurname").name("TestName").birthday(LocalDate.parse("2000-01-01")).
                accounts(showAllAccountDtoList.stream().map(showAllAccountMapper::map).collect(Collectors.toList())).build();

        given(peopleRepository.findById(1)).willReturn(Optional.of(person));

        //Aсt
        ShowCardsDto showCardsDto = peopleService.getCardsByPersonId(person.getId());

        //Assert
        assertThat(showCardsDto.getAccounts().size()).isEqualTo(3);
        assertThat(showCardsDto.getAccounts().get(0).getBalance()).isEqualTo(new BigInteger("1"));
        assertThat(showCardsDto.getAccounts().get(1).getBalance()).isEqualTo(new BigInteger("5"));
        assertThat(showCardsDto.getAccounts().get(2).getBalance()).isEqualTo(new BigInteger("10"));
    }
}
