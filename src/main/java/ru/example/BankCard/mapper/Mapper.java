package ru.example.BankCard.mapper;
import ru.example.BankCard.dto.AbstractDto;
import ru.example.BankCard.entity.AbstractEntity;

public interface Mapper <E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);

    D toDto(E entity);
}
