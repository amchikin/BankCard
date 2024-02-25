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
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.mapper.PersonMapper;
import ru.example.BankCard.mapper.PersonSaveMapper;
import ru.example.BankCard.mapper.ShowCardsMapper;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.service.PeopleServiceImpl;
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
    PersonSaveMapper personSaveMapper = Mappers.getMapper(PersonSaveMapper.class);
    @Spy
    AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
    @Spy
    ShowCardsMapper showCardsMapper = Mappers.getMapper(ShowCardsMapper.class);

    @Test
    void testGetPersonList() {
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
    void testGetPersonByIdOrThrow_findCase() {
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
//TODO переделать - не то
    void testGetPersonByIdOrThrow_notFindCase() {
        //Arrange
        given(peopleRepository.findById(1)).willReturn(null);

        //Aсt
        Executable actual = () -> peopleService.getPersonByIdOrThrow(1);

        //Assert
        assertThrows(RuntimeException.class, actual);
    }

    @Test
    void testSavePersonRqDto() {
        //Arrange
        PersonSaveDto expectedPersonSaveDto = PersonSaveDto.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        Person person1 = Person.builder().surname("Surname1").name("Name1").birthday(LocalDate.parse("2000-01-01")).build();
        given(peopleRepository.save(personSaveMapper.map(expectedPersonSaveDto))).willReturn(person1);

        //Aсt
        PersonSaveDto actualPersonDto = peopleService.savePersonRqDto(expectedPersonSaveDto);

        //Assert
        assertThat(actualPersonDto).isEqualTo(expectedPersonSaveDto);
    }

    @Test
    void testGetCardsByPersonId() {
        //Arrange
        AccountDto accountDto1 = AccountDto.builder().cardNumber("1111888888888888").balance(new BigInteger("10")).build();
        AccountDto accountDto2 = AccountDto.builder().cardNumber("2222888888888888").balance(new BigInteger("5")).build();
        AccountDto accountDto3 = AccountDto.builder().cardNumber("3333888888888888").balance(new BigInteger("1")).build();
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(accountDto1);
        accountDtoList.add(accountDto2);
        accountDtoList.add(accountDto3);

        Person person = Person.builder().
                id(1).surname("TestSurname").name("TestName").birthday(LocalDate.parse("2000-01-01")).
                accounts(accountDtoList.stream().map(accountMapper::map).collect(Collectors.toList())).build();

        //ShowCardsDto showCardsDto1 = ShowCardsDto.builder().fio("TestSurname TestName").accounts(accountDtoList).build();

        given(peopleRepository.findById(1)).willReturn(Optional.of(person));

        //Aсt
        ShowCardsDto showCardsDto = peopleService.getCardsByPersonId(person.getId());//TODO не могу получить доступ к ShowCardsMapper в сервисном слое(там он null). Почему?

        //Assert
        assertThat(showCardsDto.getAccounts().size()).isEqualTo(3);
        assertThat(showCardsDto.getAccounts().get(0).getCardNumber()).isEqualTo(new BigInteger("1"));
        assertThat(showCardsDto.getAccounts().get(1).getCardNumber()).isEqualTo(new BigInteger("5"));
        assertThat(showCardsDto.getAccounts().get(2).getCardNumber()).isEqualTo(new BigInteger("10"));
    }
}
