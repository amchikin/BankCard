package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.example.BankCard.dto.ShowCardsResponseDto;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapperService.ShowCardsResponseService;
@Mapper(componentModel = "spring")
public abstract class ShowCardsResponseMapperInjectService {
    @Autowired  // TODO Почему подчёркивает и как сделать лучше
    protected ShowCardsResponseService showCardsResponseService;
    public abstract Person ToModel(ShowCardsResponseDto source);
    @Mapping(target = "fio", expression = "java(showCardsResponseService.enrichName(destination.getName(), destination.getSurname()))")
    public abstract ShowCardsResponseDto ToDto(Person destination);
}
